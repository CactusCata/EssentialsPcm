package com.gmail.cactuscata.essentials.commands.spy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactuscata.essentials.enums.PrefixMessage;

public class SocialSpy implements CommandExecutor {

	private MessageObj messageObj;

	public SocialSpy(MessageObj messageObj) {
		this.messageObj = messageObj;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
			return true;
		}

		Player p = (Player) sender;
		if (messageObj.getSocial().contains(p.getName())) {
			p.sendMessage(PrefixMessage.PREFIX + "Vous ne voyez plus les messages des gens !");
			messageObj.getSocial().remove(p.getName());
			return true;
		}
		p.sendMessage(PrefixMessage.PREFIX + "Vous voyez les messages des gens !");
		messageObj.getSocial().add(p.getName());
		return true;

	}

}
