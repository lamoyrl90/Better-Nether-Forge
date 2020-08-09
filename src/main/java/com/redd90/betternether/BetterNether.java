package com.redd90.betternether;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.redd90.betternether.registry.BNBiomes;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNCarvers;
import com.redd90.betternether.registry.BNEntities;
import com.redd90.betternether.registry.BNFeatures;
import com.redd90.betternether.registry.BNItems;
import com.redd90.betternether.registry.BNPlacements;
import com.redd90.betternether.registry.BNStructurePieceTypes;
import com.redd90.betternether.registry.BNTags;

@Mod(BetterNether.MODID)
@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterNether
{
    public static final String MODID = "betternether";

    public static final Logger LOGGER = LogManager.getLogger();

    public BetterNether() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	    	
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        
        BNConfig.registerConfig();
        
        BNTags.init();
        
        BNBlocks.BLOCKS.register(modEventBus);
        BNItems.ITEMS.register(modEventBus);
        BNEntities.ENTITIES.register(modEventBus);
        BNPlacements.PLACEMENTS.register(modEventBus);
        BNFeatures.FEATURES.register(modEventBus);
        BNFeatures.STRUCTURES.register(modEventBus);
        BNCarvers.CARVERS.register(modEventBus);
        BNBiomes.BIOMES.register(modEventBus);
        
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
    	BNBlocks.setupRenderLayers();
    	BNEntities.registerRenderers();
    }

	private void commonSetup(final FMLCommonSetupEvent event) {
    	
		BNStructurePieceTypes.init();
    	BNEntities.finalizeEntities();
    	BNBiomes.registerBiomeTypes();
    	BNBiomes.injectBiomeFeatures();
        BNBiomes.runCompat();
    	
    }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
    
}
