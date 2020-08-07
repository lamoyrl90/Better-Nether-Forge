package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BoneReefStructure extends Feature<NoFeatureConfig> implements IStructure {
	
	private static final StructureNBT[] BONES = new StructureNBT[] {
			new StructureNBT("bone_01"),
			new StructureNBT("bone_02"),
			new StructureNBT("bone_03")
		};
	
	
	public BoneReefStructure(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	
	@Override
	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		if (BlocksHelper.isNetherGround(world.getBlockState(pos.down())) && world.isAirBlock(pos.up(2)) && world.isAirBlock(pos.up(4)))
		{
			StructureNBT bone = BONES[random.nextInt(BONES.length)];
			bone.randomRM(random);
			bone.generateCentered(world, pos.down(random.nextInt(4)));
			return true;
		} 
		return false;
	}



	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}
}
