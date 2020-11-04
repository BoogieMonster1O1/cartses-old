package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithEnderChestEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithGlowstoneEntity;

import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.util.math.BlockPos;

public class MinecartWithEnderChestEntityRenderer extends MinecartEntityRenderer<MinecartWithEnderChestEntity> {
	public MinecartWithEnderChestEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected int method_27950(MinecartWithEnderChestEntity entity, BlockPos blockPos) {
		return Math.max(Blocks.ENDER_CHEST.getDefaultState().getLuminance(), super.method_27950(entity, blockPos));
	}

	@Override
	protected int getBlockLight(MinecartWithEnderChestEntity entity, BlockPos blockPos) {
		return Math.max(Blocks.ENDER_CHEST.getDefaultState().getLuminance(), super.getBlockLight(entity, blockPos));
	}
}
