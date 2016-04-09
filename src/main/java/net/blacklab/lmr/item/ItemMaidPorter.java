package net.blacklab.lmr.item;

import net.minecraft.item.Item;

public class ItemMaidPorter extends Item {
	public ItemMaidPorter() {
		setMaxStackSize(1);
		setUnlocalizedName("maidporter");
	}
	
	@Override
	public boolean isDamageable() {
		return true;
	}
}
