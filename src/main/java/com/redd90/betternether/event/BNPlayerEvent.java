package com.redd90.betternether.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.redd90.betternether.BNConfig;
import com.redd90.betternether.util.SpawnUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.util.RegistryKey;
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
		if(BNConfig.NetherSpawn==true) {
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
		if(BNConfig.NetherSpawn==true) {
			PlayerEntity original = event.getOriginal();
			PlayerEntity clone = event.getPlayer();
			ServerWorld overworld = original.getServer().getWorld(World.field_234918_g_);
			
			deathCheck.put(original, event.isWasDeath() 
					&& original.getEntityWorld() != overworld
					&& clone.getEntityWorld() == overworld);
		}
	}
	
	@SubscribeEvent
	public static void PlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
		if(BNConfig.NetherSpawn==true) {
			PlayerEntity player = event.getPlayer();
			UUID uuid = player.getGameProfile().getId();
			boolean netherspawn = deathCheck.get(player);
			deathCheck.remove(player);
			if (netherspawn)
				SpawnUtils.netherSpawn(player, uuid);
		}
	}
	
}
