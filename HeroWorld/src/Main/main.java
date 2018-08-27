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
import org.bukkit.plugin.java.JavaPlugin;


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
	
	
	
	
	public static File Friends;
	public static FileConfiguration Frdb;
	
	
	
	
	
	@Override	

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);

		new Main.HeroWorldEvent(this);
		
		
		
		
		main.Friends = new File("plugins/Noneless","FriendsDB.yml");
    	main.Frdb = YamlConfiguration.loadConfiguration(main.Friends); 
	}
	
	
	
	public void onDisable() {
		try {
			Frdb.save(Friends);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
