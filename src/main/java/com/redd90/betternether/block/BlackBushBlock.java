package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlackBushBlock extends BNBlock implements IGrowable {
	private static final VoxelShape SHAPE = VoxelShapes.create(0.1875, 0.0, 0.1875, 0.8125, 0.625, 0.8125);
	
	public BlackBushBlock()
	{
		super(BNBlockProperties.plant(MaterialColor.BLACK, false));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return BlocksHelper.isNetherGround(world.getBlockState(pos.down()));
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
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient)
	{
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random random, BlockPos pos, BlockState state)
	{
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		spawnAsEntity(world, pos, new ItemStack(this.asItem()));
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {	
		if (entityIn instanceof LivingEntity) {
			entityIn.setMotionMultiplier(state, new Vector3d((double) 0.8F, 0.75D, (double) 0.8F));
		}
	}
}
