package com.redd90.betternether.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

public class BNShovelItem extends ShovelItem {

	public BNShovelItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.isBurnable();
	}

}
