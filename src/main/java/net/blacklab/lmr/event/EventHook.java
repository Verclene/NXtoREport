package net.blacklab.lmr.event;

import littleMaidMobX.LMM_EntityLittleMaid;
import net.blacklab.lmr.LittleMaidReengaged;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHook {
	private int lastInteractTick = 0;
	private Entity lastInteractEntity;

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {
		if (!event.target.worldObj.isRemote && event.target instanceof LMM_EntityLittleMaid) {
			LMM_EntityLittleMaid lMaid = (LMM_EntityLittleMaid) event.target;
			if (!lMaid.isContract()) {
				return;
			}

			if (lastInteractEntity == null) {
				// 1回目
				event.entityPlayer.addChatComponentMessage(new ChatComponentText(
						StatCollector.translateToLocal("net.blacklab.lmr.text.port.caution.0")));
				event.entityPlayer.addChatComponentMessage(new ChatComponentText(
						StatCollector.translateToLocal("net.blacklab.lmr.text.port.caution.1")));
				event.entityPlayer.addChatComponentMessage(new ChatComponentText(
						StatCollector.translateToLocal("net.blacklab.lmr.text.port.caution.2")));
				lastInteractEntity = event.target;
				lastInteractTick = lastInteractEntity.ticksExisted;
			} else if (event.target == lastInteractEntity && event.target.ticksExisted-lastInteractTick < 20*30) {
				// 2回目
				ItemStack stack = new ItemStack(LittleMaidReengaged.maidPorter);

				// 保存用タグ
				NBTTagCompound tagCompound = new NBTTagCompound();

				// メイド経験値
				tagCompound.setFloat(LittleMaidReengaged.DOMAIN + ":EXPERIENCE", lMaid.getMaidExperience());
				// 名前
				if (lMaid.hasCustomName()) {
					tagCompound.setString(LittleMaidReengaged.DOMAIN + ":MAID_NAME", lMaid.getCustomNameTag());
				}
				
				// インベントリ
				NBTTagList inventoryTag = new NBTTagList();
				lMaid.maidInventory.writeToNBT(inventoryTag);
				tagCompound.setTag(LittleMaidReengaged.DOMAIN + ":MAID_INVENTORY", inventoryTag);
				
				// モデル文字列
				tagCompound.setString(LittleMaidReengaged.DOMAIN + ":MAIN_MODEL_NAME" , lMaid.textureModelNameForClient);
				tagCompound.setString(LittleMaidReengaged.DOMAIN + ":ARMOR_MODEL_NAME", lMaid.textureArmorNameForClient);
				
				// メイド色
				tagCompound.setInteger(LittleMaidReengaged.DOMAIN + ":MAID_COLOR", lMaid.getColor());
				
				stack.setTagCompound(tagCompound);
				
				// アイテムを取得させる
				int emptyIndex = event.entityPlayer.inventory.getFirstEmptyStack();
				if (emptyIndex >= 0) {
					event.entityPlayer.inventory.setInventorySlotContents(emptyIndex, stack);
				} else {
					lMaid.entityDropItem(stack, 0);
				}
				lMaid.setDead();
				lastInteractEntity = null;
			}
			event.setCanceled(true);
		}
	}
}
