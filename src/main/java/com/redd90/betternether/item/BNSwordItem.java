package com.redd90.betternether.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class BNSwordItem extends SwordItem {

	public BNSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		this.isBurnable();
	}

}
