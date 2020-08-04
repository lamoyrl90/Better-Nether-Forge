package com.redd90.betternether.registry;

import java.util.function.Supplier;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.item.BNItemGroup;
import com.redd90.betternether.item.BNItemTier;
import com.redd90.betternether.item.BNPickaxeItem;
import com.redd90.betternether.item.BNShovelItem;
import com.redd90.betternether.item.BNSwordItem;
import com.redd90.betternether.item.BNArmorItem;
import com.redd90.betternether.item.BNArmorMaterial;
import com.redd90.betternether.item.BNAxeItem;
import com.redd90.betternether.item.BNFood;
import com.redd90.betternether.item.BNHoeItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterNether.MODID);
	
	public static final RegistryObject<Item> DUSTMITE_FLESH = registerItem("dustmite_flesh", () -> new Item(new Item.Properties().food(BNFood.Dustmite(false)).group(BNItemGroup.ITEM_GROUP)));
	public static final RegistryObject<Item> DUSTMITE_JERKY = registerItem("dustmite_jerky", () -> new Item(new Item.Properties().food(BNFood.Dustmite(true)).group(BNItemGroup.ITEM_GROUP)));
		
	public static final RegistryObject<Item> CINCINNASITE = registerItem("cincinnasite", () -> new Item(defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_INGOT = registerItem("cincinnasite_ingot", () -> new Item(defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_NUGGET = registerItem("cincinnasite_nugget", () -> new Item(defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY = registerItem("nether_ruby", () -> new Item(defaultProps()));
	
	public static final RegistryObject<Item> BLACK_APPLE = registerItem("black_apple", () -> new Item(defaultProps().food(BNFood.BLACK_APPLE)));
	
	
	public static final RegistryObject<Item> GLOWSTONE_TORCH = registerItem("glowstone_torch", () -> new WallOrFloorItem(BNBlocks.GLOWSTONE_TORCH.get(), BNBlocks.GLOWSTONE_WALL_TORCH.get(), (new Item.Properties()).group(BNItemGroup.ITEM_GROUP)));
	
	public static final RegistryObject<Item> CINCINNASITE_PICKAXE = registerItem("cincinnasite_pickaxe", () -> new BNPickaxeItem(BNItemTier.CINCINNASITE, 1, -2.8f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_AXE = registerItem("cincinnasite_axe", () -> new BNAxeItem(BNItemTier.CINCINNASITE, 6, -3.1f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_SHOVEL = registerItem("cincinnasite_shovel", () -> new BNShovelItem(BNItemTier.CINCINNASITE, 1.5f, -3.0f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_HOE = registerItem("cincinnasite_hoe", () -> new BNHoeItem(BNItemTier.CINCINNASITE, -2, -1.0f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_PICKAXE_DIAMOND = registerItem("cincinnasite_pickaxe_diamond", () -> new BNPickaxeItem(BNItemTier.CINCINNASITE_DIAMOND, 1, -2.8f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_AXE_DIAMOND = registerItem("cincinnasite_axe_diamond", () -> new BNAxeItem(BNItemTier.CINCINNASITE_DIAMOND, 5, -3.0f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_SHOVEL_DIAMOND = registerItem("cincinnasite_shovel_diamond", () -> new BNShovelItem(BNItemTier.CINCINNASITE_DIAMOND, 1.5f, -3.0f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_HOE_DIAMOND = registerItem("cincinnasite_hoe_diamond", () -> new BNHoeItem(BNItemTier.CINCINNASITE_DIAMOND, -2, -1.0f, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_PICKAXE = registerItem("nether_ruby_pickaxe", () -> new BNPickaxeItem(BNItemTier.NETHER_RUBY, 1, -2.8f, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_AXE = registerItem("nether_ruby_axe", () -> new BNAxeItem(BNItemTier.NETHER_RUBY, 5, -3.0f, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_SHOVEL = registerItem("nether_ruby_shovel", () -> new BNShovelItem(BNItemTier.NETHER_RUBY, 1.5f, -3.0f, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_HOE = registerItem("nether_ruby_hoe", () -> new BNHoeItem(BNItemTier.NETHER_RUBY, -2, -1.0f, defaultProps()));
	public static final RegistryObject<Item> NETHERRACK_PICKAXE = registerItem("netherrack_pickaxe", () -> new BNPickaxeItem(BNItemTier.NETHERRACK, 1, -2.8f, defaultProps()));
	public static final RegistryObject<Item> NETHERRACK_AXE = registerItem("netherrack_axe", () -> new BNAxeItem(BNItemTier.NETHERRACK, 5, -3.0f, defaultProps()));
	public static final RegistryObject<Item> NETHERRACK_SHOVEL = registerItem("netherrack_shovel", () -> new BNShovelItem(BNItemTier.NETHERRACK, 1.5f, -3.0f, defaultProps()));
	public static final RegistryObject<Item> NETHERRACK_HOE = registerItem("netherrack_hoe", () -> new BNHoeItem(BNItemTier.NETHERRACK, -2, -1.0f, defaultProps()));
	public static final RegistryObject<Item> NETHERRACK_SWORD = registerItem("netherrack_sword", () -> new BNSwordItem(BNItemTier.NETHERRACK, 3, -2.4f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_SWORD = registerItem("cincinnasite_sword", () -> new BNSwordItem(BNItemTier.CINCINNASITE, 3, -2.4f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_SWORD_DIAMOND = registerItem("cincinnasite_sword_diamond", () -> new BNSwordItem(BNItemTier.CINCINNASITE, 3, -2.4f, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_SWORD = registerItem("nether_ruby_sword", () -> new BNSwordItem(BNItemTier.NETHER_RUBY, 3, -2.4f, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_BOOTS = registerItem("cincinnasite_boots", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE, EquipmentSlotType.FEET, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_LEGGINGS = registerItem("cincinnasite_leggings", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE, EquipmentSlotType.LEGS, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_CHESTPLATE = registerItem("cincinnasite_chestplate", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE, EquipmentSlotType.CHEST, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_HELMET = registerItem("cincinnasite_helmet", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE, EquipmentSlotType.HEAD, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_BOOTS_DIAMOND = registerItem("cincinnasite_boots_diamond", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE_DIAMOND, EquipmentSlotType.FEET, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_LEGGINGS_DIAMOND = registerItem("cincinnasite_leggings_diamond", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE_DIAMOND, EquipmentSlotType.LEGS, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_CHESTPLATE_DIAMOND = registerItem("cincinnasite_chestplate_diamond", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE_DIAMOND, EquipmentSlotType.CHEST, defaultProps()));
	public static final RegistryObject<Item> CINCINNASITE_HELMET_DIAMOND = registerItem("cincinnasite_helmet_diamond", () -> new BNArmorItem(BNArmorMaterial.CINCINNASITE_DIAMOND, EquipmentSlotType.HEAD, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_BOOTS = registerItem("nether_ruby_boots", () -> new BNArmorItem(BNArmorMaterial.RUBY, EquipmentSlotType.FEET, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_LEGGINGS = registerItem("nether_ruby_leggings", () -> new BNArmorItem(BNArmorMaterial.RUBY, EquipmentSlotType.LEGS, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_CHESTPLATE = registerItem("nether_ruby_chestplate", () -> new BNArmorItem(BNArmorMaterial.RUBY, EquipmentSlotType.CHEST, defaultProps()));
	public static final RegistryObject<Item> NETHER_RUBY_HELMET = registerItem("nether_ruby_helmet", () -> new BNArmorItem(BNArmorMaterial.RUBY, EquipmentSlotType.HEAD, defaultProps()));
	
	private static <I extends Item> RegistryObject<I> registerItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = ITEMS.register(name, supplier);
		return item;
	}
	
	private static Item.Properties defaultProps(){
		return new Item.Properties().group(BNItemGroup.ITEM_GROUP);
	}
	
}
