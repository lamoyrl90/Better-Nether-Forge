package com.redd90.betternether.registry;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.world.gen.carver.ObsidianChasmCarver;

import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNCarvers {
	public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, BetterNether.MODID);
	
	public static final RegistryObject<WorldCarver<ProbabilityConfig>> OBSIDIAN_CHASM_CARVER = CARVERS.register("obsidian_chasm_carver", ()-> new ObsidianChasmCarver(ProbabilityConfig.field_236576_b_));
}
