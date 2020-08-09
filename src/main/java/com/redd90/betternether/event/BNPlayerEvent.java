package com.redd90.betternether.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.redd90.betternether.BNConfig;
import com.redd90.betternether.util.SpawnUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class BNPlayerEvent {
	
	private static HashMap<PlayerEntity, Boolean> deathCheck = new HashMap<PlayerEntity, Boolean>();
	
	@SubscribeEvent
	public static void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		if(BNConfig.CommonConfig.netherSpawn.get()) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
	
	        int timePlayed = player.getStats().getValue(Stats.CUSTOM.get(Stats.PLAY_ONE_MINUTE));
	
	        if(timePlayed == 0)
	        {
	        	SpawnUtils.netherSpawn(player);
	        }
	        
		}
	}

	
	@SubscribeEvent
	public static void PlayerCopyEvent(PlayerEvent.Clone event) {
		if(BNConfig.CommonConfig.netherSpawn.get()) {
			PlayerEntity player = event.getOriginal();
			ServerPlayerEntity serverplayer = (ServerPlayerEntity) player;
			MinecraftServer server = serverplayer.getServer();
			
			
			BlockPos blockpos = serverplayer.func_241140_K_();
			boolean spawnPointSet = serverplayer.func_241142_M_();
			Optional<Vector3d> optional;
			ServerWorld respawnWorld = server.getWorld(serverplayer.func_241141_L_());
			
			if (respawnWorld != null && blockpos != null) {
				optional = PlayerEntity.func_234567_a_(respawnWorld, blockpos, spawnPointSet, !event.isWasDeath());
			} else {
				optional = Optional.empty();
			}
			
			if (respawnWorld == null || !optional.isPresent()) {
				deathCheck.put(player, true);
			} else {
				deathCheck.put(player, false);
			}
		}
	}
	
	
	@SubscribeEvent
	public static void PlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
		if(BNConfig.CommonConfig.netherSpawn.get()) {
			PlayerEntity player = event.getPlayer();
			UUID uuid = player.getGameProfile().getId();
			boolean netherspawn = deathCheck.remove(player);
			if (netherspawn)
				SpawnUtils.netherSpawn(player, uuid);
		}
	}
	
}
