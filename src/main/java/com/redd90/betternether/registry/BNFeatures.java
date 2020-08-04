package com.redd90.betternether.registry;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.world.gen.feature.BNFloorOreFeature;
import com.redd90.betternether.world.gen.feature.BNFloorOreFeatureConfig;
import com.redd90.betternether.world.gen.feature.StrayLavaFixer;
import com.redd90.betternether.world.gen.feature.StructureFrequencyConfig;
import com.redd90.betternether.world.gen.feature.NetherrackFeature;
import com.redd90.betternether.world.gen.feature.decorations.BlackstoneSpikeFeature;
import com.redd90.betternether.world.gen.feature.decorations.ObsidianCrystalFeature;
import com.redd90.betternether.world.gen.feature.decorations.StalactiteFeature;
import com.redd90.betternether.world.gen.feature.decorations.StalagmiteFeature;
import com.redd90.betternether.world.gen.feature.plants.AgaveFeature;
import com.redd90.betternether.world.gen.feature.plants.BarrelCactusFeature;
import com.redd90.betternether.world.gen.feature.plants.BlackAppleFeature;
import com.redd90.betternether.world.gen.feature.plants.BlackBushFeature;
import com.redd90.betternether.world.gen.feature.plants.BlackVineFeature;
import com.redd90.betternether.world.gen.feature.plants.BrownMushroomWallFeature;
import com.redd90.betternether.world.gen.feature.plants.FeatherFernFeature;
import com.redd90.betternether.world.gen.feature.plants.GiantMoldFeature;
import com.redd90.betternether.world.gen.feature.plants.GoldenVineFeature;
import com.redd90.betternether.world.gen.feature.plants.GrayMoldFeature;
import com.redd90.betternether.world.gen.feature.plants.InkBushFeature;
import com.redd90.betternether.world.gen.feature.plants.JungleMossFeature;
import com.redd90.betternether.world.gen.feature.plants.JunglePlantFeature;
import com.redd90.betternether.world.gen.feature.plants.LucisFeature;
import com.redd90.betternether.world.gen.feature.plants.MagmaFlowerFeature;
import com.redd90.betternether.world.gen.feature.plants.MediumBrownMushroomFeature;
import com.redd90.betternether.world.gen.feature.plants.MediumRedMushroomFeature;
import com.redd90.betternether.world.gen.feature.plants.MushroomFirFeature;
import com.redd90.betternether.world.gen.feature.plants.NetherCactusFeature;
import com.redd90.betternether.world.gen.feature.plants.NetherGrassFeature;
import com.redd90.betternether.world.gen.feature.plants.NetherReedsFeature;
import com.redd90.betternether.world.gen.feature.plants.NetherWartFeature;
import com.redd90.betternether.world.gen.feature.plants.OrangeMushroomFeature;
import com.redd90.betternether.world.gen.feature.plants.RedMoldFeature;
import com.redd90.betternether.world.gen.feature.plants.RedMushroomWallFeature;
import com.redd90.betternether.world.gen.feature.plants.SmokerFeature;
import com.redd90.betternether.world.gen.feature.plants.SoulVeinFeature;
import com.redd90.betternether.world.gen.feature.plants.StalagnateFeature;
import com.redd90.betternether.world.gen.feature.plants.SwampGrassFeature;
import com.redd90.betternether.world.gen.feature.plants.VanillaMushroomsFeature;
import com.redd90.betternether.world.gen.feature.plants.WallMossFeature;
import com.redd90.betternether.world.gen.feature.plants.WartSeedFeature;
import com.redd90.betternether.world.gen.feature.structure.OldBrownMushroomStructure;
import com.redd90.betternether.world.gen.feature.structure.OldRedMushroomStructure;
import com.redd90.betternether.world.gen.feature.trees.WillowTreeFeature;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNFeatures {
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BetterNether.MODID);
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BetterNether.MODID);
	
	/// FEATURES ///
	
	public static final RegistryObject<Feature<NoFeatureConfig>> NETHER_CACTUS = FEATURES.register("nether_cactus", () -> new NetherCactusFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> AGAVE = FEATURES.register("agave", () -> new AgaveFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BARREL_CACTUS = FEATURES.register("barrel_cactus", () -> new BarrelCactusFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> MAGMA_FLOWER = FEATURES.register("magma_flower", () -> new MagmaFlowerFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> STALAGMITES = FEATURES.register("floor_stalactites", () -> new StalagmiteFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> STALACTITES = FEATURES.register("ceiling_stalactites", () -> new StalactiteFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<BNFloorOreFeatureConfig>> FLOOR_VEINS = FEATURES.register("floor_veins", () -> new BNFloorOreFeature(BNFloorOreFeatureConfig.field_236566_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GOLDEN_VINES = FEATURES.register("golden_vines", () -> new GoldenVineFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BLACK_VINES = FEATURES.register("black_vines", () -> new BlackVineFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GEYSER = FEATURES.register("geyser", () -> new NetherrackFeature(BNBlocks.GEYSER.get().getDefaultState(), NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> OBSIDIAN_CRYSTALS = FEATURES.register("obsidian_crystals", () -> new ObsidianCrystalFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> FIX_LAVA = FEATURES.register("nether_swamp_mask", () -> new StrayLavaFixer(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> WILLOW_TREE = FEATURES.register("willow_tree", () -> new WillowTreeFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> NETHER_REEDS = FEATURES.register("nether_reeds", () -> new NetherReedsFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> FEATHER_FERN = FEATURES.register("feather_fern", () -> new FeatherFernFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SOUL_VEIN = FEATURES.register("soul_vein", () -> new SoulVeinFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SMOKER = FEATURES.register("smoker", () -> new SmokerFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BLACK_BUSH = FEATURES.register("black_bush", () -> new BlackBushFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SWAMP_GRASS = FEATURES.register("swamp_grass", () -> new SwampGrassFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> WALL_MUSHROOM_RED = FEATURES.register("wall_mushroom_red", () -> new RedMushroomWallFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> WALL_MUSHROOM_BROWN = FEATURES.register("wall_mushroom_brown", () -> new BrownMushroomWallFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> WALL_MOSS = FEATURES.register("wall_moss", () -> new WallMossFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> MEDIUM_RED_MUSHROOM = FEATURES.register("medium_red_mushroom", () -> new MediumRedMushroomFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> MEDIUM_BROWN_MUSHROOM = FEATURES.register("medium_brown_mushroom", () -> new MediumBrownMushroomFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GIANT_MOLD = FEATURES.register("giant_mold", () -> new GiantMoldFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> MUSHROOM_FIR = FEATURES.register("mushroom_fir", () -> new MushroomFirFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> VANILLA_MUSHROOMS = FEATURES.register("vanilla_mushrooms", () -> new VanillaMushroomsFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> ORANGE_MUSHROOM = FEATURES.register("orange_mushroom", () -> new OrangeMushroomFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> RED_MOLD = FEATURES.register("red_mold", () -> new RedMoldFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GRAY_MOLD = FEATURES.register("gray_mold", () -> new GrayMoldFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> LUCIS_MUSHROOM = FEATURES.register("lucis_mushroom", () -> new LucisFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> INK_BUSH = FEATURES.register("ink_bush", () -> new InkBushFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> NETHER_GRASS = FEATURES.register("nether_grass", () -> new NetherGrassFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> NETHER_WART = FEATURES.register("nether_wart", () -> new NetherWartFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> WART_SEED = FEATURES.register("wart_seed", () -> new WartSeedFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BLACK_APPLE = FEATURES.register("black_apple", () -> new BlackAppleFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BLACKSTONE_SPIKE = FEATURES.register("blackstone_spike", () -> new BlackstoneSpikeFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> JUNGLE_PLANT = FEATURES.register("jungle_plant", () -> new JunglePlantFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> JUNGLE_MOSS = FEATURES.register("jungle_moss", () -> new JungleMossFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> STALAGNATE = FEATURES.register("stalagnate", () -> new StalagnateFeature(NoFeatureConfig.field_236558_a_));
	
	/// STRUCTURES ///
	
	public static final RegistryObject<Structure<StructureFrequencyConfig>> OLD_BROWN_MUSHROOM = register("old_brown_mushroom", new OldBrownMushroomStructure(StructureFrequencyConfig.codec), GenerationStage.Decoration.SURFACE_STRUCTURES);
	public static final RegistryObject<Structure<StructureFrequencyConfig>> OLD_RED_MUSHROOM = register("old_red_mushroom", new OldRedMushroomStructure(StructureFrequencyConfig.codec), GenerationStage.Decoration.SURFACE_STRUCTURES);

    private static <T extends Structure<?>> RegistryObject<T> register(String name, T structure, GenerationStage.Decoration decoration) {
        Structure.field_236365_a_.put(BetterNether.MODID + ":" + name, structure);
        Structure.field_236385_u_.put(structure, decoration);
        return STRUCTURES.register(name, () -> structure);
    }
}
