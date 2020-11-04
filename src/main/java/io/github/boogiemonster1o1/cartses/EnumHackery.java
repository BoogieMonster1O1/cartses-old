package io.github.boogiemonster1o1.cartses;

import com.chocohead.mm.api.ClassTinkerers;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class EnumHackery implements Runnable {
	@Override
	public void run() {
		MappingResolver resolver = FabricLoader.getInstance().getMappingResolver();
		String minecartTypeName = resolver.mapClassName("intermediary", "net.minecraft.class_1688$class_1689");
		ClassTinkerers.enumBuilder(minecartTypeName, new String[0]).addEnum("CRAFTING_TABLE").addEnum("BARREL").build();
	}
}
