package com.redd90.betternether.block;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BNGrassBlock extends BNBlock {
	private static final VoxelShape SHAPE = Block.makeCuboidShape(2, 0, 2, 14, 12, 14);
	
	public BNGrassBlock()
	{
		super(BNBlockProperties.plant(MaterialColor.GRAY_TERRACOTTA, false));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		Vector3d vec3d = state.getOffset(view, pos);
		return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	@Override
	public Block.OffsetType getOffsetType()
	{
		return Block.OffsetType.XZ;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return BlocksHelper.isNetherrack(world.getBlockState(pos.down())) || BlocksHelper.isNylium(world.getBlockState(pos.down()));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (!isValidPosition(state, world, pos))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}
}
