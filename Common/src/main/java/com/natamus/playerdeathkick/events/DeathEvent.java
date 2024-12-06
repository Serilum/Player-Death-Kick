package com.natamus.playerdeathkick.events;

import com.natamus.collective.functions.StringFunctions;
import com.natamus.playerdeathkick.config.ConfigHandler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class DeathEvent {
	public static void onDeathEvent(ServerPlayer serverplayer, DamageSource source, float damageAmount) {
		if (ConfigHandler.exemptAdminPlayers) {
			if (serverplayer.hasPermissions(2)) {
				return;
			}
		}
		
		Level world = serverplayer.level();
		String deathmessage = ConfigHandler.disconnectMessage;
		
		if (ConfigHandler.addDeathCauseToMessage) {
			String imsourcename = source.getMsgId();
			String sourcename = "";
			
			Entity truesource = source.getEntity();
			if (truesource != null) {
				sourcename = truesource.getName().getString();
			}
			if (!sourcename.equals("") && imsourcename.equals("player")) {
				imsourcename = sourcename;
			}
			else if (imsourcename.contains(".")) {
				imsourcename = imsourcename.split("\\.")[0];
			}
			else {
				if (truesource != null) {
					imsourcename = "a " +  sourcename;
				}
			}
			
			deathmessage = deathmessage.replace("%death%", imsourcename);
		}
		
		serverplayer.connection.disconnect(Component.literal(deathmessage));
		
		if (ConfigHandler.broadcastKickToServer) {
			String playername = serverplayer.getName().getString();
			StringFunctions.broadcastMessage(world, "The player " + playername + " has died and been kicked from the server.", ChatFormatting.DARK_GRAY);
		}
	}
}
