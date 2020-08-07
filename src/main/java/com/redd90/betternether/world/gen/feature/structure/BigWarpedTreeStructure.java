package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BigWarpedTreeStructure extends StructureObjScatter {
	private static final StructureWorld[] TREES = new StructureWorld[] {
			new StructureWorld("trees/warped_tree_01", -2, StructureType.FLOOR),
			new StructureWorld("trees/warped_tree_02", -2, StructureType.FLOOR),
			new StructureWorld("trees/warped_tree_03", -2, StructureType.FLOOR),
			new StructureWorld("trees/warped_tree_04", -2, StructureType.FLOOR),
			new StructureWorld("trees/warped_tree_05", -4, StructureType.FLOOR)
		};
	
	public BigWarpedTreeStructure(Codec<NoFeatureConfig> codec)
	{
		super(codec, 10, TREES);
	}
	
	protected boolean isGround(BlockState state)
	{
		return BlocksHelper.isNetherGround(state);
	}
	
	protected boolean isStructure(BlockState state)
	{
		return  state.getBlock() == Blocks.WARPED_STEM ||
				state.getBlock() == Blocks.WARPED_WART_BLOCK ||
				state.getBlock() == Blocks.SHROOMLIGHT;
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return super.generate(world, pos, random);
	}
}
