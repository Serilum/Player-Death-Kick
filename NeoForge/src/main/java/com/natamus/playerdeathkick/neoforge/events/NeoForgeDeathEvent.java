package com.natamus.playerdeathkick.neoforge.events;

import com.natamus.playerdeathkick.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeDeathEvent {
	@SubscribeEvent
	public static void onDeathEvent(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level world = entity.level();
		if (world.isClientSide) {
			return;
		}
		
		if (!(entity instanceof ServerPlayer)) {
			return;
		}

		DeathEvent.onDeathEvent((ServerPlayer)entity, e.getSource(), 0);
	}
}
