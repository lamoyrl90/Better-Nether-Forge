package com.redd90.betternether.block;

import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;


public class LumabusVineBlock extends BNBlock {
	private static final VoxelShape MIDDLE_SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 16, 12);
	private static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(2, 4, 2, 14, 16, 14);
	public static final EnumProperty<TripleShape> SHAPE = EnumProperty.create("shape", TripleShape.class);

	public LumabusVineBlock()
	{
		super(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.CYAN)
				.sound(SoundType.CROP)
				.doesNotBlockMovement()
				.zeroHardnessAndResistance()
				.notSolid());
		this.setRenderLayer(BNRenderLayer.CUTOUT);
		this.setDefaultState(getStateContainer().getBaseState().with(SHAPE, TripleShape.TOP));
	}
	
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> StateContainer)
	{
        StateContainer.add(SHAPE);
    }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return state.get(SHAPE) == TripleShape.BOTTOM ? BOTTOM_SHAPE : MIDDLE_SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState upState = world.getBlockState(pos.up());
		return upState.getBlock() == this || upState.isSolidSide(world, pos, Direction.DOWN);
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
		return isValidPosition(state, world, pos) && (world.getBlockState(pos.down()).getBlock() == this || state.get(SHAPE) == TripleShape.BOTTOM) ? state : Blocks.AIR.getDefaultState();
	}
	
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return new ItemStack(BNBlocks.LUMABUS_SEED.get());
	}
	
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() instanceof LumabusVineBlock) {
    		return world.getBlockState(pos).get(SHAPE) == TripleShape.BOTTOM ? 15 : 0;
        }
        return 0;
    }
}
