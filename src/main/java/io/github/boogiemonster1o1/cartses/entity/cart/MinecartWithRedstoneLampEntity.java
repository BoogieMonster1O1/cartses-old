package io.github.boogiemonster1o1.cartses.entity.cart;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.boogiemonster1o1.cartses.networking.EntityPacketUtils;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.network.Packet;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class MinecartWithRedstoneLampEntity extends AbstractMinecartEntity {
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "REDSTONE_LAMP");
	private static final TrackedData<Integer> LIGHT_TICKS = DataTracker.registerData(MinecartWithRedstoneLampEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public MinecartWithRedstoneLampEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public MinecartWithRedstoneLampEntity(EntityType<?> type, World world, double x, double y, double z) {
		super(type, world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.world.isClient()){
			int x = MathHelper.floor(this.getX());
			int y = MathHelper.floor(this.getY());
			int z = MathHelper.floor(this.getZ());
			if (this.world.getBlockState(new BlockPos(x, y - 1, z)).isIn(BlockTags.RAILS)) {
				--y;
			}
			BlockPos blockPos = new BlockPos(x, y, z);
			BlockState blockState = this.world.getBlockState(blockPos);
			if (AbstractRailBlock.isRail(blockState)) {
				if (blockState.isOf(Blocks.DETECTOR_RAIL) && blockState.get(DetectorRailBlock.POWERED) && this.getLightTicks() <= 1) {
					this.setLightTicks(5);
				}
			}
		}
		if (this.getLightTicks() > 1) {
			this.setLightTicks(this.getLightTicks() - 1);
		}
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(LIGHT_TICKS, 0);
	}

	public void setLightTicks(int value) {
		this.dataTracker.set(LIGHT_TICKS, value);
	}

	public int getLightTicks() {
		return this.dataTracker.get(LIGHT_TICKS);
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
	public void dropItems(DamageSource damageSource) {
		super.dropItems(damageSource);
		if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			this.dropItem(Blocks.REDSTONE_LAMP);
		}
	}
}
