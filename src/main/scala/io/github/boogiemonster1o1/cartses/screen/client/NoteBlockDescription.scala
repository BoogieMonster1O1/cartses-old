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
