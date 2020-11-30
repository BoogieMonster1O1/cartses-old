package io.github.boogiemonster1o1.cartses.compat.client;

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes;
import me.lambdaurora.lambdynlights.api.DynamicLightHandlers;
import me.lambdaurora.lambdynlights.api.DynamicLightsInitializer;

import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DynLightsCompat implements DynamicLightsInitializer {
	@Override
	public void onInitializeDynamicLights() {
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_GLOWSTONE, entity -> Blocks.GLOWSTONE.getDefaultState().getLuminance());
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_ENDER_CHEST, entity -> Blocks.ENDER_CHEST.getDefaultState().getLuminance());
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_REDSTONE_LAMP, entity -> {
			if (entity.isLit()) {
				return Blocks.REDSTONE_LAMP.getDefaultState().with(RedstoneLampBlock.LIT, true).getLuminance();
			}
			return 0;
		});
	}
}
