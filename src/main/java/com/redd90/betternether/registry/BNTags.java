package com.redd90.betternether.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class BNTags {
	
	public static void init() {
		Items.init();
		Blocks.init();
	}
	
	public static class Items {
		public static final ITag.INamedTag<Item> SOUL_GROUND = createTag("soul_ground");
		public static final ITag.INamedTag<Item> WILLOW_LOGS = createTag("willow_logs");
		public static final ITag.INamedTag<Item> MUSHROOM_FIR_LOGS = createTag("mushroom_fir_logs");
		public static final ITag.INamedTag<Item> STALAGNATE_LOGS = createTag("stalagnate_logs");
		public static final ITag.INamedTag<Item> WART_LOGS = createTag("wart_logs");
		
		private static ITag.INamedTag<Item> createTag(String name) {
			return ItemTags.makeWrapperTag("betternether:" + name);
		}
		
		public static void init() {}
	}
	
	public static class Blocks {
		public static final ITag.INamedTag<Block> NETHERRACK = createTag("netherrack");
		public static final ITag.INamedTag<Block> SOUL_GROUND = createTag("soul_ground");
		public static final ITag.INamedTag<Block> NETHER_MYCELIUM = createTag("nether_mycelium");
		public static final ITag.INamedTag<Block> NYLIUM = createTag("nylium");
		public static final ITag.INamedTag<Block> GRAVEL_DESERT_GROUND = createTag("gravel_desert_ground");
		public static final ITag.INamedTag<Block> NETHER_GROUND = createTag("nether_ground");
		public static final ITag.INamedTag<Block> WILLOW_LOGS = createTag("willow_logs");
		public static final ITag.INamedTag<Block> MUSHROOM_FIR_LOGS = createTag("mushroom_fir_logs");
		public static final ITag.INamedTag<Block> WART_LOGS = createTag("wart_logs");
		public static final ITag.INamedTag<Block> STALAGNATE_LOGS = createTag("stalagnate_logs");
		
		
		private static ITag.INamedTag<Block> createTag(String name) {
			return BlockTags.makeWrapperTag("betternether:" + name);
		}
		
		public static void init() {}
	}
}
