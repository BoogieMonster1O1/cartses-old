package io.github.boogiemonster1o1.cartses.screen.client

import io.github.boogiemonster1o1.cartses.screen.Note
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import io.github.cottonmc.cotton.gui.widget.{WGridPanel, WLabeledSlider}

class NoteBlockDescription extends LightweightGuiDescription {
	val root: WGridPanel = new WGridPanel()
//	root.setSize(200, 200)
	setRootPanel(root)

	val slider: WLabeledSlider = new WLabeledSlider(0, 24)
	slider.setValue(0)
	slider.setLabel(Note.fSharpLower.title)
	slider.setValueChangeListener((int: Int) => {
		slider.setLabel(Note.byId(int).title)
	})
	root.add(slider, 1, 2)

	root.validate(this)
}
