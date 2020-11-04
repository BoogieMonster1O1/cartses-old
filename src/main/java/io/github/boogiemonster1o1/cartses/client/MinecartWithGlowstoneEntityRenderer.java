package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithGlowstoneEntity;

import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class MinecartWithGlowstoneEntityRenderer extends MinecartEntityRenderer<MinecartWithGlowstoneEntity> {
	public MinecartWithGlowstoneEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(MinecartWithGlowstoneEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, Blocks.GLOWSTONE.getDefaultState().getLuminance());
	}
}
