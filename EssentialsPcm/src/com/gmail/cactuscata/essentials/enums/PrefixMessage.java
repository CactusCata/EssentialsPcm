package com.gmail.cactuscata.essentials.enums;

public enum PrefixMessage {

	PREFIX("§5§l[§3PCM§5§l]§a "),

	PREFIX_SENDER_BE_PLAYER(PrefixMessage.PREFIX + "§4La commande ne peut etre execute que par un joueur !"),

	PREFIX_VANISH("§f[§bVanish§f]§e ");

	private final String message;

	private PrefixMessage(String message) {

		this.message = message;

	}

	@Override
	public String toString() {
		return message;
	}

}
