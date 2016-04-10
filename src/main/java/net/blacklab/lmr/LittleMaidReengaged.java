package net.blacklab.lmr;

import net.blacklab.lmr.event.EventHook;
import net.blacklab.lmr.item.ItemMaidPorter;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = LittleMaidReengaged.DOMAIN, name = LittleMaidReengaged.NAME, version = LittleMaidReengaged.VERSION, dependencies = LittleMaidReengaged.DEPENDENCIES)
public class LittleMaidReengaged {
	public static final String DOMAIN = "lmreengaged";
	public static final String NAME = "LittleMaid Porter NXtoRE";
	public static final String VERSION = "1.0.1";
	public static final String DEPENDENCIES = "required-after:lmmx@[5.2.84,)";

	public static Item maidPorter;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		maidPorter = new ItemMaidPorter();
		GameRegistry.registerItem(maidPorter, "maidporter");
		
		MinecraftForge.EVENT_BUS.register(new EventHook());
	}
}
