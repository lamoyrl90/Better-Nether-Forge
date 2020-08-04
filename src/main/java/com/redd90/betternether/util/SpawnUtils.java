package com.redd90.betternether.util;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.redd90.betternether.registry.BNBiomes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.SpawnLocationHelper;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;

public class SpawnUtils {
	
	
	
	//Probably best to limit this to biomes with wood available
	private static final Set<Biome> safeNetherSpawnBiomes = ImmutableSet.of(
			Biomes.CRIMSON_FOREST, 
			Biomes.WARPED_FOREST, 
			//BNBiomes.GRAVEL_DESERT.get(), 
			//BNBiomes.OBSIDIAN_CHASM.get(), 
			BNBiomes.SWELTERING_SWAMPLAND.get(),
			BNBiomes.FUNGAL_WOODLANDS.get(),
			//BNBiomes.FUNGAL_WOODLANDS_EDGE.get(),
			BNBiomes.OLD_FUNGIWOODS.get()//,
			//BNBiomes.NETHER_GRASSLANDS.get()
			);
	
	public static ServerWorld getNether(ServerPlayerEntity serverplayer) {
		return serverplayer.getServer().getWorld(World.field_234919_h_);
	}
	
	public static void netherSpawn(ServerPlayerEntity player) {
		ServerWorld overworld = player.getServerWorld();
		MinecraftServer mcserver = overworld.getServer();
		RegistryKey<World> netherKey = World.field_234919_h_;
		ServerWorld nether = mcserver.getWorld(netherKey);
		ChunkPos chunkPos = new ChunkPos(player.getPosition());
		
		//Get rough guesstimate for nether spawn location using Vanilla function
		
		BlockPos netherSpawnPos = SpawnLocationHelper.func_241094_a_(nether, chunkPos, false);
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw;
		
		//Get the closest spawnable nether biome
		
		BlockPos nearestSafeBiome = findNearestSpawnBiome(player, nether, netherSpawnPos);
		
		//Get an actually (likely) safe spawn location in the Nether
		
		BlockPos safeSpawn = getSafePos(nether, nearestSafeBiome);
		
		//Teleport the player, apply Fire Resistance (cause lava and magma are still a thing), and set player Nether spawn
		
		player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600));
		player.teleport(nether, safeSpawn.getX(), safeSpawn.getY(), safeSpawn.getZ(), yaw, pitch);
		player.func_241153_a_(netherKey, safeSpawn, true, false);
		
	}
	
	public static void netherSpawn(PlayerEntity player, UUID uuid) {
		MinecraftServer mcserver = player.getServer();
		ServerPlayerEntity serverPlayer = mcserver.getPlayerList().getPlayerByUUID(uuid);
		RegistryKey<World> netherKey = World.field_234919_h_;
		ServerWorld nether = mcserver.getWorld(netherKey);
		ChunkPos chunkPos = new ChunkPos(player.getPosition());
		
		//Get rough guesstimate for nether spawn location using Vanilla function
		
		BlockPos netherSpawnPos = SpawnLocationHelper.func_241094_a_(nether, chunkPos, false);
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw;
		
		//Get the closest spawnable nether biome
		BlockPos nearestSafeBiome = findNearestSpawnBiome(serverPlayer, nether, netherSpawnPos);
		
		//Get an actually (likely) safe spawn location in the Nether
		
		BlockPos safeSpawn = getSafePos(nether, nearestSafeBiome);
		
		//Teleport the player, apply Fire Resistance (cause lava and magma are still a thing), and set player Nether spawn
		
		player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600));
		serverPlayer.teleport(nether, safeSpawn.getX(), safeSpawn.getY(), safeSpawn.getZ(), yaw, pitch);
		//serverPlayer.func_241153_a_(netherKey, safeSpawn, true, false);
		
	}
	
	public static void setNetherSpawn(ServerPlayerEntity serverplayer) {
		MinecraftServer mcserver = serverplayer.getServer();
		RegistryKey<World> netherKey = World.field_234919_h_;
		ServerWorld nether = mcserver.getWorld(netherKey);
		ChunkPos chunkPos = new ChunkPos(BlockPos.ZERO);
		
		BlockPos netherSpawnPos = SpawnLocationHelper.func_241094_a_(nether, chunkPos, false);
		BlockPos nearestSafeBiome = findNearestSpawnBiome(serverplayer, nether, netherSpawnPos);
		BlockPos safeSpawn = getSafePos(nether, nearestSafeBiome);
		serverplayer.func_241153_a_(netherKey, safeSpawn, true, false);
	}
	
	private static boolean checkSafe(IWorld world, BlockPos pos) {
		Mutable npos = pos.toMutable();
		
		if (world.getBlockState(npos.down()).isSolid() 
				&& world.getFluidState(npos.down()).isEmpty() 
				/*&& safeNetherSpawnBiomes.contains(world.getBiome(npos))*/) {
			for(int i=0;i<8;i++) {
				npos.move(Direction.UP);
				if(!world.isAirBlock(npos)) {
					return false;
				}
			}
				return true;
		 } else return false;
	}
	
	private static BlockPos findNearestSpawnBiome(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		
		double min = 0;
		@Nullable BlockPos test = null;
		@Nullable BlockPos nearest = null;
		
		//BetterNether.LOGGER.debug("Searching for spawn biome...");
		
		if (world == null) {
			world = SpawnUtils.getNether(player);
		}
		
		if (pos == null) {
			pos = BlockPos.ZERO;
		}
		
		
		for (Biome biome : safeNetherSpawnBiomes) {
			
			//BetterNether.LOGGER.debug("Searching for " + biome.getRegistryName().getPath());
			test = world.func_241116_a_(biome, pos, 6400, 8);
			if (test != null) {
				//BetterNether.LOGGER.debug(biome.getRegistryName().getPath() + " located at " + test.toString());
				double dist = Math.sqrt(Math.pow(test.getX()-pos.getX(),2)+Math.pow(test.getZ()-pos.getZ(),2));
				//BetterNether.LOGGER.debug(dist + " meters away");
				if (dist < min)
					nearest = test;
					min = dist;
			} //else BetterNether.LOGGER.debug(biome.getRegistryName().getPath() + " not found");
		}
		
		if (nearest != null) {
			return nearest;
		} else return BlockPos.ZERO;
		
	}
	
	private static BlockPos getSafePos(ServerWorld world, BlockPos initialPos) {
		Mutable testPos = initialPos.toMutable();
		Random random = world.getRandom();
		int x = initialPos.getX();
		int z = initialPos.getZ();
		int y = initialPos.getY();
		
			while(!checkSafe(world, testPos)) {
				while(y>0) {
					testPos.move(Direction.DOWN);
					y=testPos.getY();
				}
				x = testPos.getX() + random.nextInt(9) - 4;
				z = testPos.getZ() + random.nextInt(9) - 4;
				y = random.nextInt(64)+32;
				testPos.setPos(x,y,z);
			}
	
		
		return testPos;
	}
}
