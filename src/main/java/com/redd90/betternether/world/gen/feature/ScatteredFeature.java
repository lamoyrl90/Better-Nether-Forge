package com.redd90.betternether.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

@SuppressWarnings("deprecation")
public abstract class ScatteredFeature extends Feature<NoFeatureConfig> {

	public ScatteredFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}
	
	public Mutable npos = new Mutable();
	public BlockState blockstate;
	
	//Generate
	
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if(canPlaceAt(world, pos) && world.getBlockState(pos).isAir()) {
			generate(world, pos, random);
			return true;
		} else return false;
	}
	
	public boolean canPlaceAt(IWorld world, BlockPos pos)
	{
		return false;
	}
	
	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		if (world.getBlockState(pos).isAir() && canPlaceAt(world, pos))
		{
			int rndState = random.nextInt(2);
			for (int i = 0; i < 20; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 4);
				int z = pos.getZ() + (int) (random.nextGaussian() * 4);
				if (((x + z + rndState) & 1) == 0)
				{
					if (random.nextBoolean())
					{
						x += random.nextBoolean() ? 1 : -1;
					}
					else
					{
						z += random.nextBoolean() ? 1 : -1;
					}
				}
				int y = pos.getY() + random.nextInt(8);
				for (int j = 0; j < 8; j++)
				{
					npos.setPos(x, y - j, z);
					if (world.getBlockState(npos).isAir() && canPlaceAt(world, npos))
					{
						BlocksHelper.setWithoutUpdate(world, npos, blockstate);
					}
				}
			}
		}
	}
}

