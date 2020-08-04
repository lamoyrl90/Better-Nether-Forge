package com.redd90.betternether.registry;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.world.gen.placement.DensityConfig;
import com.redd90.betternether.world.gen.placement.FloorSearch;
import com.redd90.betternether.world.gen.placement.VerticalDown;
import com.redd90.betternether.world.gen.placement.VerticalUp;
import com.redd90.betternether.world.gen.placement.WallSearch;

import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNPlacements {
	public static final DeferredRegister<Placement<?>> PLACEMENTS = DeferredRegister.create(ForgeRegistries.DECORATORS, BetterNether.MODID);
	
	public static final RegistryObject<Placement<DensityConfig>> FLOOR_DENSITY = PLACEMENTS.register("floor_density", () -> new FloorSearch(DensityConfig.codec));
	public static final RegistryObject<Placement<DensityConfig>> VERTICAL_DOWN = PLACEMENTS.register("vertical_down", () -> new VerticalDown(DensityConfig.codec));
	public static final RegistryObject<Placement<DensityConfig>> VERTICAL_UP = PLACEMENTS.register("vertical_up", () -> new VerticalUp(DensityConfig.codec));
	// BROKEN //public static final RegistryObject<Placement<FrequencyConfig>> HORIZONTAL = PLACEMENTS.register("horizontal", () -> new Horizontal(FrequencyConfig.field_236971_a_));
	public static final RegistryObject<Placement<DensityConfig>> WALL_SEARCH = PLACEMENTS.register("wall_search", () -> new WallSearch(DensityConfig.codec));
	
}
