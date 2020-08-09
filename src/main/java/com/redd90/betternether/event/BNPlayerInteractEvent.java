package com.redd90.betternether.event;

import java.util.Random;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BNPlayerInteractEvent {
	
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		ItemStack itemstack = event.getItemStack();
		BlockPos pos = event.getPos();
		World world = event.getWorld();
		PlayerEntity player = event.getPlayer();
		Direction face = event.getFace();
		
		
		if (itemstack.getItem() == Items.BLAZE_POWDER) {
	         BlockPos blockpos = pos.offset(face);
	         if (AbstractFireBlock.canLightBlock(world, blockpos)) {
	            playSound(world, pos, world.getRandom());
	            world.setBlockState(blockpos, AbstractFireBlock.getFireForPlacement(world, blockpos), 1);
	            if (!player.isCreative())
	            	itemstack.shrink(1);
	         }
		}
	}
	
	private static void playSound(IWorld world, BlockPos pos, Random random) {
		world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
	}
	
}
