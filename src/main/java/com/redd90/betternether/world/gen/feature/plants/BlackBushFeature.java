package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNTags;
import com.redd90.betternether.world.gen.feature.ScatteredFeature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BlackBushFeature extends ScatteredFeature {

	public BlackBushFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.blockstate = BNBlocks.BLACK_BUSH.get().getDefaultState();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if(canPlaceAt(world, pos) && world.getBlockState(pos).isAir()) {
			generate(world, pos, random);
			return true;
		} else return false;
	}
	
	@Override
	public boolean canPlaceAt(IWorld world, BlockPos pos)
	{
		if (world.getBlockState(pos.down()).isIn(BNTags.Blocks.NETHER_GROUND)) return true;
		else return false;
	}
	
}
