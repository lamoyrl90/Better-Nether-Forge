package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class TwistingVinesFeature extends Feature<NoFeatureConfig> {
	
	public TwistingVinesFeature(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
	}


	private Mutable npos = new Mutable();
	
	private boolean isValidPosition(IWorld world, BlockPos pos)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == Blocks.WARPED_NYLIUM || block == Blocks.TWISTING_VINES;
	}
	
	
	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		if (isValidPosition(world, pos))
		{
			for (int i = 0; i < 10; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 2);
				int z = pos.getZ() + (int) (random.nextGaussian() * 2);
				int y = pos.getY() + random.nextInt(6);
				for (int j = 0; j < 6; j++)
				{
					npos.setPos(x, y - j, z);
					if (world.isAirBlock(npos) && isValidPosition(world, npos))
					{
						int h = random.nextInt(20) + 1;
						int sy = npos.getY();
						for (int n = 0; n < h; n++)
						{
							npos.setY(sy + n);
							if (!world.isAirBlock(npos.up()))
							{
								BlocksHelper.setWithoutUpdate(world, npos, Blocks.TWISTING_VINES.getDefaultState());
								break;
							}
							BlocksHelper.setWithoutUpdate(world, npos, Blocks.TWISTING_VINES_PLANT.getDefaultState());
						}
						BlocksHelper.setWithoutUpdate(world, npos, Blocks.TWISTING_VINES.getDefaultState());
						break;
					}
				}
				
			} return true;
		} return false;
	}


	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}
}
