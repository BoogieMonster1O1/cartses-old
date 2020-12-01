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

package io.github.boogiemonster1o1.cartses.entity.networking

import io.github.boogiemonster1o1.cartses.CartsesRef
import io.github.boogiemonster1o1.cartses.entity.cart._
import io.github.boogiemonster1o1.cartses.entity.{MinecartTypes, ModEntityTypes}
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.{Packet, PacketByteBuf}
import net.minecraft.util.Identifier
import net.minecraft.util.math.{MathHelper, Vec3d}
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object EntityUtils {

	val packetId: Identifier = new Identifier(CartsesRef.modId, "spawn_entity")

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

	def checkAndSetReturnValue(world: World, x: Double, y: Double, z: Double, entityType: AbstractMinecartEntity.Type, cir: CallbackInfoReturnable[AbstractMinecartEntity]): Unit = {
		if (entityType eq MinecartTypes.craftingTable) cir.setReturnValue(new MinecartWithCraftingTableEntity(ModEntityTypes.minecartWithCraftingTable, world, x, y, z))
		else if (entityType eq MinecartTypes.barrel) cir.setReturnValue(new MinecartWithBarrelEntity(ModEntityTypes.minecartWithBarrel, x, y, z, world))
		else if (entityType eq MinecartTypes.glowstone) cir.setReturnValue(new MinecartWithGlowstoneEntity(ModEntityTypes.minecartWithGlowstone, world, x, y, z))
		else if (entityType eq MinecartTypes.redstoneLamp) cir.setReturnValue(new MinecartWithRedstoneLampEntity(ModEntityTypes.minecartWithRedstoneLamp, world, x, y, z))
		else if (entityType eq MinecartTypes.enderChest) cir.setReturnValue(new MinecartWithEnderChestEntity(ModEntityTypes.minecartWithEnderChest, world, x, y, z))
	}
}
