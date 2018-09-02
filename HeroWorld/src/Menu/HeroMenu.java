package Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.iCo6.iConomy;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.VaultEco;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.plugins.Economy_eWallet;

public class HeroMenu {
	
	public static void Summon(Player p) {
		if(Main.main.Frdb2.getBoolean(p.getName()+".isAnHero")) {
			int Count = 0;
			Inventory Menue = p.getServer().createInventory(null, 27,p.getName()+"§b Heros");
			for(String S : Main.main.Frdb2.getStringList("config.Heros")) {
				
				ItemStack Skull = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta SMeta = (SkullMeta) Skull.getItemMeta();
				SMeta.setOwner((S));
				SMeta.setDisplayName("Hero: "+S);
				Skull.setItemMeta(SMeta);
				Menue.setItem(Count, Skull);
				Count = Count+1;
				
				
			}
			ItemStack Shop = new ItemStack(Material.GOLD_NUGGET);
			ItemMeta MShop = Shop.getItemMeta();
			MShop.setDisplayName("Shop");
			Shop.setItemMeta(MShop);
			Menue.setItem(26, Shop);
			p.openInventory(Menue);
			
			
			
			
			
		}else {
			p.sendMessage(ChatColor.RED+"ups da ist was Schiefgelaufen");
			p.teleport(p.getLocation());
			
		}
	}
	
}
