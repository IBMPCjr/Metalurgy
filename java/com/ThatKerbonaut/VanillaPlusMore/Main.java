package com.ThatKerbonaut.VanillaPlusMore;

import com.ThatKerbonaut.VanillaPlusMore.Tabs.MetalurgyTab;
import com.ThatKerbonaut.VanillaPlusMore.init.ModRecipes;
import com.ThatKerbonaut.VanillaPlusMore.init.ModSettings;
import com.ThatKerbonaut.VanillaPlusMore.proxy.CommonProxy;
import com.ThatKerbonaut.VanillaPlusMore.util.Reference;
import com.ThatKerbonaut.VanillaPlusMore.util.handlers.GuiHandler;
import com.ThatKerbonaut.VanillaPlusMore.util.handlers.RegistryHandler;
import com.ThatKerbonaut.VanillaPlusMore.world.ModWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main{
	@Instance
	public static Main instance;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	public static final CreativeTabs METALURGY = new MetalurgyTab();
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		ModSettings.changeSettings();
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 5);
	}
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		ModRecipes.init();
		ModSettings.changeBlockSettings();
	}
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
			
	}
	@EventHandler
	public void serverInit(FMLServerStartingEvent event){
		RegistryHandler.serverRegistries(event);
	}
}
