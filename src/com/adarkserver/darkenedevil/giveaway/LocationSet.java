package com.adarkserver.darkenedevil.giveaway;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LocationSet implements CommandExecutor{
	
	private final EventManager plugin;
	
	public LocationSet(EventManager plugin)    {
        this.plugin = plugin;
    }
	
	public void createspawn(int x, int y , int z, World world, int LocNum, String Event){
		File locations = new File (plugin.getDataFolder() + ("locations.yml"));
		FileConfiguration spawns = YamlConfiguration.loadConfiguration(locations);
		spawns.set("Worlds." + world, world);
		spawns.set("Worlds." + world + "." + Event + "." + LocNum + ".x", x);
		spawns.set("Worlds." + world + "." + Event + "." + LocNum + ".y", y);
		spawns.set("Worlds." + world + "." + Event + "." + LocNum + ".z", z);
		try {
			spawns.save("locations.yml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("locationset")) {
			if (args.length == 1){
				if (sender instanceof Player) {
						if (sender.hasPermission("EventManager.LSet")){
						File locations = new File (plugin.getDataFolder() + ("locations.yml"));
						FileConfiguration spawns = YamlConfiguration.loadConfiguration(locations);
						if(args[1].equalsIgnoreCase("spleef")){
							int locnum = spawns.getInt("Event.Spleef.Loc");
							int newnum = locnum + 1;
							spawns.set("Event.Spleef.Loc", newnum);
							Player player = (Player) sender;
							Location loc = player.getLocation();
							int x = loc.getBlockX();
							int y = loc.getBlockY();
							int z = loc.getBlockZ();
							World world = player.getWorld();
							createspawn(x, y, z, world, locnum, "Spleef");
							try {
								spawns.save("locations.yml");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else if(args[1].equalsIgnoreCase("buildoff")){
							int locnum = spawns.getInt("Event.Buildoff.Loc");
							int newnum = locnum + 1;
							spawns.set("Event.Buildoff.Loc", newnum);
							Player player = (Player) sender;
							Location loc = player.getLocation();
							int x = loc.getBlockX();
							int y = loc.getBlockY();
							int z = loc.getBlockZ();
							World world = player.getWorld();
							createspawn(x, y, z, world, locnum, "Buildoff");
							try {
								spawns.save("locations.yml");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}else{
						sender.sendMessage("YOu don't have permission for this command.");
					}
				}else{
					sender.sendMessage("You must be a player to use this command!");
				}
			}else{
				sender.sendMessage("Usage: /locationset [eventtype]");
			}
		}
		return true;
	}
}