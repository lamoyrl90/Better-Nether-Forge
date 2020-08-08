package com.redd90.betternether.block;

import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GiantMoldBlock extends BNBlock {

	private static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(0, 0.1, 0, 16, 16, 16);
	private static final VoxelShape MIDDLE_SHAPE = Block.makeCuboidShape(5, 0, 5, 11, 16, 11);
	public static final EnumProperty<TripleShape> SHAPE = EnumProperty.create("shape", TripleShape.class);
		
	public GiantMoldBlock() {
		super(BNBlockProperties.giantMold());
		this.properties.notSolid();
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(SHAPE);
    }
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return state.get(SHAPE) == TripleShape.TOP ? TOP_SHAPE : MIDDLE_SHAPE;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return state.get(SHAPE) == TripleShape.TOP ? new ItemStack(BNBlocks.GIANT_MOLD_SAPLING.get()) : new ItemStack(BNBlocks.MUSHROOM_STEM.get());
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		switch (state.get(SHAPE))
		{
		case BOTTOM:
			return state;
		case MIDDLE:
		case TOP:
		default:
			return world.getBlockState(pos.down()).getBlock() == this ? state : Blocks.AIR.getDefaultState();
		}
	}
}
