package com.redd90.betternether.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
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
import net.minecraftforge.common.Tags;

public class BarrelCactusBlock extends BNBlock implements IGrowable
{
	private static final VoxelShape SELECTION = Block.makeCuboidShape(0, 0, 0, 16, 14, 16);
	private static final VoxelShape COLLISION = Block.makeCuboidShape(1, 0, 1, 15, 13, 15);
	
	public BarrelCactusBlock()
	{
		super(Block.Properties.create(Material.CACTUS, MaterialColor.BLUE_TERRACOTTA)
				.sound(SoundType.CLOTH)
				.notSolid()
				.hardnessAndResistance(0.4F));
	}
	
	
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		Vector3d vec3d = state.getOffset(view, pos);
		return SELECTION.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		Vector3d vec3d = state.getOffset(view, pos);
		return COLLISION.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	@Override
	public Block.OffsetType getOffsetType()
	{
		return Block.OffsetType.XYZ;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		for(Direction direction : Direction.Plane.HORIZONTAL) {
			BlockState blockstate = world.getBlockState(pos.offset(direction));
			Material material = blockstate.getMaterial();
			if (material.isSolid() || world.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER)) {
				return false;
			}
		}
		
		Block down = world.getBlockState(pos.down()).getBlock();
		return down.isIn(Tags.Blocks.GRAVEL);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (isValidPosition(state, world, pos))
			{
				return this.getDefaultState();
			}
			else {
			return Blocks.AIR.getDefaultState();
			}
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
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
}

