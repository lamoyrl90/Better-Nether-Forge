package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class OldBrownMushroomStructure extends StructureObjScatter {
	private static final StructureWorld[] TREES = new StructureWorld[] {
			new StructureWorld("trees/brown_mushroom_01", -4, StructureType.FLOOR),
			new StructureWorld("trees/brown_mushroom_02", -3, StructureType.FLOOR),
			new StructureWorld("trees/brown_mushroom_03", -3, StructureType.FLOOR),
			new StructureWorld("trees/brown_mushroom_04", -2, StructureType.FLOOR)
		};
	
	public OldBrownMushroomStructure(Codec<NoFeatureConfig> codec)
	{
		super(codec, 9, TREES);
	}
	
	protected boolean isGround(BlockState state)
	{
		return state.getBlock() == BNBlocks.NETHER_MYCELIUM.get() || BlocksHelper.isNetherGround(state);
	}
	
	protected boolean isStructure(BlockState state)
	{
		return  state.getBlock() == Blocks.MUSHROOM_STEM ||
				state.getBlock() == Blocks.BROWN_MUSHROOM_BLOCK ||
				state.getBlock() == Blocks.RED_MUSHROOM_BLOCK;
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}
}
