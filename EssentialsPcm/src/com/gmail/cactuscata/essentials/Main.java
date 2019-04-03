package com.gmail.cactuscata.essentials;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.cactuscata.essentials.commands.other.Fly;
import com.gmail.cactuscata.essentials.commands.other.Gamemode;
import com.gmail.cactuscata.essentials.commands.other.Heal;
import com.gmail.cactuscata.essentials.commands.other.Speed;
import com.gmail.cactuscata.essentials.commands.spy.MessageObj;
import com.gmail.cactuscata.essentials.commands.spy.Msg;
import com.gmail.cactuscata.essentials.commands.spy.R;
import com.gmail.cactuscata.essentials.commands.spy.SocialSpy;
import com.gmail.cactuscata.essentials.commands.spy.SpyList;
import com.gmail.cactuscata.essentials.commands.warp.CWarp;
import com.gmail.cactuscata.essentials.commands.warp.DelWarp;
import com.gmail.cactuscata.essentials.commands.warp.SetWarp;
import com.gmail.cactuscata.essentials.commands.warp.Warp;
import com.gmail.cactuscata.essentials.commands.warp.WarpInfo;
import com.gmail.cactuscata.essentials.commands.warp.Warps;
import com.gmail.cactuscata.essentials.vanish.Vanish;
import com.gmail.cactuscata.essentials.vanish.VanishManager;

public class Main extends JavaPlugin {

	public void onEnable() {

		PluginManager pm = getServer().getPluginManager();
		MessageObj messageObj = new MessageObj();
		VanishManager vanishManager = new VanishManager();

		pm.registerEvents(new PlayerSendMessage(), this);
		pm.registerEvents(new Sign(), this);

		getCommand("gm").setExecutor(new Gamemode());
		getCommand("msg").setExecutor(new Msg(messageObj, vanishManager));
		getCommand("r").setExecutor(new R(messageObj));
		getCommand("socialspy").setExecutor(new SocialSpy(messageObj));
		getCommand("spylist").setExecutor(new SpyList(messageObj));
		getCommand("speed").setExecutor(new Speed());
		getCommand("delwarp").setExecutor(new DelWarp(this));
		getCommand("setwarp").setExecutor(new SetWarp(this));
		getCommand("warp").setExecutor(new Warp(this));
		getCommand("warps").setExecutor(new Warps(this));
		getCommand("warpinfo").setExecutor(new WarpInfo(this));
		getCommand("vanish").setExecutor(new Vanish(vanishManager));
		getCommand("cwarp").setExecutor(new CWarp(this));
		getCommand("fly").setExecutor(new Fly());
		getCommand("heal").setExecutor(new Heal());

	}

}