package io.github.boogiemonster1o1.cartses.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.CraftingScreenHandler
import net.minecraft.screen.ScreenHandlerContext

class MinecartWithCraftingTableScreenHandler(val syncId: Int, val playerInventory: PlayerInventory, val context: ScreenHandlerContext) extends CraftingScreenHandler(syncId, playerInventory, context) {
	override def canUse(player: PlayerEntity) = true
}
