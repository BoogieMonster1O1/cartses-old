package io.github.boogiemonster1o1.cartses.entity.cart;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.boogiemonster1o1.cartses.networking.EntityPacketUtils;
import me.lambdaurora.lambdynlights.DynamicLightSource;
import me.lambdaurora.lambdynlights.LambDynLights;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.network.Packet;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class MinecartWithGlowstoneEntity extends AbstractMinecartEntity {
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "GLOWSTONE");

	public MinecartWithGlowstoneEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public MinecartWithGlowstoneEntity(EntityType<?> type, World world, double x, double y, double z) {
		super(type, world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.world.isClient()) {
			if (this.removed) {
				((DynamicLightSource) this).setDynamicLightEnabled(false);
			} else {
				((DynamicLightSource) this).dynamicLightTick();
				LambDynLights.updateTracking((DynamicLightSource) this);
			}
		}
	}

	@Override
	public BlockState getContainedBlock() {
		return Blocks.GLOWSTONE.getDefaultState();
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntityPacketUtils.createPacket(this);
	}

	@Override
	public Type getMinecartType() {
		return MINECART_TYPE;
	}

	@Override
	public void dropItems(DamageSource damageSource) {
		super.dropItems(damageSource);
		if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			this.dropItem(Blocks.GLOWSTONE);
		}
	}
}
