package Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

	public static void Builder(boolean hasEnchant, Material mat, Player p,String ItemName,int slot) {
		ItemStack PowerItem = new ItemStack(mat);
		if(hasEnchant) {
			for(String s: Main.main.Frdb.getStringList("Heros."+Main.main.Frdb.getString(p.getName()+".Power")+".Item."+ItemName+".Enchants") ) {
				p.sendMessage(s);
				PowerItem.addEnchantment(Enchantment.getByName(s), Main.main.Frdb.getInt("Heros."+Main.main.Frdb.getString(p.getName()+".Power")+".Item."+ItemName+"."+s+".GetLevel"));
			
			}
		}
		p.getInventory().setItem(slot,PowerItem);
	}
}
