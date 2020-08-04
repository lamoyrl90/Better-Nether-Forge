package com.redd90.betternether.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class StructureFrequencyConfig implements IFeatureConfig {
	public static final Codec<StructureFrequencyConfig> codec = RecordCodecBuilder.create((instance) -> {
	      return instance.group(Codec.INT.fieldOf("frequency").withDefault(1).forGetter((config) -> {
	          return config.frequency;
	       })).apply(instance, StructureFrequencyConfig::new);
	    });
	public final int frequency;
	
	public StructureFrequencyConfig(int frequency) {
		this.frequency = frequency;
	}
}
