package io.github.boogiemonster1o1.cartses.networking;

import io.github.boogiemonster1o1.cartses.Cartses;
import io.netty.buffer.Unpooled;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class EntityPacketUtils {
	public static final Identifier ID;

	private EntityPacketUtils() {
	}

	public static Packet<?> createPacket(Entity entity) {
		PacketByteBuf buf = createBuffer();
		buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(entity.getType()));
		buf.writeUuid(entity.getUuid());
		buf.writeVarInt(entity.getEntityId());
		buf.writeDouble(entity.getX());
		buf.writeDouble(entity.getY());
		buf.writeDouble(entity.getZ());
		buf.writeByte(MathHelper.floor(entity.pitch * 256.0F / 360.0F));
		buf.writeByte(MathHelper.floor(entity.yaw * 256.0F / 360.0F));
		buf.writeFloat(entity.pitch);
		buf.writeFloat(entity.yaw);
		return ServerSidePacketRegistry.INSTANCE.toPacket(ID, buf);
	}

	private static PacketByteBuf createBuffer() {
		return new PacketByteBuf(Unpooled.buffer());
	}

	static {
		ID = new Identifier(Cartses.MOD_ID, "spawn_entity");
	}
}
