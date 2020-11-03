package io.github.boogiemonster1o1.cartses;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import io.github.boogiemonster1o1.cartses.cart.MinecartWithCraftingTableEntity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

public class ModEntityTypes {
	public static final EntityType<MinecartWithCraftingTableEntity> MINECART_WITH_CRAFTING_TABLE = FabricEntityTypeBuilder.<MinecartWithCraftingTableEntity>create().entityFactory(MinecartWithCraftingTableEntity::new).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build();

	public static void init() {
		for (Field field : ModEntityTypes.class.getFields()) {
			if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers()) || !Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			RegisterMe annotation = field.getAnnotation(RegisterMe.class);
			if (annotation == null) {
				continue;
			}
			try {
				Registry.register(Registry.ENTITY_TYPE, new Identifier(Cartses.MOD_ID, annotation.name()), (EntityType<?>) field.get(null));
			} catch (IllegalAccessException e) {
				throw new AssertionError(e);
			}
		}
	}
}
