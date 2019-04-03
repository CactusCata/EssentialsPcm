package com.gmail.cactuscata.essentials.commands.spy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactuscata.essentials.enums.PrefixMessage;
import com.gmail.cactuscata.essentials.vanish.VanishManager;

public class Msg implements CommandExecutor {

	private MessageObj messageObj;
	private VanishManager vanishManag;

	public Msg(MessageObj messageObj, VanishManager vanishManager) {
		this.messageObj = messageObj;
		this.vanishManag = vanishManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
			return true;
		}

		if (args.length < 1) {
			sender.sendMessage(PrefixMessage.PREFIX + "Veillez pr�ciser le joueur !");
			return true;
		}

		if (args.length < 2) {
			sender.sendMessage(PrefixMessage.PREFIX + "Veillez pr�ciser le message !");
			return true;
		}

		Player target = Bukkit.getPlayerExact(args[0]);
		if (target == null || !target.isOnline() || vanishManag.getList().contains(target)) {
			sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'a pas �t� trouv� !");
			return true;
		}

		if (target.getName().equals(sender.getName())) {
			sender.sendMessage(PrefixMessage.PREFIX + "Vous ne pouvez pas vous envoyer des messages � vous m�me !");
			return true;
		}

		String msg = "";
		for (int i = 1, j = args.length; i < j; i++)
			msg += " " + args[i];

		Player playersender = (Player) sender;
		playersender
				.sendMessage("�7[" + playersender.getDisplayName() + "�7->" + target.getDisplayName() + "�7]" + msg);
		target.sendMessage("�7[" + playersender.getDisplayName() + "�7->" + target.getDisplayName() + "�7]" + msg);

		messageObj.getMsgPlayer().put(target, playersender);

		for (int i = 0, j = messageObj.getSocial().size(); i < j; i++) {

			if (!(messageObj.getSocial().get(i) == playersender.getName()
					|| messageObj.getSocial().get(i) == target.getName())) {

				Player player = Bukkit.getServer().getPlayer(messageObj.getSocial().get(i));
				player.sendMessage("�6�l[�aSpy�6�l]�7" + playersender.getDisplayName() + "�7->"
						+ target.getDisplayName() + "�7]" + msg);

			}

		}

		return true;

	}

}
