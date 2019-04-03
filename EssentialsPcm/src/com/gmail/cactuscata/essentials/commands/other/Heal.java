package com.gmail.cactuscata.essentials.commands.other;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactuscata.essentials.enums.PrefixMessage;

public class Heal implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
			return true;
		}

		Player player;

		if (args.length > 0) {
			Player playerTarget = Bukkit.getPlayerExact(args[0]);
			if (playerTarget != null && playerTarget.isOnline()) {
				player = playerTarget;
				player.sendMessage(PrefixMessage.PREFIX + "Vous avez heal " + player.getDisplayName() + " !");
			} else {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'est pas en ligne !");
				return true;
			}
		} else {
			player = (Player) sender;
			player.sendMessage(PrefixMessage.PREFIX + "Vous vous êtes heal !");
		}

		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		return true;

	}

}
