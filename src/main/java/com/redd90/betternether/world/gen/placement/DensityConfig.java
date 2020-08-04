package com.redd90.betternether.world.gen.placement;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.placement.IPlacementConfig;

public class DensityConfig implements IPlacementConfig {
	public static final Codec<DensityConfig> codec = Codec.FLOAT.fieldOf("density").xmap(DensityConfig::new, (config) -> {
		return config.density;
			}).codec();
	public final float density;
	
	public DensityConfig(float density) {
		this.density = density;
	}
}
