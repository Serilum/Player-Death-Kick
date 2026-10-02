package com.serilum.playerdeathkick.forge.events;

import com.serilum.playerdeathkick.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeDeathEvent {
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
