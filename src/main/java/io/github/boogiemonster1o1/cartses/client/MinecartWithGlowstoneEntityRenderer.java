package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithGlowstoneEntity;

import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.util.math.BlockPos;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class MinecartWithGlowstoneEntityRenderer extends MinecartEntityRenderer<MinecartWithGlowstoneEntity> {
	public MinecartWithGlowstoneEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected int method_27950(MinecartWithGlowstoneEntity entity, BlockPos blockPos) {
		return Blocks.GLOWSTONE.getDefaultState().getLuminance();
	}

	@Override
	protected int getBlockLight(MinecartWithGlowstoneEntity entity, BlockPos blockPos) {
		return Blocks.GLOWSTONE.getDefaultState().getLuminance();
	}
}
