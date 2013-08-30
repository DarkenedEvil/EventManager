package com.adarkserver.darkenedevil.giveaway;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class EventCheck {
	
	public FileConfiguration config;
	private final EventManager plugin;
	
	public EventCheck(EventManager plugin)    {
        this.plugin = plugin;
    }
	
	public void eventcheck(String EventName, Player playername){
		config = plugin.getConfig();
		File PLog = new File (plugin.getDataFolder() + ("tempPlayers.yml"));
		FileConfiguration players = YamlConfiguration.loadConfiguration(PLog);
		
		String Event = (String) players.get("EventType");
		if(Event == null){
			playername.sendMessage("Sorry there is no event currently!");
		}else{
			int numleft = (int) players.get("NumPlayer");
			if(numleft < 0 ){
				playername.sendMessage("Sorry the event is full:( Try again next time!");
			}else if(numleft > 1){
				int newnum = numleft - 1;
				players.set("NumPlayer", newnum);
				playername.sendMessage("You got into the event!");
				playername.teleport((Location) config.get(Event + "location"));
				playername.sendMessage("Wait here untill event starts!");
				playername.setWalkSpeed(0);
				playername.setFlySpeed(0);
			}
		}
	}
}
