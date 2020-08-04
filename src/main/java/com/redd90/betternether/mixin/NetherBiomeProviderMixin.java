package com.redd90.betternether.mixin;


import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableList;

import com.mojang.datafixers.util.Pair;
import com.redd90.betternether.BetterNether;
import com.redd90.betternether.registry.BNBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.NetherBiomeProvider;



@Mixin(NetherBiomeProvider.class)
public class NetherBiomeProviderMixin {

	
	@Inject(method = "func_235285_d_", at = @At("HEAD"), cancellable = true)
	private static void replaceGenerator(long seed, CallbackInfoReturnable<NetherBiomeProvider> info) {
		
		BetterNether.LOGGER.debug("Creating biome list for The Nether");
		
		ImmutableList<Biome> immutablelist = ImmutableList.copyOf(BNBiomes.getNetherBiomes());
		BetterNether.LOGGER.debug("List of biomes:" + immutablelist);
		info.setReturnValue(new NetherBiomeProvider(seed, immutablelist.stream().flatMap((p_235273_0_) -> {
			return p_235273_0_.func_235055_B_().map((p_235274_1_) -> {
				return Pair.of(p_235274_1_, p_235273_0_);
			});
		}).collect(ImmutableList.toImmutableList()), Optional.of(NetherBiomeProvider.Preset.field_235288_b_)));
		info.cancel();
	}
	
}
