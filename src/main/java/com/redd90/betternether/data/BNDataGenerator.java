package com.redd90.betternether.data;

import com.redd90.betternether.data.loot.BNBlockLootTables;
import com.redd90.betternether.data.loot.BNEntityLootTables;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNDataGenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		 DataGenerator generator = event.getGenerator();
		 generator.addProvider(new BNRecipes(generator));
		 generator.addProvider(new BNEntityLootTables(generator));
		 generator.addProvider(new BNBlockLootTables(generator));
	}
}
