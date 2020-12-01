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
import net.minecraft.block._
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.{DataTracker, TrackedDataHandlerRegistry}
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.Packet
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.{BlockPos, MathHelper}
import net.minecraft.world.{GameRules, World}

object MinecartWithRedstoneLampEntity {

	private val LIT = DataTracker.registerData(classOf[MinecartWithRedstoneLampEntity], TrackedDataHandlerRegistry.BOOLEAN)
}

@SuppressWarnings(Array("EntityConstructor"))
class MinecartWithRedstoneLampEntity(entityType: EntityType[_], world: World) extends AbstractMinecartEntity(entityType, world) {

	def this(`type`: EntityType[_], world: World, x: Double, y: Double, z: Double) {
		this(`type`, world)
		EntityUtils.setupPos(this, x, y, z)
	}

	override def tick(): Unit = {
		super.tick()
		if (!this.world.isClient) {
			val x = MathHelper.floor(this.getX)
			var y = MathHelper.floor(this.getY)
			val z = MathHelper.floor(this.getZ)
			if (this.world.getBlockState(new BlockPos(x, y - 1, z)).isIn(BlockTags.RAILS)) y -= 1
			val blockPos = new BlockPos(x, y, z)
			val blockState = this.world.getBlockState(blockPos)
			if (AbstractRailBlock.isRail(blockState)) if (blockState.isOf(Blocks.ACTIVATOR_RAIL) && blockState.get(PoweredRailBlock.POWERED)) this.setLit(true)
			else this.setLit(false)
		}
	}

	override protected def initDataTracker(): Unit = {
		super.initDataTracker()
		this.dataTracker.startTracking[java.lang.Boolean](MinecartWithRedstoneLampEntity.LIT, false)
	}

	def setLit(value: Boolean): Unit = {
		this.dataTracker.set[java.lang.Boolean](MinecartWithRedstoneLampEntity.LIT, value)
	}

	def isLit: Boolean = this.dataTracker.get(MinecartWithRedstoneLampEntity.LIT)

	override def getContainedBlock: BlockState = {
		val state = Blocks.REDSTONE_LAMP.getDefaultState
		if (this.isLit) return state.`with`[java.lang.Boolean, java.lang.Boolean](RedstoneLampBlock.LIT, true)
		state
	}

	override def getMinecartType: AbstractMinecartEntity.Type = MinecartTypes.redstoneLamp

	override def createSpawnPacket: Packet[_] = EntityUtils.createPacket(this)

	override def dropItems(damageSource: DamageSource): Unit = {
		super.dropItems(damageSource)
		if (this.world.getGameRules.getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(Blocks.REDSTONE_LAMP)
	}
}
