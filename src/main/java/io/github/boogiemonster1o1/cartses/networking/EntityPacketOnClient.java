package io.github.boogiemonster1o1.cartses.networking;

import java.util.Objects;
import java.util.UUID;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;

@Environment(EnvType.CLIENT)
public class EntityPacketOnClient {
	private EntityPacketOnClient() {
	}

	public static void onPacket(PacketContext context, PacketByteBuf byteBuf) {
		EntityType<?> type = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
		UUID entityUUID = byteBuf.readUuid();
		int entityID = byteBuf.readVarInt();
		double x = byteBuf.readDouble();
		double y = byteBuf.readDouble();
		double z = byteBuf.readDouble();
		float pitch = (float)(byteBuf.readByte() * 360) / 256.0F;
		float yaw = (float)(byteBuf.readByte() * 360) / 256.0F;
		context.getTaskQueue().execute(() -> {
			ClientWorld world = MinecraftClient.getInstance().world;
			Entity entity = type.create(world);
			if (entity != null) {
				entity.updatePosition(x, y, z);
				entity.updateTrackedPosition(x, y, z);
				entity.pitch = pitch;
				entity.yaw = yaw;
				entity.setEntityId(entityID);
				entity.setUuid(entityUUID);
				Objects.requireNonNull(world).addEntity(entityID, entity);
			}
		});
	}

	public static void initNetworking() {
		ClientSidePacketRegistry.INSTANCE.register(EntityPacketUtils.ID, EntityPacketOnClient::onPacket);
	}
}
