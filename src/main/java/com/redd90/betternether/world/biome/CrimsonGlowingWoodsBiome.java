package com.redd90.betternether.world.biome;

import com.google.common.collect.ImmutableList;
import com.redd90.betternether.registry.BNEntities;

import net.minecraft.block.Blocks;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.biome.SoundAdditionsAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class CrimsonGlowingWoodsBiome extends Biome {
	public CrimsonGlowingWoodsBiome() {
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.field_237189_ad_, SurfaceBuilder.field_237185_P_)
				.precipitation(Biome.RainType.NONE)
				.category(Biome.Category.NETHER)
				.depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F)
				.func_235097_a_((new BiomeAmbience.Builder())
						.setWaterColor(4159204)
						.setWaterFogColor(329011)
						.setFogColor(3343107)
						.setParticle(new ParticleEffectAmbience(ParticleTypes.CRIMSON_SPORE, 0.025F))
						.setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
						.setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D))
						.setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D))
						.setMusic(BackgroundMusicTracks.func_232677_a_(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST))
						.build()).parent((String)null)
				.func_235098_a_(ImmutableList.of(new Biome.Attributes(0.0F, 0.5F, 0.0F, 0.0F, 0.375F))));
		this.func_235063_a_(DefaultBiomeFeatures.FORTRESS);
		this.func_235063_a_(DefaultBiomeFeatures.BASTION_REMNANT);
		this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_NETHER);
		this.addCarver(GenerationStage.Carving.AIR, createCarver(WorldCarver.field_236240_b_, new ProbabilityConfig(0.2F)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(DefaultBiomeFeatures.LAVA_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 256))));
		DefaultBiomeFeatures.addMushrooms(this);
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.withConfiguration(DefaultBiomeFeatures.NETHER_SPRING_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 4, 8, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.NETHER_FIRE).withPlacement(Placement.field_236960_A_.configure(new FrequencyConfig(10))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.NETHER_SOUL_FIRE).withPlacement(Placement.field_236960_A_.configure(new FrequencyConfig(10))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.LIGHT_GEM_CHANCE.configure(new FrequencyConfig(10))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 33)).withPlacement(Placement.MAGMA.configure(new FrequencyConfig(4))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.withConfiguration(DefaultBiomeFeatures.ENCLOSED_NETHER_SPRING_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(16, 10, 20, 128))));
		//DefaultBiomeFeatures.func_235195_av_(this);
		DefaultBiomeFeatures.func_235192_as_(this);
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 1, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.STRIDER, 60, 1, 2));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.HOGLIN, 9, 3, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.PIGLIN, 5, 3, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.STRIDER, 60, 1, 2));
	}
	
	public void finalizeFeatures() {
		BNBiomeFeatures.addGoldenVines(this, 0.3f);
		BNBiomeFeatures.addWartSeeds(this, 0.02f);
		BNBiomeFeatures.addCrimsonFungus(this, 0.05f);
		BNBiomeFeatures.addCrimsonRoots(this, 0.2f);
		BNBiomeFeatures.addWallMoss(this, 0.8f);
		BNBiomeFeatures.addWallMushrooms(this, 0.4f);
		BNBiomeFeatures.addWartBushes(this, 0.05f);
		BNBiomeFeatures.addCrimsonGlowingTrees(this, 0.2f);
		BNBiomeFeatures.addCrimsonPinewoods(this, 0.2f);
		

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BNEntities.FIREFLY.get(), 60, 4, 4));
	}
}
