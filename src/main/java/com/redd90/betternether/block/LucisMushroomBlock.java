package com.redd90.betternether.block;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class LucisMushroomBlock extends BNBlock {
	private static final VoxelShape V_SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 9, 16);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final EnumProperty<EnumShape> SHAPE = EnumProperty.create("shape", EnumShape.class);

	public LucisMushroomBlock()
	{
		super(Block.Properties.create(Material.ORGANIC, MaterialColor.YELLOW)
				.harvestTool(ToolType.AXE)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1F)
				.notSolid());
		this.setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(SHAPE, EnumShape.CORNER));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(FACING, SHAPE);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		Direction direction = (Direction) state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return BlocksHelper.isNetherrack(blockState);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (isValidPosition(state, world, pos))
			return state;
		else
			return Blocks.AIR.getDefaultState();
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return V_SHAPE;
	}

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }
	
	public enum EnumShape implements IStringSerializable
	{
		CORNER("corner"),
		SIDE("side"),
		CENTER("center");

		final String name;
		
		EnumShape(String name)
		{
			this.name = name;
		}
		
		@Override
		public String getString()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return name;
		}
	}
}
