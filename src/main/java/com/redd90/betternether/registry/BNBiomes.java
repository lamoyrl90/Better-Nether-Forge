package com.redd90.betternether.registry;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.compatibility.BiomesoPlenty;
import com.redd90.betternether.world.biome.BNBiomeFeatures;
import com.redd90.betternether.world.biome.BoneReefBiome;
import com.redd90.betternether.world.biome.GravelDesertBiome;
import com.redd90.betternether.world.biome.HadeanJungleBiome;
import com.redd90.betternether.world.biome.NetherGrasslandsBiome;
import com.redd90.betternether.world.biome.FungalWoodlandsBiome;
import com.redd90.betternether.world.biome.ObsidianChasmBiome;
import com.redd90.betternether.world.biome.OldFungiwoodsBiome;
import com.redd90.betternether.world.biome.SwelteringSwamplandBiome;
import com.redd90.betternether.world.biome.TorridTerracesBiome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNBiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, BetterNether.MODID);
	
	public static final RegistryObject<Biome> GRAVEL_DESERT = BIOMES.register("gravel_desert", () -> new GravelDesertBiome());
	public static final RegistryObject<Biome> OBSIDIAN_CHASM = BIOMES.register("obsidian_chasm", () -> new ObsidianChasmBiome());
	public static final RegistryObject<Biome> SWELTERING_SWAMPLAND = BIOMES.register("sweltering_swampland", () -> new SwelteringSwamplandBiome());
	public static final RegistryObject<Biome> TORRID_TERRACES = BIOMES.register("torrid_terraces", () -> new TorridTerracesBiome());
	public static final RegistryObject<Biome> FUNGAL_WOODLANDS = BIOMES.register("fungal_woodlands", () -> new FungalWoodlandsBiome());
	public static final RegistryObject<Biome> OLD_FUNGIWOODS = BIOMES.register("old_fungiwoods", () -> new OldFungiwoodsBiome());
	public static final RegistryObject<Biome> NETHER_GRASSLANDS = BIOMES.register("nether_grasslands", () -> new NetherGrasslandsBiome());
	public static final RegistryObject<Biome> HADEAN_JUNGLE = BIOMES.register("hadean_jungle", () -> new HadeanJungleBiome());
	public static final RegistryObject<Biome> BONE_REEF = BIOMES.register("bone_reef", () -> new BoneReefBiome());
	
	
	public static void registerBiomeTypes() {
		BetterNether.LOGGER.debug("Adding Biome Types to the Biome Dictionary");
		BiomeDictionary.addTypes(Biomes.NETHER_WASTES, BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(Biomes.CRIMSON_FOREST, BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(Biomes.WARPED_FOREST, BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(Biomes.SOUL_SAND_VALLEY, BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(Biomes.BASALT_DELTAS, BiomeDictionary.Type.NETHER);
		
		BiomeDictionary.addTypes(GRAVEL_DESERT.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(OBSIDIAN_CHASM.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(SWELTERING_SWAMPLAND.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(TORRID_TERRACES.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(FUNGAL_WOODLANDS.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(OLD_FUNGIWOODS.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(NETHER_GRASSLANDS.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(HADEAN_JUNGLE.get(), BiomeDictionary.Type.NETHER);
		BiomeDictionary.addTypes(BONE_REEF.get(), BiomeDictionary.Type.NETHER);

	}
	
	
	public static List<Biome> getNetherBiomes(){
		Set<Biome> biomeSet = BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER);
		List<Biome> biomeList = biomeSet.stream().collect(Collectors.toList());
		Collections.sort(biomeList, (o1, o2) -> o1.getDisplayName().toString().compareTo(o2.getDisplayName().toString()));
		return biomeList;
	}
	
	public static void injectBiomeFeatures() {
		BetterNether.LOGGER.debug("Adding global biome features...");
		List<Biome> biomes = getNetherBiomes();
		for (Biome biome : biomes) {
			BNBiomeFeatures.fixCheatyLava(biome);
			BNBiomeFeatures.addStalactites(biome, 0.06f);
			BNBiomeFeatures.addStalagmites(biome, 0.012f);
			BNBiomeFeatures.addBlackstoneSpikes(biome, 0.005f);
			BNBiomeFeatures.addBNOres(biome);
		}
		BetterNether.LOGGER.debug("Global biome features successfully added...");
		
		((GravelDesertBiome) GRAVEL_DESERT.get()).finalizeFeatures();
		((ObsidianChasmBiome) OBSIDIAN_CHASM.get()).finalizeFeatures();
		((SwelteringSwamplandBiome) SWELTERING_SWAMPLAND.get()).finalizeFeatures();
		((TorridTerracesBiome) TORRID_TERRACES.get()).finalizeFeatures();
		((FungalWoodlandsBiome) FUNGAL_WOODLANDS.get()).finalizeFeatures();
		((OldFungiwoodsBiome) OLD_FUNGIWOODS.get()).finalizeFeatures();
		((NetherGrasslandsBiome) NETHER_GRASSLANDS.get()).finalizeFeatures();
		((HadeanJungleBiome) HADEAN_JUNGLE.get()).finalizeFeatures();
		((BoneReefBiome) BONE_REEF.get()).finalizeFeatures();
		
		Biomes.CRIMSON_FOREST.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BNEntities.FIREFLY.get(), 50, 2, 4));
		Biomes.WARPED_FOREST.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BNEntities.FIREFLY.get(), 50, 2, 4));
	}
	
	public static void runCompat() {
		boolean bop = (ModList.get().isLoaded("biomesoplenty"));
				
		if (bop)
			BiomesoPlenty.registerBiomesToBOP();
		
	}
}
