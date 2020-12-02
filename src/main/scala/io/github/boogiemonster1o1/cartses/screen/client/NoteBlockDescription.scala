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

import io.github.boogiemonster1o1.cartses.entity.cart.MinecartWithNoteBlockEntity
import io.github.boogiemonster1o1.cartses.screen.Note
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import io.github.cottonmc.cotton.gui.widget.{WGridPanel, WLabeledSlider, WToggleButton}
import net.minecraft.text.TranslatableText

class NoteBlockDescription(entity: MinecartWithNoteBlockEntity) extends LightweightGuiDescription {
	val root: WGridPanel = new WGridPanel()
	val slider: WLabeledSlider = new WLabeledSlider(0, 24)
	val button: WToggleButton = new WToggleButton(new TranslatableText("cartses.screen.note_block.repeat"))
	root.setSize(200, 200)
	setRootPanel(root)
	slider.setValue(entity.getNote)
	slider.setLabel(Note.byId(entity.getNote).title)
	slider.setValueChangeListener((int: Int) => {
		slider.setLabel(Note.byId(int).title)
	})
	root.add(slider, 1, 2)
	root.validate(this)
}
