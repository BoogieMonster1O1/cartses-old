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

package io.github.boogiemonster1o1.cartses

import io.github.boogiemonster1o1.cartses.entity.ModEntityTypes
import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithNoteBlockEntity
import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import io.github.boogiemonster1o1.cartses.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.network.{PacketContext, ServerSidePacketRegistry}
import net.minecraft.network.PacketByteBuf
import org.apache.logging.log4j.{Level, LogManager, Logger}

object Cartses extends ModInitializer {
	override def onInitialize(): Unit = {
		log(Level.INFO, "Initializing Cartses")
		ModEntityTypes.init()
		ModItems.init()
		ServerSidePacketRegistry.INSTANCE.register(EntityUtils.updateScreenC2SId, (ctx: PacketContext, buf: PacketByteBuf) => {
			val entitySyncId: Int = buf.readVarInt()
			val note: Int = buf.readVarInt()
			val repeat: Boolean = buf.readBoolean()
			ctx.getTaskQueue.execute(() => {
				val entity: MinecartWithNoteBlockEntity = ctx.getPlayer.world.getEntityById(entitySyncId).asInstanceOf[MinecartWithNoteBlockEntity]
				entity.setNote(note)
				entity.setRepeat(repeat)
			})
		})
	}

	val modId = "cartses"
	val logger: Logger = LogManager.getLogger

	def log(level: Level, message: String): Unit = {
		logger.log(level, "[Cartses] " + message)
	}
}
