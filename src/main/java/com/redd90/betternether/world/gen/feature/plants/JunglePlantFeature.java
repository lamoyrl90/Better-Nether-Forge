package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;
import com.redd90.betternether.world.gen.feature.ScatteredFeature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class JunglePlantFeature extends ScatteredFeature {

	public JunglePlantFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.blockstate = BNBlocks.JUNGLE_PLANT.get().getDefaultState();
	}

	public boolean canPlaceAt(IWorld world, BlockPos pos)
	{
		return BlocksHelper.isNetherrack(world.getBlockState(pos.down())) || BlocksHelper.isNylium(world.getBlockState(pos.down()));
	}
	
}
