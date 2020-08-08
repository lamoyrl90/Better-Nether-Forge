package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class GhastHiveStructure extends Feature<NoFeatureConfig> {

	private static final StructureWorld[] STRUCTURES = new StructureWorld[] {
			new StructureWorld("ghast_hive", -20, StructureType.CEIL)
	};
	
	public GhastHiveStructure(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}

	private boolean isGround(IWorld world, BlockState state, BlockPos pos)
	{
		return world.getBlockState(pos).isSolid();
	}
	
	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		if (isGround(world, world.getBlockState(pos.up()), pos.up()) && isGround(world, world.getBlockState(pos.up(2)), pos.up(2)))
		{
			StructureWorld structure = STRUCTURES[random.nextInt(STRUCTURES.length)];
			structure.generate(world, pos, random);
			return true;
		} return false;
	}
}
