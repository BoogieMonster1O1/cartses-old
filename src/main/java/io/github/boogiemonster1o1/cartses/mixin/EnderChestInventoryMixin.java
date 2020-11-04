package io.github.boogiemonster1o1.cartses.mixin;

import io.github.boogiemonster1o1.cartses.util.EnderChestInventoryExtended;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;

@Mixin(EnderChestInventory.class)
public class EnderChestInventoryMixin implements EnderChestInventoryExtended {
	@Unique
	public boolean forceCanUse = false;

	@Override
	public void forceCanUse() {
		this.forceCanUse = true;
	}

	@Inject(at = @At("HEAD"), cancellable = true, method = "canPlayerUse")
	public void forceCanUse(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
		if (this.forceCanUse) {
			cir.setReturnValue(true);
		}
	}

	@Inject(at = @At("TAIL"), method = "onClose")
	public void unForceAfterClose(PlayerEntity player, CallbackInfo ci) {
		this.forceCanUse = false;
	}
}
