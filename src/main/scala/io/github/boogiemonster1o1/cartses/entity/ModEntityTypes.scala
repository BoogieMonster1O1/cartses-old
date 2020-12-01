package io.github.boogiemonster1o1.cartses.entity

import io.github.boogiemonster1o1.cartses.{Cartses, RegisterMe}
import io.github.boogiemonster1o1.cartses.entity.cart.{MinecartWithBarrelEntity, MinecartWithCraftingTableEntity, MinecartWithEnderChestEntity, MinecartWithGlowstoneEntity, MinecartWithRedstoneLampEntity}
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.{EntityDimensions, EntityType}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModEntityTypes {

	@RegisterMe("minecart_with_crafting_table") val minecartWithCraftingTable: EntityType[MinecartWithCraftingTableEntity] = FabricEntityTypeBuilder.create[MinecartWithCraftingTableEntity].entityFactory(MinecartWithCraftingTableEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	@RegisterMe("minecart_with_barrel") val minecartWithBarrel: EntityType[MinecartWithBarrelEntity] = FabricEntityTypeBuilder.create[MinecartWithBarrelEntity].entityFactory(MinecartWithBarrelEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	@RegisterMe("minecart_with_glowstone") val minecartWithGlowstone: EntityType[MinecartWithGlowstoneEntity] = FabricEntityTypeBuilder.create[MinecartWithGlowstoneEntity].entityFactory(MinecartWithGlowstoneEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	@RegisterMe("minecart_with_redstone_lamp") val minecartWithRedstoneLamp: EntityType[MinecartWithRedstoneLampEntity] = FabricEntityTypeBuilder.create[MinecartWithRedstoneLampEntity].entityFactory(MinecartWithRedstoneLampEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	@RegisterMe("minecart_with_ender_chest") val minecartWithEnderChest: EntityType[MinecartWithEnderChestEntity] = FabricEntityTypeBuilder.create[MinecartWithEnderChestEntity].entityFactory(MinecartWithEnderChestEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	def init(): Unit = {
		register(minecartWithCraftingTable, "minecart_with_crafting_table")
		register(minecartWithBarrel, "minecart_with_barrel")
		register(minecartWithGlowstone, "minecart_with_glowstone")
		register(minecartWithRedstoneLamp, "minecart_with_redstone_lamp")
		register(minecartWithEnderChest, "minecart_with_ender_chest")
	}

	private def register(entityType: EntityType[_], name: String): Unit = {
		Registry.register(Registry.ITEM, new Identifier(Cartses.modId, name), entityType)
	}
}
