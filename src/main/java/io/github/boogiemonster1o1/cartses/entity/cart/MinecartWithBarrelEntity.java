package io.github.boogiemonster1o1.cartses.entity.cart;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.boogiemonster1o1.cartses.networking.EntityPacketUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.network.Packet;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class MinecartWithBarrelEntity extends StorageMinecartEntity {
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "BARREL");

	public MinecartWithBarrelEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public MinecartWithBarrelEntity(EntityType<?> type, double x, double y, double z, World world) {
		super(type, x, y, z, world);
	}

	@Override
	protected ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
	}

	@Override
	public Type getMinecartType() {
		return MINECART_TYPE;
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntityPacketUtils.createPacket(this);
	}

	@Override
	public int size() {
		return 27;
	}

	@Override
	public int getDefaultBlockOffset() {
		return 8;
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return Blocks.BARREL.getDefaultState();
	}
}
