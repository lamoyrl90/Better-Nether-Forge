package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class OrangeMushroomBlock extends BNPlantBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[] {
			VoxelShapes.create(0.25, 0.0, 0.25, 0.75, 0.375, 0.75),
			VoxelShapes.create(0.125, 0.0, 0.125, 0.875, 0.625, 0.875),
			VoxelShapes.create(0.0625, 0.0, 0.0625, 0.9375, 0.875, 0.9375),
			VoxelShapes.create(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)
	};

	public OrangeMushroomBlock()
	{
		super(BNBlockProperties.plant(MaterialColor.ORANGE_TERRACOTTA, false).hardnessAndResistance(0.5f));
	}


	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPES[state.get(AGE)];
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPES[state.get(AGE)];
	}
}
