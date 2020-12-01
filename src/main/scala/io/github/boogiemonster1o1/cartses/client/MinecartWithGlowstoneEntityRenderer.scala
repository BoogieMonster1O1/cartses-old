package io.github.boogiemonster1o1.cartses.client

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithGlowstoneEntity
import net.fabricmc.api.{EnvType, Environment}
import net.minecraft.block.Blocks
import net.minecraft.client.render.entity.{EntityRenderDispatcher, MinecartEntityRenderer}
import net.minecraft.util.math.BlockPos;


@Environment(EnvType.CLIENT) class MinecartWithGlowstoneEntityRenderer(val dispatcher: EntityRenderDispatcher) extends MinecartEntityRenderer[MinecartWithGlowstoneEntity](dispatcher) {

	override protected def method_27950(entity: MinecartWithGlowstoneEntity, blockPos: BlockPos): Int = Blocks.GLOWSTONE.getDefaultState.getLuminance

	override protected def getBlockLight(entity: MinecartWithGlowstoneEntity, blockPos: BlockPos): Int = Blocks.GLOWSTONE.getDefaultState.getLuminance
}
