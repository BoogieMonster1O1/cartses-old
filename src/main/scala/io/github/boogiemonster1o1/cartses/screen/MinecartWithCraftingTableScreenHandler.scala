package io.github.boogiemonster1o1.cartses.screen

import net.minecraft.entity.player.{PlayerEntity, PlayerInventory}
import net.minecraft.screen.{CraftingScreenHandler, ScreenHandlerContext}

class MinecartWithCraftingTableScreenHandler(val syncId: Int, val playerInventory: PlayerInventory, val context: ScreenHandlerContext) extends CraftingScreenHandler(syncId, playerInventory, context) {

	override def canUse(player: PlayerEntity) = true
}
