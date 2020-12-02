/*
 * Copyright (c) 2020 BoogieMonster1O1
 *
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 * WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
 * AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package io.github.boogiemonster1o1.cartses.item

import io.github.boogiemonster1o1.cartses.Cartses
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

	val minecartWithNoteBlock = new MinecartItem(MinecartTypes.noteBlock, new Item.Settings().group(ItemGroup.TRANSPORTATION))

	def init(): Unit = {
		register("minecart_with_crafting_table", minecartWithCraftingTable)
		register("minecart_with_barrel", minecartWithBarrel)
		register("minecart_with_glowstone", minecartWithGlowstone)
		register("minecart_with_redstone_lamp", minecartWithRedstoneLamp)
		register("minecart_with_ender_chest", minecartWithEnderChest)
		register("minecart_with_note_block", minecartWithNoteBlock)
	}

	private def register(name: String, item: Item): Unit = {
		Registry.register(Registry.ITEM, new Identifier(Cartses.modId, name), item)
	}
}
