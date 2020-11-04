package io.github.boogiemonster1o1.cartses.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;

public class MinecartWithCraftingTableScreenHandler extends CraftingScreenHandler {
	public MinecartWithCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(syncId, playerInventory, context);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		BlockPos blockPos = player.getBlockPos();
		return player.squaredDistanceTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D) <= 64.0D;
	}
}
