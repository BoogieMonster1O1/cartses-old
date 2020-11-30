package io.github.boogiemonster1o1.cartses.entity.cart

import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import io.github.boogiemonster1o1.cartses.screen.MinecartWithCraftingTableScreenHandler
import net.minecraft.block.{BlockState, Blocks}
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.{PlayerEntity, PlayerInventory}
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.Packet
import net.minecraft.screen.{ScreenHandlerContext, SimpleNamedScreenHandlerFactory}
import net.minecraft.stat.Stats
import net.minecraft.text.TranslatableText
import net.minecraft.util.{ActionResult, Hand}
import net.minecraft.world.{GameRules, World}

@SuppressWarnings(Array(Array("EntityConstructor", "CodeBlock2Expr"))) class MinecartWithCraftingTableEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {
	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def dropItems(damageSource: DamageSource): Unit = {
		super.dropItems(damageSource)
		if (this.world.getGameRules.getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(Blocks.CRAFTING_TABLE)
	}

	override def getContainedBlock: BlockState = Blocks.CRAFTING_TABLE.getDefaultState

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.craftingTable

	override def interact(player: PlayerEntity, hand: Hand): ActionResult = {
		if (this.world.isClient) return ActionResult.SUCCESS
		player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE)
		player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId: Int, inv: PlayerInventory, _: PlayerEntity) => {
			new MinecartWithCraftingTableScreenHandler(syncId, inv, ScreenHandlerContext.create(this.world, this.getBlockPos))
		}, new TranslatableText("screenhandler.cartses.crafting")))
		ActionResult.CONSUME
	}
}
