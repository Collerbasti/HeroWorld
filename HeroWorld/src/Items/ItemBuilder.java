package Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

	public static void Builder(boolean hasEnchant, Material mat, Player p,String ItemName,int slot) {
		ItemStack PowerItem = new ItemStack(mat);
		if(hasEnchant) {
			for(String s: Main.main.Frdb2.getStringList("Heros."+Main.main.Frdb2.getString(p.getName()+".Power")+".Item."+ItemName+".Enchants") ) {
				p.sendMessage(s);
				PowerItem.addEnchantment(Enchantment.getByName(s), Main.main.Frdb2.getInt("Heros."+Main.main.Frdb2.getString(p.getName()+".Power")+".Item."+ItemName+"."+s+".GetLevel"));
			// Enchantment.ARROW_KNOCKBACK
			}
		}
		p.getInventory().setItem(slot,PowerItem);
	}
}
