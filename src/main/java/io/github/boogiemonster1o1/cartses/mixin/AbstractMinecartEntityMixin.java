package io.github.boogiemonster1o1.cartses.mixin;

import io.github.boogiemonster1o1.cartses.ModEntityTypes;
import io.github.boogiemonster1o1.cartses.cart.MinecartWithCraftingTableEntity;
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
		}
	}
}
