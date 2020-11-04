package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithRedstoneLampEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class MinecartWithRedstoneLampEntityRenderer extends MinecartEntityRenderer<MinecartWithRedstoneLampEntity> {
	public MinecartWithRedstoneLampEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(MinecartWithRedstoneLampEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		BlockState state = Blocks.REDSTONE_LAMP.getDefaultState();
		light = entity.isLit() ? state.with(RedstoneLampBlock.LIT, true).getLuminance() : state.getLuminance();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}
}
