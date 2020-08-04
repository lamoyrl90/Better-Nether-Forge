package com.redd90.betternether.item;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.redd90.betternether.registry.BNItems;

public enum BNArmorMaterial implements IArmorMaterial {
	
	CINCINNASITE("cincinnasite", 13, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> {
	      return Ingredient.fromItems(BNItems.CINCINNASITE_INGOT.get());}),
	RUBY("nether_ruby", 30, new int[]{3, 6, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> {
	      return Ingredient.fromItems(BNItems.NETHER_RUBY.get());}),
	CINCINNASITE_DIAMOND("cincinnasite_diamond", 33, new int[]{2, 5, 6, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, 0.0F, () -> {
	      return Ingredient.fromItems(Items.DIAMOND);});

	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairMaterial;
	
	BNArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, 
			SoundEvent equipSound, float toughness, float knockbackResistance,
			Supplier<Ingredient> repairMaterial) {
	this.name = name;
	this.maxDamageFactor = maxDamageFactor;
	this.damageReductionAmountArray = damageReductionAmountArray;
	this.enchantability = enchantability;
	this.soundEvent = equipSound;
	this.toughness = toughness;
	this.knockbackResistance = knockbackResistance;
	this.repairMaterial = new LazyValue<>(repairMaterial);
	}

	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	/**
	* Gets the percentage of knockback resistance provided by armor of the material. 
	*/
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
