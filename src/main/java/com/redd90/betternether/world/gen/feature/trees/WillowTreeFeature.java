package com.redd90.betternether.world.gen.feature.trees;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.WillowBranchNaturalBlock;
import com.redd90.betternether.block.WillowBranchNaturalBlock.WillowBranchShape;
import com.redd90.betternether.block.WillowLeavesBlock;
import com.redd90.betternether.block.WillowTrunkBlock;
import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class WillowTreeFeature extends Feature<NoFeatureConfig> {

	private static final Direction[] HOR = HorizontalBlock.HORIZONTAL_FACING.getAllowedValues().toArray(new Direction[] {});
		
	public WillowTreeFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}
	
	public WillowTreeFeature() {
		this(NoFeatureConfig.field_236558_a_);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager manager, ChunkGenerator chunkGen,
			Random rand, BlockPos pos, NoFeatureConfig config) {
		if (!BlocksHelper.isNetherGround(world.getBlockState(pos.down())) || !world.getBlockState(pos).isAir())
			return false;
		
		int h2 = 5 + rand.nextInt(3);
		
		int mh = BlocksHelper.upRay(world, pos.up(), h2);
		if (mh < 5)
			return false;
		
		generate(world, pos, rand, h2);
		return true;
	}
	
	public void generate(IWorld world, BlockPos pos, Random random, int size)
	{
				
		int h2 = size;
		int mh = BlocksHelper.upRay(world, pos.up(), h2);
		
		h2 = Math.min(h2, mh);
		
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.WILLOW_TRUNK.get().getDefaultState().with(WillowTrunkBlock.SHAPE, TripleShape.BOTTOM));
		for (int h = 1; h < h2; h++)
			if (world.isAirBlock(pos.up(h)))
				BlocksHelper.setWithoutUpdate(world, pos.up(h), BNBlocks.WILLOW_TRUNK.get().getDefaultState().with(WillowTrunkBlock.SHAPE, TripleShape.MIDDLE));
		if (world.isAirBlock(pos.up(h2)))
			BlocksHelper.setWithoutUpdate(world, pos.up(h2), BNBlocks.WILLOW_TRUNK.get().getDefaultState().with(WillowTrunkBlock.SHAPE, TripleShape.TOP));
		
		for (int i = 0; i < 4; i++)
			branch(world, pos.up(h2).offset(HOR[i]), 3 + random.nextInt(2), random, HOR[i], pos.up(h2), 0);
		
		BlocksHelper.setWithoutUpdate(world, pos.up(h2 + 1), BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, Direction.UP));
		for (int i = 0; i < 4; i++)
			BlocksHelper.setWithoutUpdate(world, pos.up(h2 + 1).offset(HOR[i]), BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, HOR[i]));
	}
	
	private void branch(IWorld world, BlockPos pos, int length, Random random, Direction direction, BlockPos center, int level)
	{
		if (level > 5)
			return;
		Mutable bpos = new Mutable().setPos(pos);
		BlocksHelper.setWithoutUpdate(world, bpos, BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, direction));
		vine(world, pos.down(), 1 + random.nextInt(1));
		Direction preDir = direction;
		int l2 = length * length;
		for (int i = 0; i < l2; i ++)
		{
			Direction dir = random.nextInt(3) > 0 ? preDir : random.nextBoolean() ? preDir.rotateY() : preDir.rotateYCCW();//HOR[random.nextInt(4)];
			BlockPos p = bpos.offset(dir);
			if (world.isAirBlock(p))
			{
				bpos.setPos(p);
				if (bpos.manhattanDistance(center) > length)
					break;
				BlocksHelper.setWithoutUpdate(world, bpos, BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, dir));
				
				if (random.nextBoolean())
				{
					BlocksHelper.setWithoutUpdate(world, bpos.up(), BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, Direction.UP));
				}
				
				if (random.nextInt(3) == 0)
				{
					bpos.setY(bpos.getY() - 1);
					BlocksHelper.setWithoutUpdate(world, bpos, BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, Direction.DOWN));
				}
				
				if (random.nextBoolean())
					vine(world, bpos.down(), 1 + random.nextInt(4));
				
				if (random.nextBoolean())
				{
					Direction right = dir.rotateY();
					BlockPos p2 = bpos.offset(right);
					if (world.isAirBlock(p2))
						branch(world, p2, length, random, right, center, level + 1);
					right = right.getOpposite();
					p2 = bpos.offset(right);
					if (world.isAirBlock(p2))
						branch(world, p2, length, random, right, center, level + 1);
				}
				
				Direction dir2 = HOR[random.nextInt(4)];
				BlockPos p2 = bpos.offset(dir2);
				if (world.isAirBlock(p2))
					BlocksHelper.setWithoutUpdate(world, p2, BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, dir2));

				preDir = dir;
			}
		}
		if (random.nextBoolean())
		{
			if (world.isAirBlock(bpos))
				BlocksHelper.setWithoutUpdate(world, bpos, BNBlocks.WILLOW_LEAVES.get().getDefaultState().with(WillowLeavesBlock.FACING, preDir));
		}
	}
	
	private void vine(IWorld world, BlockPos pos, int length)
	{
		if (!world.isAirBlock(pos))
			return;
		
		for (int i = 0; i < length; i++)
		{
			BlockPos p = pos.down(i);
			if (world.isAirBlock(p.down()))
				BlocksHelper.setWithoutUpdate(world, p, BNBlocks.WILLOW_BRANCH_NATURAL.get().getDefaultState().with(WillowBranchNaturalBlock.SHAPE, WillowBranchShape.MIDDLE));
			else
			{
				BlocksHelper.setWithoutUpdate(world, p, BNBlocks.WILLOW_BRANCH_NATURAL.get().getDefaultState().with(WillowBranchNaturalBlock.SHAPE, WillowBranchShape.END));
				return;
			}
		}
		BlocksHelper.setWithoutUpdate(world, pos.down(length), BNBlocks.WILLOW_BRANCH_NATURAL.get().getDefaultState().with(WillowBranchNaturalBlock.SHAPE, WillowBranchShape.END));
	}


}
