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
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.registry.Registry

class CartsesClient extends ClientModInitializer {
	override def onInitializeClient(): Unit = {
		ClientSidePacketRegistry.INSTANCE.register(EntityUtils.packetId, onPacket)
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_CRAFTING_TABLE, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartEntityRenderer[MinecartWithCraftingTableEntity](dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_BARREL, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartEntityRenderer[MinecartWithBarrelEntity](dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_GLOWSTONE, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartWithGlowstoneEntityRenderer(dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_REDSTONE_LAMP, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartWithRedstoneLampEntityRenderer(dispatcher))
		EntityRendererRegistry.INSTANCE.register(ModEntityTypes.MINECART_WITH_ENDER_CHEST, (dispatcher: EntityRenderDispatcher, ctx: EntityRendererRegistry.Context) => new MinecartWithEnderChestEntityRenderer(dispatcher))
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
			val entity = `type`.create(world)
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
