package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class CincinnasitePillarBlock extends BNBlock {

	public static final EnumProperty<CincinnasitePillarShape> SHAPE = EnumProperty.create("shape", CincinnasitePillarShape.class);
		
	public CincinnasitePillarBlock(Properties properties) {
		super(properties);
	}

	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(SHAPE);
    }
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		boolean top = world.getBlockState(pos.up()).getBlock() == this;
		boolean bottom = world.getBlockState(pos.down()).getBlock() == this;
		if (top && bottom)
			return state.with(SHAPE, CincinnasitePillarShape.MIDDLE);
		else if (top)
			return state.with(SHAPE, CincinnasitePillarShape.BOTTOM);
		else if (bottom)
			return state.with(SHAPE, CincinnasitePillarShape.TOP);
		else
			return state.with(SHAPE, CincinnasitePillarShape.SMALL);
	}
	
	public static enum CincinnasitePillarShape implements IStringSerializable
	{
		SMALL("small"),
		TOP("top"),
		MIDDLE("middle"),
		BOTTOM("bottom");
		
		final String name;
		
		CincinnasitePillarShape(String name)
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
