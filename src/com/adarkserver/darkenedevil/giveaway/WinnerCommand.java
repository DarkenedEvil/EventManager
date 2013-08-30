package com.adarkserver.darkenedevil.giveaway;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class WinnerCommand implements CommandExecutor{

	public FileConfiguration config;
	private final EventManager plugin;
	
	public WinnerCommand(EventManager plugin)    {
        this.plugin = plugin;
    }

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		config = plugin.getConfig();
		if (args.length == 0){
			if (sender instanceof Player) {
				if (commandLabel.equalsIgnoreCase("winner")) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " has won the event! Server will stop in" + config.get("Time before shutdown after /winner [name]. (In seconds)") + " seconds!");
					
				}
			}
		}
		return true;
	}
}
