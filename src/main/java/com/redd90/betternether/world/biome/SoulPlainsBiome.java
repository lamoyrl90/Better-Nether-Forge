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
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class SoulPlainsBiome extends Biome {
	   public SoulPlainsBiome() {
		      super((new Biome.Builder()).surfaceBuilder(BNSurfaceBuilders.SOUL_PLAINS, BNSurfaceBuilders.WART_FOREST_CONFIG)
		    		  .precipitation(Biome.RainType.NONE)
		    		  .category(Biome.Category.NETHER)
		    		  .depth(0.025F)
		    		  .scale(0.01F)
		    		  .temperature(2.0F)
		    		  .downfall(0.0F)
		    		  .func_235097_a_((new BiomeAmbience.Builder())
		    				  .setWaterColor(4159204)
		    				  .setWaterFogColor(329011)
		    				  .setFogColor(12874223)
		    				  .setAmbientSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
		    				  .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
		    				  .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
		    				  .setParticle(new ParticleEffectAmbience(ParticleTypes.PORTAL, 0.02F))
							  .setMusic(BackgroundMusicTracks.func_232677_a_(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).parent((String)null)
		    		  .func_235098_a_(ImmutableList.of(new Biome.Attributes(0.10F, -0.0f, -0.475F, 0.0F, 0.0F))));
		      this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_NETHER);
		      this.func_235063_a_(DefaultBiomeFeatures.FORTRESS);
		      this.func_235063_a_(DefaultBiomeFeatures.BASTION_REMNANT);
		      this.addCarver(GenerationStage.Carving.AIR, createCarver(WorldCarver.field_236240_b_, new ProbabilityConfig(0.2F)));
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
		      
		      this.func_235059_a_(EntityType.SKELETON, 0.7D, 0.15D);
		      this.func_235059_a_(EntityType.GHAST, 0.7D, 0.15D);
		      this.func_235059_a_(EntityType.ENDERMAN, 0.7D, 0.15D);
		      this.func_235059_a_(EntityType.STRIDER, 0.7D, 0.15D);
		   }
	   
		public void finalizeFeatures() {
			BNBiomeFeatures.addSoulVeins(this, 0.5f);
			BNBiomeFeatures.addBlackBushes(this, 0.02f);
			BNBiomeFeatures.addSoulGrass(this, 0.3f);
			
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BNEntities.FIREFLY.get(), 50, 2, 4));
		}
}
