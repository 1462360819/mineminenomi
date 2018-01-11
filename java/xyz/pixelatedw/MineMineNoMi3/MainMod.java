package xyz.pixelatedw.MineMineNoMi3;

import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandAbility;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandBelly;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandBounty;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandDoriki;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandExtol;
import xyz.pixelatedw.MineMineNoMi3.gui.GUIHandler;
import xyz.pixelatedw.MineMineNoMi3.lists.ListCreativeTabs;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;
import xyz.pixelatedw.MineMineNoMi3.lists.ListEntities;
import xyz.pixelatedw.MineMineNoMi3.lists.ListForge;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.lists.ListPackets;
import xyz.pixelatedw.MineMineNoMi3.proxy.CommonProxy;

@Mod(modid = ID.PROJECT_ID, name = ID.PROJECT_NAME, version = ID.PROJECT_VERSION)
public class MainMod 
{
	@Instance(ID.PROJECT_ID)
	private static MainMod instance;	
	@SidedProxy(clientSide = "xyz.pixelatedw.MineMineNoMi3.proxy.ClientProxy", serverSide = "xyz.pixelatedw.MineMineNoMi3.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static SimpleNetworkWrapper dispatcher;
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{		
		dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("mineminenomi");
		MainConfig.init(event.getSuggestedConfigurationFile());
		
		ListPackets.init();
		ListEntities.init();
		
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{ 		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		GameRegistry.registerWorldGenerator(new MainWorldGen(), 1);	
		
		ListDevilFruits.init(); 
		ListMisc.init();
		ListCreativeTabs.init();
		ListForge.init();
		
		proxy.init();
		
		//if(WyDebug.isDebug())
		//	WyHelper.generateLangFiles();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {}	
	
	@EventHandler
	public void serverInit(FMLServerStartingEvent event)
	{
		if(WyDebug.isDebug())
			event.registerServerCommand(new CommandAbility());
		event.registerServerCommand(new CommandDoriki());
		event.registerServerCommand(new CommandBelly());
		event.registerServerCommand(new CommandBounty());
		event.registerServerCommand(new CommandExtol());
	}
	
	public static MainMod getMineMineNoMi() {return instance;}
	public String getModId() { return "mineminenomi"; }
}
