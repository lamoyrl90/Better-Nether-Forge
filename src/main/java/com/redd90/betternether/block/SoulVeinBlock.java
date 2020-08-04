package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.registry.BNBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SoulVeinBlock extends BNBlock implements IGrowable{
private static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 1, 16);
	
	public SoulVeinBlock()
	{
		super(Block.Properties.create(Material.PLANTS, MaterialColor.PURPLE)
				.sound(SoundType.CROP)
				.notSolid()
				.doesNotBlockMovement()
				.zeroHardnessAndResistance()
				.tickRandomly());
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == Blocks.SOUL_SAND || block == BNBlocks.VEINED_SAND.get();
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (isValidPosition(state, world, pos))
			return state;
		else
			return Blocks.AIR.getDefaultState();
	}

	@Override
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient)
	{
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random random, BlockPos pos, BlockState state)
	{
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		spawnAsEntity(world, pos, new ItemStack(this.asItem()));
	}

	private boolean canSpread(IWorld world, BlockPos pos) {
		return world.isAirBlock(pos) && world.getBlockState(pos.down()).isIn(Blocks.SOUL_SAND) && world.getLight(pos) > 11; 
	}
	
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;
		
		Direction dir = Direction.byIndex(random.nextInt(4)+2);
		BlockPos blockpos = new BlockPos(pos.offset(dir));
		if (canSpread(worldIn, blockpos))
			worldIn.setBlockState(blockpos, this.getDefaultState());
	}
	
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack)
	{
		if (world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND)
			world.setBlockState(pos.down(), BNBlocks.VEINED_SAND.get().getDefaultState());
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {	
		if (entityIn instanceof LivingEntity) {
			entityIn.setMotionMultiplier(state, new Vector3d((double) 0.8F, 0.75D, (double) 0.8F));
		}
	}
}
