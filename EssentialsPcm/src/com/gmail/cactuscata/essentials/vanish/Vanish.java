package com.gmail.cactuscata.essentials.vanish;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.cactuscata.essentials.enums.PrefixMessage;

public class Vanish implements CommandExecutor {

	private VanishManager vanishManager;

	public Vanish(VanishManager vanishManager) {
		this.vanishManager = vanishManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("vanish")) {

			if (!(sender instanceof Player)) {

				sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
				return true;
			}

			Player playersender = (Player) sender;

			if (vanishManager.getList().contains(playersender)) {

				for (Player playergetter : Bukkit.getOnlinePlayers())
					playergetter.showPlayer(playersender);

				playersender.sendMessage(PrefixMessage.PREFIX_VANISH + "Vous n'êtes plus en vanish");
				playersender.setDisplayName(playersender.getName());
				playersender.setPlayerListName(playersender.getDisplayName());
				if (playersender.getGameMode() == GameMode.SURVIVAL || playersender.getGameMode() == GameMode.ADVENTURE)
					playersender.setAllowFlight(false);
				playersender.removePotionEffect(PotionEffectType.NIGHT_VISION);

				vanishManager.getList().remove(playersender);

				return true;

			} else {

				playersender.sendMessage(PrefixMessage.PREFIX_VANISH + "Vous êtes maintenant en vanish");
				playersender.setDisplayName(playersender.getName() + "§f[Vanish]");
				playersender.setPlayerListName(playersender.getDisplayName());
				playersender.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0, true));
				playersender.setAllowFlight(true);
				vanishManager.getList().add(playersender);

				return true;

			}

		}

		return false;
	}

}
