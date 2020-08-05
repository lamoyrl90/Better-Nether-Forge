package com.redd90.betternether.registry;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import com.redd90.betternether.BetterNether;
import com.redd90.betternether.block.AgaveBlock;
import com.redd90.betternether.block.BNBlock;
import com.redd90.betternether.block.BNBlockProperties;
import com.redd90.betternether.block.BNGlassBlock;
import com.redd90.betternether.block.BNGlassPaneBlock;
import com.redd90.betternether.block.BNLogStrippable;
import com.redd90.betternether.block.BNOreBlock;
import com.redd90.betternether.block.BNPaneBlock;
import com.redd90.betternether.block.BNPillarBlock;
import com.redd90.betternether.block.BNPlateBlock;
import com.redd90.betternether.block.BNSlabBlock;
import com.redd90.betternether.block.BNStairsBlock;
import com.redd90.betternether.block.BNTrapdoorBlock;
import com.redd90.betternether.block.BNVineBlock;
import com.redd90.betternether.block.BNWall;
import com.redd90.betternether.block.BNButtonBlock;
import com.redd90.betternether.block.BNDoorBlock;
import com.redd90.betternether.block.BNFenceBlock;
import com.redd90.betternether.block.BNGateBlock;
import com.redd90.betternether.block.BarrelCactusBlock;
import com.redd90.betternether.block.BlackAppleBlock;
import com.redd90.betternether.block.BlackAppleSeedBlock;
import com.redd90.betternether.block.BlackBushBlock;
import com.redd90.betternether.block.BlackVineBlock;
import com.redd90.betternether.block.BrownMushroomCapBlock;
import com.redd90.betternether.block.CincinnasiteFireBowlBlock;
import com.redd90.betternether.block.CincinnasiteFrameBlock;
import com.redd90.betternether.block.CincinnasitePedestalBlock;
import com.redd90.betternether.block.CincinnasitePillarBlock;
import com.redd90.betternether.block.EggPlantBlock;
import com.redd90.betternether.block.EyeSeedBlock;
import com.redd90.betternether.block.EyeVineBlock;
import com.redd90.betternether.block.EyeballBlock;
import com.redd90.betternether.block.FeatherFernBlock;
import com.redd90.betternether.block.GeyserBlock;
import com.redd90.betternether.block.GiantMoldBlock;
import com.redd90.betternether.block.GiantMoldSpore;
import com.redd90.betternether.block.GlowstoneTorchBlock;
import com.redd90.betternether.block.GlowstoneWallTorchBlock;
import com.redd90.betternether.block.GoldenVineBlock;
import com.redd90.betternether.block.GrayMoldBlock;
import com.redd90.betternether.block.IRenderTypeable;
import com.redd90.betternether.block.InkBushBlock;
import com.redd90.betternether.block.InkBushSeedBlock;
import com.redd90.betternether.block.LucisMushroomBlock;
import com.redd90.betternether.block.LucisSporeBlock;
import com.redd90.betternether.block.MagmaFlowerBlock;
import com.redd90.betternether.block.MushroomFirBlock;
import com.redd90.betternether.block.MushroomFirSaplingBlock;
import com.redd90.betternether.block.NetherCactusBlock;
import com.redd90.betternether.block.NetherMyceliumBlock;
import com.redd90.betternether.block.BNGrassBlock;
import com.redd90.betternether.block.NetherReedsBlock;
import com.redd90.betternether.block.NetherrackMossBlock;
import com.redd90.betternether.block.OrangeMushroomBlock;
import com.redd90.betternether.block.RedMoldBlock;
import com.redd90.betternether.block.RedMushroomCapBlock;
import com.redd90.betternether.block.RespawnStatueBlock;
import com.redd90.betternether.block.RubeusConeBlock;
import com.redd90.betternether.block.RubeusLeavesBlock;
import com.redd90.betternether.block.RubeusSaplingBlock;
import com.redd90.betternether.block.SmallCincinnasiteLanternBlock;
import com.redd90.betternether.block.SmallEyeballBlock;
import com.redd90.betternether.block.SmokerBlock;
import com.redd90.betternether.block.SoulFarmlandBlock;
import com.redd90.betternether.block.SoulVeinBlock;
import com.redd90.betternether.block.SpeleothemBlock;
import com.redd90.betternether.block.StalagnateBlock;
import com.redd90.betternether.block.StalagnateSaplingBlock;
import com.redd90.betternether.block.StemBlock;
import com.redd90.betternether.block.VeinedSandBlock;
import com.redd90.betternether.block.WallPlantBlock;
import com.redd90.betternether.block.WartSeedBlock;
import com.redd90.betternether.block.WillowBranchBlock;
import com.redd90.betternether.block.WillowBranchNaturalBlock;
import com.redd90.betternether.block.WillowFruitBlock;
import com.redd90.betternether.block.WillowLeavesBlock;
import com.redd90.betternether.block.WillowSaplingBlock;
import com.redd90.betternether.block.WillowTrunkBlock;
import com.redd90.betternether.item.BNFood;
import com.redd90.betternether.item.BNItemGroup;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterNether.MODID);

	public static final RegistryObject<Block> WILLOW_LEAVES = registerBlock("willow_leaves", () -> new WillowLeavesBlock());
	public static final RegistryObject<Block> RUBEUS_LEAVES = registerBlock("rubeus_leaves", () -> new RubeusLeavesBlock());
		
	public static final RegistryObject<Block> CINCINNASITE_ORE = registerBlock("cincinnasite_ore", () -> new BNOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).harvestLevel(1).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).sound(SoundType.NETHERRACK)));
	public static final RegistryObject<Block> NETHER_RUBY_ORE = registerBlock("nether_ruby_ore", () -> new BNOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).harvestLevel(2).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).sound(SoundType.NETHERRACK)));
	
	public static final RegistryObject<Block> PIG_STATUE_RESPAWNER = registerBlock("pig_statue_respawner", () -> new RespawnStatueBlock());
	
	public static final RegistryObject<Block> CINCINNASITE_BLOCK = registerBlock("cincinnasite_block", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_FORGED = registerBlock("cincinnasite_forged", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_PILLAR = registerBlock("cincinnasite_pillar", () -> new CincinnasitePillarBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_BRICKS = registerBlock("cincinnasite_bricks", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_BRICK_PLATE = registerBlock("cincinnasite_brick_plate", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_STAIRS = registerBlock("cincinnasite_stairs", () -> new BNStairsBlock(()-> CINCINNASITE_BLOCK.get().getDefaultState(), BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_SLAB = registerBlock("cincinnasite_slab", () -> new BNSlabBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_BUTTON = registerBlock("cincinnasite_button", () -> new BNButtonBlock(false, BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_PLATE = registerBlock("cincinnasite_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.MOBS, BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_LANTERN = registerBlock("cincinnasite_lantern", () -> new BNBlock(BNBlockProperties.CINCINNASITE_LANTERN));
	public static final RegistryObject<Block> CINCINNASITE_TILE_LARGE = registerBlock("cincinnasite_tile_large", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_TILE_SMALL = registerBlock("cincinnasite_tile_small", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_CARVED = registerBlock("cincinnasite_carved", () -> new BNBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_WALL = registerBlock("cincinnasite_wall", () -> new BNWall(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_FIRE_BOWL = registerBlock("cincinnasite_fire_bowl", () -> new CincinnasiteFireBowlBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_BRICKS_PILLAR = registerBlock("cincinnasite_bricks_pillar", () -> new BNPillarBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_BARS = registerBlock("cincinnasite_bars", () -> new BNPaneBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_PEDESTAL = registerBlock("cincinnasite_pedestal", () -> new CincinnasitePedestalBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_FRAME = registerBlock("cincinnasite_frame", () -> new CincinnasiteFrameBlock(BNBlockProperties.CINCINNASITE));
	public static final RegistryObject<Block> CINCINNASITE_LANTERN_SMALL = registerBlock("cincinnasite_lantern_small", () -> new SmallCincinnasiteLanternBlock(BNBlockProperties.CINCINNASITE_LANTERN));
		
	public static final RegistryObject<Block> BONE_BLOCK = registerBlock("bone_block", () -> new BNBlock(BNBlockProperties.BONE));
	
	public static final RegistryObject<Block> QUARTZ_GLASS = registerBlock("quartz_glass", () -> new BNGlassBlock(BNBlockProperties.QUARTZ_GLASS));
	public static final RegistryObject<Block> QUARTZ_GLASS_FRAMED = registerBlock("quartz_glass_framed", () -> new BNGlassBlock(BNBlockProperties.CINCINNASITE));
		
	public static final RegistryObject<Block> SOUL_FARMLAND = registerBlock("soul_farmland", () -> new SoulFarmlandBlock());
	public static final RegistryObject<Block> GLOWSTONE_TORCH = registerBlockNoItem("glowstone_torch", () -> new GlowstoneTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH)));
	public static final RegistryObject<Block> GLOWSTONE_WALL_TORCH = registerBlockNoItem("glowstone_wall_torch", () -> new GlowstoneWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH)));
	public static final RegistryObject<Block> GEYSER = registerBlock("geyser", () -> new GeyserBlock());
	
	
	public static final RegistryObject<Block> NETHER_CACTUS = registerBlock("nether_cactus", () -> new NetherCactusBlock());
	public static final RegistryObject<Block> AGAVE = registerBlock("agave", () -> new AgaveBlock());
	public static final RegistryObject<Block> BARREL_CACTUS = registerBlock("barrel_cactus", () -> new BarrelCactusBlock());
	public static final RegistryObject<Block> MAGMA_FLOWER = registerBlock("magma_flower", () -> new MagmaFlowerBlock());
	public static final RegistryObject<Block> GOLDEN_VINE = registerBlock("golden_vine", () -> new GoldenVineBlock());
	public static final RegistryObject<Block> BLACK_VINE = registerBlock("black_vine", () -> new BlackVineBlock());
		
	public static final RegistryObject<Block> NETHERRACK_SPELEOTHEM = registerBlock("netherrack_speleothem", () -> new SpeleothemBlock(AbstractBlock.Properties.from(Blocks.NETHERRACK).setRequiresTool()));
	public static final RegistryObject<Block> GLOWSTONE_SPELEOTHEM = registerBlock("glowstone_speleothem", () -> new SpeleothemBlock(AbstractBlock.Properties.from(Blocks.GLOWSTONE)));
	public static final RegistryObject<Block> BLACKSTONE_SPELEOTHEM = registerBlock("blackstone_speleothem", () -> new SpeleothemBlock(AbstractBlock.Properties.from(Blocks.BLACKSTONE).setRequiresTool()));
	public static final RegistryObject<Block> BASALT_SPELEOTHEM = registerBlock("basalt_speleothem", () -> new SpeleothemBlock(AbstractBlock.Properties.from(Blocks.BASALT).setRequiresTool()));
	public static final RegistryObject<Block> QUARTZ_SPELEOTHEM = registerBlock("quartz_speleothem", () -> new SpeleothemBlock(AbstractBlock.Properties.from(Blocks.QUARTZ_BLOCK).setRequiresTool()));
	
	public static final RegistryObject<Block> OBSIDIAN_BRICKS = registerBlock("obsidian_bricks", () -> new BNBlock(BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_BRICKS_STAIRS = registerBlock("obsidian_bricks_stairs", () -> new BNStairsBlock(() -> OBSIDIAN_BRICKS.get().getDefaultState(), BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_BRICKS_SLAB = registerBlock("obsidian_bricks_slab", () -> new BNSlabBlock(BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_TILE = registerBlock("obsidian_tile", () -> new BNBlock(BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_TILE_SMALL = registerBlock("obsidian_tile_small", () -> new BNBlock(BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_TILE_STAIRS = registerBlock("obsidian_tile_stairs", () -> new BNStairsBlock(() -> OBSIDIAN_TILE_SMALL.get().getDefaultState(), BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_TILE_SLAB = registerBlock("obsidian_tile_slab", () -> new BNSlabBlock(BNBlockProperties.OBSIDIAN));
	public static final RegistryObject<Block> OBSIDIAN_GLASS = registerBlock("obsidian_glass", () -> new BNGlassBlock(BNBlockProperties.OBSIDIAN_GLASS));
	public static final RegistryObject<Block> OBSIDIAN_GLASS_PANE = registerBlock("obsidian_glass_pane", () -> new BNGlassPaneBlock(BNBlockProperties.OBSIDIAN_GLASS));
	public static final RegistryObject<Block> BLUE_OBSIDIAN = registerBlock("blue_obsidian", () -> new BNBlock(BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_BRICKS = registerBlock("blue_obsidian_bricks", () -> new BNBlock(BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_BRICKS_STAIRS = registerBlock("blue_obsidian_bricks_stairs", () -> new BNStairsBlock(() -> BLUE_OBSIDIAN_BRICKS.get().getDefaultState(), BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_BRICKS_SLAB = registerBlock("blue_obsidian_bricks_slab", () -> new BNSlabBlock(BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_TILE = registerBlock("blue_obsidian_tile", () -> new BNBlock(BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_TILE_SMALL = registerBlock("blue_obsidian_tile_small", () -> new BNBlock(BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_TILE_STAIRS = registerBlock("blue_obsidian_tile_stairs", () -> new BNStairsBlock(() -> BLUE_OBSIDIAN_TILE_SMALL.get().getDefaultState(), BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_TILE_SLAB = registerBlock("blue_obsidian_tile_slab", () -> new BNSlabBlock(BNBlockProperties.BLUE_OBSIDIAN));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_GLASS = registerBlock("blue_obsidian_glass", () -> new BNGlassBlock(BNBlockProperties.BLUE_OBSIDIAN_GLASS));
	public static final RegistryObject<Block> BLUE_OBSIDIAN_GLASS_PANE = registerBlock("blue_obsidian_glass_pane", () -> new BNGlassPaneBlock(BNBlockProperties.BLUE_OBSIDIAN_GLASS));
	
	public static final RegistryObject<Block> NETHERRACK_MOSS = registerBlock("netherrack_moss", () -> new NetherrackMossBlock());
	public static final RegistryObject<Block> WILLOW_FRUIT = registerBlockEdible("willow_fruit", () -> new WillowFruitBlock(), BNFood.WILLOW_FRUIT);
	public static final RegistryObject<Block> WILLOW_BRANCH = registerBlock("willow_branch", () -> new WillowBranchBlock());
	public static final RegistryObject<Block> WILLOW_BRANCH_NATURAL = registerBlockNoItem("willow_branch_natural", () -> new WillowBranchNaturalBlock());
	public static final RegistryObject<Block> WILLOW_TRUNK = registerBlockNoItem("willow_trunk", () -> new WillowTrunkBlock());
	
	public static final RegistryObject<Block> NETHER_REED = registerBlock("nether_reed", () -> new NetherReedsBlock());
	public static final RegistryObject<Block> FEATHER_FERN = registerBlock("feather_fern", () -> new FeatherFernBlock());
	public static final RegistryObject<Block> SOUL_VEIN = registerBlock("soul_vein", () -> new SoulVeinBlock());
	public static final RegistryObject<Block> VEINED_SAND = registerBlockNoItem("veined_sand", () -> new VeinedSandBlock());
	public static final RegistryObject<Block> SMOKER = registerBlock("smoker", () -> new SmokerBlock());
	public static final RegistryObject<Block> BLACK_BUSH = registerBlock("black_bush", () -> new BlackBushBlock());
	public static final RegistryObject<Block> SWAMP_GRASS = registerBlock("swamp_grass", () -> new BNGrassBlock());
	public static final RegistryObject<Block> WALL_MUSHROOM_BROWN = registerBlock("wall_mushroom_brown", () -> new WallPlantBlock(MaterialColor.BROWN));
	public static final RegistryObject<Block> WALL_MUSHROOM_RED = registerBlock("wall_mushroom_red", () -> new WallPlantBlock(MaterialColor.RED));
	public static final RegistryObject<Block> WALL_MOSS = registerBlock("wall_moss", () -> new WallPlantBlock(MaterialColor.RED));
	
	public static final RegistryObject<Block> STRIPPED_LOG_WILLOW = registerBlock("stripped_log_willow", () -> new BNPillarBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> STRIPPED_BARK_WILLOW = registerBlock("stripped_bark_willow", () -> new BNPillarBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_LOG = registerBlock("willow_log", () -> new BNLogStrippable(BNBlockProperties.WILLOW, STRIPPED_LOG_WILLOW.get()));
	public static final RegistryObject<Block> WILLOW_BARK = registerBlock("willow_bark", () -> new BNLogStrippable(BNBlockProperties.WILLOW, STRIPPED_BARK_WILLOW.get()));
	public static final RegistryObject<Block> WILLOW_PLANKS = registerBlock("willow_planks", () -> new BNBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_STAIRS = registerBlock("willow_stairs", () -> new BNStairsBlock(() -> WILLOW_PLANKS.get().getDefaultState(), BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_SLAB = registerBlock("willow_slab", () -> new BNSlabBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_FENCE = registerBlock("willow_fence", () -> new BNFenceBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_GATE = registerBlock("willow_gate", () -> new BNGateBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_BUTTON = registerBlock("willow_button", () -> new BNButtonBlock(true, BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_PLATE = registerBlock("willow_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_TRAPDOOR = registerBlock("willow_trapdoor", () -> new BNTrapdoorBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_DOOR = registerBlock("willow_door", () -> new BNDoorBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WILLOW_SAPLING = registerBlock("willow_sapling", () -> new WillowSaplingBlock());
	
	public static final RegistryObject<Block> NETHER_MYCELIUM = registerBlock("nether_mycelium", () -> new NetherMyceliumBlock());
	
	public static final RegistryObject<Block> MUSHROOM_STEM = registerBlock("mushroom_stem", () -> new StemBlock(BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> RED_LARGE_MUSHROOM = registerBlockNoItem("red_large_mushroom", () -> new RedMushroomCapBlock());
	public static final RegistryObject<Block> BROWN_LARGE_MUSHROOM = registerBlockNoItem("brown_large_mushroom", () -> new BrownMushroomCapBlock());
	public static final RegistryObject<Block> GIANT_MOLD = registerBlockNoItem("giant_mold", () -> new GiantMoldBlock());
	public static final RegistryObject<Block> GIANT_MOLD_SAPLING = registerBlock("giant_mold_sapling", () -> new GiantMoldSpore());
	public static final RegistryObject<Block> MUSHROOM_FIR = registerBlockNoItem("mushroom_fir", () -> new MushroomFirBlock());
	public static final RegistryObject<Block> MUSHROOM_FIR_STEM = registerBlock("mushroom_fir_stem", () -> new StemBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_SAPLING = registerBlock("mushroom_fir_sapling", () -> new MushroomFirSaplingBlock());
	public static final RegistryObject<Block> ORANGE_MUSHROOM = registerBlock("orange_mushroom", () -> new OrangeMushroomBlock());
	public static final RegistryObject<Block> RED_MOLD = registerBlock("red_mold", () -> new RedMoldBlock());
	public static final RegistryObject<Block> GRAY_MOLD = registerBlock("gray_mold", () -> new GrayMoldBlock());
	public static final RegistryObject<Block> LUCIS_MUSHROOM = registerBlockNoItem("lucis_mushroom", () -> new LucisMushroomBlock());
	public static final RegistryObject<Block> LUCIS_SPORE = registerBlock("lucis_spore", () -> new LucisSporeBlock());
	
	public static final RegistryObject<Block> INK_BUSH = registerBlockNoItem("ink_bush", () -> new InkBushBlock());
	public static final RegistryObject<Block> INK_BUSH_SEED = registerBlock("ink_bush_seed", () -> new InkBushSeedBlock());
	public static final RegistryObject<Block> NETHER_GRASS = registerBlock("nether_grass", () -> new BNGrassBlock());
	
	public static final RegistryObject<Block> MUSHROOM_PLANKS = registerBlock("mushroom_planks", () -> new BNBlock(BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_STAIRS = registerBlock("mushroom_stairs", () -> new BNStairsBlock(() -> MUSHROOM_PLANKS.get().getDefaultState(), BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_SLAB = registerBlock("mushroom_slab", () -> new BNSlabBlock(BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_FENCE = registerBlock("mushroom_fence", () -> new BNFenceBlock(BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_GATE = registerBlock("mushroom_gate", () -> new BNGateBlock(BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_BUTTON = registerBlock("mushroom_button", () -> new BNButtonBlock(true, BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_PLATE = registerBlock("mushroom_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_TRAPDOOR = registerBlock("mushroom_trapdoor", () -> new BNTrapdoorBlock(BNBlockProperties.MUSHROOM));
	public static final RegistryObject<Block> MUSHROOM_DOOR = registerBlock("mushroom_door", () -> new BNDoorBlock(BNBlockProperties.MUSHROOM));
	
	public static final RegistryObject<Block> STRIPPED_LOG_MUSHROOM_FIR = registerBlock("stripped_log_mushroom_fir", () -> new BNPillarBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_LOG = registerBlock("mushroom_fir_log", () -> new BNLogStrippable(BNBlockProperties.MUSHROOM_FIR, STRIPPED_LOG_MUSHROOM_FIR.get()));
	public static final RegistryObject<Block> MUSHROOM_FIR_PLANKS = registerBlock("mushroom_fir_planks", () -> new BNBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_STAIRS = registerBlock("mushroom_fir_stairs", () -> new BNStairsBlock(() -> MUSHROOM_FIR_PLANKS.get().getDefaultState(), BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_SLAB = registerBlock("mushroom_fir_slab", () -> new BNSlabBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_FENCE = registerBlock("mushroom_fir_fence", () -> new BNFenceBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_GATE = registerBlock("mushroom_fir_gate", () -> new BNGateBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_BUTTON = registerBlock("mushroom_fir_button", () -> new BNButtonBlock(true, BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_PLATE = registerBlock("mushroom_fir_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_TRAPDOOR = registerBlock("mushroom_fir_trapdoor", () -> new BNTrapdoorBlock(BNBlockProperties.MUSHROOM_FIR));
	public static final RegistryObject<Block> MUSHROOM_FIR_DOOR = registerBlock("mushroom_fir_door", () -> new BNDoorBlock(BNBlockProperties.MUSHROOM_FIR));
		
	public static final RegistryObject<Block> WART_ROOTS = registerBlockNoItem("wart_roots", () -> new BNBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> WART_SEED = registerBlock("wart_seed", () -> new WartSeedBlock());
	public static final RegistryObject<Block> STRIPPED_LOG_WART = registerBlock("stripped_log_wart", () -> new BNPillarBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> STRIPPED_BARK_WART = registerBlock("stripped_bark_wart", () -> new BNPillarBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_LOG = registerBlock("wart_log", () -> new BNLogStrippable(BNBlockProperties.WART_WOOD, STRIPPED_LOG_WART.get()));
	public static final RegistryObject<Block> WART_BARK = registerBlock("wart_bark", () -> new BNLogStrippable(BNBlockProperties.WART_WOOD, STRIPPED_BARK_WART.get()));
	public static final RegistryObject<Block> WART_PLANKS = registerBlock("wart_planks", () -> new BNBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_STAIRS = registerBlock("wart_stairs", () -> new BNStairsBlock(() -> WART_PLANKS.get().getDefaultState(), BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_SLAB = registerBlock("wart_slab", () -> new BNSlabBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_FENCE = registerBlock("wart_fence", () -> new BNFenceBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_GATE = registerBlock("wart_gate", () -> new BNGateBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_BUTTON = registerBlock("wart_button", () -> new BNButtonBlock(true, BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_PLATE = registerBlock("wart_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_TRAPDOOR = registerBlock("wart_trapdoor", () -> new BNTrapdoorBlock(BNBlockProperties.WART_WOOD));
	public static final RegistryObject<Block> WART_DOOR = registerBlock("wart_door", () -> new BNDoorBlock(BNBlockProperties.WART_WOOD));
	
	public static final RegistryObject<Block> SOUL_LILY = registerBlockNoItem("soul_lily", () -> new BNBlock(BNBlockProperties.WILLOW));
	public static final RegistryObject<Block> SOUL_LILY_SAPLING = registerBlock("soul_lily_sapling", () -> new BNBlock(BNBlockProperties.WILLOW));
	
	public static final RegistryObject<Block> BLACK_APPLE = registerBlockNoItem("black_apple", () -> new BlackAppleBlock());
	public static final RegistryObject<Block> BLACK_APPLE_SEED = registerBlock("black_apple_seed", () -> new BlackAppleSeedBlock());
	
	public static final RegistryObject<Block> JUNGLE_GRASS = registerBlockNoItem("jungle_grass", () -> new BNBlock(BNBlockProperties.NETHERRACK.sound(SoundType.NYLIUM)));
	public static final RegistryObject<Block> JUNGLE_PLANT = registerBlock("jungle_plant", () -> new BNGrassBlock());
	public static final RegistryObject<Block> JUNGLE_MOSS = registerBlock("jungle_moss", () -> new WallPlantBlock(MaterialColor.CYAN));
	
	public static final RegistryObject<Block> STALAGNATE = registerBlockNoItem("stalagnate", () -> new StalagnateBlock());
	public static final RegistryObject<Block> STALAGNATE_STEM = registerBlock("stalagnate_stem", () -> new StemBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_SEED = registerBlock("stalagnate_seed", () -> new StalagnateSaplingBlock());
	public static final RegistryObject<Block> STRIPPED_LOG_STALAGNATE = registerBlock("stripped_log_stalagnate", () -> new BNPillarBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STRIPPED_BARK_STALAGNATE = registerBlock("stripped_bark_stalagnate", () -> new BNPillarBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_LOG = registerBlock("stalagnate_log", () -> new BNLogStrippable(BNBlockProperties.STALAGNATE, STRIPPED_LOG_STALAGNATE.get()));
	public static final RegistryObject<Block> STALAGNATE_BARK = registerBlock("stalagnate_bark", () -> new BNLogStrippable(BNBlockProperties.STALAGNATE, STRIPPED_BARK_STALAGNATE.get()));
	public static final RegistryObject<Block> STALAGNATE_PLANKS = registerBlock("stalagnate_planks", () -> new BNBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_STAIRS = registerBlock("stalagnate_stairs", () -> new BNStairsBlock(() -> STALAGNATE_PLANKS.get().getDefaultState(), BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_SLAB = registerBlock("stalagnate_slab", () -> new BNSlabBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_FENCE = registerBlock("stalagnate_fence", () -> new BNFenceBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_GATE = registerBlock("stalagnate_gate", () -> new BNGateBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_BUTTON = registerBlock("stalagnate_button", () -> new BNButtonBlock(true, BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_PLATE = registerBlock("stalagnate_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_TRAPDOOR = registerBlock("stalagnate_trapdoor", () -> new BNTrapdoorBlock(BNBlockProperties.STALAGNATE));
	public static final RegistryObject<Block> STALAGNATE_DOOR = registerBlock("stalagnate_door", () -> new BNDoorBlock(BNBlockProperties.STALAGNATE));
	
	public static final RegistryObject<Block> EGG_PLANT = registerBlock("egg_plant", () -> new EggPlantBlock());
	
	public static final RegistryObject<Block> RUBEUS_SAPLING = registerBlock("rubeus_sapling", () -> new RubeusSaplingBlock());
	public static final RegistryObject<Block> RUBEUS_CONE = registerBlock("rubeus_cone", () -> new RubeusConeBlock());
	public static final RegistryObject<Block> STRIPPED_LOG_RUBEUS = registerBlock("stripped_log_rubeus", () -> new BNPillarBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_LOG_HALF_STRIPPED = registerBlock("rubeus_log_half_stripped", () -> new BNLogStrippable(BNBlockProperties.RUBEUS, STRIPPED_LOG_RUBEUS.get()));
	public static final RegistryObject<Block> RUBEUS_LOG = registerBlock("rubeus_log", () -> new BNLogStrippable(BNBlockProperties.RUBEUS, RUBEUS_LOG_HALF_STRIPPED.get()));
	public static final RegistryObject<Block> STRIPPED_BARK_RUBEUS = registerBlock("stripped_bark_rubeus", () -> new BNPillarBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_BARK_HALF_STRIPPED = registerBlock("rubeus_bark_half_stripped", () -> new BNLogStrippable(BNBlockProperties.RUBEUS, STRIPPED_BARK_RUBEUS.get()));
	public static final RegistryObject<Block> RUBEUS_BARK = registerBlock("rubeus_bark", () -> new BNLogStrippable(BNBlockProperties.RUBEUS, RUBEUS_BARK_HALF_STRIPPED.get()));
	public static final RegistryObject<Block> RUBEUS_PLANKS = registerBlock("rubeus_planks", () -> new BNBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_STAIRS = registerBlock("rubeus_stairs", () -> new BNStairsBlock(() -> RUBEUS_PLANKS.get().getDefaultState(), BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_SLAB = registerBlock("rubeus_slab", () -> new BNSlabBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_FENCE = registerBlock("rubeus_fence", () -> new BNFenceBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_GATE = registerBlock("rubeus_gate", () -> new BNGateBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_BUTTON = registerBlock("rubeus_button", () -> new BNButtonBlock(true, BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_PLATE = registerBlock("rubeus_plate", () -> new BNPlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_TRAPDOOR = registerBlock("rubeus_trapdoor", () -> new BNTrapdoorBlock(BNBlockProperties.RUBEUS));
	public static final RegistryObject<Block> RUBEUS_DOOR = registerBlock("rubeus_door", () -> new BNDoorBlock(BNBlockProperties.RUBEUS));
	
	public static final RegistryObject<Block> EYE_VINE = registerBlockNoItem("eye_vine", () -> new EyeVineBlock());
	public static final RegistryObject<Block> EYEBALL = registerBlockNoItem("eyeball", () -> new EyeballBlock());
	public static final RegistryObject<Block> EYEBALL_SMALL = registerBlockNoItem("eyeball_small", () -> new SmallEyeballBlock());
	public static final RegistryObject<Block> EYE_SEED = registerBlock("eye_seed", () -> new EyeSeedBlock());
	
	public static final RegistryObject<Block> BLOOMING_VINE = registerBlock("blooming_vine", () -> new BNVineBlock(MaterialColor.PURPLE));
	public static final RegistryObject<Block> SOUL_GRASS = registerBlock("soul_grass", () -> new BNGrassBlock());
	
	
	private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
		RegistryObject<B> block = BLOCKS.register(name, supplier);
		BNItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(BNItemGroup.ITEM_GROUP)));
		return block;
	}
	
	private static <B extends Block> RegistryObject<B> registerBlockEdible(String name, Supplier<? extends B> supplier, Food food) {
		RegistryObject<B> block = BLOCKS.register(name, supplier);
		BNItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().food(food).group(BNItemGroup.ITEM_GROUP)));
		return block;
	}
	
	private static <B extends Block> RegistryObject<B> registerBlockNoItem(String name, Supplier<? extends B> supplier) {
		RegistryObject<B> block = BLOCKS.register(name, supplier);
		return block;
	}
	
	public static void setupRenderLayers() {
		for (RegistryObject<Block> block : BLOCKS.getEntries()) {
			Block blockEntry = block.get();
			if (blockEntry instanceof IRenderTypeable) {
				switch (((IRenderTypeable) blockEntry).getRenderLayer()){
				case CUTOUT :
					BetterNether.LOGGER.debug("Setting the render layer of " + blockEntry.getRegistryName().getPath() + " to cutout");
					RenderTypeLookup.setRenderLayer(blockEntry, RenderType.getCutout()::equals);
					break;
				case TRANSLUCENT :
					BetterNether.LOGGER.debug("Setting the render layer of " + blockEntry.getRegistryName().getPath() + " to translucent");
					RenderTypeLookup.setRenderLayer(blockEntry, RenderType.getTranslucent()::equals);
					break;
				case CUTOUT_MIPPED :
					BetterNether.LOGGER.debug("Setting the render layer of " + blockEntry.getRegistryName().getPath() + " to mipped cutout");
					RenderTypeLookup.setRenderLayer(blockEntry, RenderType.getCutoutMipped()::equals);
					break;
				default:
					break;
				}
			}
		}
	}

}
