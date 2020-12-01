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

package io.github.boogiemonster1o1.cartses.entity.cart

import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import net.minecraft.block.{BlockState, Blocks}
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.Packet
import net.minecraft.world.{GameRules, World}


@SuppressWarnings(Array("EntityConstructor")) class MinecartWithGlowstoneEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {

	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def getContainedBlock: BlockState = Blocks.GLOWSTONE.getDefaultState

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.glowstone

	override def dropItems(damageSource: DamageSource): Unit = {
		super.dropItems(damageSource)
		if (this.world.getGameRules.getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(Blocks.GLOWSTONE)
	}
}
