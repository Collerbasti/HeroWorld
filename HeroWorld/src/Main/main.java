package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import HeroCommands.CMDreload;
import net.milkbowl.vault.economy.Economy;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




public class main extends JavaPlugin implements Listener 
 {
	
	
	
	
	public static File Friends2;
	public static FileConfiguration Frdb2;
	public static File Shop;
	public static FileConfiguration Shp;
	
	private static Economy econ = null;
	public static Economy economy;
	
	
	@Override	

	public void onEnable() {
		
		
		if(!setupEconomy()) {
			Bukkit.shutdown();
		}
		
		
		
		Bukkit.getPluginManager().registerEvents(this, this);

		
		this.getCommand("HReload").setExecutor(new CMDreload());
		
		new HeroWorldEvent(this);
		
		
		
		main.Friends2 = new File("plugins/HeroWorld","FriendsDB.yml");
    	main.Frdb2 = YamlConfiguration.loadConfiguration(main.Friends2); 
    	
    	main.Shop = new File("plugins/HeroWorld","Shop.yml");
    	main.Shp = YamlConfiguration.loadConfiguration(main.Shop); 
	}
	
	
	
	  private boolean setupEconomy() {
	        if (getServer().getPluginManager().getPlugin("Vault") == null) {
	            return false;
	        }
	        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	        if (rsp == null) {
	            return false;
	        }
	        econ = rsp.getProvider();
	        return econ != null;
	    }
	
	
	
	public static void reload() {
		main.Frdb2 = YamlConfiguration.loadConfiguration(main.Friends2);
		main.Shp = YamlConfiguration.loadConfiguration(main.Shop); 
	}
	
	public void onDisable() {
		try {
			Frdb2.save(Friends2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
