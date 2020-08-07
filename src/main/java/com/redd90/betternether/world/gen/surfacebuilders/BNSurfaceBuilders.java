package com.redd90.betternether.world.gen.surfacebuilders;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class BNSurfaceBuilders {
	
	public static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
	public static final BlockState NETHERRACK = Blocks.NETHERRACK.getDefaultState();
	public static final BlockState MAGMA = Blocks.MAGMA_BLOCK.getDefaultState();
	public static final BlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
	public static final BlockState WARPED_NYLIUM = Blocks.WARPED_NYLIUM.getDefaultState();
	public static final BlockState NETHER_MYCELIUM = BNBlocks.NETHER_MYCELIUM.get().getDefaultState();
	public static final BlockState JUNGLE_GRASS = BNBlocks.JUNGLE_GRASS.get().getDefaultState();
	public static final BlockState MUSHROOM_GRASS = BNBlocks.MUSHROOM_GRASS.get().getDefaultState();
	public static final BlockState SOUL_SANDSTONE = BNBlocks.SOUL_SANDSTONE.get().getDefaultState();
	
	public static final SurfaceBuilderConfig GRAVEL_DESERT_CONFIG = new SurfaceBuilderConfig(GRAVEL, NETHERRACK, NETHERRACK);
	public static final SurfaceBuilderConfig NETHER_MYCELIUM_CONFIG = new SurfaceBuilderConfig(NETHER_MYCELIUM, NETHERRACK, NETHERRACK);
	public static final SurfaceBuilderConfig MAGMA_SHORES = new SurfaceBuilderConfig(NETHERRACK, NETHERRACK, MAGMA);
	public static final SurfaceBuilderConfig SOUL_SAND_SHORES = new SurfaceBuilderConfig(NETHERRACK, NETHERRACK, SOUL_SAND);
	public static final SurfaceBuilderConfig JUNGLE_GRASS_CONFIG = new SurfaceBuilderConfig(JUNGLE_GRASS, NETHERRACK, SOUL_SAND);
	public static final SurfaceBuilderConfig MUSHROOM_GRASS_CONFIG = new SurfaceBuilderConfig(MUSHROOM_GRASS, NETHERRACK, SOUL_SAND);
	public static final SurfaceBuilderConfig WARPED_NYLIUM_CONFIG = new SurfaceBuilderConfig(WARPED_NYLIUM, NETHERRACK, SOUL_SAND);
	public static final SurfaceBuilderConfig WART_FOREST_CONFIG = new SurfaceBuilderConfig(NETHERRACK, SOUL_SANDSTONE, SOUL_SAND);
	public static final SurfaceBuilder<SurfaceBuilderConfig> OBSIDIAN_CHASM = register("obsidian_swamp", new ObsidianChasmSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> SWELTERING_SWAMP = register("sweltering_swamp", new SwelteringSwampSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> TORRID_TERRACES = register("torrid_terraces", new TorridTerracesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> FUNGAL_WOODLANDS_EDGE = register("fungal_woodlands_edge", new FungalWoodlandsEdgeSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> NETHER_GRASSLANDS = register("nether_grasslands", new NetherGrasslandsSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> WART_FOREST = register("wart_forest", new WartForestSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> SOUL_PLAINS = register("soul_plains", new SoulPlainsSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	
	
	private static SurfaceBuilder<SurfaceBuilderConfig> register(String name, SurfaceBuilder<SurfaceBuilderConfig> surfaceBuilder) {
		surfaceBuilder.setRegistryName(name);
		ForgeRegistries.SURFACE_BUILDERS.register(surfaceBuilder);
		return surfaceBuilder;
	}
}
