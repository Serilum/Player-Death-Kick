package com.natamus.playerdeathkick.forge.events;

import com.natamus.playerdeathkick.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeDeathEvent {
	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level world = entity.getCommandSenderWorld();
		if (world.isClientSide) {
			return;
		}
		
		if (!(entity instanceof ServerPlayer)) {
			return;
		}

		DeathEvent.onDeathEvent((ServerPlayer)entity, e.getSource(), 0);
	}
}
