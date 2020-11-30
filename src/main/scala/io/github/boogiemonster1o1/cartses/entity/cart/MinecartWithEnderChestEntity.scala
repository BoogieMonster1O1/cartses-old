package io.github.boogiemonster1o1.cartses.entity.cart

import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import io.github.boogiemonster1o1.cartses.util.EnderChestInventoryExtended
import net.minecraft.block.{BlockState, Blocks}
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.{PlayerEntity, PlayerInventory}
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.Packet
import net.minecraft.screen.{GenericContainerScreenHandler, SimpleNamedScreenHandlerFactory}
import net.minecraft.stat.Stats
import net.minecraft.text.TranslatableText
import net.minecraft.util.{ActionResult, Hand}
import net.minecraft.world.{GameRules, World}

@SuppressWarnings(Array(Array("EntityConstructor", "CodeBlock2Expr"))) class MinecartWithEnderChestEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {
	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.enderChest

	override def getContainedBlock: BlockState = Blocks.ENDER_CHEST.getDefaultState

	override def dropItems(damageSource: DamageSource): Unit = {
		super.dropItems(damageSource)
		if (this.world.getGameRules.getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(Blocks.ENDER_CHEST)
	}

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def interact(player: PlayerEntity, hand: Hand): ActionResult = {
		val enderChestInventory = player.getEnderChestInventory
		if (enderChestInventory != null) if (this.world.isClient) ActionResult.SUCCESS
		else {
			enderChestInventory.asInstanceOf[EnderChestInventoryExtended].forceCanUse()
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId: Int, playerInv: PlayerInventory, _: PlayerEntity) => {
				new GenericContainerScreenHandler.createGeneric9x3(syncId, playerInv, enderChestInventory)
			}, new TranslatableText("screenhandler.cartses.ender_chest")))
			player.incrementStat(Stats.OPEN_ENDERCHEST)
			ActionResult.CONSUME
		}
		else ActionResult.success(this.world.isClient)
	}
}
