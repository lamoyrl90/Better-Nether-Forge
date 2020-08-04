package com.redd90.betternether.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class BNAxeItem extends AxeItem {

	public BNAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.isBurnable();
	}
	

}
