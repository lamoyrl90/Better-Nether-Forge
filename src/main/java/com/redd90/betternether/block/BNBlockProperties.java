package com.redd90.betternether.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BNBlockProperties {

	public static final AbstractBlock.Properties STALAGNATE = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	public static final AbstractBlock.Properties REEDS = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.LIGHT_BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	public static final AbstractBlock.Properties WILLOW = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	public static final AbstractBlock.Properties WILLOW_BRANCH = AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BROWN).zeroHardnessAndResistance().sound(SoundType.PLANT).notSolid().doesNotBlockMovement();
	public static final AbstractBlock.Properties WILLOW_LEAVES = AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid();
	public static final AbstractBlock.Properties SMOKER = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	public static final AbstractBlock.Properties MUSHROOM = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	public static final AbstractBlock.Properties RED_MUSHROOM = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	public static final AbstractBlock.Properties BROWN_MUSHROOM = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	public static final AbstractBlock.Properties GIANT_MOLD = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	public static final AbstractBlock.Properties MUSHROOM_FIR = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	public static final AbstractBlock.Properties NETHER_WART = AbstractBlock.Properties.from(Blocks.NETHER_WART_BLOCK);
	public static final AbstractBlock.Properties WART_WOOD = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.HYPHAE);
	public static final AbstractBlock.Properties RUBEUS = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.MAGENTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	public static final AbstractBlock.Properties RUBEUS_LEAVES = AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.LIGHT_BLUE).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid();
	public static final AbstractBlock.Properties JELLYFISH_MUSHROOM = AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.CYAN).hardnessAndResistance(0.1F).sound(SoundType.FUNGUS).notSolid();//.setLightLevel((state)->{return 13;});
	
	public static final AbstractBlock.Properties BONE = AbstractBlock.Properties.from(Blocks.BONE_BLOCK);
	public static final AbstractBlock.Properties BASALT = AbstractBlock.Properties.from(Blocks.BASALT);
	public static final AbstractBlock.Properties CINCINNASITE = AbstractBlock.Properties.create(Material.IRON, MaterialColor.YELLOW).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL);
	public static final AbstractBlock.Properties NETHERRACK = AbstractBlock.Properties.from(Blocks.NETHERRACK);
	public static final AbstractBlock.Properties NETHER_BRICKS = AbstractBlock.Properties.from(Blocks.NETHER_BRICKS);
	public static final AbstractBlock.Properties QUARTZ_GLASS = AbstractBlock.Properties.from(Blocks.GLASS);
	public static final AbstractBlock.Properties OBSIDIAN = AbstractBlock.Properties.from(Blocks.OBSIDIAN);
	public static final AbstractBlock.Properties OBSIDIAN_GLASS = AbstractBlock.Properties.from(Blocks.OBSIDIAN).sound(SoundType.GLASS).notSolid();//.setAllowsSpawn(BNBlockProperties::neverAllowSpawn).setOpaque(BNBlockProperties::isntSolid).setSuffocates(BNBlockProperties::isntSolid).setBlocksVision(BNBlockProperties::isntSolid);
	public static final AbstractBlock.Properties BLUE_OBSIDIAN = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).setRequiresTool().hardnessAndResistance(50.0F, 1200.0F);
	public static final AbstractBlock.Properties BLUE_OBSIDIAN_GLASS = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).setRequiresTool().hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.GLASS).notSolid();//.setAllowsSpawn(BNBlockProperties::neverAllowSpawn).setOpaque(BNBlockProperties::isntSolid).setSuffocates(BNBlockProperties::isntSolid).setBlocksVision(BNBlockProperties::isntSolid);
	
	public static final AbstractBlock.Properties SOUL_SANDSTONE = AbstractBlock.Properties.from(Blocks.SANDSTONE);
	
	
	public static AbstractBlock.Properties Vine(MaterialColor color) {
		return AbstractBlock.Properties.create(Material.PLANTS, color)
				.sound(SoundType.CROP)
				.doesNotBlockMovement()
				.zeroHardnessAndResistance()
				.notSolid();
	}
	
	public static AbstractBlock.Properties Plant(MaterialColor color, boolean solid) {
		AbstractBlock.Properties properties = AbstractBlock.Properties.create(Material.PLANTS, color)
				.sound(SoundType.PLANT)
				.zeroHardnessAndResistance()
				.notSolid()
				.tickRandomly();
		if (!solid)
			properties.doesNotBlockMovement();
		return properties;
	}
	
	//private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
	//	return (boolean)false;
	//}
	
	//private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
	//	return false;
	//}
}
