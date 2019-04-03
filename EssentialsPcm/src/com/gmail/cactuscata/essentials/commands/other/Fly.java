package com.gmail.cactuscata.essentials.commands.other;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactuscata.essentials.enums.PrefixMessage;

public class Fly implements CommandExecutor {

	private ArrayList<UUID> flying = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
			return true;
		}

		Player player;

		if (args.length == 1) {
			player = Bukkit.getPlayerExact(args[0]);
			if (player == null || !player.isOnline()) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'est pas connecté !");
				return true;
			}
		} else
			player = (Player) sender;

		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			player.sendMessage(PrefixMessage.PREFIX + "Vous pouvez déjà voler !");
			return true;
		}

		if (flying.contains(player.getUniqueId())) {
			player.sendMessage(PrefixMessage.PREFIX + "Vous ne volez plus !");
			player.setAllowFlight(false);
			flying.remove(player.getUniqueId());
			return true;
		} else {
			player.sendMessage(PrefixMessage.PREFIX + "" + ChatColor.ITALIC + "i believe i can fly !");
			player.setAllowFlight(true);
			flying.add(player.getUniqueId());
			return true;
		}

	}
}
