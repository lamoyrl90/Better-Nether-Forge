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
	public static final BlockState NETHER_MYCELIUM = BNBlocks.NETHER_MYCELIUM.get().getDefaultState();
	
	public static final SurfaceBuilderConfig GRAVEL_DESERT_CONFIG = new SurfaceBuilderConfig(GRAVEL, NETHERRACK, NETHERRACK);
	public static final SurfaceBuilderConfig NETHER_MYCELIUM_CONFIG = new SurfaceBuilderConfig(NETHER_MYCELIUM, NETHERRACK, NETHERRACK);
	public static final SurfaceBuilderConfig MAGMA_SHORES = new SurfaceBuilderConfig(NETHERRACK, NETHERRACK, MAGMA);
	public static final SurfaceBuilder<SurfaceBuilderConfig> OBSIDIAN_CHASM = register("obsidian_swamp", new ObsidianChasmSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> SWELTERING_SWAMP = register("sweltering_swamp", new SwelteringSwampSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> TORRID_TERRACES = register("torrid_terraces", new TorridTerracesSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> FUNGAL_WOODLANDS_EDGE = register("fungal_woodlands_edge", new FungalWoodlandsEdgeSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> NETHER_GRASSLANDS = register("nether_grasslands", new NetherGrasslandsSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
	
	
	private static SurfaceBuilder<SurfaceBuilderConfig> register(String name, SurfaceBuilder<SurfaceBuilderConfig> surfaceBuilder) {
		surfaceBuilder.setRegistryName(name);
		ForgeRegistries.SURFACE_BUILDERS.register(surfaceBuilder);
		return surfaceBuilder;
	}
}
