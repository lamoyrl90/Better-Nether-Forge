package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.NetherCactusBlock;
import com.redd90.betternether.registry.BNBlocks;
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
import net.minecraftforge.common.Tags;

public class NetherCactusFeature extends Feature<NoFeatureConfig> {
	public NetherCactusFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}
	
	private Mutable npos = new Mutable();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig featureConfig) {
		if (isValidPosition(worldIn, pos) && worldIn.getBlockState(pos).isAir()) {
			generate(worldIn, pos, random);
			return true;
		} else return false;
	}

	private boolean isValidPosition(IWorld world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).isIn(Tags.Blocks.GRAVEL);
	}

	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		if (isValidPosition(world, pos))
		{
			BlockState top = BNBlocks.NETHER_CACTUS.get().getDefaultState().with(NetherCactusBlock.AGE, random.nextInt(15));
			BlockState bottom = BNBlocks.NETHER_CACTUS.get().getDefaultState().with(NetherCactusBlock.TOP, false);
			for (int i = 0; i < 16; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 4);
				int z = pos.getZ() + (int) (random.nextGaussian() * 4);
				if (((x + z + pos.getY()) & 1) == 0)
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
					if (world.isAirBlock(npos) && isValidPosition(world, npos))
					{
						int h = random.nextInt(3);
						for (int n = 0; n < h; n++)
							if (world.isAirBlock(npos.up(n))) {
								BlocksHelper.setWithoutUpdate(world, npos.up(n), bottom);
							} else break;
						if (world.isAirBlock(npos.up(h)))
							BlocksHelper.setWithoutUpdate(world, npos.up(h), top);
						break;
					}
				}
			}
		}
	}
}
