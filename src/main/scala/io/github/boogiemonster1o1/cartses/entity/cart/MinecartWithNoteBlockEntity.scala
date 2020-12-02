/*
 * Copyright (c) 2020 BoogieMonster1O1
 *
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 * WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
 * AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

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
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.{Packet, PacketByteBuf}
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
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

	override def readCustomDataFromTag(tag: CompoundTag): Unit = {
		setNote(tag.getInt("Note"))
		setRepeat(tag.getBoolean("Repeat"))
		super.readCustomDataFromTag(tag)
	}

	override def writeCustomDataToTag(tag: CompoundTag): Unit = {
		tag.putInt("Note", getNote)
		tag.putBoolean("Repeat", isRepeat)
		super.writeCustomDataToTag(tag)
	}

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.noteBlock

	override def initDataTracker(): Unit = {
		super.initDataTracker()
		dataTracker.startTracking[Integer](MinecartWithNoteBlockEntity.note, 0)
		dataTracker.startTracking[java.lang.Boolean](MinecartWithNoteBlockEntity.repeat, false)
	}

	def getNote: Int = {
		dataTracker.get[Integer](MinecartWithNoteBlockEntity.note)
	}

	def setNote(value: Int): Unit = {
		dataTracker.set[Integer](MinecartWithNoteBlockEntity.note, MathHelper.clamp(value, 0, 0))
	}

	def isRepeat: Boolean = {
		dataTracker.get[java.lang.Boolean](MinecartWithNoteBlockEntity.repeat)
	}

	def setRepeat(value: Boolean): Unit = {
		dataTracker.set[java.lang.Boolean](MinecartWithNoteBlockEntity.repeat, value)
	}

	def playSound(blockState: BlockState): Unit = {
		val note: Int = getNote
		val pitch: Float = Math.pow(2.0D, (note - 12).toDouble / 12.0D).toFloat
		world.playSound(null, getBlockPos, Instrument.fromBlockState(blockState).getSound, SoundCategory.RECORDS, 3.0F, pitch)
		this.world.asInstanceOf[ServerWorld].spawnParticles(ParticleTypes.NOTE, this.getX, this.getY + 1.7, this.getZ, 0, note / 24.0F, 0, 0, 1)
	}

	override def interact(player: PlayerEntity, hand: Hand): ActionResult = {
		if (!player.isSneaking) return super.interact(player, hand)
		if (!world.isClient) {
			val buf: PacketByteBuf = new PacketByteBuf(Unpooled.buffer)
			buf.writeVarInt(getEntityId)
			ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, EntityUtils.openNbScreenS2CId, buf)
		}
		ActionResult.SUCCESS
	}

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def getContainedBlock: BlockState = Blocks.NOTE_BLOCK.getDefaultState.`with`[Integer, Integer](NoteBlock.NOTE, getNote)

	override def onActivatorRail(x: Int, y: Int, z: Int, powered: Boolean): Unit = {
		val pos: BlockPos = new BlockPos(x, y - 1, z)
		if (!world.isClient) {
			if (isRepeat) playSound(world.getBlockState(pos))
			else if (!pos.equals(cachedPos)) {
				playSound(world.getBlockState(pos))
				cachedPos = pos
			}
		}
		super.onActivatorRail(x, y, z, powered)
	}
}
