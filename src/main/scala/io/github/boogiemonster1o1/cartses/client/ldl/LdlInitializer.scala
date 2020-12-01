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

package io.github.boogiemonster1o1.cartses.client.ldl

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes
import io.github.boogiemonster1o1.cartses.entity.cart.{MinecartWithEnderChestEntity, MinecartWithGlowstoneEntity, MinecartWithRedstoneLampEntity}
import me.lambdaurora.lambdynlights.api.{DynamicLightHandlers, DynamicLightsInitializer}
import net.minecraft.block.{Blocks, RedstoneLampBlock}

object LdlInitializer extends DynamicLightsInitializer {
	override def onInitializeDynamicLights(): Unit = {
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.minecartWithGlowstone, (_: MinecartWithGlowstoneEntity) => Blocks.GLOWSTONE.getDefaultState.getLuminance)
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.minecartWithEnderChest, (_: MinecartWithEnderChestEntity) => Blocks.ENDER_CHEST.getDefaultState.getLuminance)
		DynamicLightHandlers.registerDynamicLightHandler(ModEntityTypes.minecartWithRedstoneLamp, (entity: MinecartWithRedstoneLampEntity) => {
			if (entity.isLit) Blocks.REDSTONE_LAMP.getDefaultState.`with`[java.lang.Boolean, java.lang.Boolean](RedstoneLampBlock.LIT, true).getLuminance
			else 0
		})
	}
}
