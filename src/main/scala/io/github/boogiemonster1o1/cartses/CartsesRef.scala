package io.github.boogiemonster1o1.cartses

import org.apache.logging.log4j.{Level, LogManager, Logger}

object CartsesRef {

	val logger: Logger = LogManager.getLogger
	val modId = "cartses"

	def log(level: Level, message: String): Unit = {
		logger.log(level, "[Cartses] " + message)
	}
}
