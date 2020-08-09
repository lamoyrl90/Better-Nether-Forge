package com.redd90.betternether.data.loot;

import com.redd90.betternether.block.RespawnStatueBlock;
import com.redd90.betternether.block.WillowBranchNaturalBlock;
import com.redd90.betternether.data.BNLootTableProvider;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;

public class BNBlockLootTables extends BNLootTableProvider {

	public BNBlockLootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		registerBlockState(BNBlocks.PIG_STATUE_RESPAWNER.get(), BNBlocks.PIG_STATUE_RESPAWNER.get(), RespawnStatueBlock.TOP, false);
				
		registerFortune(BNBlocks.CINCINNASITE_ORE.get(), BNItems.CINCINNASITE.get(), 4.0f, 6.0f);
		registerFortune(BNBlocks.NETHER_RUBY_ORE.get(), BNItems.NETHER_RUBY.get(), 1.0f, 1.0f);
		
		registerSelfDrop(BNBlocks.CINCINNASITE_BLOCK.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_FORGED.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_PILLAR.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_BRICKS.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_BRICK_PLATE.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_STAIRS.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_SLAB.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_BUTTON.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_PLATE.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_LANTERN.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_TILE_LARGE.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_TILE_SMALL.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_CARVED.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_WALL.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_FIRE_BOWL.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_BRICKS_PILLAR.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_BARS.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_PEDESTAL.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_FRAME.get());
		registerSelfDrop(BNBlocks.CINCINNASITE_LANTERN_SMALL.get());

		registerSelfDrop(BNBlocks.BONE_BLOCK.get());
		
		registerSilktouch(BNBlocks.QUARTZ_GLASS.get());
		registerSilktouch(BNBlocks.QUARTZ_GLASS_FRAMED.get());
		
		registerSelfDrop(BNBlocks.SOUL_FARMLAND.get());
		registerDropItem(BNBlocks.GLOWSTONE_TORCH.get(), BNItems.GLOWSTONE_TORCH.get());
		registerDropItem(BNBlocks.GLOWSTONE_WALL_TORCH.get(), BNItems.GLOWSTONE_TORCH.get());
		
		registerSelfDrop(BNBlocks.NETHER_CACTUS.get());
		registerSelfDrop(BNBlocks.BARREL_CACTUS.get());
		registerSilkOrShears(BNBlocks.AGAVE.get());
		
		registerSilkWithAltChance(BNBlocks.NETHERRACK_SPELEOTHEM.get(), Blocks.NETHERRACK.asItem(), 0.75f);
		registerSilkWithAltChance(BNBlocks.GLOWSTONE_SPELEOTHEM.get(), Items.GLOWSTONE_DUST, 0.75f);
		registerSilkWithAltChance(BNBlocks.BASALT_SPELEOTHEM.get(), Blocks.BASALT.asItem(), 0.75f);
		registerSilkWithAltChance(BNBlocks.BLACKSTONE_SPELEOTHEM.get(), Blocks.BLACKSTONE.asItem(), 0.75f);
		registerSilkWithAltChance(BNBlocks.QUARTZ_SPELEOTHEM.get(), Items.QUARTZ, 0.75f);
		
		registerSilktouch(BNBlocks.GEYSER.get());
		registerCrop(BNBlocks.MAGMA_FLOWER.get(),Items.MAGMA_CREAM,3,2,1);
		registerShears(BNBlocks.GOLDEN_VINE.get());
		registerShears(BNBlocks.BLACK_VINE.get());
		
		registerSelfDrop(BNBlocks.OBSIDIAN_BRICKS.get());
		registerSelfDrop(BNBlocks.OBSIDIAN_BRICKS_STAIRS.get());
		registerSelfDrop(BNBlocks.OBSIDIAN_BRICKS_SLAB.get());
		registerSelfDrop(BNBlocks.OBSIDIAN_TILE.get());
		registerSelfDrop(BNBlocks.OBSIDIAN_TILE_SMALL.get());
		registerSelfDrop(BNBlocks.OBSIDIAN_TILE_STAIRS.get());
		registerSelfDrop(BNBlocks.OBSIDIAN_TILE_SLAB.get());

		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_BRICKS.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_BRICKS_STAIRS.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_BRICKS_SLAB.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_TILE.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_TILE_SMALL.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_TILE_STAIRS.get());
		registerSelfDrop(BNBlocks.BLUE_OBSIDIAN_TILE_SLAB.get());
		
		registerSilktouch(BNBlocks.OBSIDIAN_GLASS.get());
		registerSilktouch(BNBlocks.OBSIDIAN_GLASS_PANE.get());
		registerSilktouch(BNBlocks.BLUE_OBSIDIAN_GLASS.get());
		registerSilktouch(BNBlocks.BLUE_OBSIDIAN_GLASS_PANE.get());
		
		registerSilkWithAltChance(BNBlocks.NETHERRACK_MOSS.get(), Blocks.NETHERRACK.asItem(), 1.0f);
		registerSilkWithAltChance(BNBlocks.NETHER_MYCELIUM.get(), Blocks.NETHERRACK.asItem(), 1.0f);
		registerSelfDrop(BNBlocks.WILLOW_FRUIT.get());
		registerWillowBranch(BNBlocks.WILLOW_BRANCH_NATURAL.get(), BNBlocks.WILLOW_BRANCH.get(), BNBlocks.WILLOW_FRUIT.get(), WillowBranchNaturalBlock.SHAPE, WillowBranchNaturalBlock.WillowBranchShape.MIDDLE, WillowBranchNaturalBlock.WillowBranchShape.END);
		registerShears(BNBlocks.WILLOW_BRANCH.get());
		registerDropItem(BNBlocks.WILLOW_TRUNK.get(), BNBlocks.WILLOW_LOG.get().asItem());
		
		registerSelfDrop(BNBlocks.NETHER_REED.get());
		registerSilkOrShears(BNBlocks.FEATHER_FERN.get());
		registerSilkOrShears(BNBlocks.SOUL_VEIN.get());
		registerSelfDrop(BNBlocks.SMOKER.get());
		registerSilkOrShears(BNBlocks.BLACK_BUSH.get());
		registerShears(BNBlocks.SWAMP_GRASS.get());
		registerSelfDrop(BNBlocks.WALL_MUSHROOM_BROWN.get());
		registerSelfDrop(BNBlocks.WALL_MUSHROOM_RED.get());
		registerShears(BNBlocks.WALL_MOSS.get());
		
		registerSelfDrop(BNBlocks.WILLOW_LOG.get());
		registerSelfDrop(BNBlocks.WILLOW_BARK.get());
		registerSelfDrop(BNBlocks.STRIPPED_LOG_WILLOW.get());
		registerSelfDrop(BNBlocks.STRIPPED_BARK_WILLOW.get());
		registerSelfDrop(BNBlocks.WILLOW_PLANKS.get());
		registerSelfDrop(BNBlocks.WILLOW_STAIRS.get());
		registerSelfDrop(BNBlocks.WILLOW_SLAB.get());
		registerSelfDrop(BNBlocks.WILLOW_FENCE.get());
		registerSelfDrop(BNBlocks.WILLOW_GATE.get());
		registerSelfDrop(BNBlocks.WILLOW_BUTTON.get());
		registerSelfDrop(BNBlocks.WILLOW_PLATE.get());
		registerSelfDrop(BNBlocks.WILLOW_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.WILLOW_DOOR.get());
		registerLeaves(BNBlocks.WILLOW_LEAVES.get(), BNBlocks.WILLOW_SAPLING.get(), .1f, .133f, .166f, .2f);

		
		registerSelfDrop(BNBlocks.MUSHROOM_STEM.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_STEM.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_SAPLING.get());
		registerSelfDrop(BNBlocks.GIANT_MOLD_SAPLING.get());
		
		registerCrop(BNBlocks.ORANGE_MUSHROOM.get(), BNBlocks.ORANGE_MUSHROOM.get(), 3, 2, 1);
		
		registerShears(BNBlocks.RED_MOLD.get());
		registerShears(BNBlocks.GRAY_MOLD.get());
		
		registerDropItem(BNBlocks.LUCIS_MUSHROOM.get(), BNBlocks.LUCIS_SPORE.get());
		registerSelfDrop(BNBlocks.LUCIS_SPORE.get());
		
		registerCrop(BNBlocks.INK_BUSH.get(), Items.INK_SAC, 3, 3, 1);
		registerSelfDrop(BNBlocks.INK_BUSH_SEED.get());
		registerShears(BNBlocks.NETHER_GRASS.get());
		
		registerSelfDrop(BNBlocks.MUSHROOM_PLANKS.get());
		registerSelfDrop(BNBlocks.MUSHROOM_STAIRS.get());
		registerSelfDrop(BNBlocks.MUSHROOM_SLAB.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FENCE.get());
		registerSelfDrop(BNBlocks.MUSHROOM_GATE.get());
		registerSelfDrop(BNBlocks.MUSHROOM_BUTTON.get());
		registerSelfDrop(BNBlocks.MUSHROOM_PLATE.get());
		registerSelfDrop(BNBlocks.MUSHROOM_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.MUSHROOM_DOOR.get());
		
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_LOG.get());
		registerSelfDrop(BNBlocks.STRIPPED_LOG_MUSHROOM_FIR.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_PLANKS.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_STAIRS.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_SLAB.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_FENCE.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_GATE.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_BUTTON.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_PLATE.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.MUSHROOM_FIR_DOOR.get());

		registerSelfDrop(BNBlocks.WART_SEED.get());
		registerSelfDrop(BNBlocks.WART_LOG.get());
		registerSelfDrop(BNBlocks.WART_BARK.get());
		registerSelfDrop(BNBlocks.STRIPPED_LOG_WART.get());
		registerSelfDrop(BNBlocks.STRIPPED_BARK_WART.get());
		registerSelfDrop(BNBlocks.WART_PLANKS.get());
		registerSelfDrop(BNBlocks.WART_STAIRS.get());
		registerSelfDrop(BNBlocks.WART_SLAB.get());
		registerSelfDrop(BNBlocks.WART_FENCE.get());
		registerSelfDrop(BNBlocks.WART_GATE.get());
		registerSelfDrop(BNBlocks.WART_BUTTON.get());
		registerSelfDrop(BNBlocks.WART_PLATE.get());
		registerSelfDrop(BNBlocks.WART_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.WART_DOOR.get());
		
		registerCrop(BNBlocks.BLACK_APPLE.get(), BNItems.BLACK_APPLE.get(), 3, 1, 1);
		registerSelfDrop(BNBlocks.BLACK_APPLE_SEED.get());
		
		registerDropItemWithSecondaryChance(BNBlocks.STALAGNATE.get(), BNBlocks.STALAGNATE_STEM.get(), BNBlocks.STALAGNATE_SEED.get(), 0.08333f);
		registerSelfDrop(BNBlocks.STALAGNATE_STEM.get());
		registerSelfDrop(BNBlocks.STALAGNATE_SEED.get());
		registerSelfDrop(BNBlocks.STALAGNATE_LOG.get());
		registerSelfDrop(BNBlocks.STALAGNATE_BARK.get());
		registerSelfDrop(BNBlocks.STRIPPED_LOG_STALAGNATE.get());
		registerSelfDrop(BNBlocks.STRIPPED_BARK_STALAGNATE.get());
		registerSelfDrop(BNBlocks.STALAGNATE_PLANKS.get());
		registerSelfDrop(BNBlocks.STALAGNATE_STAIRS.get());
		registerSelfDrop(BNBlocks.STALAGNATE_SLAB.get());
		registerSelfDrop(BNBlocks.STALAGNATE_FENCE.get());
		registerSelfDrop(BNBlocks.STALAGNATE_GATE.get());
		registerSelfDrop(BNBlocks.STALAGNATE_BUTTON.get());
		registerSelfDrop(BNBlocks.STALAGNATE_PLATE.get());
		registerSelfDrop(BNBlocks.STALAGNATE_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.STALAGNATE_DOOR.get());
		
		registerLeaves(BNBlocks.RUBEUS_LEAVES.get(), BNBlocks.RUBEUS_SAPLING.get(), .05f, .0666f, .08333f, .1f);
		registerSelfDrop(BNBlocks.RUBEUS_CONE.get());
		registerSelfDrop(BNBlocks.RUBEUS_LOG.get());
		registerSelfDrop(BNBlocks.RUBEUS_BARK.get());
		registerSelfDrop(BNBlocks.RUBEUS_LOG_HALF_STRIPPED.get());
		registerSelfDrop(BNBlocks.RUBEUS_BARK_HALF_STRIPPED.get());
		registerSelfDrop(BNBlocks.STRIPPED_LOG_RUBEUS.get());
		registerSelfDrop(BNBlocks.STRIPPED_BARK_RUBEUS.get());
		registerSelfDrop(BNBlocks.RUBEUS_PLANKS.get());
		registerSelfDrop(BNBlocks.RUBEUS_STAIRS.get());
		registerSelfDrop(BNBlocks.RUBEUS_SLAB.get());
		registerSelfDrop(BNBlocks.RUBEUS_FENCE.get());
		registerSelfDrop(BNBlocks.RUBEUS_GATE.get());
		registerSelfDrop(BNBlocks.RUBEUS_BUTTON.get());
		registerSelfDrop(BNBlocks.RUBEUS_PLATE.get());
		registerSelfDrop(BNBlocks.RUBEUS_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.RUBEUS_DOOR.get());
		
		registerShears(BNBlocks.EGG_PLANT.get());
		registerShears(BNBlocks.BLOOMING_VINE.get());
		registerShears(BNBlocks.JUNGLE_MOSS.get());
		registerShears(BNBlocks.JUNGLE_PLANT.get());
		registerSilkWithAltChance(BNBlocks.JUNGLE_GRASS.get(), Blocks.NETHERRACK.asItem(), 1.0f);
		
		registerDropItem(BNBlocks.JELLYFISH_MUSHROOM.get(), BNBlocks.JELLYFISH_MUSHROOM_SAPLING.get());
		registerDropItem(BNBlocks.LUMABUS_VINE.get(), BNBlocks.LUMABUS_SEED.get());
		
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_CUT.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_SMOOTH.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_CHISELED.get());
		
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_STAIRS.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_CUT_STAIRS.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_SMOOTH_STAIRS.get());
		
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_SLAB.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_CUT_SLAB.get());
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_SMOOTH_SLAB.get());
		
		registerSelfDrop(BNBlocks.SOUL_SANDSTONE_WALL.get());
		
		registerSelfDrop(BNBlocks.BASALT_BRICKS.get());
		registerSelfDrop(BNBlocks.BASALT_BRICKS_STAIRS.get());
		registerSelfDrop(BNBlocks.BASALT_BRICKS_SLAB.get());
		registerSelfDrop(BNBlocks.BASALT_BRICKS_WALL.get());
		
		registerSelfDrop(BNBlocks.ROOF_TILE_REEDS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_REEDS_STAIRS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_REEDS_SLAB.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_WART.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_WART_STAIRS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_WART_SLAB.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_CINCINNASITE.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_CINCINNASITE_STAIRS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_CINCINNASITE_SLAB.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_WILLOW.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_WILLOW_STAIRS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_WILLOW_SLAB.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_NETHER_BRICKS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_NETHER_BRICKS_STAIRS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_NETHER_BRICKS_SLAB.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_STALAGNATE.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_STALAGNATE_STAIRS.get());
		registerSelfDrop(BNBlocks.ROOF_TILE_STALAGNATE_SLAB.get());
		
		registerSelfDrop(BNBlocks.NETHER_BRICK_TILE_LARGE.get());
		registerSelfDrop(BNBlocks.NETHER_BRICK_TILE_SMALL.get());
		registerSelfDrop(BNBlocks.NETHER_BRICK_TILE_STAIRS.get());
		registerSelfDrop(BNBlocks.NETHER_BRICK_TILE_SLAB.get());
		registerSelfDrop(BNBlocks.NETHER_BRICK_WALL.get());
		
		registerSelfDrop(BNBlocks.BRICK_POT.get());
		
		registerSelfDrop(BNBlocks.REEDS_BLOCK.get());
		registerSelfDrop(BNBlocks.REEDS_STAIRS.get());
		registerSelfDrop(BNBlocks.REEDS_SLAB.get());
		registerSelfDrop(BNBlocks.REEDS_FENCE.get());
		registerSelfDrop(BNBlocks.REEDS_GATE.get());
		registerSelfDrop(BNBlocks.REEDS_BUTTON.get());
		registerSelfDrop(BNBlocks.REEDS_PLATE.get());
		registerSelfDrop(BNBlocks.REEDS_TRAPDOOR.get());
		registerSelfDrop(BNBlocks.REEDS_DOOR.get());
		
		registerSelfDrop(BNBlocks.SOUL_LILY_SAPLING.get());
		registerDropItem(BNBlocks.SOUL_LILY.get(), BNBlocks.SOUL_LILY_SAPLING.get());
		
		
		blockLootTables.put(BNBlocks.RED_LARGE_MUSHROOM.get(), redMediumMushroom());
		blockLootTables.put(BNBlocks.BROWN_LARGE_MUSHROOM.get(), brownMediumMushroom());
		blockLootTables.put(BNBlocks.MUSHROOM_FIR.get(), mushroomFir());
		blockLootTables.put(BNBlocks.GIANT_MOLD.get(), giantMold());
		blockLootTables.put(BNBlocks.EYEBALL.get(), eyeball());
		blockLootTables.put(BNBlocks.EYEBALL_SMALL.get(), eyeball());
	}
	
	private void registerBlockState(Block block, Block item, BooleanProperty property, boolean value) {
		blockLootTables.put(block, blockState(block.getRegistryName().getPath(), block, item, property, value));
	}



	private void registerSelfDrop(Block block) {
		blockLootTables.put(block, dropItself(block.getRegistryName().getPath(), block));
	}
	
	private void registerDropItemWithSecondaryChance(Block block, IItemProvider item, IItemProvider secondary, float chance) {
		blockLootTables.put(block, dropItemWithSecondaryChance(block.getRegistryName().getPath(), block, item, secondary, chance));
	}
	
	private void registerFortune(Block block, Item item, float min, float max) {
		blockLootTables.put(block, fortune(block.getRegistryName().getPath(), block, item, min, max));
	}
	
	private void registerSilktouch(Block block) {
		blockLootTables.put(block, silktouch(block.getRegistryName().getPath(), block));
	}
	
	private void registerDropItem(Block block, IItemProvider item) {
		blockLootTables.put(block, dropItem(block.getRegistryName().getPath(), block, item));
	}
	
	private void registerSilkWithAltChance(Block block, Item item, float chance) {
		blockLootTables.put(block, silktouchWithAltChance(block.getRegistryName().getPath(), block, item, chance));
	}
	
	private void registerSilkOrShears(Block block) {
		blockLootTables.put(block, silkOrShears(block.getRegistryName().getPath(), block));
	}
	
	
	private void registerShears(Block block) {
		blockLootTables.put(block, shears(block.getRegistryName().getPath(), block));
	}
	
	private void registerLeaves(Block block, Block sapling, float ... chances) {
		blockLootTables.put(block, leaves(block.getRegistryName().getPath(), block, sapling, chances));
	}
	
	@SuppressWarnings("unused")
	private void registerLeaves(Block block, Block leaves, Block sapling, float ... chances) {
		blockLootTables.put(block, leaves(block.getRegistryName().getPath(), block, leaves, sapling, chances));
	}
	
	private void registerCrop(Block block, IItemProvider crop, int harvestAge, int yield, int seeds) {
		blockLootTables.put(block, crop(block.getRegistryName().getPath(), block, crop, harvestAge, yield, seeds));
	}
	
	@SuppressWarnings("unused")
	private <T extends Comparable<T> & IStringSerializable> void registerBlockState(Block block, IItemProvider item, Property<T> property, T value) {
		blockLootTables.put(block, blockState(block.getRegistryName().getPath(), block, item, property, value));
	}
	
	@SuppressWarnings("unused")
	private <T extends Comparable<T> & IStringSerializable> void registerBlockState(String name, Block block, IItemProvider item, Property<T> property, T value) {
		blockLootTables.put(block, blockState(name, block, item, property, value));
	}
	
	@SuppressWarnings("unused")
	private <T extends Comparable<T> & IStringSerializable> void registerBlockStateCount(Block block, IItemProvider item, int min, int max, Property<T> property, T value) {
		blockLootTables.put(block, blockStateCount(block.getRegistryName().getPath(), block, item, min, max, property, value));
	}
	
	@SuppressWarnings("unused")
	private <T extends Comparable<T> & IStringSerializable> void registerBlockStateCount(String name, Block block, IItemProvider item, int min, int max, Property<T> property, T value) {
		blockLootTables.put(block, blockStateCount(name, block, item, min, max, property, value));
	}
		
	@SuppressWarnings("unused")
	private <T extends Comparable<T> & IStringSerializable> void registerBlockStateWithShears(Block block, Item item, Property<T> property, T value) {
		blockLootTables.put(block, blockState(block.getRegistryName().getPath(), block, item, property, value));
	}
	
	private <T extends Comparable<T> & IStringSerializable> void registerWillowBranch(Block block, IItemProvider branch, IItemProvider fruit, Property<T> property, T branchValue, T fruitValue) {
		blockLootTables.put(block, willowBranch(block.getRegistryName().getPath(), block, branch, fruit, property, branchValue, fruitValue));
	}
}
