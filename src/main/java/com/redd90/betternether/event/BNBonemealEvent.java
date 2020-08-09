package com.redd90.betternether.event;

import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;
import com.redd90.betternether.world.biome.SwelteringSwamplandBiome;
import com.redd90.betternether.world.biome.TorridTerracesBiome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BNBonemealEvent {

	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event) {
		World world = event.getWorld();
		BlockPos blockPos = event.getPos();
		if (!world.isRemote)
		{
			if (BlocksHelper.isNetherrack(world.getBlockState(blockPos)))
			{
				BlockState nylium = getNylium(world, blockPos);
				boolean consume = true;
				if (nylium != null && world.getBlockState(blockPos).getBlock() == Blocks.NETHERRACK)
				{
					BlocksHelper.setWithoutUpdate(world, blockPos, nylium);
				}
				else
				{
					consume = growGrass(world, blockPos);
				}
				if (consume)
				{
					if (!event.getPlayer().isCreative())
						event.getStack().shrink(1);
					world.playEvent(2005, blockPos, 0);
					event.setResult(Result.ALLOW);
				}
			}
			else if (BlocksHelper.isSoulSand(world.getBlockState(blockPos)))
			{
				if (growGrass(world, blockPos))
				{
					world.playEvent(2005, blockPos, 0);
					if (!event.getPlayer().isCreative())
						event.getStack().shrink(1);
					event.setResult(Result.ALLOW);
				}
			}
		}
	}
	
	private static boolean growGrass(World world, BlockPos pos)
	{
		Mutable POS = new Mutable();
		
		int y1 = pos.getY() + 3;
		int y2 = pos.getY() - 3;
		boolean result = false;
		for (int i = 0; i < 64; i++)
		{
			int x = (int) (pos.getX() + world.rand.nextGaussian() * 2);
			int z = (int) (pos.getZ() + world.rand.nextGaussian() * 2);
			POS.setX(x);
			POS.setZ(z);
			for (int y = y1; y >= y2; y--)
			{
				POS.setY(y);
				BlockPos down = POS.down();
				if (world.isAirBlock(POS) && !world.isAirBlock(down))
				{
					BlockState grass = getGrassState(world, down);
					if (grass != null)
					{
						BlocksHelper.setWithoutUpdate(world, POS, grass);
					}
					break;
				}
			}
		}
		return result;
	}
	
	private static BlockState getGrassState(World world, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() == BNBlocks.JUNGLE_GRASS.get())
			return BNBlocks.JUNGLE_PLANT.get().getDefaultState();
		else if (BlocksHelper.isSoulSand(state))
			return BNBlocks.SOUL_GRASS.get().getDefaultState();
		else if (state.getBlock() == BNBlocks.MUSHROOM_GRASS.get())
			return BNBlocks.BONE_GRASS.get().getDefaultState();
		else if (BlocksHelper.isNetherrack(state) && !BlocksHelper.isNylium(state))
			return world.getBiome(pos) instanceof SwelteringSwamplandBiome || world.getBiome(pos) instanceof TorridTerracesBiome ?
					BNBlocks.SWAMP_GRASS.get().getDefaultState() :
						BNBlocks.NETHER_GRASS.get().getDefaultState();
		return null;
	}
	
	private static BlockState getNylium(World world, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos.north());
		if (BlocksHelper.isNylium(state))
			return state;
		
		state = world.getBlockState(pos.south());
		if (BlocksHelper.isNylium(state))
			return state;
		
		state = world.getBlockState(pos.east());
		if (BlocksHelper.isNylium(state))
			return state;
		
		state = world.getBlockState(pos.west());
		if (BlocksHelper.isNylium(state))
			return state;
		
		return null;
	}
}
