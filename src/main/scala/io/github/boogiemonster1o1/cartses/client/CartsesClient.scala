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

package io.github.boogiemonster1o1.cartses.client

import java.util.Objects

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes
import io.github.boogiemonster1o1.cartses.entity.cart.{MinecartWithBarrelEntity, MinecartWithCraftingTableEntity}
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.network.{ClientSidePacketRegistry, PacketContext}
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.{EntityRenderDispatcher, MinecartEntityRenderer}
import net.minecraft.entity.Entity
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.registry.Registry

object CartsesClient extends ClientModInitializer {
	override def onInitializeClient(): Unit = {
		ClientSidePacketRegistry.INSTANCE.register(EntityUtils.packetId, onPacket)
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.minecartWithCraftingTable, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartEntityRenderer[MinecartWithCraftingTableEntity](dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.minecartWithBarrel, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartEntityRenderer[MinecartWithBarrelEntity](dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.minecartWithGlowstone, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartWithGlowstoneEntityRenderer(dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.minecartWithRedstoneLamp, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartWithRedstoneLampEntityRenderer(dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.minecartWithEnderChest, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartWithEnderChestEntityRenderer(dispatcher))
	}

	private def onPacket(context: PacketContext, byteBuf: PacketByteBuf): Unit = {
		val `type` = Registry.ENTITY_TYPE.get(byteBuf.readVarInt)
		val entityUUID = byteBuf.readUuid
		val entityID = byteBuf.readVarInt
		val x = byteBuf.readDouble
		val y = byteBuf.readDouble
		val z = byteBuf.readDouble
		val pitch = (byteBuf.readByte * 360).toFloat / 256.0F
		val yaw = (byteBuf.readByte * 360).toFloat / 256.0F
		context.getTaskQueue.execute(() => {
			val world = MinecraftClient.getInstance.world
			val entity: Entity = `type`.create(world)
			if (entity != null) {
				entity.updatePosition(x, y, z)
				entity.updateTrackedPosition(x, y, z)
				entity.pitch = pitch
				entity.yaw = yaw
				entity.setEntityId(entityID)
				entity.setUuid(entityUUID)
				Objects.requireNonNull(world).addEntity(entityID, entity)
			}
		})
	}
}
