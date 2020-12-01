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

package io.github.boogiemonster1o1.cartses.entity

import io.github.boogiemonster1o1.cartses.CartsesRef
import io.github.boogiemonster1o1.cartses.entity.cart._
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.{EntityDimensions, EntityType}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModEntityTypes {

	val minecartWithCraftingTable: EntityType[MinecartWithCraftingTableEntity] = FabricEntityTypeBuilder.create[MinecartWithCraftingTableEntity].entityFactory(MinecartWithCraftingTableEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	val minecartWithBarrel: EntityType[MinecartWithBarrelEntity] = FabricEntityTypeBuilder.create[MinecartWithBarrelEntity].entityFactory(MinecartWithBarrelEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	val minecartWithGlowstone: EntityType[MinecartWithGlowstoneEntity] = FabricEntityTypeBuilder.create[MinecartWithGlowstoneEntity].entityFactory(MinecartWithGlowstoneEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	val minecartWithRedstoneLamp: EntityType[MinecartWithRedstoneLampEntity] = FabricEntityTypeBuilder.create[MinecartWithRedstoneLampEntity].entityFactory(MinecartWithRedstoneLampEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	val minecartWithEnderChest: EntityType[MinecartWithEnderChestEntity] = FabricEntityTypeBuilder.create[MinecartWithEnderChestEntity].entityFactory(MinecartWithEnderChestEntity.`new`).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build

	def init(): Unit = {
		register(minecartWithCraftingTable, "minecart_with_crafting_table")
		register(minecartWithBarrel, "minecart_with_barrel")
		register(minecartWithGlowstone, "minecart_with_glowstone")
		register(minecartWithRedstoneLamp, "minecart_with_redstone_lamp")
		register(minecartWithEnderChest, "minecart_with_ender_chest")
	}

	private def register(entityType: EntityType[_], name: String): Unit = {
		Registry.register(Registry.ITEM, new Identifier(CartsesRef.modId, name), entityType)
	}
}
