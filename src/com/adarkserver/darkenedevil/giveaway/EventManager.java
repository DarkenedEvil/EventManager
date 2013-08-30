package com.adarkserver.darkenedevil.giveaway;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;




public final class EventManager extends JavaPlugin {
	
	public FileConfiguration config;
	
	public void loadConfig(){
		if(! (getDataFolder().exists())){
	    getDataFolder().mkdir();	    
		config = getConfig();
		config.options().header("EventManager Configuration");
	    config.set("Time to build(in minutes)", 15);
	    saveConfig();
	    getLogger().info("Config Created");
		}else{
	    	getLogger().info("Config Exists! Not creating one.");
	    }
	}
	
	public void TempPlayers(){
		File PLog = new File(getDataFolder() + ("tempPlayers.yml"));
		if(!(PLog.exists())){
			try {
				PLog.createNewFile();
				getLogger().info("TempPlayer File made!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getLogger().info("TempPlayer File Already exists not making a new one!");
		FileConfiguration players = YamlConfiguration.loadConfiguration(PLog);
		players.set("Event.Players", null);
	}
	
	public void Locations(){
		File locations = new File(getDataFolder() + ("locations.yml"));
		if(!(locations.exists())){
			try{
				locations.createNewFile();
				FileConfiguration players = YamlConfiguration.loadConfiguration(locations);
				players.set("Event.Spleef.Loc", 0);
				players.set("Event.Buildoff.Loc", 0);
				getLogger().info("Location file created!");
			} catch(IOException e){
				e.printStackTrace();
			}
		}else{
			getLogger().info("location file already made not making a new one!");
		}
	}
	
	public void onEnable(){
		
		loadConfig();
		TempPlayers();
		
		this.getCommand("winner").setExecutor(new WinnerCommand(this));
		this.getCommand("join").setExecutor(new JoinCommand(this));
		this.getCommand("EventCreate").setExecutor(new EventCreateCommand(this));
		this.getCommand("locationset").setExecutor(new LocationSet(this));
		
		getLogger().info("V 1 enabled!");
	}
	
	public void onDisable(){
		getLogger().info("V 1 disabled!");
		saveConfig();
	}

	public void onReload(){
		getLogger().info("Reloaded!");
		saveConfig();
	}

}
