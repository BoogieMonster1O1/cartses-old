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

package io.github.boogiemonster1o1.cartses;

import com.chocohead.mm.api.ClassTinkerers;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

// Doesn't work in scala :(
public class CartsesEarlyRiser implements Runnable {
	@Override
	public void run() {
		MappingResolver mappingResolver = FabricLoader.getInstance().getMappingResolver();
		String minecartTypeName = mappingResolver.mapClassName("intermediary", "net.minecraft.class_1688$class_1689");
		ClassTinkerers.enumBuilder(minecartTypeName, new String[0]).addEnum("CRAFTING_TABLE").addEnum("GLOWSTONE").addEnum("REDSTONE_LAMP").addEnum("BARREL").addEnum("ENDER_CHEST").addEnum("NOTE_BLOCK").build();
	}
}
