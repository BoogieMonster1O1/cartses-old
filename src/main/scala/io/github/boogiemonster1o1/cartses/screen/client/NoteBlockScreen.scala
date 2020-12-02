package io.github.boogiemonster1o1.cartses.screen.client

import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import net.minecraft.text.TranslatableText

class NoteBlockScreen extends CottonClientScreen(new TranslatableText("cartses.screen.note_block"), null) {
	override def isPauseScreen: Boolean = false

	override def removed(): Unit = {
		super.removed()
	}
}
