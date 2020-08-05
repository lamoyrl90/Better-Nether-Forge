package com.redd90.betternether.data.loot;

import com.redd90.betternether.data.BNLootTableProvider;
import com.redd90.betternether.registry.BNEntities;
import com.redd90.betternether.registry.BNItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;

public class BNEntityLootTables extends BNLootTableProvider {
	
	public BNEntityLootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	protected void addTables() {
		entityLootTables.put(BNEntities.DUST_DEVIL.get(), simpleEntityLootTable("dust_devil", Items.REDSTONE, 0, 2));
		entityLootTables.put(BNEntities.DUSTMITE.get(), simpleEntityLootTable("dust_devil", BNItems.DUSTMITE_FLESH.get(), 0, 1));
		entityLootTables.put(BNEntities.OVERGROWN_SKELETON.get(), overgrownSkeletonLootTable());
	}
		
}
