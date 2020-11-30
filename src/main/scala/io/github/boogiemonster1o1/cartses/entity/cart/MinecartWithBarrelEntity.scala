package io.github.boogiemonster1o1.cartses.entity.cart

import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import net.minecraft.block.{BarrelBlock, BlockState, Blocks}
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.entity.vehicle.{AbstractMinecartEntity, StorageMinecartEntity}
import net.minecraft.network.Packet
import net.minecraft.screen.{GenericContainerScreenHandler, ScreenHandler}
import net.minecraft.util.math.{Direction, Vec3d}
import net.minecraft.world.{GameRules, World}

@SuppressWarnings(Array("EntityConstructor")) class MinecartWithBarrelEntity(entityType: EntityType[_], world: World) extends StorageMinecartEntity(entityType, world) {
  	def this(`type`: EntityType[_], x: Double, y: Double, z: Double, world: World) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override protected def getScreenHandler(syncId: Int, playerInventory: PlayerInventory): ScreenHandler = GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this)

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.barrel

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def size = 27

	override def getDefaultBlockOffset = 8

	override def getDefaultContainedBlock: BlockState = Blocks.BARREL.getDefaultState.`with`(BarrelBlock.FACING, Direction.UP)

	override def dropItems(damageSource: DamageSource): Unit = {
		super.dropItems(damageSource)
		if (this.world.getGameRules.getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(Blocks.BARREL)
	}
}
