package com.redd90.betternether.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

public class NetherCactusBlock extends BNBlock {

	private static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 8, 12);
	private static final VoxelShape SIDE_SHAPE = Block.makeCuboidShape(5, 0, 5, 11, 16, 11);
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;

	
	
	public NetherCactusBlock()
	{
		super(Properties.create(Material.CACTUS, MaterialColor.ORANGE_TERRACOTTA)
				.sound(SoundType.CLOTH)
				.notSolid()
				.hardnessAndResistance(0.4F)
				.tickRandomly());
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
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return state.get(TOP).booleanValue() ? TOP_SHAPE : SIDE_SHAPE;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (isValidPosition(state, world, pos))
		{
			Block up = world.getBlockState(pos.up()).getBlock();
			if (up == this)
				return state.with(TOP, false);
			else
				return this.getDefaultState();
		}
		else
			return Blocks.AIR.getDefaultState();
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		Block down = world.getBlockState(pos.down()).getBlock();
		return down.getDefaultState().isIn(Tags.Blocks.GRAVEL) || down == this;
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
	      if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
	      if (!state.isValidPosition(worldIn, pos)) {
	         worldIn.destroyBlock(pos, true);
	      }

	   }
	
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		BlockPos blockpos = pos.up();
		if (worldIn.isAirBlock(blockpos)) {
			int i;
			for(i = 1; worldIn.getBlockState(pos.down(i)).isIn(this); ++i) {
			}

			if (i < 3) {
				int j = state.get(AGE);
				if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
					if (j == 15) {
						worldIn.setBlockState(blockpos, this.getDefaultState());
						BlockState blockstate = state.with(TOP, false).with(AGE, Integer.valueOf(0));
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
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
	}
}
