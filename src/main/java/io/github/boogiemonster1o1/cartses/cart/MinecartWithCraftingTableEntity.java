package io.github.boogiemonster1o1.cartses.cart;

import com.chocohead.mm.api.ClassTinkerers;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class MinecartWithCraftingTableEntity extends AbstractMinecartEntity {
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "CRAFTING_TABLE");

	public MinecartWithCraftingTableEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public MinecartWithCraftingTableEntity(EntityType<?> type, World world, double x, double y, double z) {
		super(type, world, x, y, z);
	}

	@Override
	public Type getMinecartType() {
		return MINECART_TYPE;
	}
}
