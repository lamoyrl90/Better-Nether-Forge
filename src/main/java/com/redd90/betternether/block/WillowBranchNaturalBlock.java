package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class WillowBranchNaturalBlock extends BNBlock {

	private static final VoxelShape V_SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 16, 12);
	public static final EnumProperty<WillowBranchShape> SHAPE = EnumProperty.create("shape", WillowBranchShape.class);
	
	
	public WillowBranchNaturalBlock() {
		super(BNBlockProperties.WILLOW_BRANCH);
		this.setRenderLayer(BNRenderLayer.CUTOUT);
		this.setDefaultState(getStateContainer().getBaseState().with(SHAPE, WillowBranchShape.MIDDLE));
	}

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() instanceof WillowBranchNaturalBlock) {
    		return world.getBlockState(pos).get(SHAPE) == WillowBranchShape.END ? 15 : 0;
        }
        return 0;
    }
	
	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(SHAPE);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return V_SHAPE;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (world.isAirBlock(pos.up()))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}
	
	public enum WillowBranchShape implements IStringSerializable
	{
		END("end"),
		MIDDLE("middle");
		
		final String name;
		
		WillowBranchShape(String name)
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
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {	
		if (entityIn instanceof LivingEntity) {
			entityIn.setMotionMultiplier(state, new Vector3d((double) 0.8F, 0.75D, (double) 0.8F));
		}
	}
	
}
