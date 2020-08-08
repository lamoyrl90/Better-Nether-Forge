package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class InkBushBlock extends BNPlantBlock {

	public InkBushBlock() {
		super(BNBlockProperties.plant(MaterialColor.BLACK, false));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {	
		if (entityIn instanceof LivingEntity) {
			entityIn.setMotionMultiplier(state, new Vector3d((double) 0.8F, 0.75D, (double) 0.8F));
		}
	}
	
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int i = state.get(AGE);
		boolean flag = i == 3;
		if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (i == 3) {
			int j = 1 + worldIn.rand.nextInt(2);
			int k = worldIn.rand.nextBoolean() ? 1 : 0;
			spawnAsEntity(worldIn, pos, new ItemStack(Items.INK_SAC, j));
			spawnAsEntity(worldIn, pos, new ItemStack(BNBlocks.INK_BUSH_SEED.get(), k));
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(1)), 2);
			return ActionResultType.func_233537_a_(worldIn.isRemote);
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return new ItemStack(BNBlocks.INK_BUSH_SEED.get());
	}

}
