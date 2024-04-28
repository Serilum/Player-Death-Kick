package com.natamus.playerdeathkick.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.playerdeathkick.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String disconnectMessage = "You died by %death%! You have been disconnected from the server.";
	@Entry public static boolean addDeathCauseToMessage = true;
	@Entry public static boolean exemptAdminPlayers = true;
	@Entry public static boolean broadcastKickToServer = true;

	public static void initConfig() {
		configMetaData.put("disconnectMessage", Arrays.asList(
			"The message players will receive when disconnected on death."
		));
		configMetaData.put("addDeathCauseToMessage", Arrays.asList(
			"If enabled, replaces %death% in the disconnect message with the death cause."
		));
		configMetaData.put("exemptAdminPlayers", Arrays.asList(
			"If enabled, exempts admin players (with cheat access, OPs) from being kicked on death."
		));
		configMetaData.put("broadcastKickToServer", Arrays.asList(
			"If enabled, sends a message to all players online with who was kicked."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}