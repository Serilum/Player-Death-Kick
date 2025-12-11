package com.natamus.playerdeathkick.forge.events;

import com.natamus.playerdeathkick.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeDeathEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeDeathEvent.class);

		LivingDeathEvent.BUS.addListener(ForgeDeathEvent::onDeathEvent);
	}

	@SubscribeEvent
	public static void onDeathEvent(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level world = entity.level();
		if (world.isClientSide()) {
			return;
		}
		
		if (!(entity instanceof ServerPlayer)) {
			return;
		}

		DeathEvent.onDeathEvent((ServerPlayer)entity, e.getSource(), 0);
	}
}
