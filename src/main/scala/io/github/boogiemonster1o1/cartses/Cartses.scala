package io.github.boogiemonster1o1.cartses

import net.fabricmc.api.ModInitializer
import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes
import io.github.boogiemonster1o1.cartses.item.ModItems
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


object Cartses {
	var logger: Logger = LogManager.getLogger
	val modId = "cartses"

	def log(level: Level, message: String): Unit = {
		logger.log(level, "[" + classOf[Cartses].getSimpleName + "] " + message)
	}
}

class Cartses extends ModInitializer {
	override def onInitialize(): Unit = {
		Cartses.log(Level.INFO, "Initializing")
		ModEntityTypes.init()
		ModItems.init()
	}
}
