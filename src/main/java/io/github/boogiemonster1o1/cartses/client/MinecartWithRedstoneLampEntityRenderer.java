package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithRedstoneLampEntity;

import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.util.math.BlockPos;

public class MinecartWithRedstoneLampEntityRenderer extends MinecartEntityRenderer<MinecartWithRedstoneLampEntity> {
	public MinecartWithRedstoneLampEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected int method_27950(MinecartWithRedstoneLampEntity entity, BlockPos blockPos) {
		return entity.isLit() ? Blocks.REDSTONE_LAMP.getDefaultState().with(RedstoneLampBlock.LIT, true).getLuminance() : super.method_27950(entity, blockPos);
	}

	@Override
	protected int getBlockLight(MinecartWithRedstoneLampEntity entity, BlockPos blockPos) {
		return entity.isLit() ? Blocks.REDSTONE_LAMP.getDefaultState().with(RedstoneLampBlock.LIT, true).getLuminance() : super.getBlockLight(entity, blockPos);
	}
}
