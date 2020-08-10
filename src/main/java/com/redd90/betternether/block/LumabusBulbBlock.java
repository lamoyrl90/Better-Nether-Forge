package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class LumabusBulbBlock extends BNBlock {
	
	private static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(2, 4, 2, 14, 16, 14);

	public LumabusBulbBlock()
	{
		super(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.CYAN)
				.sound(SoundType.CROP)
				.doesNotBlockMovement()
				.zeroHardnessAndResistance()
				.notSolid()
				.setLightLevel((state)->{return 15;}));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return BOTTOM_SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState upState = world.getBlockState(pos.up());
		return upState.getBlock() == BNBlocks.LUMABUS_VINE.get() || upState.isSolidSide(world, pos, Direction.DOWN);
	}

	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader view, BlockPos pos)
	{
		return true;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		return isValidPosition(state, world, pos) ? state : Blocks.AIR.getDefaultState();
	}
	
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return new ItemStack(BNBlocks.LUMABUS_SEED.get());
	}
}
