package com.redd90.betternether.data;

import java.util.function.Consumer;

import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNItems;
import com.redd90.betternether.registry.BNTags;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

public class BNRecipes extends BNRecipeProvider {

	public BNRecipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    	
    	//Cincinnasite and cincinnasite accessories
    	
    	super.recipe2x2(BNBlocks.CINCINNASITE_BLOCK.get().asItem(), 1, BNItems.CINCINNASITE.get(), consumer);
        super.recipe3x3(BNBlocks.CINCINNASITE_FORGED.get().asItem(), 1, BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.recipe3x3(BNItems.CINCINNASITE_INGOT.get(), 1, BNItems.CINCINNASITE_NUGGET.get().asItem(), consumer);
        super.smeltQuick(BNItems.CINCINNASITE_NUGGET.get(), BNItems.CINCINNASITE.get(), 0.08f, consumer);
        super.smeltOre(BNItems.CINCINNASITE_INGOT.get(), BNBlocks.CINCINNASITE_ORE.get().asItem(), 0.7f, consumer);
        
        super.recipe2x2(Blocks.GLOWSTONE.asItem(), 3, BNBlocks.GLOWSTONE_SPELEOTHEM.get().asItem(), consumer);
        super.recipe2x2(Blocks.BASALT.asItem(), 3, BNBlocks.BASALT_SPELEOTHEM.get().asItem(), consumer);
        super.recipe2x2(Blocks.BLACKSTONE.asItem(), 3, BNBlocks.BLACKSTONE_SPELEOTHEM.get().asItem(), consumer);
        super.recipe2x2(Blocks.QUARTZ_BLOCK.asItem(), 3, BNBlocks.QUARTZ_SPELEOTHEM.get().asItem(), consumer);
        super.columnRecipe(BNBlocks.NETHERRACK_SPELEOTHEM.get().asItem(), 4, Blocks.NETHERRACK.asItem(), consumer);
        super.columnRecipe(BNBlocks.GLOWSTONE_SPELEOTHEM.get().asItem(), 4, Blocks.GLOWSTONE.asItem(), consumer);
        super.columnRecipe(BNBlocks.BASALT_SPELEOTHEM.get().asItem(), 4, Blocks.BASALT.asItem(), consumer);
        super.columnRecipe(BNBlocks.BLACKSTONE_SPELEOTHEM.get().asItem(), 4, Blocks.BLACKSTONE.asItem(), consumer);
        super.columnRecipe(BNBlocks.QUARTZ_SPELEOTHEM.get().asItem(), 4, Blocks.QUARTZ_BLOCK.asItem(), consumer);
        
        super.recipe2x2(BNBlocks.CINCINNASITE_TILE_SMALL.get().asItem(), 4, BNBlocks.CINCINNASITE_TILE_LARGE.get().asItem(), consumer);
        super.pillarRecipe(BNBlocks.CINCINNASITE_TILE_LARGE.get().asItem(), 1, BNBlocks.CINCINNASITE_SLAB.get().asItem(), consumer);
        super.pillarRecipe(BNBlocks.CINCINNASITE_PILLAR.get().asItem(), 2, BNBlocks.CINCINNASITE_BLOCK.get().asItem(), consumer);
        super.pillarRecipe(BNBlocks.CINCINNASITE_PILLAR.get().asItem(), 5, BNBlocks.CINCINNASITE_FORGED.get().asItem(), consumer);
        super.recipe2x2(BNBlocks.CINCINNASITE_CARVED.get().asItem(), 4, BNBlocks.CINCINNASITE_FORGED.get().asItem(), consumer);
        super.pillarRecipe(BNBlocks.CINCINNASITE_BRICKS_PILLAR.get().asItem(), 2, BNBlocks.CINCINNASITE_BRICKS.get().asItem(), consumer);
         
        
        super.cookFood(BNItems.DUSTMITE_JERKY.get(), BNItems.DUSTMITE_FLESH.get(), consumer);
        
        super.pickaxeRecipe(BNItems.CINCINNASITE_PICKAXE.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.axeRecipe(BNItems.CINCINNASITE_AXE.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.shovelRecipe(BNItems.CINCINNASITE_SHOVEL.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.hoeRecipe(BNItems.CINCINNASITE_HOE.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.swordRecipe(BNItems.CINCINNASITE_SWORD.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.pickaxeRecipe(BNItems.NETHER_RUBY_PICKAXE.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.axeRecipe(BNItems.NETHER_RUBY_AXE.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.shovelRecipe(BNItems.NETHER_RUBY_SHOVEL.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.hoeRecipe(BNItems.NETHER_RUBY_HOE.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.swordRecipe(BNItems.NETHER_RUBY_SWORD.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.pickaxeRecipe(BNItems.NETHERRACK_PICKAXE.get(), Blocks.NETHERRACK, consumer);
        super.axeRecipe(BNItems.NETHERRACK_AXE.get(), Blocks.NETHERRACK, consumer);
        super.shovelRecipe(BNItems.NETHERRACK_SHOVEL.get(), Blocks.NETHERRACK, consumer);
        super.hoeRecipe(BNItems.NETHERRACK_HOE.get(), Blocks.NETHERRACK, consumer);
        super.swordRecipe(BNItems.NETHERRACK_SWORD.get(), Blocks.NETHERRACK, consumer);
        
        super.helmetRecipe(BNItems.CINCINNASITE_HELMET.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.chestplateRecipe(BNItems.CINCINNASITE_CHESTPLATE.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.leggingsRecipe(BNItems.CINCINNASITE_LEGGINGS.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.bootsRecipe(BNItems.CINCINNASITE_BOOTS.get(), BNItems.CINCINNASITE_INGOT.get(), consumer);
        super.helmetRecipe(BNItems.NETHER_RUBY_HELMET.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.chestplateRecipe(BNItems.NETHER_RUBY_CHESTPLATE.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.leggingsRecipe(BNItems.NETHER_RUBY_LEGGINGS.get(), BNItems.NETHER_RUBY.get(), consumer);
        super.bootsRecipe(BNItems.NETHER_RUBY_BOOTS.get(), BNItems.NETHER_RUBY.get(), consumer);
        
        super.stairsRecipe(BNBlocks.CINCINNASITE_STAIRS.get(), BNBlocks.CINCINNASITE_BLOCK.get(), consumer);
        super.slabRecipe(BNBlocks.CINCINNASITE_SLAB.get(), 6, BNBlocks.CINCINNASITE_BLOCK.get(), consumer);
        super.plateRecipe(BNBlocks.CINCINNASITE_PLATE.get(), BNBlocks.CINCINNASITE_BLOCK.get(), consumer);
        super.singleItemRecipe(BNBlocks.CINCINNASITE_BUTTON.get(), 1, BNBlocks.CINCINNASITE_BLOCK.get(), consumer);
        super.wallRecipe(BNBlocks.CINCINNASITE_WALL.get(), 6, BNBlocks.CINCINNASITE_BLOCK.get(), consumer);
        
        super.furnace(Items.ORANGE_DYE, BNBlocks.NETHER_CACTUS.get(), .7f, consumer);
        super.furnace(Items.LIGHT_BLUE_DYE, BNBlocks.WILLOW_FRUIT.get(), .7f, consumer);
        super.singleItemRecipe(Items.BLACK_DYE, 1, BNBlocks.BLACK_BUSH.get(), consumer);
        super.singleItemRecipe(Items.RED_MUSHROOM, 1, BNBlocks.WALL_MUSHROOM_RED.get(), consumer);
        super.singleItemRecipe(BNBlocks.WALL_MUSHROOM_RED.get(), 1, Items.RED_MUSHROOM, consumer);
        super.singleItemRecipe(Items.BROWN_MUSHROOM, 1, BNBlocks.WALL_MUSHROOM_BROWN.get(), consumer);
        super.singleItemRecipe(BNBlocks.WALL_MUSHROOM_BROWN.get(), 1, Items.BROWN_MUSHROOM, consumer);
        super.furnace(BNBlocks.QUARTZ_GLASS.get(), Blocks.QUARTZ_BLOCK, .7f, consumer);
        super.furnace(BNBlocks.OBSIDIAN_GLASS.get(), Blocks.OBSIDIAN, .7f, consumer);
        super.furnace(BNBlocks.BLUE_OBSIDIAN_GLASS.get(), BNBlocks.BLUE_OBSIDIAN.get(), .7f, consumer);
        
        super.recipe2x2(BNBlocks.OBSIDIAN_BRICKS.get().asItem(), 4, Blocks.OBSIDIAN, consumer);
        super.recipe2x2(BNBlocks.OBSIDIAN_TILE.get().asItem(), 4, BNBlocks.OBSIDIAN_BRICKS.get(), consumer);
        super.recipe2x2(BNBlocks.OBSIDIAN_TILE_SMALL.get().asItem(), 4, BNBlocks.OBSIDIAN_TILE.get(), consumer);
        super.stairsRecipe(BNBlocks.OBSIDIAN_TILE_STAIRS.get().asItem(), BNBlocks.OBSIDIAN_TILE_SMALL.get(), consumer);
        super.slabRecipe(BNBlocks.OBSIDIAN_TILE_SLAB.get().asItem(), 6, BNBlocks.OBSIDIAN_TILE_SMALL.get(), consumer);

        super.recipe2x2(BNBlocks.BLUE_OBSIDIAN_BRICKS.get().asItem(), 4, BNBlocks.BLUE_OBSIDIAN.get(), consumer);
        super.recipe2x2(BNBlocks.BLUE_OBSIDIAN_TILE.get().asItem(), 4, BNBlocks.BLUE_OBSIDIAN_BRICKS.get(), consumer);
        super.recipe2x2(BNBlocks.BLUE_OBSIDIAN_TILE_SMALL.get().asItem(), 4, BNBlocks.BLUE_OBSIDIAN_TILE.get(), consumer);
        super.stairsRecipe(BNBlocks.BLUE_OBSIDIAN_TILE_STAIRS.get().asItem(), BNBlocks.BLUE_OBSIDIAN_TILE_SMALL.get(), consumer);
        super.slabRecipe(BNBlocks.BLUE_OBSIDIAN_TILE_SLAB.get().asItem(), 6, BNBlocks.BLUE_OBSIDIAN_TILE_SMALL.get(), consumer);
        
        super.wallRecipe(BNBlocks.OBSIDIAN_GLASS_PANE.get(), 16, BNBlocks.OBSIDIAN_GLASS.get(), consumer);
        super.wallRecipe(BNBlocks.BLUE_OBSIDIAN_GLASS_PANE.get(), 16, BNBlocks.BLUE_OBSIDIAN_GLASS.get(), consumer);
        
        super.slabRecipe(Items.PAPER, 3, BNBlocks.NETHER_REED.get(), consumer);
        
        super.singleItemRecipe("willow_planks", BNBlocks.WILLOW_PLANKS.get(), 4, BNTags.Items.WILLOW_LOGS, consumer);
        super.recipe2x2(BNBlocks.WILLOW_BARK.get(), 3, BNBlocks.WILLOW_LOG.get(), consumer);
        super.stairsRecipe(BNBlocks.WILLOW_STAIRS.get().asItem(), BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.slabRecipe(BNBlocks.WILLOW_SLAB.get().asItem(), 6, BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.fenceRecipe(BNBlocks.WILLOW_FENCE.get().asItem(), BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.gateRecipe(BNBlocks.WILLOW_GATE.get().asItem(), BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.plateRecipe(BNBlocks.WILLOW_PLATE.get().asItem(), BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.wallRecipe(BNBlocks.WILLOW_TRAPDOOR.get().asItem(), 2, BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.doorRecipe(BNBlocks.WILLOW_DOOR.get().asItem(), 3, BNBlocks.WILLOW_PLANKS.get(), consumer);
        super.singleItemRecipe(BNBlocks.WILLOW_BUTTON.get(), 4, BNBlocks.WILLOW_PLANKS.get(), consumer);
        
        super.singleItemRecipe("mushroom_planks", BNBlocks.MUSHROOM_PLANKS.get(), 1, BNBlocks.MUSHROOM_STEM.get(), consumer);
        super.stairsRecipe(BNBlocks.MUSHROOM_STAIRS.get().asItem(), BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.slabRecipe(BNBlocks.MUSHROOM_SLAB.get().asItem(), 6, BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.fenceRecipe(BNBlocks.MUSHROOM_FENCE.get().asItem(), BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.gateRecipe(BNBlocks.MUSHROOM_GATE.get().asItem(), BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.plateRecipe(BNBlocks.MUSHROOM_PLATE.get().asItem(), BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.wallRecipe(BNBlocks.MUSHROOM_TRAPDOOR.get().asItem(), 2, BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.doorRecipe(BNBlocks.MUSHROOM_DOOR.get().asItem(), 3, BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        super.singleItemRecipe(BNBlocks.MUSHROOM_BUTTON.get(), 4, BNBlocks.MUSHROOM_PLANKS.get(), consumer);
        
        super.singleItemRecipe("mushroom_fir_planks_from_stem", BNBlocks.MUSHROOM_FIR_PLANKS.get(), 1, BNBlocks.MUSHROOM_FIR_STEM.get(), consumer);
        super.singleItemRecipe("mushroom_fir_planks_from_log", BNBlocks.MUSHROOM_FIR_PLANKS.get(), 1, BNTags.Items.MUSHROOM_FIR_LOGS, consumer);
        super.recipe2x2(BNBlocks.MUSHROOM_FIR_LOG.get(), 1, BNBlocks.MUSHROOM_FIR_STEM.get(), consumer);
        super.stairsRecipe(BNBlocks.MUSHROOM_FIR_STAIRS.get().asItem(), BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.slabRecipe(BNBlocks.MUSHROOM_FIR_SLAB.get().asItem(), 6, BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.fenceRecipe(BNBlocks.MUSHROOM_FIR_FENCE.get().asItem(), BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.gateRecipe(BNBlocks.MUSHROOM_FIR_GATE.get().asItem(), BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.plateRecipe(BNBlocks.MUSHROOM_FIR_PLATE.get().asItem(), BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.wallRecipe(BNBlocks.MUSHROOM_FIR_TRAPDOOR.get().asItem(), 2, BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.doorRecipe(BNBlocks.MUSHROOM_FIR_DOOR.get().asItem(), 3, BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        super.singleItemRecipe(BNBlocks.MUSHROOM_FIR_BUTTON.get(), 4, BNBlocks.MUSHROOM_FIR_PLANKS.get(), consumer);
        
        super.singleItemRecipe("wart_planks", BNBlocks.WART_PLANKS.get(), 4, BNTags.Items.WART_LOGS, consumer);
        super.recipe2x2(BNBlocks.WART_BARK.get(), 3, BNBlocks.WART_LOG.get(), consumer);
        super.stairsRecipe(BNBlocks.WART_STAIRS.get().asItem(), BNBlocks.WART_PLANKS.get(), consumer);
        super.slabRecipe(BNBlocks.WART_SLAB.get().asItem(), 6, BNBlocks.WART_PLANKS.get(), consumer);
        super.fenceRecipe(BNBlocks.WART_FENCE.get().asItem(), BNBlocks.WART_PLANKS.get(), consumer);
        super.gateRecipe(BNBlocks.WART_GATE.get().asItem(), BNBlocks.WART_PLANKS.get(), consumer);
        super.plateRecipe(BNBlocks.WART_PLATE.get().asItem(), BNBlocks.WART_PLANKS.get(), consumer);
        super.wallRecipe(BNBlocks.WART_TRAPDOOR.get().asItem(), 2, BNBlocks.WART_PLANKS.get(), consumer);
        super.doorRecipe(BNBlocks.WART_DOOR.get().asItem(), 3, BNBlocks.WART_PLANKS.get(), consumer);
        super.singleItemRecipe(BNBlocks.WART_BUTTON.get(), 4, BNBlocks.WART_PLANKS.get(), consumer);
        
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.PIG_STATUE_RESPAWNER.get())
		.key('C', BNBlocks.CINCINNASITE_FIRE_BOWL.get())
		.key('W', BNItems.CINCINNASITE_INGOT.get())
		.patternLine("WCW")
		.patternLine("WWW")
		.patternLine(" W ")
		.addCriterion("has_cincinnasite", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(Items.SHIELD)
		.key('C', BNItems.CINCINNASITE_INGOT.get())
		.key('W', ItemTags.PLANKS)
		.patternLine("WCW")
		.patternLine("WWW")
		.patternLine(" W ")
		.addCriterion("has_cincinnasite", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer, "shield_from_cincinnasite");
        
        ShapedRecipeBuilder
		.shapedRecipe(BNItems.GLOWSTONE_TORCH.get(), 4)
		.key('G', Items.GLOWSTONE_DUST)
		.key('N', Items.NETHERRACK)
		.patternLine("G")
		.patternLine("N")
		.addCriterion("has_glowstone_dust", hasItem(Items.GLOWSTONE_DUST))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_LANTERN.get(), 1)
		.key('G', Items.GLOWSTONE)
		.key('C', BNItems.CINCINNASITE_INGOT.get())
		.patternLine(" C ")
		.patternLine("CGC")
		.patternLine(" C ")
		.addCriterion("has_glowstone", hasItem(Items.GLOWSTONE))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_FIRE_BOWL.get(), 1)
		.key('N', Items.NETHERRACK)
		.key('C', BNItems.CINCINNASITE_INGOT.get())
		.patternLine("CNC")
		.patternLine(" C ")
		.addCriterion("has_glowstone", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_BRICKS.get(), 1)
		.key('N', Items.NETHER_BRICK)
		.key('C', BNItems.CINCINNASITE_INGOT.get())
		.patternLine("NC")
		.patternLine("CN")
		.addCriterion("has_glowstone", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_BRICK_PLATE.get(), 5)
		.key('N', Items.NETHER_BRICK)
		.key('C', BNBlocks.CINCINNASITE_BLOCK.get())
		.patternLine(" C ")
		.patternLine("NNN")
		.patternLine(" C ")
		.addCriterion("has_glowstone", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_BRICK_PLATE.get(), 12)
		.key('N', Items.NETHER_BRICK)
		.key('C', BNBlocks.CINCINNASITE_FORGED.get())
		.patternLine(" C ")
		.patternLine("NNN")
		.patternLine(" C ")
		.addCriterion("has_glowstone", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer, "cincinnasite_brick_plate_from_forged");
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_BARS.get(), 16)
		.key('C', BNItems.CINCINNASITE_INGOT.get())
		.patternLine("C C")
		.patternLine("C C")
		.patternLine("C C")
		.addCriterion("has_glowstone", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(BNBlocks.CINCINNASITE_PEDESTAL.get(), 5)
		.key('C', BNBlocks.CINCINNASITE_FORGED.get())
		.patternLine("CC")
		.patternLine("CC")
		.patternLine("CC")
		.addCriterion("has_glowstone", hasItem(BNItems.CINCINNASITE_INGOT.get()))
		.build(consumer);
        
        ShapedRecipeBuilder
		.shapedRecipe(Items.STICK, 4)
		.key('P', ItemTags.PLANKS)
		.patternLine("P")
		.patternLine("P")
		.addCriterion("has_planks", hasItem(ItemTags.PLANKS))
		.build(consumer, "sticks_from_planks");
    }
}
