package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;
import com.redd90.betternether.world.gen.feature.plants.JellyfishMushroomFeature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

public class JellyfishMushroomSaplingBlock extends BNBlock implements IGrowable {

	private static final VoxelShape SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 6, 12);
	private static final JellyfishMushroomFeature STRUCTURE = new JellyfishMushroomFeature(NoFeatureConfig.field_236558_a_);
	
	public JellyfishMushroomSaplingBlock() {
		super(BNBlockProperties.plant(MaterialColor.CYAN, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).getBlock() == BNBlocks.MUSHROOM_GRASS.get();
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (!isValidPosition(state, world, pos))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return BlocksHelper.isFertile(worldIn.getBlockState(pos.down())) ? (rand.nextInt(8) == 0) : (rand.nextInt(16) == 0);
	}

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		STRUCTURE.grow(worldIn, pos, rand);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		super.randomTick(state, world, pos, random);
		if (canUseBonemeal(world, random, pos, state))
			grow(world, random, pos, state);
	}
	
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 3;
    }

}
