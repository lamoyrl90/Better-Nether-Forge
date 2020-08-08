package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BNVineBlock extends BNBlock implements IGrowable {

	private static final VoxelShape SHAPE = Block.makeCuboidShape(2, 0, 2, 14, 16, 14);
	public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
	
	public BNVineBlock(MaterialColor color) {
		super(BNBlockProperties.vine(color));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
		this.setDefaultState(getStateContainer().getBaseState().with(BOTTOM, true));
	}

	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(BOTTOM);
        stateManager.add(AGE);
    }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState upState = world.getBlockState(pos.up());
		return upState.getBlock() == this || upState.isSolidSide(world, pos, Direction.DOWN);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightLevel(BlockState state, IBlockReader view, BlockPos pos)
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
		if (isValidPosition(state, world, pos))
			return world.getBlockState(pos.down()).getBlock() == this ? state.with(BOTTOM, false) : state.with(BOTTOM, true);
		else
			return Blocks.AIR.getDefaultState();
	}
	
	@Override
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient)
	{
		Mutable blockPos = new Mutable().setPos(pos);
		for (int y = pos.getY() - 1; y > 1; y--)
		{
			blockPos.setY(y);
			if (world.getBlockState(blockPos).getBlock() != this)
				return world.getBlockState(blockPos).getBlock() == Blocks.AIR;
		}
		return false;
	}

	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		BlockPos blockpos = pos.down();
		if (worldIn.isAirBlock(blockpos)) {
			int i;
			for(i = 1; worldIn.getBlockState(pos.up(i)).isIn(this); ++i) {
			}

			if (i < 25) {
				int j = state.get(AGE);
				if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
					if (j == 15) {
						worldIn.setBlockState(blockpos, this.getDefaultState());
						BlockState blockstate = state.with(BOTTOM, false).with(AGE, Integer.valueOf(0));
						worldIn.setBlockState(pos, blockstate, 4);
						blockstate.neighborChanged(worldIn, blockpos, this, pos, false);
					} else {
						worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(j + 1)), 4);
					}
					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
				}
			}
		}
	}
	
	@Override
	public boolean canUseBonemeal(World world, Random random, BlockPos pos, BlockState state)
	{
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		Mutable blockPos = new Mutable().setPos(pos);
		for (int y = pos.getY(); y > 1; y--)
		{
			blockPos.setY(y);
			if (world.getBlockState(blockPos).getBlock() != this)
				break;
		}
		BlocksHelper.setWithoutUpdate(world, blockPos.up(), getDefaultState().with(BOTTOM, false));
		BlocksHelper.setWithoutUpdate(world, blockPos, getDefaultState().with(BOTTOM, true));
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {	
		if (entityIn instanceof LivingEntity) {
			entityIn.setMotionMultiplier(state, new Vector3d((double) 0.8F, 0.75D, (double) 0.8F));
		}
	}
}
