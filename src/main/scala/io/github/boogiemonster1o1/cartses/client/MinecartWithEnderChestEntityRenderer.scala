package io.github.boogiemonster1o1.cartses.client

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithEnderChestEntity
import net.fabricmc.api.{EnvType, Environment}
import net.minecraft.block.Blocks
import net.minecraft.client.render.entity.{EntityRenderDispatcher, MinecartEntityRenderer}
import net.minecraft.util.math.BlockPos


@Environment(EnvType.CLIENT) class MinecartWithEnderChestEntityRenderer(val dispatcher: EntityRenderDispatcher) extends MinecartEntityRenderer[MinecartWithEnderChestEntity](dispatcher) {

	override protected def method_27950(entity: MinecartWithEnderChestEntity, blockPos: BlockPos): Int = Math.max(Blocks.ENDER_CHEST.getDefaultState.getLuminance, super.method_27950(entity, blockPos))

	override protected def getBlockLight(entity: MinecartWithEnderChestEntity, blockPos: BlockPos): Int = Math.max(Blocks.ENDER_CHEST.getDefaultState.getLuminance, super.getBlockLight(entity, blockPos))
}
