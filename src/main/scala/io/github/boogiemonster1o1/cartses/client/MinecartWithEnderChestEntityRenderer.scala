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

package io.github.boogiemonster1o1.cartses.client

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithEnderChestEntity
import net.fabricmc.api.{EnvType, Environment}
import net.minecraft.block.Blocks
import net.minecraft.client.render.entity.{EntityRenderDispatcher, MinecartEntityRenderer}
import net.minecraft.util.math.BlockPos


@Environment(EnvType.CLIENT) class MinecartWithEnderChestEntityRenderer(val entityRenderDispatcher: EntityRenderDispatcher) extends MinecartEntityRenderer[MinecartWithEnderChestEntity](entityRenderDispatcher) {
	override protected def method_27950(entity: MinecartWithEnderChestEntity, blockPos: BlockPos): Int = Math.max(Blocks.ENDER_CHEST.getDefaultState.getLuminance, super.method_27950(entity, blockPos))

	override protected def getBlockLight(entity: MinecartWithEnderChestEntity, blockPos: BlockPos): Int = Math.max(Blocks.ENDER_CHEST.getDefaultState.getLuminance, super.getBlockLight(entity, blockPos))
}
