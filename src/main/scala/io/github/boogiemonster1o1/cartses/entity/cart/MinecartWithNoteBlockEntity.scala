package io.github.boogiemonster1o1.cartses.entity.cart

import com.chocohead.mm.api.ClassTinkerers
import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import net.minecraft.block.enums.Instrument
import net.minecraft.block.{BlockState, Blocks, NoteBlock}
import net.minecraft.entity.EntityType
import net.minecraft.entity.data.{DataTracker, TrackedDataHandlerRegistry}
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World

object MinecartWithNoteBlockEntity {
	private val note = DataTracker.registerData(classOf[MinecartWithRedstoneLampEntity], TrackedDataHandlerRegistry.INTEGER)
	private val instrument = DataTracker.registerData(classOf[MinecartWithRedstoneLampEntity], TrackedDataHandlerRegistry.STRING)
}

class MinecartWithNoteBlockEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {
	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.noteBlock

	override def initDataTracker(): Unit = {
		super.initDataTracker()
		dataTracker.startTracking(MinecartWithNoteBlockEntity.note, 0)
		dataTracker.startTracking(MinecartWithNoteBlockEntity.instrument, Instrument.HARP.name)
	}

	def getNote: Int = {
		dataTracker.get(MinecartWithNoteBlockEntity.note)
	}

	def setNote(value: Int): Unit = {
		dataTracker.set(MinecartWithNoteBlockEntity.note, MathHelper.clamp(value, 0, 0))
	}

	def getInstrument: Instrument = {
		ClassTinkerers.getEnum(classOf[Instrument], dataTracker.get(MinecartWithNoteBlockEntity.instrument))
	}

	def setInstrument(value: Instrument): Unit = {
		dataTracker.set(MinecartWithNoteBlockEntity.instrument, value.name)
	}

	def playSound(): Unit = {
		val note: Int = getNote
		val pitch: Float = Math.pow(2.0D, (note - 12).toDouble / 12.0D).toFloat
		world.playSound(null, getBlockPos, getInstrument.getSound, SoundCategory.RECORDS, 3.0F, pitch)
		world.addParticle(ParticleTypes.NOTE, this.getX, this.getY + 1.7, this.getZ, note / 24.0F, 0, 0)
	}

	override def getContainedBlock: BlockState = Blocks.NOTE_BLOCK.getDefaultState.`with`(NoteBlock.NOTE, getNote).`with`(NoteBlock.INSTRUMENT, getInstrument)
}
