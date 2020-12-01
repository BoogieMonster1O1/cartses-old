package io.github.boogiemonster1o1.cartses

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes
import io.github.boogiemonster1o1.cartses.item.ModItems
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level

class CartsesInitializer extends ModInitializer {

	override def onInitialize(): Unit = {
		CartsesRef.log(Level.INFO, "Initializing Cartses")
		ModEntityTypes.init()
		ModItems.init()
	}
}
