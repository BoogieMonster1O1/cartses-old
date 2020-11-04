package io.github.boogiemonster1o1.cartses;

import net.fabricmc.api.ModInitializer;

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes;
import io.github.boogiemonster1o1.cartses.item.ModItems;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cartses implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "cartses";

	@Override
	public void onInitialize() {
		log(Level.INFO, "Initializing");
		ModEntityTypes.init();
		ModItems.init();
	}

	public static void log(Level level, String message) {
		LOGGER.log(level, "[" + Cartses.class.getSimpleName() + "] " + message);
	}
}
