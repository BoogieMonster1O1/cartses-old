package io.github.boogiemonster1o1.cartses.client

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithRedstoneLampEntity
import net.fabricmc.api.{EnvType, Environment}
import net.minecraft.block.{Blocks, RedstoneLampBlock}
import net.minecraft.client.render.entity.{EntityRenderDispatcher, MinecartEntityRenderer}
import net.minecraft.util.math.BlockPos


@Environment(EnvType.CLIENT) class MinecartWithRedstoneLampEntityRenderer(val dispatcher: EntityRenderDispatcher) extends MinecartEntityRenderer[MinecartWithRedstoneLampEntity](dispatcher) {
	override protected def method_27950(entity: MinecartWithRedstoneLampEntity, blockPos: BlockPos): Int = if (entity.isLit) Blocks.REDSTONE_LAMP.getDefaultState.`with`(RedstoneLampBlock.LIT, true).getLuminance else super.method_27950(entity, blockPos)

	override protected def getBlockLight(entity: MinecartWithRedstoneLampEntity, blockPos: BlockPos): Int = if (entity.isLit) Blocks.REDSTONE_LAMP.getDefaultState.`with`(RedstoneLampBlock.LIT, true).getLuminance else super.getBlockLight(entity, blockPos)
}
