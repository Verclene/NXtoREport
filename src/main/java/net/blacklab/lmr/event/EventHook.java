package net.blacklab.lmr.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHook {
	private int lastInteractTick = 0;
	private Entity lastInteractEntity;

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {

	}
}
