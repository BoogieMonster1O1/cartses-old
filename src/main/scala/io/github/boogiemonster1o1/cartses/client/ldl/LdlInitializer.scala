package io.github.boogiemonster1o1.cartses.client.ldl

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes
import io.github.boogiemonster1o1.cartses.entity.cart.{MinecartWithEnderChestEntity, MinecartWithGlowstoneEntity, MinecartWithRedstoneLampEntity}
import me.lambdaurora.lambdynlights.api.{DynamicLightHandlers, DynamicLightsInitializer}
import net.minecraft.block.{Blocks, RedstoneLampBlock}

class LdlInitializer extends DynamicLightsInitializer {
	override def onInitializeDynamicLights(): Unit = {
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_GLOWSTONE, (_: MinecartWithGlowstoneEntity) => Blocks.GLOWSTONE.getDefaultState.getLuminance)
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_ENDER_CHEST, (_: MinecartWithEnderChestEntity) => Blocks.ENDER_CHEST.getDefaultState.getLuminance)
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_REDSTONE_LAMP, (entity: MinecartWithRedstoneLampEntity) => {
			if (entity.isLit) {
				Blocks.REDSTONE_LAMP.getDefaultState.`with`(RedstoneLampBlock.LIT, true).getLuminance
			}
			0
		})
	}
}
