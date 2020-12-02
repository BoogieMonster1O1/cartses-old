package io.github.boogiemonster1o1.cartses.entity.cart

import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry
import net.minecraft.block.enums.Instrument
import net.minecraft.block.{BlockState, Blocks, NoteBlock}
import net.minecraft.entity.EntityType
import net.minecraft.entity.data.{DataTracker, TrackedDataHandlerRegistry}
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.PacketByteBuf
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.util.{ActionResult, Hand}
import net.minecraft.util.math.{BlockPos, MathHelper}
import net.minecraft.world.World

object MinecartWithNoteBlockEntity {
	private val note = DataTracker.registerData(classOf[MinecartWithRedstoneLampEntity], TrackedDataHandlerRegistry.INTEGER)
	private val repeat = DataTracker.registerData(classOf[MinecartWithRedstoneLampEntity], TrackedDataHandlerRegistry.BOOLEAN)
}

class MinecartWithNoteBlockEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {
	private var cachedPos: BlockPos = _

	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.noteBlock

	override def initDataTracker(): Unit = {
		super.initDataTracker()
		dataTracker.startTracking(MinecartWithNoteBlockEntity.note, 0)
		dataTracker.startTracking(MinecartWithNoteBlockEntity.repeat, false)
	}

	def getNote: Int = {
		dataTracker.get(MinecartWithNoteBlockEntity.note)
	}

	def setNote(value: Int): Unit = {
		dataTracker.set(MinecartWithNoteBlockEntity.note, MathHelper.clamp(value, 0, 0))
	}

	def isRepeat: Boolean = {
		dataTracker.get(MinecartWithNoteBlockEntity.repeat)
	}

	def setRepeat(value: Boolean): Unit = {
		dataTracker.set(MinecartWithNoteBlockEntity.repeat, value)
	}

	def playSound(blockState: BlockState): Unit = {
		val note: Int = getNote
		val pitch: Float = Math.pow(2.0D, (note - 12).toDouble / 12.0D).toFloat
		world.playSound(null, getBlockPos, Instrument.fromBlockState(blockState).getSound, SoundCategory.RECORDS, 3.0F, pitch)
		world.addParticle(ParticleTypes.NOTE, this.getX, this.getY + 1.7, this.getZ, note / 24.0F, 0, 0)
	}

	override def interact(player: PlayerEntity, hand: Hand): ActionResult = {
		if (!player.isSneaking) return super.interact(player, hand)
		val buf: PacketByteBuf = new PacketByteBuf(Unpooled.buffer)
		buf.writeVarInt(getEntityId)
		ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, EntityUtils.openNbScreenS2CId, buf)
		ActionResult.SUCCESS
	}

	override def getContainedBlock: BlockState = Blocks.NOTE_BLOCK.getDefaultState.`with`(NoteBlock.NOTE, getNote).`with`(NoteBlock.INSTRUMENT, getInstrument)

	override def onActivatorRail(x: Int, y: Int, z: Int, powered: Boolean): Unit = {
		val pos: BlockPos = new BlockPos(x, y - 1, z)
		if (isRepeat) playSound(world.getBlockState(pos))
		else if (!pos.equals(cachedPos)) playSound(world.getBlockState(pos))
		super.onActivatorRail(x, y, z, powered)
	}
}
