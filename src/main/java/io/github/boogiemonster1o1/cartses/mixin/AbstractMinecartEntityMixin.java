package io.github.boogiemonster1o1.cartses.mixin;

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithBarrelEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithCraftingTableEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithEnderChestEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithGlowstoneEntity;
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithRedstoneLampEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.world.World;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
	@Inject(method = "create", at = @At("TAIL"), cancellable = true)
	private static void returnIt(World world, double x, double y, double z, AbstractMinecartEntity.Type type, CallbackInfoReturnable<AbstractMinecartEntity> cir) {
		if (type == MinecartWithCraftingTableEntity.MINECART_TYPE) {
			cir.setReturnValue(new MinecartWithCraftingTableEntity(ModEntityTypes.MINECART_WITH_CRAFTING_TABLE, world, x, y, z));
		} else if (type == MinecartWithBarrelEntity.MINECART_TYPE) {
			cir.setReturnValue(new MinecartWithBarrelEntity(ModEntityTypes.MINECART_WITH_BARREL, x, y, z, world));
		} else if (type == MinecartWithGlowstoneEntity.MINECART_TYPE) {
			cir.setReturnValue(new MinecartWithGlowstoneEntity(ModEntityTypes.MINECART_WITH_GLOWSTONE, world, x, y, z));
		} else if (type == MinecartWithRedstoneLampEntity.MINECART_TYPE) {
			cir.setReturnValue(new MinecartWithRedstoneLampEntity(ModEntityTypes.MINECART_WITH_REDSTONE_LAMP, world, x, y, z));
		} else if (type == MinecartWithEnderChestEntity.MINECART_TYPE) {
			cir.setReturnValue(new MinecartWithEnderChestEntity(ModEntityTypes.MINECART_WITH_ENDER_CHEST, world, x, y, z));
		}
	}
}
