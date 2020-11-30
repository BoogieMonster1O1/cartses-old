package io.github.boogiemonster1o1.cartses.entity.networking

import io.github.boogiemonster1o1.cartses.Cartses
import io.netty.buffer.Unpooled
import net.minecraft.entity.Entity
import net.minecraft.network.Packet
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier
import net.minecraft.util.math.{MathHelper, Vec3d}
import net.minecraft.util.registry.Registry
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry

object EntityUtils {
	val packetId: Identifier = new Identifier(Cartses.MOD_ID, "spawn_entity")

	def createPacket(entity: Entity): Packet[_] = {
		val buf = new PacketByteBuf(Unpooled.buffer)
		buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(entity.getType))
		buf.writeUuid(entity.getUuid)
		buf.writeVarInt(entity.getEntityId)
		buf.writeDouble(entity.getX)
		buf.writeDouble(entity.getY)
		buf.writeDouble(entity.getZ)
		buf.writeByte(MathHelper.floor(entity.pitch * 256.0F / 360.0F))
		buf.writeByte(MathHelper.floor(entity.yaw * 256.0F / 360.0F))
		buf.writeFloat(entity.pitch)
		buf.writeFloat(entity.yaw)
		ServerSidePacketRegistry.INSTANCE.toPacket(packetId, buf)
	}

	def setupPos(entity: Entity, x: Double, y: Double, z: Double): Unit = {
		entity.updatePosition(x, y, z)
		entity.setVelocity(Vec3d.ZERO)
		entity.prevX = x
		entity.prevY = y
		entity.prevZ = z
	}
}
