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

package io.github.boogiemonster1o1.cartses.screen.client

import io.github.boogiemonster1o1.cartses.entity.networking.EntityUtils
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry
import net.minecraft.network.PacketByteBuf
import net.minecraft.text.TranslatableText

class NoteBlockScreen(val entitySyncId: Int, val desc: NoteBlockDescription) extends CottonClientScreen(new TranslatableText("cartses.screen.note_block"), desc) {
	override def isPauseScreen: Boolean = false

	override def removed(): Unit = {
		super.removed()
		val buf: PacketByteBuf = new PacketByteBuf(Unpooled.buffer)
		buf.writeVarInt(entitySyncId)
		buf.writeVarInt(desc.slider.getValue)
		buf.writeBoolean(desc.button.getToggle)
		ClientSidePacketRegistry.INSTANCE.sendToServer(EntityUtils.updateScreenC2SId, buf)
	}
}
