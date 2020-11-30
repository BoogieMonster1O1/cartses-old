package io.github.boogiemonster1o1.cartses.entity.cart

import com.chocohead.mm.api.ClassTinkerers
import io.github.boogiemonster1o1.cartses.entity.MinecartTypes
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.network.Packet
import net.minecraft.world.GameRules
import net.minecraft.world.World


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
