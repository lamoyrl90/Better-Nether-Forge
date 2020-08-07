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

public class CrimsonPinewoodStructure extends StructureObjScatter {
	private static final StructureWorld[] TREES = new StructureWorld[] {
			new StructureWorld("trees/crimson_pine_01", -2, StructureType.FLOOR),
			new StructureWorld("trees/crimson_pine_02", -2, StructureType.FLOOR),
			new StructureWorld("trees/crimson_pine_03", -2, StructureType.FLOOR),
			new StructureWorld("trees/crimson_pine_04", -1, StructureType.FLOOR),
			new StructureWorld("trees/crimson_pine_05", -1, StructureType.FLOOR)
		};
	
	public CrimsonPinewoodStructure(Codec<NoFeatureConfig> codec)
	{
		super(codec, 7, TREES);
	}
	
	protected boolean isGround(BlockState state)
	{
		return BlocksHelper.isNetherGround(state);
	}
	
	protected boolean isStructure(BlockState state)
	{
		return  state.getBlock() == Blocks.CRIMSON_STEM;
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}
}
