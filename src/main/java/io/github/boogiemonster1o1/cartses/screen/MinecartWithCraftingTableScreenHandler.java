package io.github.boogiemonster1o1.cartses.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class MinecartWithCraftingTableScreenHandler extends CraftingScreenHandler {
	public MinecartWithCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(syncId, playerInventory, context);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
