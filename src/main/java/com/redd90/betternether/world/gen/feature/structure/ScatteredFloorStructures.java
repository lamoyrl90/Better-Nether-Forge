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

public class ScatteredFloorStructures extends Feature<NoFeatureConfig> {

	private static final StructureWorld[] STRUCTURES = new StructureWorld[] {
			new StructureWorld("altar_01", -2, StructureType.FLOOR),
			new StructureWorld("altar_02", -4, StructureType.FLOOR),
			new StructureWorld("altar_03", -3, StructureType.FLOOR),
			new StructureWorld("altar_04", -3, StructureType.FLOOR),
			new StructureWorld("altar_05", -2, StructureType.FLOOR),
			new StructureWorld("altar_06", -2, StructureType.FLOOR),
			new StructureWorld("altar_07", -2, StructureType.FLOOR),
			new StructureWorld("altar_08", -2, StructureType.FLOOR),
			new StructureWorld("portal_01", -4, StructureType.FLOOR),
			new StructureWorld("portal_02", -3, StructureType.FLOOR),
			new StructureWorld("garden_01", -3, StructureType.FLOOR),
			new StructureWorld("garden_02", -2, StructureType.FLOOR),
			new StructureWorld("pillar_01", -1, StructureType.FLOOR),
			new StructureWorld("pillar_02", -1, StructureType.FLOOR),
			new StructureWorld("pillar_03", -1, StructureType.FLOOR),
			new StructureWorld("pillar_04", -1, StructureType.FLOOR),
			new StructureWorld("pillar_05", -1, StructureType.FLOOR),
			new StructureWorld("pillar_06", -1, StructureType.FLOOR),
			new StructureWorld("respawn_point_01", -3, StructureType.FLOOR),
			new StructureWorld("respawn_point_02", -2, StructureType.FLOOR),
			new StructureWorld("respawn_point_03", -3, StructureType.FLOOR),
			new StructureWorld("respawn_point_04", -2, StructureType.FLOOR),
			new StructureWorld("spawn_altar_ladder", -5, StructureType.FLOOR)
	};
	
	public ScatteredFloorStructures(Codec<NoFeatureConfig> p_i231953_1_) {
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
		if (isGround(world, world.getBlockState(pos.down()), pos.down()) && isGround(world, world.getBlockState(pos.down(2)), pos.down(2)))
		{
			StructureWorld tree = STRUCTURES[random.nextInt(STRUCTURES.length)];
			tree.generate(world, pos, random);
			return true;
		} return false;
	}
}
