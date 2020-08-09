package com.redd90.betternether.world.biome;

import com.google.common.collect.ImmutableList;
import com.redd90.betternether.registry.BNEntities;
import com.redd90.betternether.world.gen.surfacebuilders.BNSurfaceBuilders;

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
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BoneReefBiome extends Biome {

	public BoneReefBiome() {
		   super((new Biome.Builder())
				   .surfaceBuilder(SurfaceBuilder.NETHER, BNSurfaceBuilders.MUSHROOM_GRASS_CONFIG)
				   .precipitation(Biome.RainType.NONE)
				   .category(Biome.Category.NETHER)
				   .depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F)
				   .func_235097_a_((new BiomeAmbience.Builder())
						   .setWaterColor(4159204)
						   .setWaterFogColor(329011)
						   .setFogColor(3136970)
						   .setAmbientSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
						   .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
						   .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
						   .setMusic(BackgroundMusicTracks.func_232677_a_(SoundEvents.MUSIC_NETHER_NETHER_WASTES))
						   .setParticle(new ParticleEffectAmbience(ParticleTypes.WARPED_SPORE, 0.01F))
						   .build()).parent((String)null)
				   .func_235098_a_(ImmutableList.of(new Biome.Attributes(-0.225f, 0.25f, -0.35f, 0.05F, 0.0F))));
		   this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_NETHER);
		this.func_235063_a_(DefaultBiomeFeatures.FORTRESS);
		this.func_235063_a_(DefaultBiomeFeatures.BASTION_REMNANT);
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(DefaultBiomeFeatures.LAVA_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 256))));
		DefaultBiomeFeatures.addMushrooms(this);
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.withConfiguration(DefaultBiomeFeatures.NETHER_SPRING_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 4, 8, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.NETHER_FIRE).withPlacement(Placement.field_236960_A_.configure(new FrequencyConfig(10))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.NETHER_SOUL_FIRE).withPlacement(Placement.field_236960_A_.configure(new FrequencyConfig(10))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.LIGHT_GEM_CHANCE.configure(new FrequencyConfig(10))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(0.5F, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.RED_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(0.5F, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 33)).withPlacement(Placement.MAGMA.configure(new FrequencyConfig(4))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.withConfiguration(DefaultBiomeFeatures.ENCLOSED_NETHER_SPRING_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(16, 10, 20, 128))));
		DefaultBiomeFeatures.func_235192_as_(this);
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.GHAST, 30, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIFIED_PIGLIN, 30, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.MAGMA_CUBE, 100, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.STRIDER, 60, 1, 2));
	}
	
	public void finalizeFeatures() {

		
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BNEntities.FIREFLY.get(), 50, 2, 4));
		
		BNBiomeFeatures.addBoneReefs(this, 0.2f);
		BNBiomeFeatures.addBoneGrass(this, 0.1f);
		BNBiomeFeatures.addNetherReeds(this, 0.5f);
		BNBiomeFeatures.addBoneStalactites(this, 0.05f);
		BNBiomeFeatures.addBoneStalagmites(this, 0.05f);
		BNBiomeFeatures.addJellyfishMushrooms(this, 0.02f);
		BNBiomeFeatures.addLumabusVines(this,0.3f);
	}
	
}
