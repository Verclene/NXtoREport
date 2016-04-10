package net.blacklab.lmr;

import net.blacklab.lmr.event.EventHook;
import net.blacklab.lmr.item.ItemMaidPorter;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
		modid = LittleMaidReengaged.DOMAIN,
		name = LittleMaidReengaged.NAME,
		version = LittleMaidReengaged.VERSION,
		dependencies = LittleMaidReengaged.DEPENDENCIES,
		acceptedMinecraftVersions = LittleMaidReengaged.ACCEPTED_MCVERSIONS)
public class LittleMaidReengaged {
	public static final String DOMAIN = "lmreengaged";
	public static final String NAME = "LittleMaid Porter NXtoRE";
	public static final String VERSION = "1.0.8";
	public static final String DEPENDENCIES = "required-after:lmmx@[5.2.84,)";
	public static final String ACCEPTED_MCVERSIONS = "[1.8,1.8.9]";

	public static Item maidPorter;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Register item
		maidPorter = new ItemMaidPorter();
		GameRegistry.registerItem(maidPorter, "maidporter");
		// Register model
		if (event.getSide().isClient()) {
			ModelLoader.addVariantName(maidPorter, DOMAIN + ":maidporter_0", DOMAIN + ":maidporter_1");
			ModelLoader.setCustomModelResourceLocation(maidPorter, 0, new ModelResourceLocation(DOMAIN + ":maidporter_0", "inventory"));
			ModelLoader.setCustomModelResourceLocation(maidPorter, 1, new ModelResourceLocation(DOMAIN + ":maidporter_1", "inventory"));
		}
		
		MinecraftForge.EVENT_BUS.register(new EventHook());
	}
}
