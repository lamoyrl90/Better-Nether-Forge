package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class WartDeadwoodStructure extends Feature<NoFeatureConfig> implements IStructure {
	
	private static final StructureWorld[] TREES = new StructureWorld[] {
		new StructureWorld("trees/wart_root_01", 0, StructureType.FLOOR),
		new StructureWorld("trees/wart_root_02", 0, StructureType.FLOOR),
		new StructureWorld("trees/wart_root_03", -2, StructureType.FLOOR),
		new StructureWorld("trees/wart_fallen_log", 0, StructureType.FLOOR)
	};
		
	public WartDeadwoodStructure(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		if (isGround(world.getBlockState(pos.down())) && isGround(world.getBlockState(pos.down(2))))
		{
			StructureWorld tree = TREES[random.nextInt(TREES.length)];
			tree.generate(world, pos, random);
			return true;
		} return false;
	}
	
	private boolean isGround(BlockState state)
	{
		return BlocksHelper.isNetherGround(state);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig config) {
		return generate(world, pos, random);
	}
}
