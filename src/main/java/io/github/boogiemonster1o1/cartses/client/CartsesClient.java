package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithBarrelEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithCraftingTableEntity;

import net.minecraft.client.render.entity.MinecartEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class CartsesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_CRAFTING_TABLE, (dispatcher, ctx) -> new MinecartEntityRenderer<MinecartWithCraftingTableEntity>(dispatcher));
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_BARREL, (dispatcher, ctx) -> new MinecartEntityRenderer<MinecartWithBarrelEntity>(dispatcher));
	}
}
