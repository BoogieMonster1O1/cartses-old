package io.github.boogiemonster1o1.cartses.entity.cart;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.boogiemonster1o1.cartses.networking.EntityPacketUtils;
import me.lambdaurora.lambdynlights.DynamicLightSource;
import me.lambdaurora.lambdynlights.LambDynLights;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.RedstoneLampBlock;
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

import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.EnvironmentInterfaces;

@SuppressWarnings("EntityConstructor")
@EnvironmentInterfaces(@EnvironmentInterface(value = EnvType.CLIENT, itf = DynamicLightSource.class))
public class MinecartWithRedstoneLampEntity extends AbstractMinecartEntity {
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "REDSTONE_LAMP");
	private static final TrackedData<Boolean> LIT = DataTracker.registerData(MinecartWithRedstoneLampEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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
				if (blockState.isOf(Blocks.ACTIVATOR_RAIL) && blockState.get(PoweredRailBlock.POWERED)) {
					this.setLit(true);
				}
			} else {
				this.setLit(false);
			}
		}
		this.doClientDynLightTick();
	}


	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(LIT, false);
	}

	public void setLit(boolean value) {
		this.dataTracker.set(LIT, value);
	}

	public boolean isLit() {
		return this.dataTracker.get(LIT);
	}

	@Override
	public BlockState getContainedBlock() {
		BlockState state = Blocks.REDSTONE_LAMP.getDefaultState();
		if (this.isLit()) {
			return state.with(RedstoneLampBlock.LIT, true);
		}
		return state;
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

	private void doClientDynLightTick() {
		if (this.world.isClient()) {
			if (this.removed || !this.isLit()) {
				((DynamicLightSource) this).setDynamicLightEnabled(false);
			} else {
				((DynamicLightSource) this).dynamicLightTick();
				LambDynLights.updateTracking((DynamicLightSource) this);
			}
		}
	}
}
