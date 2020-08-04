package com.redd90.betternether.item;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BNItemGroup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("betternether") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BNBlocks.NETHER_CACTUS.get());
        }
    };
	
}
