package io.github.boogiemonster1o1.cartses.compat.client;

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes;
import me.lambdaurora.lambdynlights.api.DynamicLightHandlers;

import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;

public class DynLightsCompat {
	public static void init() {
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_GLOWSTONE, entity -> Blocks.GLOWSTONE.getDefaultState().getLuminance());
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.MINECART_WITH_REDSTONE_LAMP, entity -> {
			if (entity.getLightTicks() > 0) {
				return Blocks.REDSTONE_LAMP.getDefaultState().with(RedstoneLampBlock.LIT, true).getLuminance();
			}
			return 0;
		});
	}
}
