package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNTags;
import com.redd90.betternether.util.BlocksHelper;
import com.redd90.betternether.world.gen.feature.ScatteredFeature;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

@SuppressWarnings("deprecation")
public class AgaveFeature extends ScatteredFeature {

	public AgaveFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}
	
	private BlockState blockstate = BNBlocks.AGAVE.get().getDefaultState();

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
		if (world.getBlockState(pos.down()).isIn(BNTags.Blocks.GRAVEL_DESERT_GROUND)) return true;
		else return false;
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
