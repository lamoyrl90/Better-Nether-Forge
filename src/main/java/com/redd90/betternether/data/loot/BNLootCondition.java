package com.redd90.betternether.data.loot;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.Property;
import net.minecraft.util.IStringSerializable;

public class BNLootCondition {
	public static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
	public static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
	public static final ILootCondition.IBuilder SILK_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
	public static final ILootFunction.IBuilder FORTUNE = ApplyBonus.oreDrops(Enchantments.FORTUNE);
	public static final ILootCondition.IBuilder NOT_SILK_OR_SHEARS = SILK_OR_SHEARS.inverted();
	
	public static final ILootCondition.IBuilder chance(float chance){
		return RandomChance.builder(chance);
	}
	
	public static final <T extends Comparable<T> & IStringSerializable> ILootCondition.IBuilder blockState(Block block, Property<T> property, T value){
		return BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(property, value));
	}
	
	public static final ILootFunction.IBuilder randomCount(float min, float max) {
		return SetCount.builder(RandomValueRange.of(min, max));
	}
	
	public static final ILootCondition.IBuilder integerProperty(Block block, Property<Integer> property, int value){
		return BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(property, value));
	}
	
}
