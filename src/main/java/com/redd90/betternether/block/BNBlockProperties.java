package com.redd90.betternether.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BNBlockProperties {

	public static AbstractBlock.Properties stalagnate(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	public static AbstractBlock.Properties reeds(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.LIGHT_BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	public static AbstractBlock.Properties willow(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	public static AbstractBlock.Properties willowBranch(){
		return AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BROWN).zeroHardnessAndResistance().sound(SoundType.PLANT).notSolid().doesNotBlockMovement();
	}
	
	public static AbstractBlock.Properties willowLeaves(){
		return AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid();
	}
	
	public static AbstractBlock.Properties smoker(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	public static AbstractBlock.Properties mushroom(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	}
	
	public static AbstractBlock.Properties redMushroom(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	}
	
	public static AbstractBlock.Properties brownMushroom(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	}
	
	public static AbstractBlock.Properties giantMold(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	}
	
	public static AbstractBlock.Properties mushroomFir(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.HYPHAE);
	}
	
	public static AbstractBlock.Properties netherWart(){
		return AbstractBlock.Properties.from(Blocks.NETHER_WART_BLOCK);
	}
	
	public static AbstractBlock.Properties wartWood(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.HYPHAE);
	}
	
	public static AbstractBlock.Properties rubeus(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.MAGENTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	public static AbstractBlock.Properties rubeusLeaves(){
		return AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.LIGHT_BLUE).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid();
	}
	
	public static AbstractBlock.Properties jellyfishMushroom(){
		return AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.CYAN).hardnessAndResistance(0.1F).sound(SoundType.FUNGUS).notSolid();//.setLightLevel((state)->{return 13;});
	}
	
	public static AbstractBlock.Properties bone(){
		return AbstractBlock.Properties.from(Blocks.BONE_BLOCK);
	}
	
	public static AbstractBlock.Properties basalt(){
		return AbstractBlock.Properties.from(Blocks.BASALT);
	}
	
	public static AbstractBlock.Properties ruby(){
		return AbstractBlock.Properties.create(Material.IRON, MaterialColor.RED).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL);
	}
		
	public static AbstractBlock.Properties cincinnasite(){
		return AbstractBlock.Properties.create(Material.IRON, MaterialColor.YELLOW).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL);
	}
	
	public static AbstractBlock.Properties netherrack(){
		return AbstractBlock.Properties.from(Blocks.NETHERRACK);
	}
	
	public static AbstractBlock.Properties netherBricks(){
		return AbstractBlock.Properties.from(Blocks.NETHER_BRICKS);
	}
	
	public static AbstractBlock.Properties quartzGlass(){
		return AbstractBlock.Properties.from(Blocks.GLASS);
	}
	
	public static AbstractBlock.Properties obsidian(){
		return AbstractBlock.Properties.from(Blocks.OBSIDIAN);
	}
	
	public static AbstractBlock.Properties obsidianGlass(){
		return AbstractBlock.Properties.from(Blocks.OBSIDIAN).sound(SoundType.GLASS).notSolid();//.setAllowsSpawn(BNBlockProperties::neverAllowSpawn).setOpaque(BNBlockProperties::isntSolid).setSuffocates(BNBlockProperties::isntSolid).setBlocksVision(BNBlockProperties::isntSolid);
	}
	
	public static AbstractBlock.Properties blueObsidian(){
		return AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).setRequiresTool().hardnessAndResistance(50.0F, 1200.0F);
	}
	
	public static AbstractBlock.Properties blueObsidianGlass(){
		return AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).setRequiresTool().hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.GLASS).notSolid();//.setAllowsSpawn(BNBlockProperties::neverAllowSpawn).setOpaque(BNBlockProperties::isntSolid).setSuffocates(BNBlockProperties::isntSolid).setBlocksVision(BNBlockProperties::isntSolid);
	}
	
	public static AbstractBlock.Properties soulSandstone(){
		return AbstractBlock.Properties.from(Blocks.SANDSTONE);
	}
	
	
	public static AbstractBlock.Properties vine(MaterialColor color) {
		return AbstractBlock.Properties.create(Material.PLANTS, color)
				.sound(SoundType.CROP)
				.doesNotBlockMovement()
				.zeroHardnessAndResistance()
				.notSolid();
	}
	
	public static AbstractBlock.Properties plant(MaterialColor color, boolean solid) {
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
