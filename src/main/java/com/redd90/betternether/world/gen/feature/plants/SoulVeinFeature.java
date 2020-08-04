package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
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

@SuppressWarnings("deprecation")
public class SoulVeinFeature extends Feature<NoFeatureConfig> {

	private Mutable npos = new Mutable();
	
	public SoulVeinFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (world.isAirBlock(pos) && isValidPosition(world, pos)) {
			generate(world, pos, random);
			return true;
		} else return false;
			
	}

	private boolean isValidPosition(IWorld world, BlockPos pos)
	{
		return BNBlocks.SOUL_VEIN.get().isValidPosition(BNBlocks.SOUL_VEIN.get().getDefaultState(), world, pos);
	}
	
	public void generate(IWorld world, BlockPos pos, Random random) {
		BlockState state = BNBlocks.SOUL_VEIN.get().getDefaultState();
		BlockState sand = BNBlocks.VEINED_SAND.get().getDefaultState();
		int x1 = pos.getX() - 1;
		int x2 = pos.getX() + 1;
		int z1 = pos.getZ() - 1;
		int z2 = pos.getZ() + 1;
		for (int x = x1; x <= x2; x++)
			for (int z = z1; z <= z2; z++)
			{
				int y = pos.getY() + 2;
				for (int j = 0; j < 4; j++)
				{
					npos.setPos(x, y - j, z);
					if (world.isAirBlock(npos) && isValidPosition(world, npos))
					{
						BlocksHelper.setWithoutUpdate(world, npos, state);
						BlocksHelper.setWithoutUpdate(world, npos.down(), sand);
					}
				}
			}
	}
	
}
