package com.redd90.betternether.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WillowFruitBlock extends BNBlock {

	private static final VoxelShape V_SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 16, 12);
	public static BooleanProperty BRANCH = BooleanProperty.create("branch");
	
	public WillowFruitBlock() {
		super(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.LIGHT_BLUE).zeroHardnessAndResistance().sound(SoundType.SHROOMLIGHT).doesNotBlockMovement().notSolid().setLightLevel((state)->{return 15;}));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
		this.setDefaultState(getStateContainer().getBaseState().with(BRANCH, false));
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(BRANCH);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return V_SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{		
		BlockState up = world.getBlockState(pos.up());
		if (up.isSolid() || up.getBlock() instanceof WillowBranchBlock) {
			return true;
		} else return false;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (isValidPosition(state, world, pos))
		{
			Block up = world.getBlockState(pos.up()).getBlock();
			if (up instanceof WillowBranchBlock)
				return state.with(BRANCH, true);
			else
				return this.getDefaultState();
		}
		else
			return Blocks.AIR.getDefaultState();
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) {
		BlockPos pos = ctx.getPos();
		Block up = ctx.getWorld().getBlockState(pos.up()).getBlock();
		if (up instanceof WillowBranchBlock) {
			return this.getDefaultState().with(BRANCH, true);
		} else return this.getDefaultState();
	}
}
