package net.blacklab.lmr.item;

import net.blacklab.lmr.LittleMaidReengaged;
import net.minecraft.item.Item;

public class ItemMaidPorter extends Item {
	public ItemMaidPorter() {
		setMaxStackSize(1);
		setUnlocalizedName(LittleMaidReengaged.DOMAIN + ":maidporter");
	}
	
	@Override
	public boolean isDamageable() {
		return true;
	}
}
