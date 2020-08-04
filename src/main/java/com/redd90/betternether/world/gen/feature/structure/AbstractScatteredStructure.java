package com.redd90.betternether.world.gen.feature.structure;

import com.mojang.serialization.Codec;
import com.redd90.betternether.world.gen.feature.StructureFrequencyConfig;

public abstract class AbstractScatteredStructure extends AbstractBNStructure {

	public AbstractScatteredStructure(Codec<StructureFrequencyConfig> codec) {
		super(codec);
	}

}
