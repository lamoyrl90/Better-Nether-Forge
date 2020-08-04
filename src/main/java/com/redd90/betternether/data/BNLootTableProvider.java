package com.redd90.betternether.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redd90.betternether.BetterNether;
import com.redd90.betternether.block.BNPlantBlock;
import com.redd90.betternether.block.BrownMushroomCapBlock;
import com.redd90.betternether.block.BrownMushroomCapBlock.BrownMushroomShape;
import com.redd90.betternether.block.GiantMoldBlock;
import com.redd90.betternether.block.MushroomFirBlock;
import com.redd90.betternether.block.MushroomFirBlock.MushroomFirShape;
import com.redd90.betternether.block.RedMushroomCapBlock;
import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.data.loot.BNLootCondition;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.Property;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public abstract class BNLootTableProvider extends LootTableProvider {

	private static final Logger LOGGER = BetterNether.LOGGER;
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	protected final Map<EntityType<?>, LootTable.Builder> entityLootTables = new HashMap<>();
	protected final Map<Block, LootTable.Builder> blockLootTables = new HashMap<>();
		
	private final DataGenerator dataGenerator;
	
	public BNLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
		this.dataGenerator = dataGeneratorIn;
	}

	protected abstract void addTables();
	
	public void act(DirectoryCache cache) {
        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<EntityType<?>, LootTable.Builder> entry : entityLootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        for (Entry<Block, Builder> entry : blockLootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
	}

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.dataGenerator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }
	
    //Entity methods
    
	protected LootTable.Builder simpleEntityLootTable(String name, IItemProvider item, float min, float max) {
		LootPool.Builder builder = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item.asItem())
					.acceptFunction(SetCount.builder(RandomValueRange.of(min, max)))
					.acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(min, max))));
		return LootTable.builder().addLootPool(builder);
	}

	// Block methods
	
	protected LootTable.Builder dropItself(String name, Block block) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem()));
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder fortune(String name, Block block, Item item, float min, float max) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.SILK_TOUCH)
						.alternatively(ItemLootEntry.builder(item)
								.acceptFunction(BNLootCondition.randomCount(min, max))
								.acceptFunction(BNLootCondition.FORTUNE)));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder silktouch(String name, Block block) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.SILK_TOUCH));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder silktouchWithAltChance(String name, Block block, Item item, float chance) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.SILK_TOUCH)
						.alternatively(ItemLootEntry.builder(item)
						.acceptCondition(RandomChance.builder(chance))));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder dropItem(String name, Block block, IItemProvider item) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item));
		return LootTable.builder().addLootPool(pool1);
	}

	protected LootTable.Builder silkOrShears(String name, Block block) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.SILK_OR_SHEARS));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder shears(String name, Block block) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.SHEARS));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder shears(String name, Block block, Item item) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item.asItem())
						.acceptCondition(BNLootCondition.SHEARS));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected <T extends Comparable<T> & IStringSerializable> LootTable.Builder blockState(String name, Block block, IItemProvider item, Property<T> property, T value) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item.asItem())
						.acceptCondition(BNLootCondition.blockState(block, property, value)));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected <T extends Comparable<T> & IStringSerializable> LootTable.Builder blockStateCount(String name, Block block, IItemProvider item, int min, int max, Property<T> property, T value) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item.asItem())
						.acceptCondition(BNLootCondition.blockState(block, property, value))
						.acceptFunction(BNLootCondition.randomCount(min, max)));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected <T extends Comparable<T> & IStringSerializable> LootTable.Builder blockStateAndShears(String name, Block block, Item item, Property<T> property, T value) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item.asItem())
						.acceptCondition(BNLootCondition.blockState(block, property, value))
						.acceptCondition(BNLootCondition.SHEARS));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected <T extends Comparable<T> & IStringSerializable> LootTable.Builder willowBranch(String name, Block block, IItemProvider branchItem, IItemProvider fruitItem, Property<T> property, T branchValue, T fruitValue) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(branchItem.asItem())
						.acceptCondition(BNLootCondition.blockState(block, property, branchValue))
						.acceptCondition(BNLootCondition.SHEARS)
						.alternatively(ItemLootEntry.builder(fruitItem.asItem())
								.acceptCondition(BNLootCondition.blockState(block, property, fruitValue))));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder leaves(String name, Block block, Block sapling, float ... chances) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.SILK_OR_SHEARS)
						.alternatively(ItemLootEntry.builder(sapling.asItem())
								.acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances))));
		LootPool.Builder pool2 = LootPool.builder()
				.name("pool2")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.STICK)
						.acceptCondition(BNLootCondition.NOT_SILK_OR_SHEARS)
						.acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F)))
						.acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F)));
		
		return LootTable.builder().addLootPool(pool1).addLootPool(pool2);
	}
	
	protected LootTable.Builder leaves(String name, Block block, Block leaves, Block sapling, float ... chances) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(leaves.asItem())
						.acceptCondition(BNLootCondition.SILK_OR_SHEARS)
						.alternatively(ItemLootEntry.builder(sapling.asItem())
								.acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances))));
		LootPool.Builder pool2 = LootPool.builder()
				.name("pool2")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.STICK)
						.acceptCondition(BNLootCondition.NOT_SILK_OR_SHEARS)
						.acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F)))
						.acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F)));
		
		return LootTable.builder().addLootPool(pool1).addLootPool(pool2);
	}
	
	protected LootTable.Builder crop(String name, Block block, IItemProvider crop, int harvestAge, int yield, int seeds) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem())
						.acceptCondition(BNLootCondition.integerProperty(block, BNPlantBlock.AGE, harvestAge))
						.acceptFunction(SetCount.builder(RandomValueRange.of(1, 1+seeds))))
				.addEntry(ItemLootEntry.builder(crop.asItem())
						.acceptCondition(BNLootCondition.integerProperty(block, BNPlantBlock.AGE, harvestAge))
						.acceptFunction(SetCount.builder(RandomValueRange.of(1, 1+yield))));
		LootPool.Builder pool2 = LootPool.builder()
				.name("pool2")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block.asItem()));
						

		return LootTable.builder().addLootPool(pool1).addLootPool(pool2);
	}
	
	protected LootTable.Builder mushroomFir() {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(BNBlocks.MUSHROOM_FIR_STEM.get().asItem())
						.acceptCondition(BNLootCondition.blockState(BNBlocks.MUSHROOM_FIR.get(), MushroomFirBlock.SHAPE, MushroomFirShape.BOTTOM))
						.alternatively(ItemLootEntry.builder(BNBlocks.MUSHROOM_FIR_STEM.get().asItem())
								.acceptCondition(BNLootCondition.blockState(BNBlocks.MUSHROOM_FIR.get(), MushroomFirBlock.SHAPE, MushroomFirShape.MIDDLE))
								.alternatively(ItemLootEntry.builder(BNBlocks.MUSHROOM_FIR_SAPLING.get().asItem())
										.acceptFunction(BNLootCondition.randomCount(0, 1)))));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder redMediumMushroom() {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(BNBlocks.MUSHROOM_STEM.get().asItem())
						.acceptCondition(BNLootCondition.blockState(BNBlocks.RED_LARGE_MUSHROOM.get(), RedMushroomCapBlock.SHAPE, TripleShape.BOTTOM))
						.alternatively(ItemLootEntry.builder(BNBlocks.MUSHROOM_STEM.get().asItem())
								.acceptCondition(BNLootCondition.blockState(BNBlocks.RED_LARGE_MUSHROOM.get(), RedMushroomCapBlock.SHAPE, TripleShape.MIDDLE))
								.alternatively(ItemLootEntry.builder(Blocks.RED_MUSHROOM.asItem())
										.acceptFunction(BNLootCondition.randomCount(0, 4)))));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder brownMediumMushroom() {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(BNBlocks.MUSHROOM_STEM.get().asItem())
						.acceptCondition(BNLootCondition.blockState(BNBlocks.BROWN_LARGE_MUSHROOM.get(), BrownMushroomCapBlock.SHAPE, BrownMushroomShape.BOTTOM))
						.alternatively(ItemLootEntry.builder(BNBlocks.MUSHROOM_STEM.get().asItem())
								.acceptCondition(BNLootCondition.blockState(BNBlocks.BROWN_LARGE_MUSHROOM.get(), BrownMushroomCapBlock.SHAPE, BrownMushroomShape.MIDDLE))
								.alternatively(ItemLootEntry.builder(Blocks.BROWN_MUSHROOM.asItem())
										.acceptFunction(BNLootCondition.randomCount(0, 1)))));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder giantMold() {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(BNBlocks.MUSHROOM_STEM.get().asItem())
						.acceptCondition(BNLootCondition.blockState(BNBlocks.GIANT_MOLD.get(), GiantMoldBlock.SHAPE, TripleShape.BOTTOM))
						.alternatively(ItemLootEntry.builder(BNBlocks.MUSHROOM_STEM.get().asItem())
								.acceptCondition(BNLootCondition.blockState(BNBlocks.GIANT_MOLD.get(), GiantMoldBlock.SHAPE, TripleShape.MIDDLE))
								.alternatively(ItemLootEntry.builder(BNBlocks.GIANT_MOLD_SAPLING.get().asItem())
										.acceptFunction(BNLootCondition.randomCount(0, 4)))));
		
		return LootTable.builder().addLootPool(pool1);
	}
	
	protected LootTable.Builder dropItemWithSecondaryChance(String name, Block block, IItemProvider item, IItemProvider secondary, float secondaryChance) {
		LootPool.Builder pool1 = LootPool.builder()
				.name("pool1")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(item));
		LootPool.Builder pool2 = LootPool.builder()
				.name("pool2")
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(secondary)
						.acceptCondition(BNLootCondition.chance(secondaryChance)));
		return LootTable.builder().addLootPool(pool1).addLootPool(pool2);
	}
	
	@Override
	public String getName() {
		return "Better Nether LootTables";
	}
	
}
