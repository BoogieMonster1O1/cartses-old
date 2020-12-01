package io.github.boogiemonster1o1.cartses.item

import io.github.boogiemonster1o1.cartses.{Cartses, RegisterMe, RegistryEntry}
import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import net.minecraft.item.{Item, ItemGroup, MinecartItem}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModItems {
	val minecartWithCraftingTable = new MinecartItem(MinecartTypes.craftingTable, new Item.Settings().group(ItemGroup.TRANSPORTATION))

	val minecartWithBarrel = new MinecartItem(MinecartTypes.barrel, new Item.Settings().group(ItemGroup.TRANSPORTATION))

	val minecartWithGlowstone = new MinecartItem(MinecartTypes.glowstone, new Item.Settings().group(ItemGroup.TRANSPORTATION))

	val minecartWithRedstoneLamp = new MinecartItem(MinecartTypes.redstoneLamp, new Item.Settings().group(ItemGroup.TRANSPORTATION))

	val minecartWithEnderChest = new MinecartItem(MinecartTypes.enderChest, new Item.Settings().group(ItemGroup.TRANSPORTATION))

	def init(): Unit = {
		register("minecart_with_crafting_table", minecartWithCraftingTable)
		register("minecart_with_barrel", minecartWithCraftingTable)
		register("minecart_with_glowstone", minecartWithCraftingTable)
		register("minecart_with_redstone_lamp", minecartWithCraftingTable)
		register("minecart_with_ender_chest", minecartWithCraftingTable)
	}

	private def register(name: String, item: Item): Unit = {
		Registry.register(Registry.ITEM, new Identifier(Cartses.modId, name), item)
	}
}
