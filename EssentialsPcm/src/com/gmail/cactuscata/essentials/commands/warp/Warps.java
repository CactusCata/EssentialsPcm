package com.gmail.cactuscata.essentials.commands.warp;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.cactuscata.essentials.Main;
import com.gmail.cactuscata.essentials.enums.PrefixMessage;

public class Warps implements CommandExecutor {

	private Main main;

	public Warps(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		File file = new File(main.getDataFolder(), "warps.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		String warps = PrefixMessage.PREFIX + "Liste de tous les warps :";

		if (!file.exists()) {
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (config.getConfigurationSection("warps.") == null) {

			sender.sendMessage(PrefixMessage.PREFIX + "Il n'y a aucun warp existant !");
			return true;

		}

		int i = 0;

		for (String key : config.getConfigurationSection("warps.").getKeys(false)) {
			warps += (i < 1) ? "" : ',';
			warps += " " + key;
			i++;
		}

		warps += " !";

		sender.sendMessage(warps);
		return true;

	}

}
