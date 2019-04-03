package com.gmail.cactuscata.essentials.commands.warp;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.gmail.cactuscata.essentials.Main;
import com.gmail.cactuscata.essentials.enums.PrefixMessage;

public class CWarp implements CommandExecutor {

	private Main main;

	public CWarp(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length < 1) {

			sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le joueur !");
			return true;
		}

		Player player = Bukkit.getPlayerExact(args[0]);
		if (player == null || !player.isOnline()) {

			sender.sendMessage(PrefixMessage.PREFIX + "Le joueur n'est pas en ligne !");
			return true;
		}

		if (args.length < 2) {

			sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le warp !");
			return true;
		}

		File file = new File(main.getDataFolder(), "warps.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		try {

			double x = (double) config.get("warps." + args[1] + ".x");
			double y = (double) config.get("warps." + args[1] + ".y");
			double z = (double) config.get("warps." + args[1] + ".z");
			double pitch = (double) config.get("warps." + args[1] + ".pitch");
			double yaw = (double) config.get("warps." + args[1] + ".yaw");
			World world = Bukkit.getWorld((String) config.get("warps." + args[1] + ".monde"));
			player.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));

		} catch (NullPointerException e) {

			sender.sendMessage(PrefixMessage.PREFIX + "Le warp " + args[1] + " n'existe pas !");
			return true;

		}

		sender.sendMessage(
				PrefixMessage.PREFIX + "Le joueur " + args[0] + " a bien été téléporté au warp " + args[1] + " !");

		return true;

	}

}
