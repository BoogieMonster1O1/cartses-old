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
