package com.natamus.playerdeathkick;

import com.natamus.collective.check.RegisterMod;
import com.natamus.playerdeathkick.events.DeathEvent;
import com.natamus.playerdeathkick.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		ServerPlayerEvents.ALLOW_DEATH.register((ServerPlayer player, DamageSource damageSource, float damageAmount) -> {
			DeathEvent.onDeathEvent(player, damageSource, damageAmount);
			return true;
		});
	}

	private static void setGlobalConstants() {

	}
}
