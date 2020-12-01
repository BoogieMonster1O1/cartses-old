package io.github.boogiemonster1o1.cartses.entity

import com.chocohead.mm.api.ClassTinkerers
import net.minecraft.entity.vehicle.AbstractMinecartEntity

object MinecartTypes {

	val craftingTable: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "CRAFTING_TABLE")
	val barrel: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "BARREL")
	val enderChest: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "ENDER_CHEST")
	val glowstone: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "GLOWSTONE")
	val redstoneLamp: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "REDSTONE_LAMP")
}
