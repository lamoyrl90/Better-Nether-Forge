package com.redd90.betternether.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BNFood {

	public static final Food WILLOW_FRUIT = new Food.Builder().hunger(3).saturation(0.4f).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 40), 1.0f).build();
	public static final Food BLACK_APPLE = new Food.Builder().hunger(6).saturation(0.5F).effect(() -> new EffectInstance(Effects.REGENERATION, 60, 1), 1.0f).build();
	
	
	public static final Food Dustmite(boolean cooked) {
		return cooked ? new Food.Builder().hunger(4).saturation(0.8F).build() : new Food.Builder().hunger(2).saturation(0.3F).build();
	}
}
