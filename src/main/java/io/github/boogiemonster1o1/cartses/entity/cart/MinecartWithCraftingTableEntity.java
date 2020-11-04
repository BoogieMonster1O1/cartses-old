package io.github.boogiemonster1o1.cartses.entity.cart;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.boogiemonster1o1.cartses.networking.EntityPacketUtils;
import io.github.boogiemonster1o1.cartses.screen.MinecartWithCraftingTableScreenHandler;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.network.Packet;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@SuppressWarnings({"EntityConstructor", "CodeBlock2Expr"})
public class MinecartWithCraftingTableEntity extends AbstractMinecartEntity {
	private static final Text DISPLAY_NAME = new TranslatableText("screenhandler.cartses.crafting", new Object[0]);
	public static final Type MINECART_TYPE = ClassTinkerers.getEnum(Type.class, "CRAFTING_TABLE");

	public MinecartWithCraftingTableEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public MinecartWithCraftingTableEntity(EntityType<?> type, World world, double x, double y, double z) {
		super(type, world, x, y, z);
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
	public ActionResult interact(PlayerEntity player, Hand hand) {
		player.openHandledScreen(
				new SimpleNamedScreenHandlerFactory(
						(syncId, inv, player1) -> {
							return new MinecartWithCraftingTableScreenHandler(
									syncId,
									inv,
									ScreenHandlerContext.create(this.world, this.getBlockPos())
							);
						},
						DISPLAY_NAME
				)
		);
		return ActionResult.SUCCESS;
	}
}