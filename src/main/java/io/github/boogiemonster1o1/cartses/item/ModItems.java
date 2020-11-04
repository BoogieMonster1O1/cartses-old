package io.github.boogiemonster1o1.cartses.item;

import java.lang.reflect.Field;

import io.github.boogiemonster1o1.cartses.Cartses;
import io.github.boogiemonster1o1.cartses.RegisterMe;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithBarrelEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithCraftingTableEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
	@RegisterMe("minecart_with_crafting_table")
	public static final Item MINECART_WITH_CRAFTING_TABLE = new MinecartItem(MinecartWithCraftingTableEntity.MINECART_TYPE, new Item.Settings().group(ItemGroup.TRANSPORTATION));

	@RegisterMe("minecart_with_barrel")
	public static final Item MINECART_WITH_BARREL = new MinecartItem(MinecartWithBarrelEntity.MINECART_TYPE, new Item.Settings().group(ItemGroup.TRANSPORTATION));

	public static void init() {
		for (Field field : ModItems.class.getFields()) {
			if (!RegisterMe.PREDICATE.test(field)) {
				continue;
			}
			RegisterMe annotation = field.getAnnotation(RegisterMe.class);
			try {
				Registry.register(Registry.ITEM, new Identifier(Cartses.MOD_ID, annotation.value()), (Item) field.get(null));
			} catch (IllegalAccessException e) {
				throw new AssertionError(e);
			}
		}
	}
}
