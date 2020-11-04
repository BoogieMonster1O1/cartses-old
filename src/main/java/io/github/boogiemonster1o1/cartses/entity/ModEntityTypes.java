package io.github.boogiemonster1o1.cartses.entity;

import java.lang.reflect.Field;

import io.github.boogiemonster1o1.cartses.Cartses;
import io.github.boogiemonster1o1.cartses.RegisterMe;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithBarrelEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithCraftingTableEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithGlowstoneEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithRedstoneLampEntity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

public class ModEntityTypes {
	@RegisterMe("minecart_with_crafting_table")
	public static final EntityType<MinecartWithCraftingTableEntity> MINECART_WITH_CRAFTING_TABLE = FabricEntityTypeBuilder.<MinecartWithCraftingTableEntity>create().entityFactory(MinecartWithCraftingTableEntity::new).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build();

	@RegisterMe("minecart_with_barrel")
	public static final EntityType<MinecartWithBarrelEntity> MINECART_WITH_BARREL = FabricEntityTypeBuilder.<MinecartWithBarrelEntity>create().entityFactory(MinecartWithBarrelEntity::new).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build();

	@RegisterMe("minecart_with_glowstone")
	public static final EntityType<MinecartWithGlowstoneEntity> MINECART_WITH_GLOWSTONE = FabricEntityTypeBuilder.<MinecartWithGlowstoneEntity>create().entityFactory(MinecartWithGlowstoneEntity::new).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build();

	@RegisterMe("minecart_with_redstone_lamp")
	public static final EntityType<MinecartWithRedstoneLampEntity> MINECART_WITH_REDSTONE_LAMP = FabricEntityTypeBuilder.<MinecartWithRedstoneLampEntity>create().entityFactory(MinecartWithRedstoneLampEntity::new).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build();

	public static void init() {
		for (Field field : ModEntityTypes.class.getFields()) {
			if (!RegisterMe.PREDICATE.test(field)) {
				continue;
			}
			RegisterMe annotation = field.getAnnotation(RegisterMe.class);
			try {
				Registry.register(Registry.ENTITY_TYPE, new Identifier(Cartses.MOD_ID, annotation.value()), (EntityType<?>) field.get(null));
			} catch (IllegalAccessException e) {
				throw new AssertionError(e);
			}
		}
	}
}
