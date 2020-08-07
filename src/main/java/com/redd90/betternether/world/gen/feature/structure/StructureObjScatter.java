package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class StructureObjScatter extends Feature<NoFeatureConfig> implements IStructure {
	private static final Mutable POS = new Mutable();
	
	final StructureWorld[] structures;
	final int distance;
	final int manDist;
	
	public StructureObjScatter(Codec<NoFeatureConfig> codec, int distance, StructureWorld[] structures) {
		super(codec);
		this.distance = distance;
		this.manDist = (int) Math.ceil(distance * 1.5);
		this.structures = structures;
	}
	
	
	@Override
	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		if (isGround(world.getBlockState(pos.down())) && isGround(world.getBlockState(pos.down(2))) && noObjNear(world, pos))
		{
			StructureWorld tree = structures[random.nextInt(structures.length)];
			tree.generate(world, pos, random);
			return true;
		} return false;
	}
	
	protected abstract boolean isStructure(BlockState state);
	
	protected abstract boolean isGround(BlockState state);
	
	private boolean noObjNear(IWorld world, BlockPos pos)
	{
		int x1 = pos.getX() - distance;
		int z1 = pos.getZ() - distance;
		int x2 = pos.getX() + distance;
		int z2 = pos.getZ() + distance;
		POS.setY(pos.getY());
		for (int x = x1; x <= x2; x++)
		{
			POS.setX(x);
			for (int z = z1; z <= z2; z++)
			{
				POS.setZ(z);
				if (isInside(x - pos.getX(), z - pos.getZ()) && isStructure(world.getBlockState(POS)))
					return false;
			}
		}
		return true;
	}
	
	private boolean isInside(int x, int z)
	{
		return (Math.abs(x) + Math.abs(z)) <= manDist;
	}
}
