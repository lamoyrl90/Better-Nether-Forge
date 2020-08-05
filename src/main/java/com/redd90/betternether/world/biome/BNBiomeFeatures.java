package com.redd90.betternether.world.biome;

import com.redd90.betternether.BNConfig;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNFeatures;
import com.redd90.betternether.registry.BNPlacements;
import com.redd90.betternether.world.gen.feature.StrayLavaFixer;
import com.redd90.betternether.world.gen.feature.StructureFrequencyConfig;
import com.redd90.betternether.world.gen.placement.DensityConfig;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

@SuppressWarnings("unused")
public class BNBiomeFeatures {
	
	public static final boolean FULL_CHUNK_SCANNING = false;
	private static final float PLANT_COUNT = BNConfig.GlobalPlantCount;
	private static final float DECOR_COUNT = BNConfig.GlobalDecorationCount;
	private static final float WALL_FACTOR = BNConfig.WallFactor;
	
	private static final BlockState AGAVE = BNBlocks.AGAVE.get().getDefaultState();
	private static final BlockState BARREL_CACTUS = BNBlocks.BARREL_CACTUS.get().getDefaultState();
	private static final BlockState CINCINNASITE_ORE = BNBlocks.CINCINNASITE_ORE.get().getDefaultState();
	private static final BlockState NETHER_RUBY_ORE = BNBlocks.NETHER_RUBY_ORE.get().getDefaultState();
	
	public static final BlockClusterFeatureConfig AGAVE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AGAVE), SimpleBlockPlacer.field_236447_c_)).tries(64).func_227317_b_().build();
	public static final BlockClusterFeatureConfig BARREL_CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BARREL_CACTUS), SimpleBlockPlacer.field_236447_c_)).tries(64).func_227317_b_().build();
	
	private static ConfiguredPlacement<DensityConfig> floorPlantPlacement(float density){
		return BNPlacements.VERTICAL_DOWN.get().configure(new DensityConfig(density*PLANT_COUNT));
	}
	
	private static ConfiguredPlacement<DensityConfig> ceilingPlantPlacement(float density){
		return BNPlacements.VERTICAL_UP.get().configure(new DensityConfig(density*PLANT_COUNT));
	}
	
	private static ConfiguredPlacement<DensityConfig> wallPlantPlacement(float density){
		return BNPlacements.WALL_SEARCH.get().configure(new DensityConfig(density*PLANT_COUNT*WALL_FACTOR));
	}
	
	private static ConfiguredPlacement<DensityConfig> floorDecorPlacement(float density){
		return BNPlacements.VERTICAL_DOWN.get().configure(new DensityConfig(density*DECOR_COUNT));
	}
	
	private static ConfiguredPlacement<DensityConfig> ceilingDecorPlacement(float density){
		return BNPlacements.VERTICAL_UP.get().configure(new DensityConfig(density*DECOR_COUNT));
	}
	
	private static ConfiguredPlacement<DensityConfig> wallDecorPlacement(float density){
		return BNPlacements.WALL_SEARCH.get().configure(new DensityConfig(density*DECOR_COUNT*WALL_FACTOR));
	}
	
	public static void addOldRedMushrooms(Biome biome, float density) {
		biome.func_235063_a_(BNFeatures.OLD_RED_MUSHROOM.get().func_236391_a_(new StructureFrequencyConfig(Math.round(PLANT_COUNT*density))));
	}
	
	public static void addOldBrownMushrooms(Biome biome, float density) {
		biome.func_235063_a_(BNFeatures.OLD_BROWN_MUSHROOM.get().func_236391_a_(new StructureFrequencyConfig(Math.round(PLANT_COUNT*density))));
	}
	
	public static void addNetherCactii(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.NETHER_CACTUS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addAgave(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.AGAVE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
		}
	
	public static void addBarrelCactii(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.BARREL_CACTUS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addBNOres(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, 
						CINCINNASITE_ORE, 14))
				.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 10, 20, 128))));
		
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, 
						NETHER_RUBY_ORE, 8))
				.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 10, 20, 48))));
	}
	
	public static void addStalagmites(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, 
				BNFeatures.STALAGMITES.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorDecorPlacement(density)));
	}
	public static void addStalactites(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, 
				BNFeatures.STALACTITES.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(ceilingDecorPlacement(density)));
	}

	public static void addGeysers(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, 
				BNFeatures.GEYSER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorDecorPlacement(density)));
	}
	
	public static void addMagmaFlowers(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.MAGMA_FLOWER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addGoldenVines(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.GOLDEN_VINES.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(ceilingPlantPlacement(density)));
	}
	
	public static void addBlackVines(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.BLACK_VINES.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(ceilingPlantPlacement(density)));
	}
	
	public static void addObsidianCrystals(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, 
				BNFeatures.OBSIDIAN_CRYSTALS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorDecorPlacement(density)));
	}
	
	public static void addBlackstoneSpikes(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, 
				BNFeatures.BLACKSTONE_SPIKE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorDecorPlacement(density)));
	}
	
	public static void fixCheatyLava(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, 
				BNFeatures.FIX_LAVA.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		StrayLavaFixer.ValidBiomes.add(biome);
	}
	
	
	public static void addWillowTrees(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, 
				BNFeatures.WILLOW_TREE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addFeatherFerns(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.FEATHER_FERN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addSoulVeins(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.SOUL_VEIN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addSmokers(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.SMOKER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}

	
	public static void addNetherReeds(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.NETHER_REEDS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addBlackBushes(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.BLACK_BUSH.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addSwampGrass(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.SWAMP_GRASS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addWallMushrooms(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.WALL_MUSHROOM_RED.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(wallPlantPlacement(density)));
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.WALL_MUSHROOM_BROWN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(wallPlantPlacement(density)));
	
	}
	
	public static void addWallMoss(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.WALL_MOSS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(wallPlantPlacement(density)));
	}
	
	public static void addMediumMushrooms(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.MEDIUM_RED_MUSHROOM.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(BNPlacements.VERTICAL_DOWN.get().configure(new DensityConfig(Math.round(PLANT_COUNT*density)))));
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.MEDIUM_BROWN_MUSHROOM.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addGiantMolds(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.GIANT_MOLD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addMushroomFirs(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.MUSHROOM_FIR.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addVanillaMushrooms(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.VANILLA_MUSHROOMS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addOrangeMushrooms(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.ORANGE_MUSHROOM.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addRedMold(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.RED_MOLD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addGrayMold(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.GRAY_MOLD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addLucisMushrooms(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.LUCIS_MUSHROOM.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(BNPlacements.WALL_SEARCH.get().configure(new DensityConfig(Math.round(PLANT_COUNT*density*WALL_FACTOR)))));
	}
	
	public static void addInkBushes(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.INK_BUSH.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addNetherGrass(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.NETHER_GRASS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addNetherWart(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.NETHER_WART.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addWartSeeds(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.WART_SEED.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addBlackApples(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.BLACK_APPLE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addJunglePlants(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.JUNGLE_PLANT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addJungleMoss(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.JUNGLE_MOSS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(wallPlantPlacement(density)));
	}
	
	public static void addStalagnates(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.STALAGNATE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addRubeusTrees(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, 
				BNFeatures.RUBEUS_TREE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addRubeusBushes(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.RUBEUS_BUSH.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addEggPlants(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.EGG_PLANT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(floorPlantPlacement(density)));
	}
	
	public static void addEyeVines(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.EYE_VINE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(ceilingPlantPlacement(density)));
	}
	
	public static void addBloomingVines(Biome biome, float density) {
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				BNFeatures.BLOOMING_VINE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(ceilingPlantPlacement(density)));
	}
}
