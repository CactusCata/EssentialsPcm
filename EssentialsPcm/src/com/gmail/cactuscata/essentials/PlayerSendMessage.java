package com.gmail.cactuscata.essentials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerSendMessage implements Listener {

	@EventHandler
	public void onSendMessage(AsyncPlayerChatEvent event) {

		if (event.getPlayer().hasPermission("pcm.msg.color"))
			event.setFormat(event.getPlayer().getDisplayName() + ": " + event.getMessage().replace('&', '§'));
		else
			event.setFormat(event.getPlayer().getDisplayName() + ": " + event.getMessage());

	}
}
