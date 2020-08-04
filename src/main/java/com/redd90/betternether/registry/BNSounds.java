package com.redd90.betternether.registry;

import java.util.HashSet;
import java.util.Set;
import com.redd90.betternether.BetterNether;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNSounds {

	static Set<SoundEvent> registeredEvents = new HashSet<>();
	public static final SoundEvent AMBIENT_GRAVEL_DESERT = registerSound("ambient.gravel_desert");
	public static final SoundEvent AMBIENT_MUSHROOM_FOREST = registerSound("ambient.mushroom_forest");
	
	public static final SoundEvent DUST_DEVIL_AMBIENT = registerSound("mob.dust_devil_ambient");
	public static final SoundEvent DUST_DEVIL_ADDITIONAL = registerSound("mob.dust_devil_additional");
		
    private static SoundEvent registerSound(String name)
    {
		ResourceLocation location = new ResourceLocation(BetterNether.MODID, name);
		SoundEvent event = new SoundEvent(location);
		registeredEvents.add(event.setRegistryName(location));
		return event;
    }
    
    
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> evt)
	{
		for(SoundEvent event : registeredEvents)
			evt.getRegistry().register(event);
	}
}
