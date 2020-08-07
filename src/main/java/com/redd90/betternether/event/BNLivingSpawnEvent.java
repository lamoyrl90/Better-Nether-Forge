package com.redd90.betternether.event;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BNLivingSpawnEvent {
	
	private static Mutable pos = new Mutable();
	private static final Block MAGMA = Blocks.MAGMA_BLOCK;
	private static final EntityType<?> MAGMA_CUBE = EntityType.MAGMA_CUBE;
	
@SubscribeEvent
	public static void checkLightLevels(LivingSpawnEvent.SpecialSpawn event) {
		pos.setPos(event.getX(), event.getY(), event.getZ());
		IWorld world = event.getWorld();
		
		boolean magma = world.getBlockState(pos.down()).getBlock() == MAGMA;
		boolean cube = event.getEntityLiving().getType() == MAGMA_CUBE;
		
		if (!magma && !cube) {
			if (BiomeDictionary.getTypes(event.getWorld().getBiome(pos)).contains(BiomeDictionary.Type.NETHER)){
				if ((event.getSpawnReason() == SpawnReason.NATURAL) || (event.getSpawnReason() == SpawnReason.SPAWNER)) {
					if (event.getEntity().getClassification(false) == EntityClassification.MONSTER) {
						if (world.getLightValue(pos) > getLightLevelForDifficulty(world.getDifficulty()))
							event.setResult(Event.Result.DENY); else event.setResult(Event.Result.DEFAULT);
					} else event.setResult(Event.Result.DEFAULT);
				} else event.setResult(Event.Result.DEFAULT);
			} else event.setResult(Event.Result.DEFAULT);
		}
	}

	private static int getLightLevelForDifficulty(Difficulty difficulty) {
		switch (difficulty) {
		case EASY:
			return 7;
		case NORMAL:
			return 11;
		case HARD:
			return 13;
		default: 
			return 7;
		}
	}
}
