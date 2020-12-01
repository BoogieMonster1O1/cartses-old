package io.github.boogiemonster1o1.cartses

import com.chocohead.mm.api.ClassTinkerers
import net.fabricmc.loader.api.{FabricLoader, MappingResolver}

class CartsesEarlyRiser extends Runnable {

	override def run(): Unit = {
		val resolver: MappingResolver = FabricLoader.getInstance.getMappingResolver
		val minecartTypeName: String = resolver.mapClassName("intermediary", "net.minecraft.class_1688$class_1689")
		ClassTinkerers.enumBuilder(minecartTypeName, new Array[String](0)).addEnum("CRAFTING_TABLE").addEnum("GLOWSTONE").addEnum("REDSTONE_LAMP").addEnum("BARREL").addEnum("ENDER_CHEST").build()
	}
}
