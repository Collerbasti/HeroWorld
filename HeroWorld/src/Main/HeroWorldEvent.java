package Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class HeroWorldEvent implements Listener{
	
	private final main plugin;
	
	
	
	public HeroWorldEvent(main plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
    }
	
	
	
	
	
	
	
	@EventHandler
	public void getWorld(PlayerTeleportEvent ev) {
		Player p = ev.getPlayer();
		if(main.Frdb.getStringList("config.GetWorlds").contains(p.getWorld().getName())) {
			main.Frdb.set(p.getName()+".isAnHero", true);
		}else {
			main.Frdb.set(p.getName()+".isAnHero", false);
		}
	}
}
