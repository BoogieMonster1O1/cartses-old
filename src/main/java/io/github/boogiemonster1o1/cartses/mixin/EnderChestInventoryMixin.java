/*
 * Copyright (c) 2020 BoogieMonster1O1
 *
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 * WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
 * AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package io.github.boogiemonster1o1.cartses.mixin;

import io.github.boogiemonster1o1.cartses.duck.ExtendedEnderChestInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;

@Mixin(EnderChestInventory.class)
public class EnderChestInventoryMixin implements ExtendedEnderChestInventory {
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
