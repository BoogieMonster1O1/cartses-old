package io.github.boogiemonster1o1.cartses.entity.cart

import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import me.lambdaurora.lambdynlights.DynamicLightSource
import net.fabricmc.api.{EnvType, EnvironmentInterface, EnvironmentInterfaces}
import net.minecraft.block._
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.{DataTracker, TrackedDataHandlerRegistry}
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.Packet
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.{BlockPos, MathHelper}
import net.minecraft.world.{GameRules, World}

object MinecartWithRedstoneLampEntity {

	private val LIT = DataTracker.registerData(classOf[MinecartWithRedstoneLampEntity], TrackedDataHandlerRegistry.BOOLEAN)
}

@SuppressWarnings(Array("EntityConstructor"))
@EnvironmentInterfaces(Array(new EnvironmentInterface(value = EnvType.CLIENT, itf = classOf[DynamicLightSource]))) class MinecartWithRedstoneLampEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {

	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def tick(): Unit = {
		super.tick()
		if (!this.world.isClient) {
			val x = MathHelper.floor(this.getX)
			var y = MathHelper.floor(this.getY)
			val z = MathHelper.floor(this.getZ)
			if (this.world.getBlockState(new BlockPos(x, y - 1, z)).isIn(BlockTags.RAILS)) y -= 1
			val blockPos = new BlockPos(x, y, z)
			val blockState = this.world.getBlockState(blockPos)
			if (AbstractRailBlock.isRail(blockState)) if (blockState.isOf(Blocks.ACTIVATOR_RAIL) && blockState.get(PoweredRailBlock.POWERED)) this.setLit(true)
			else this.setLit(false)
		}
	}

	override protected def initDataTracker(): Unit = {
		super.initDataTracker()
		this.dataTracker.startTracking(MinecartWithRedstoneLampEntity.LIT, false)
	}

	def setLit(value: Boolean): Unit = {
		this.dataTracker.set(MinecartWithRedstoneLampEntity.LIT, value)
	}

	def isLit: Boolean = this.dataTracker.get(MinecartWithRedstoneLampEntity.LIT)

	override def getContainedBlock: BlockState = {
		val state = Blocks.REDSTONE_LAMP.getDefaultState
		if (this.isLit) return state.`with`[java.lang.Boolean, Boolean](RedstoneLampBlock.LIT, true)
		state
	}

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.redstoneLamp

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def dropItems(damageSource: DamageSource): Unit = {
		super.dropItems(damageSource)
		if (this.world.getGameRules.getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(Blocks.REDSTONE_LAMP)
	}
}
