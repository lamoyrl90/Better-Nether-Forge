package com.redd90.betternether.item;

import java.util.function.Supplier;

import com.redd90.betternether.registry.BNItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum BNItemTier implements IItemTier {
	NETHERRACK(1, 118, 4.4F, 1.1F, 5, () -> {
		return Ingredient.fromItems(Items.NETHERRACK);}),
	CINCINNASITE(2, 227, 6.6F, 2.2F, 14, () -> {
		return Ingredient.fromItems(BNItems.CINCINNASITE_INGOT.get());}),
	NETHER_RUBY(3, 1419, 8.8F, 3.3F, 10, () -> {
		return Ingredient.fromItems(BNItems.NETHER_RUBY.get());}),
	CINCINNASITE_DIAMOND(3, 1788, 7.6F, 2.7F, 14, () -> {
		return Ingredient.fromItems(Items.DIAMOND);});

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
	
	BNItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}
}
