package Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class Shop {

	
	
	public static void Spawner(Player p) {
		Inventory Shop = p.getServer().createInventory(null, 27,p.getName()+"�b Effekt Shop");
		int Counter = 0;
		for(String s :Main.main.Shp.getStringList("config.Kategorien")) {
		
			ItemStack Kategorie = new ItemStack(Material.getMaterial(Main.main.Shp.getString(s+".Material")));
			ItemMeta MetaKat = Kategorie.getItemMeta();
			MetaKat.setDisplayName(Main.main.Shp.getString(s+".Name"));
			Kategorie.setItemMeta(MetaKat);
			
			Shop.addItem(Kategorie);
			
		}
		p.openInventory(Shop);
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
