package com.redd90.betternether.world.gen.feature.structure;

import net.minecraft.util.ResourceLocation;

public class StructureWithOffset {
	public final ResourceLocation location;
	public final int offsetY;
	
	public StructureWithOffset(ResourceLocation location, int offsetY) {
		this.location = location;
		this.offsetY = offsetY;
	}
}
