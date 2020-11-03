package io.github.boogiemonster1o1.cartses;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntityTypes {
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
