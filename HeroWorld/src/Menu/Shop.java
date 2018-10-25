package Menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;






public class Shop {

	public static void Kategorie(String Kategorie,Player p, String Held){
		Inventory Shop = p.getServer().createInventory(null, 27,p.getName()+"§b Customnize "+Kategorie);
		
		ItemStack Coin = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta MCoin = Coin.getItemMeta();
		MCoin.setDisplayName("Fähigkeiten");
		Coin.setItemMeta(MCoin);
		
		Shop.addItem(Coin);
		
		ItemStack Test = new ItemStack(Material.SKELETON_SKULL);
		ItemMeta MTest = Test.getItemMeta();
		MTest.setDisplayName("Testen");
		Test.setItemMeta(MTest);
		
		Shop.addItem(Test);
		
		
		ItemStack Aussehen = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta MAus = (SkullMeta) Aussehen.getItemMeta();
		ArrayList<String> Friends = new ArrayList<String>();
		Friends.add("Klicken um Namen zu Setzen");
		Friends.add("Rechtsklick um aussehen zu setzen");
		MAus.setLore(Friends);
		if(Held.equals("Cs1")) {
			if(!Main.main.Cs1.isSet(p.getName()+".Name")) {
				MAus.setDisplayName("Noch nicht Gesetzt");
			}else {
				MAus.setDisplayName(Main.main.Cs1.getString(p.getName()+".Name"));
			}
			if(!Main.main.Cs1.isSet(p.getName()+".Owner")) {
				
			}else {
				MAus.setOwner(Main.main.Cs1.getString(p.getName()+".Owner"));
			}
		}else if(Held.equals("Cs2")) {
			if(!Main.main.Cs2.isSet(p.getName()+".Name")) {
				MAus.setDisplayName("Noch nicht Gesetzt");
			}else {
				MAus.setDisplayName(Main.main.Cs2.getString(p.getName()+".Name"));
			}
			if(!Main.main.Cs2.isSet(p.getName()+".Owner")) {
				
			}else {
				MAus.setOwner(Main.main.Cs2.getString(p.getName()+".Owner"));
				
			}
		}else if(Held.equals("Cs3")) {
			if(!Main.main.Cs3.isSet(p.getName()+".Name")) {
				MAus.setDisplayName("Noch nicht Gesetzt");
			}else {
				MAus.setDisplayName(Main.main.Cs3.getString(p.getName()+".Name"));
			}
			
			if(!Main.main.Cs3.isSet(p.getName()+".Owner")) {
				
			}else {
				MAus.setOwner(Main.main.Cs3.getString(p.getName()+".Owner"));
				
			}
			
		}
		Aussehen.setItemMeta(MAus);
		Shop.addItem(Aussehen);
		p.openInventory(Shop);
		
		
		
		
	}
	
	public static void Spawner(Player p) {
		Inventory Shop = p.getServer().createInventory(null, 27,p.getName()+"§b Effekt Shop");
		for(String s :Main.main.Shp.getStringList("config.Kategorien")) {
			if(Main.main.Shp.getString(s+".Material").equalsIgnoreCase("Player_HEAD")) {
				
				
				ItemStack Kategorie = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta MetaKat = (SkullMeta) Kategorie.getItemMeta();
				MetaKat.setDisplayName(Main.main.Shp.getString(s+".Name"));
				Kategorie.setItemMeta(MetaKat);
				Shop.addItem(Kategorie);
			}else if(Main.main.Shp.getString(s+".Material").equalsIgnoreCase("GOLD_NUGGET")) {
				ItemStack Kategorie = new ItemStack(Material.GOLD_NUGGET);
				ItemMeta MetaKat =  Kategorie.getItemMeta();
				MetaKat.setDisplayName(Main.main.Shp.getString(s+".Name"));
				Kategorie.setItemMeta(MetaKat);
				Shop.addItem(Kategorie);	
			}
			
			else {
			ItemStack Kategorie = new ItemStack(Material.PLAYER_HEAD);
			ItemMeta MetaKat = Kategorie.getItemMeta();
			MetaKat.setDisplayName(Main.main.Shp.getString(s+".Name"));
			Kategorie.setItemMeta(MetaKat);
			Shop.addItem(Kategorie);
			}
			
			
		}
		p.openInventory(Shop);
		
		
	}
		
	public static void Kaufen(Player p) {
		Inventory Kaufen = p.getServer().createInventory(null, 27,p.getName()+" Kaufen");
		for(String s :Main.main.Kauf.getStringList("config.Player")) {
			if(!p.getName().equals(s)) {
			
				ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta SHead = (SkullMeta) Head.getItemMeta();
				SHead.setDisplayName(s);
				SHead.setOwner(s);
				Head.setItemMeta(SHead);
				Kaufen.addItem(Head);
				
			}
		}
		p.openInventory(Kaufen);
	}
	public static void PlayerKaufen(Player p,Player p2) {
		Inventory Kaufen = p.getServer().createInventory(null, 27,p.getName()+" Kaufen von "+p2.getName());
		Main.main.Kauf.set(p.getName()+".Klicked.1", p2.getName());
		for(String s :Main.main.Kauf.getStringList(p2.getName()+".Helden")) {
			if(!p.getName().equals(s)) {
			
				ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta SHead = (SkullMeta) Head.getItemMeta();
				SHead.setDisplayName(s);
				SHead.setOwner(s);
				Head.setItemMeta(SHead);
				Kaufen.addItem(Head);
				
			}
		}
		p.openInventory(Kaufen);
	}
		
		
		
		
	
	public static void Fähigkeiten(String Hero, Player p) {
		
		Inventory Shop = p.getServer().createInventory(null, 27,p.getName()+"§b Fähigkeiten von: "+Hero);
		for(String s :Main.main.Frdb2.getStringList("config.CustomHeros.Abilitys.List")) {
			Material mat;
			if(Main.main.Frdb2.getString("config.CustomHeros.Abilitys."+s+".Material").contains("FIRE_CHARGE")) {
				mat = Material.FIRE_CHARGE;
			}else if(Main.main.Frdb2.getString("config.CustomHeros.Abilitys."+s+".Material").contains("SHIELD")) {
				mat = Material.SHIELD;
			}else  {
				mat = Material.WHITE_WOOL;
				}
			ItemStack Power = new ItemStack(mat);
			ItemMeta MPower = Power.getItemMeta();
			MPower.setDisplayName(s);
			ArrayList<String> Friends = new ArrayList<String>();
			if(Main.main.Frdb2.getBoolean("config.CustomHeros.Abilitys."+s+".Multi")) {
			Friends.add("Klicken um Einen Hinzuzufügen");
			
			Friends.add("Rechts Klick um einen abzuziehen");
			if(Hero.equals("Cs1")) {
				Friends.add(Main.main.Cs1.getString(p.getName()+".Abilitys."+s+".Count"));
				}
			if(Hero.equals("Cs2")) {
				Friends.add(Main.main.Cs2.getString(p.getName()+".Abilitys."+s+".Count"));
				}
			if(Hero.equals("Cs3")) {
				Friends.add(Main.main.Cs3.getString(p.getName()+".Abilitys."+s+".Count"));
				}
			}else {
				if(Hero.equals("Cs1")) {
					if(Main.main.Cs1.getBoolean(p.getName()+".Abilitys."+s+".isSet")) {
						Friends.add("Klicken um zu Deaktivieren");	
					}else {
				
					Friends.add("Klicken um zu Aktivieren");
					}
				}
				
				if(Hero.equals("Cs2")) {
					if(Main.main.Cs2.getBoolean(p.getName()+".Abilitys."+s+".isSet")) {
						Friends.add("Klicken um zu Deaktivieren");	
					}else {
				
					Friends.add("Klicken um zu Aktivieren");
					}
				}
				
				if(Hero.equals("Cs3")) {
					if(Main.main.Cs3.getBoolean(p.getName()+".Abilitys."+s+".isSet")) {
						Friends.add("Klicken um zu Deaktivieren");	
					}else {
				
					Friends.add("Klicken um zu Aktivieren");
					}
				}
			
			}
			MPower.setLore(Friends);
			Power.setItemMeta(MPower);
			Shop.addItem(Power);
			
			
			
			
		}
		p.openInventory(Shop);	
		
	}
	
	
	
}
