package com.adarkserver.darkenedevil.giveaway;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class EventCreateCommand implements CommandExecutor{
	
	public FileConfiguration config;
	private final EventManager plugin;
	
	public EventCreateCommand(EventManager plugin)    {
        this.plugin = plugin;
    }

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		config = plugin.getConfig();
		if (commandLabel.equalsIgnoreCase("Eventcreate")) {
			if (args.length == 2){
				if (sender instanceof Player) {
					if (sender.hasPermission("EventManager.ECreate")){
						String Event = args[1];
						if(Event.equalsIgnoreCase("BuildOff")){
							File PLog = new File (plugin.getDataFolder() + ("tempPlayers.yml"));
							Player player = (Player) sender;
							FileConfiguration players = YamlConfiguration.loadConfiguration(PLog);
							try{
							    int Nplayers = Integer.parseInt(args[2]);
								Bukkit.getServer().broadcastMessage(player + " has started a build off! Type /join to enter!");
								players.set("Event.NumPlayers", Nplayers);
								players.set("EventType", "BuildOff");
							} catch (NumberFormatException error) {
								sender.sendMessage("Usage: /eventcreate [eventtype] [number of players]");
							    System.out.println(error);
							}
						}else if(Event.equalsIgnoreCase("Spleef")){
							File PLog = new File (plugin.getDataFolder() + ("tempPlayers.yml"));
							Player player = (Player) sender;
							FileConfiguration players = YamlConfiguration.loadConfiguration(PLog);
							try{
							    int Nplayers = Integer.parseInt(args[2]);
								Bukkit.getServer().broadcastMessage(player + " has started a spleef event! Type /join to enter!");
								players.set("Event.NumPlayers", Nplayers);
								players.set("EventType", "Spleef");
							} catch (NumberFormatException error) {
								sender.sendMessage("Uh oh! Something broke! Please check the console.");
							    System.out.println(error);
							}
						}else{
							sender.sendMessage("That Event doesn't exists!");
						}
					}else{
						sender.sendMessage("You don't have permission to do this!");
					}
				}else{
					sender.sendMessage("You must be a player to use this command!");
				}
			}else{
				sender.sendMessage("Usage: /eventcreate [eventname] [num_players]");
			}
		}
		return true;
	}
}
