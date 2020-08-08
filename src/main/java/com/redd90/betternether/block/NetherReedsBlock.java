package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class NetherReedsBlock extends BNBlock {
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
	
	public NetherReedsBlock()
	{
		super(BNBlockProperties.plant(MaterialColor.CYAN, false));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
		this.setDefaultState(getStateContainer().getBaseState().with(TOP, true).with(AGE, 0));
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(TOP);
        stateManager.add(AGE);
    }
	
	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		Block up = world.getBlockState(pos.up()).getBlock();
		BlockState down = world.getBlockState(pos.down());
		if (BlocksHelper.isNetherGround(down))
		{
			BlockPos posDown = pos.down();
			boolean lava = BlocksHelper.isLava(world.getBlockState(posDown.north()));
			lava = lava || BlocksHelper.isLava(world.getBlockState(posDown.south()));
			lava = lava || BlocksHelper.isLava(world.getBlockState(posDown.east()));
			lava = lava || BlocksHelper.isLava(world.getBlockState(posDown.west()));
			if (lava)
			{
				return up == this ? state.with(TOP, false) : state;
			}
			return Blocks.AIR.getDefaultState();
		}
		else if (down.getBlock() != this)
			return Blocks.AIR.getDefaultState();
		else if (up != this)
			return state;
		else
			return state.with(TOP, false);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos posDown = pos.down();
		BlockState down = world.getBlockState(posDown);
		if (BlocksHelper.isNetherGround(down))
		{
			boolean lava = BlocksHelper.isLava(world.getBlockState(posDown.north()));
			lava = lava || BlocksHelper.isLava(world.getBlockState(posDown.south()));
			lava = lava || BlocksHelper.isLava(world.getBlockState(posDown.east()));
			lava = lava || BlocksHelper.isLava(world.getBlockState(posDown.west()));
			return lava;
		}
		else
			return down.getBlock() == this;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!state.isValidPosition(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (worldIn.isAirBlock(pos.up())) {
			int i;
			for(i = 1; worldIn.getBlockState(pos.down(i)).isIn(this); ++i) {
			}

			if (i < 3) {
				int j = state.get(AGE);
				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
					if (j == 15) {
						worldIn.setBlockState(pos.up(), this.getDefaultState());
						worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(0)), 4);
					} else {
						worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(j + 1)), 4);
					}
				}
			}
		}
	}
}
