package net.blacklab.lmr.item;

import java.util.List;

import net.blacklab.lmmnx.entity.littlemaid.exp.ExperienceUtil;
import net.blacklab.lmr.LittleMaidReengaged;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemMaidPorter extends Item {
	public ItemMaidPorter() {
		setMaxStackSize(1);
		setUnlocalizedName(LittleMaidReengaged.DOMAIN + ":maidporter");
	}
	
	@Override
	public boolean isDamageable() {
		return true;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		NBTTagCompound stackTag = stack.getTagCompound();
		if (stackTag != null) {
			String customName = stackTag.getString(LittleMaidReengaged.DOMAIN + ":MAID_NAME");
			float experience = stackTag.getFloat(LittleMaidReengaged.DOMAIN + ":EXPERIENCE");
			
			if (!customName.isEmpty()) {
				tooltip.add("Name: ".concat(customName));
			}
			tooltip.add(String.format("Level: %3d", ExperienceUtil.getLevelFromExp(experience)));
		}
	}
}
