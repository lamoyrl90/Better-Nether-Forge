package com.redd90.betternether.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BNReplaceSurfaceBlockFeature extends Feature<ReplaceBlockConfig> {
	public BNReplaceSurfaceBlockFeature(Codec<ReplaceBlockConfig> codec) {
		   super(codec);
	}

	@SuppressWarnings("deprecation")
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGen, Random rand, BlockPos pos, ReplaceBlockConfig config) {
		if (worldIn.getBlockState(pos).isIn(config.target.getBlock()) && worldIn.getBlockState(pos.up()).isAir()) {
			worldIn.setBlockState(pos, config.state, 2);
		}

		return true;
	}
}