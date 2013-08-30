package com.adarkserver.darkenedevil.giveaway;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class JoinCommand implements CommandExecutor {
	
	public FileConfiguration config;
	private final EventManager plugin;
	
	public JoinCommand(EventManager plugin)    {
        this.plugin = plugin;
    }

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		config = plugin.getConfig();
		
		
		
		
		return true;
	}

}
