package Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;



public class Shop {

	
	
	public static void Spawner(Player p) {
		Inventory Shop = p.getServer().createInventory(null, 27,p.getName()+"§b Effekt Shop");
		for(String s :Main.main.Shp.getStringList("config.Kategorien")) {
		p.sendMessage(Material.GOLD_NUGGET.toString());
			if(Main.main.Shp.getString(s+".Material").equalsIgnoreCase("Player_HEAD")) {
				
				
				ItemStack Kategorie = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta MetaKat = (SkullMeta) Kategorie.getItemMeta();
				MetaKat.setDisplayName(Main.main.Shp.getString(s+".Name"));
				Kategorie.setItemMeta(MetaKat);
				Shop.addItem(Kategorie);
			}else {
			ItemStack Kategorie = new ItemStack(Material.PLAYER_HEAD);
			ItemMeta MetaKat = Kategorie.getItemMeta();
			MetaKat.setDisplayName(Main.main.Shp.getString(s+".Name"));
			Kategorie.setItemMeta(MetaKat);
			Shop.addItem(Kategorie);
			}
			
			
		}
		p.openInventory(Shop);
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
