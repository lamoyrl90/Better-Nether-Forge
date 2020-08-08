package com.redd90.betternether.world.gen.feature.city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.Blocks;

public class Palettes {
	private static final HashMap<String, CityPalette> REGISTRY = new HashMap<String, CityPalette>();
	private static final ArrayList<CityPalette> PALETTES = new ArrayList<CityPalette>();
	
	public static final CityPalette EMPTY = register(new CityPalette("empty"));
	
	public static final CityPalette RED = register(new CityPalette("red")
			.addRoofBlocks(BNBlocks.ROOF_TILE_WART.get())
			.addRoofSlabs(BNBlocks.ROOF_TILE_WART_SLAB.get())
			.addRoofStairs(BNBlocks.ROOF_TILE_WART_STAIRS.get())
			.addPlanksBlocks(BNBlocks.WART_PLANKS.get())
			.addPlanksSlabs(BNBlocks.WART_SLAB.get())
			.addPlanksStairs(BNBlocks.WART_STAIRS.get())
			.addFences(BNBlocks.WART_FENCE.get())
			.addGates(BNBlocks.WART_GATE.get())
			.addWalls(BNBlocks.NETHER_BRICK_WALL.get())
			.addLogs(BNBlocks.WART_LOG.get(), BNBlocks.WILLOW_LOG.get(), BNBlocks.STRIPPED_LOG_WART.get())
			.addBark(BNBlocks.WART_BARK.get(), BNBlocks.WILLOW_BARK.get(), BNBlocks.STRIPPED_BARK_WART.get())
			.addStoneBlocks(Blocks.NETHER_BRICKS, Blocks.NETHER_WART_BLOCK, BNBlocks.NETHER_BRICK_TILE_LARGE.get(), BNBlocks.NETHER_BRICK_TILE_SMALL.get())
			.addStoneSlabs(Blocks.NETHER_BRICK_SLAB, BNBlocks.NETHER_BRICK_TILE_SLAB.get())
			.addStoneStairs(Blocks.NETHER_BRICK_STAIRS, BNBlocks.NETHER_BRICK_TILE_STAIRS.get())
			.addGlowingBlocks(Blocks.GLOWSTONE, BNBlocks.CINCINNASITE_LANTERN.get())
			.addCeilingLights(Blocks.LANTERN, BNBlocks.CINCINNASITE_LANTERN_SMALL.get())
			.addWallLights(Blocks.WALL_TORCH, BNBlocks.CINCINNASITE_LANTERN_SMALL.get())
			.addFloorLights(Blocks.TORCH, BNBlocks.CINCINNASITE_LANTERN_SMALL.get())
			.addDoors(BNBlocks.WART_DOOR.get())
			.addTrapdoors(BNBlocks.WART_TRAPDOOR.get())
			.addGlassBlocks(BNBlocks.QUARTZ_GLASS_FRAMED_RED.get(), BNBlocks.QUARTZ_GLASS_RED.get(), BNBlocks.CINCINNASITE_FRAME.get())
			.addGlassPanes(BNBlocks.QUARTZ_GLASS_FRAMED_PANE_RED.get(), BNBlocks.QUARTZ_GLASS_PANE_RED.get(), BNBlocks.CINCINNASITE_BARS.get())
			.addWoodPlates(BNBlocks.WART_PLATE.get())
			.addPotsPanes(BNBlocks.BRICK_POT.get()));
	
	private static CityPalette register(CityPalette palette)
	{
		REGISTRY.put(palette.getName(), palette);
		PALETTES.add(palette);
		return palette;
	}
	
	public static CityPalette getPalette(String name)
	{
		CityPalette palette = REGISTRY.get(name);
		return palette == null ? EMPTY : palette;
	}
	
	public static CityPalette getRandom(Random random)
	{
		return random.nextBoolean() ? EMPTY : PALETTES.get(random.nextInt(PALETTES.size()));
	}
}
