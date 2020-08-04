package com.redd90.betternether.event;

import java.util.Optional;
import java.util.UUID;

import com.redd90.betternether.BNConfig;
import com.redd90.betternether.util.SpawnUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BNLivingEntityDeathEvent {
	/*
	@SubscribeEvent
	public static void onLivingDeathEvent(LivingDeathEvent event) {
		if(BNConfig.NetherSpawn==true) {
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) event.getEntityLiving();
				UUID playerUUID = player.getGameProfile().getId();
				MinecraftServer mcserver = player.getServer();
				ServerPlayerEntity serverPlayer = mcserver.getPlayerList().getPlayerByUUID(playerUUID);
				//ServerWorld serverworld = serverPlayer.getServerWorld();
				boolean flag = serverPlayer.func_241142_M_();
				Optional<Vector3d> optional;
				ServerWorld nether = mcserver.getWorld(World.field_234919_h_);
				
				BlockPos spawnPos = serverPlayer.func_241140_K_();
				if (spawnPos != null) {
					optional = PlayerEntity.func_234567_a_(nether, spawnPos, flag, true);
				} else { optional = Optional.empty(); }
				
				if (!optional.isPresent()) {
					SpawnUtils.setNetherSpawn(serverPlayer);
				}
			}
		}
	}
	*/
}
