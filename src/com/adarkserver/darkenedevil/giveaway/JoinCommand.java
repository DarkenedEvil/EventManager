package com.adarkserver.darkenedevil.giveaway;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {
	
	public FileConfiguration config;
	private final EventManager plugin;
	
	public JoinCommand(EventManager plugin)    {
        this.plugin = plugin;
    }

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		config = plugin.getConfig();
		if (commandLabel.equalsIgnoreCase("Eventjoin")) {
			if (args.length == 1){
				if (sender instanceof Player) {
					if (sender.hasPermission("EventManager.Ejoin")){
						File locations = new File (plugin.getDataFolder() + ("locations.yml"));
						FileConfiguration spawns = YamlConfiguration.loadConfiguration(locations);
						Set<String> locs = spawns.getConfigurationSection("players").getKeys(false);
						Object[] alocs = locs.toArray();
						Arrays.sort(alocs);
						Boolean wait = true;
						while (wait ==  true){
							
						}
					}
				}
			}
		}		
		return true;
	}

}
