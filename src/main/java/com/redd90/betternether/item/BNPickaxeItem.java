package com.redd90.betternether.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

public class BNPickaxeItem extends PickaxeItem{

	public BNPickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.isBurnable();
	}

}
