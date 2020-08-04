package com.redd90.betternether.event;

import net.minecraft.item.Items;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BNFurnaceFuelBurnTimeEvent {
	@SubscribeEvent
	public static void Registerfuels(FurnaceFuelBurnTimeEvent event) {
		if(event.getItemStack().getItem() == Items.MAGMA_BLOCK) {
			event.setBurnTime(800);
		}
		if(event.getItemStack().getItem() == Items.MAGMA_CREAM) {
			event.setBurnTime(200);
		}
	}
}
