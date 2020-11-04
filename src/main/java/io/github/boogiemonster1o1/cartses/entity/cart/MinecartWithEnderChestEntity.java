package io.github.boogiemonster1o1.cartses.entity.cart;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.boogiemonster1o1.cartses.networking.EntityPacketUtils;
import io.github.boogiemonster1o1.cartses.util.EnderChestInventoryExtended;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.network.Packet;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@SuppressWarnings({"EntityConstructor", "CodeBlock2Expr"})
public class MinecartWithEnderChestEntity extends AbstractMinecartEntity {
	private static final Text DISPLAY_NAME = new TranslatableText("screenhandler.cartses.ender_chest");
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "ENDER_CHEST");

	public MinecartWithEnderChestEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public MinecartWithEnderChestEntity(EntityType<?> type, World world, double x, double y, double z) {
		super(type, world, x, y, z);
	}

	@Override
	public Type getMinecartType() {
		return MINECART_TYPE;
	}

	@Override
	public BlockState getContainedBlock() {
		return Blocks.ENDER_CHEST.getDefaultState();
	}

	@Override
	public void dropItems(DamageSource damageSource) {
		super.dropItems(damageSource);
		if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			this.dropItem(Blocks.ENDER_CHEST);
		}
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntityPacketUtils.createPacket(this);
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		EnderChestInventory enderChestInventory = player.getEnderChestInventory();
		if (enderChestInventory != null) {
			if (this.world.isClient) {
				return ActionResult.SUCCESS;
			} else {
				((EnderChestInventoryExtended) enderChestInventory).forceCanUse();
				player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInv, playerEntity) -> {
					return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInv, enderChestInventory);
				}, DISPLAY_NAME));
				player.incrementStat(Stats.OPEN_ENDERCHEST);
				return ActionResult.CONSUME;
			}
		} else {
			return ActionResult.success(this.world.isClient);
		}
	}
}
