package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.NetherReedsBlock;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

@SuppressWarnings("deprecation")
public class NetherReedsFeature extends Feature<NoFeatureConfig> {

	
	public NetherReedsFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (world.isAirBlock(pos) && BNBlocks.NETHER_REED.get().isValidPosition(world.getBlockState(pos), world, pos)) {
			generate(world, pos, random);
			return true;
		} else return false;
	}

	
	public void generate(IWorld world, BlockPos pos, Random random)
	{

			BlockState med = BNBlocks.NETHER_REED.get().getDefaultState().with(NetherReedsBlock.TOP, false);
			int h = random.nextInt(3);
			for (int i = 0; i < h; i++)
			{
				BlockPos posN = pos.up(i);
				BlockPos up = posN.up();
				if (world.isAirBlock(posN))
				{
					if (world.isAirBlock(up))
						BlocksHelper.setWithoutUpdate(world, posN, med);
					else
					{
						BlocksHelper.setWithoutUpdate(world, posN, BNBlocks.NETHER_REED.get().getDefaultState());
						return;
					}
				}
			}
			BlocksHelper.setWithoutUpdate(world, pos.up(h), BNBlocks.NETHER_REED.get().getDefaultState().with(NetherReedsBlock.AGE, random.nextInt(15)));
		
	}
}
