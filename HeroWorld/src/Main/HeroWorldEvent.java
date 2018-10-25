package Main;
//Das Neue vom neuen 
import java.awt.Menu;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;
import Menu.HeroMenu;
import Menu.Shop;


import net.md_5.bungee.api.ChatColor;

public class HeroWorldEvent implements Listener{
	
	private final main plugin;
	
	
	
	public HeroWorldEvent(main plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
    }
	
	
	
	
	@EventHandler
	public void Chat(PlayerChatEvent ev) {
		Player p = ev.getPlayer();
		if(Main.main.tmp.getBoolean(p.getName()+".Listener")) {
			if(Main.main.tmp.getBoolean(p.getName()+".LeftClick")) {
				if(Main.main.tmp.getString(p.getName()+".Hero").contains("Cs1")) {
					Main.main.Cs1.set(p.getName()+".Name", ev.getMessage());
					try {
						Main.main.Cs1.save(Main.main.Custom1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(Main.main.tmp.getString(p.getName()+".Hero").contains("Cs2")) {
					Main.main.Cs2.set(p.getName()+".Name", ev.getMessage());
					try {
						Main.main.Cs2.save(Main.main.Custom2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(Main.main.tmp.getString(p.getName()+".Hero").contains("Cs3")) {
					Main.main.Cs3.set(p.getName()+".Name", ev.getMessage());
					try {
						Main.main.Cs3.save(Main.main.Custom3);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				p.sendMessage("Name Gesetzt");
				Main.main.tmp.set(p.getName()+".Listener", false);
				ev.setCancelled(true);
			
			}
		
			else {
				
				
			
					if(Main.main.tmp.getString(p.getName()+".Hero").contains("Cs1")) {
						
						Main.main.Cs1.set(p.getName()+".Owner", ev.getMessage());
						try {
							Main.main.Cs1.save(Main.main.Custom1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(Main.main.tmp.getString(p.getName()+".Hero").contains("Cs2")) {
						
						Main.main.Cs2.set(p.getName()+".Owner", ev.getMessage());
						try {
							Main.main.Cs2.save(Main.main.Custom2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(Main.main.tmp.getString(p.getName()+".Hero").contains("Cs3")) {
						
						Main.main.Cs3.set(p.getName()+".Owner", ev.getMessage());
						try {
							Main.main.Cs3.save(Main.main.Custom3);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
					
					p.sendMessage("Owner Gesetzt");
					
					Main.main.tmp.set(p.getName()+".Listener", false);
				ev.setCancelled(true);
				
				
			
				}
		}	
			
		
	}
	
	public static boolean HeroWorld(Player p) {
		int Count = 0; 
		if(main.Frdb2.getStringList("config.GetWorlds").contains(p.getWorld().getName())) {
			return true;
		}else {
		return false;
		}
		
	}
	@EventHandler
	public void getWorld(PlayerTeleportEvent ev) {
		Player p = ev.getPlayer();
		if(main.Frdb2.getStringList("config.GetWorlds").contains(p.getWorld().getName())) {
			main.Frdb2.set(p.getName()+".isAnHero", true);
			
			ItemStack Lore = new ItemStack(Material.MINECART);
			ItemMeta MLore = Lore.getItemMeta();
			MLore.setDisplayName("HeroWorld Menu");
			Lore.setItemMeta(MLore);
			ev.getPlayer().getInventory().setItem(8, Lore);
			
			
			ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta HeadMeta = (SkullMeta) Head.getItemMeta();
			HeadMeta.setOwner(main.Frdb2.getString(ev.getPlayer().getName()+".Power"));
			Head.setItemMeta(HeadMeta);
			ev.getPlayer().getInventory().setHelmet(Head);
			int Count = 0; 
			for(String s : main.Frdb2.getStringList("Heros."+main.Frdb2.getString(ev.getPlayer().getName()+".Power")+".Items")) {
		
				if(main.Frdb2.getBoolean(main.Frdb2.getString("Heros."+ev.getPlayer().getName()+".Power")+".Item."+s+".hasEnchant")) {
					Items.ItemBuilder.Builder(true, Material.getMaterial(s), p,s,Count);
					
				}else {
					Items.ItemBuilder.Builder(false, Material.getMaterial(s), p,s,Count);
				}
				Count = Count+2;
			}
			
			
		}else {
			main.Frdb2.set(p.getName()+".isAnHero", false);
		}
	}
	
	@EventHandler
	public void disconnect(PlayerQuitEvent ev) {
		main.Frdb2.set(ev.getPlayer().getName()+".isAnHero", false);
	}
	 
	
	
	@EventHandler
	public void InteracktEvent(PlayerInteractEvent ev) {
		if(ev.getItem().getItemMeta().getDisplayName().equals("HeroWorld Menu")) {
			HeroMenu.Summon(ev.getPlayer());
		}
	}
	
	
	@EventHandler
	public void ItemUse(InventoryClickEvent ev) {
		
		Player p = (Player) ev.getWhoClicked();
		ItemStack Item = ev.getCurrentItem();
		
		if(ev.getInventory().getName().contains("F�higkeiten")) {
			ArrayList<String> Abilitys =  new ArrayList<String>();
			Abilitys.addAll(Main.main.Frdb2.getStringList("config.CustomHeros.Abilitys.List"));
			p.sendMessage(Abilitys.toString());
			if(Main.main.Frdb2.getStringList("config.CustomHeros.Abilitys.List").contains(Item.getItemMeta().getDisplayName())) {
				boolean Bezahlt = false;
				if(main.econ.hasAccount(p)) {
					if(main.econ.getBalance(p)>1000) {
						p.sendMessage("Dir Wurden 1000 ECoins abgezogen");	
						main.econ.withdrawPlayer(p, 1000);
						Bezahlt = true;
					}
				}
				if(Bezahlt) {
				if(!Main.main.Frdb2.getBoolean("config.CustomHeros.Abilitys."+Item.getItemMeta().getDisplayName()+".Multi")) {
				
					ev.setCancelled(true);
					if(ev.getInventory().getName().contains("Cs1")) {
					if(Main.main.Cs1.getBoolean(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet")){
						Main.main.Cs1.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet",false);
					}else {
						Main.main.Cs1.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet",true);
					}
					Shop.F�higkeiten("Cs1", p);
				}
				if(ev.getInventory().getName().contains("Cs2")) {
					if(Main.main.Cs2.getBoolean(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet")){
						Main.main.Cs2.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet",false);
					}else {
						Main.main.Cs2.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet",true);
						
					}
					Shop.F�higkeiten("Cs2", p);
					
				}
				if(ev.getInventory().getName().contains("Cs3")) {
					if(Main.main.Cs3.getBoolean(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet")){
						Main.main.Cs3.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet",false);
					}else {
						Main.main.Cs3.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".isSet",true);
						
					}
					Shop.F�higkeiten("Cs3", p);
					
				}
			}else {
				//Multy
				
				ev.setCancelled(true);
				if(ev.getInventory().getName().contains("Cs1")) {
				if(Main.main.Cs1.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")==0){
					if(ev.getClick().isLeftClick()) {
						Main.main.Cs1.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs1.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")+1);
					}else {
						p.sendMessage("Sorry aber das Geht nicht");
					}
				}else {
					
					if(ev.getClick().isLeftClick()) {
						Main.main.Cs1.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs1.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")+1);
					}else {
						Main.main.Cs1.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs1.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")-1);
						}
					
					
				}
				Shop.F�higkeiten("Cs1", p);
			}
			if(ev.getInventory().getName().contains("Cs2")) {
				if(Main.main.Cs2.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")==0){
					if(ev.getClick().isLeftClick()) {
						Main.main.Cs2.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs2.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")+1);
					}else {
						p.sendMessage("Sorry aber das Geht nicht");
					}
				}else {
					
					if(ev.getClick().isLeftClick()) {
						Main.main.Cs2.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs2.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")+1);
					}else {
						Main.main.Cs2.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs2.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")-1);
						}
					
					
				}}
			if(ev.getInventory().getName().contains("Cs3")) {
				if(Main.main.Cs3.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")==0){
					if(ev.getClick().isLeftClick()) {
						Main.main.Cs3.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs3.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")+1);
					}else {
						p.sendMessage("Sorry aber das Geht nicht");
					}
				}else {
					
					if(ev.getClick().isLeftClick()) {
						Main.main.Cs3.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs3.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")+1);
					}else {
						Main.main.Cs3.set(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count", Main.main.Cs3.getInt(p.getName()+".Abilitys."+Item.getItemMeta().getDisplayName()+".Count")-1);
						}
					
					
				}
			}
				
				
				
			}
				
			}
			}else {
				
			}
		}
		if(ev.getInventory().getName().contains("Customnize")) {
			
			
			if(Item.getItemMeta().getDisplayName().contains("Testen")) {
				
				if(ev.getInventory().getName().contains("Held1")) {
					main.Frdb2.set(p.getName()+".Power", "CustomHero:Cs1_"+Main.main.Cs1.getString(p.getName()+".Name"));
					
				}
				if(ev.getInventory().getName().contains("Held2")) {
					main.Frdb2.set(p.getName()+".Power", "CustomHero:Cs2_"+Main.main.Cs2.getString(p.getName()+".Name"));
					
				}
				if(ev.getInventory().getName().contains("Held3")) {
					main.Frdb2.set(p.getName()+".Power", "CustomHero:Cs3_"+Main.main.Cs3.getString(p.getName()+".Name"));
					
				} 
				p.sendMessage("Dann Teste den mal");
				p.closeInventory();
				for(PotionEffect Potion: p.getActivePotionEffects()) {
				
					p.removePotionEffect(Potion.getType());
				}
			}
				
				
			
			
			if(Item.getItemMeta().getDisplayName().contains("F�higkeiten")) {
				if(ev.getInventory().getName().contains("Held1")) {
					Shop.F�higkeiten("Cs1", p);
					
				}
				if(ev.getInventory().getName().contains("Held2")) {
					Shop.F�higkeiten("Cs2", p);
					
				}
				if(ev.getInventory().getName().contains("Held3")) {
					Shop.F�higkeiten("Cs3", p);
					
				} 
			}
		}
		
		if(p.getInventory().getName().equals(p.getName()+" Kaufen")) {
			if(ev.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
				Shop.PlayerKaufen(p, Bukkit.getPlayer(ev.getCurrentItem().getItemMeta().getDisplayName()));
			}
		}
		
		if(p.getInventory().getName().equals(p.getName()+" Kaufen von "+Main.main.Kauf.getString(p.getName()+".Klicked.1"))) {
			if(Main.main.Kauf.getStringList(Main.main.Kauf.getString(p.getName()+".Klicked.1")).contains(ev.getCurrentItem().getItemMeta().getDisplayName())){
				String P2 = Main.main.Kauf.getString(p.getName()+".Klicked.1");
				String Held = ev.getCurrentItem().getItemMeta().getDisplayName();
				if(main.econ.getBalance(p)>Main.main.Kauf.getDouble(P2+"."+Held+".Wert")) {
				p.sendMessage("Du hast "+Held+" von "+ P2+" f�r "+Main.main.Kauf.getDouble(P2+"."+Held+".Wert")+" gekauft");
				ArrayList<String> Hero = new ArrayList<String>();
				Hero.addAll(Main.main.Kauf.getStringList(p.getName()+".GekaufteHelden.Helden"));
				Hero.add(Held);
				Main.main.Kauf.set(p.getName()+".GekaufteHelden.Helden", Hero);
				Main.main.Kauf.set(p.getName()+".GekaufteHelden."+Held+".Owner",P2);
				
				}else {
					p.sendMessage("Sorry aber du hast nicht genug Geld, du brauchst mindestens "+Main.main.Kauf.getDouble(P2+"."+Held+".Wert"));

				}
				
				
			}
		}
		
		
		
		if(p.getInventory().getName().equals(p.getName()+"�b Effekt Shop")) {
			if(Item.getItemMeta().getDisplayName().contains("Kaufen")) {
				Shop.Kaufen(p);
				p.sendMessage("Kauf Dir Dein Held");
			}
			ArrayList<String> Kat = new ArrayList<String>();
					 Kat.addAll(Main.main.Shp.getStringList("config.Abilytis"));
			if(Kat.contains(ev.getCurrentItem().getItemMeta().getDisplayName())) {
				
				if(Item.getItemMeta().getDisplayName().equalsIgnoreCase("CustomHeld_1")) {
				Shop.F�higkeiten("Cs1", p);
					}
				else if(Item.getItemMeta().getDisplayName().equalsIgnoreCase("CustomHeld_2")) {
					Shop.F�higkeiten("Cs2", p);
					}
				else {
					Shop.F�higkeiten("Cs3", p);
					}
			}
				
		}else {
		
				if(Item.getItemMeta().getDisplayName().contains("Shop")) {
			Shop.Spawner(p);
		}
				
				
				
				
				
				
				
				
				
		if(ev.getInventory().getName().contains("Effekt")) {
			if(Item.getItemMeta().getDisplayName().contains("1")) {
				Shop.Kategorie(Item.getItemMeta().getDisplayName(), p, "Cs1");
			}
			if(Item.getItemMeta().getDisplayName().contains("2")) {
				Shop.Kategorie(Item.getItemMeta().getDisplayName(), p, "Cs2");
			}
			if(Item.getItemMeta().getDisplayName().contains("3")) {
				Shop.Kategorie(Item.getItemMeta().getDisplayName(), p, "Cs3");
			}
		}
		if(ev.getInventory().getName().contains("Customnize")) {
			if(Item.getType().equals(Material.PLAYER_HEAD)) {
				if(ev.getInventory().getName().contains("1")){
					Main.main.tmp.set(p.getName()+".Listener", true);
					Main.main.tmp.set(p.getName()+".Hero", "Cs1");
					Main.main.tmp.set(p.getName()+".LeftClick", ev.getClick().isLeftClick());
				if(ev.getClick().isLeftClick()) {
					ev.setCancelled(true);
					p.sendMessage("Passe den Namen jetzt an indem du diesen ohne / oder �hnlichem in den Chat schreibst");
					p.closeInventory();
				}else {
					ev.setCancelled(true);
					p.sendMessage("Passe den Skin jetzt an indem du den Namen eines Spielers ohne / oder �hnlichem in den Chat schreibst");
					p.closeInventory();
				}
				}
				
				if(ev.getInventory().getName().contains("2")){
					Main.main.tmp.set(p.getName()+".Listener", true);
					Main.main.tmp.set(p.getName()+".Hero", "Cs2");
					Main.main.tmp.set(p.getName()+".LeftClick", ev.getClick().isLeftClick());
				if(ev.getClick().isLeftClick()) {
					p.sendMessage("Passe den Namen jetzt an indem du diesen ohne / oder �hnlichem in den Chat schreibst");
					p.closeInventory();
				}else {
					p.sendMessage("Passe den Skin jetzt an indem du den Namen eines Spielers ohne / oder �hnlichem in den Chat schreibst");
					p.closeInventory();
				}
				}
				
				if(ev.getInventory().getName().contains("3")){
					Main.main.tmp.set(p.getName()+".Listener", true);
					Main.main.tmp.set(p.getName()+".Hero", "Cs3");
					Main.main.tmp.set(p.getName()+".LeftClick", ev.getClick().isLeftClick());
				if(ev.getClick().isLeftClick()) {
					p.sendMessage("Passe den Namen jetzt an indem du diesen ohne / oder �hnlichem in den Chat schreibst");
					p.closeInventory();
				}else {
					p.sendMessage("Passe den Skin jetzt an indem du den Namen eines Spielers ohne / oder �hnlichem in den Chat schreibst");
					p.closeInventory();
				}
				}
			}
		}
		
		
		
		
		if(Item.getItemMeta().getDisplayName().contains("Hero:")) {
			p.sendMessage(Item.getItemMeta().getDisplayName());
			boolean Changes = false;
			String Powers = null;
			for(String s: main.Frdb2.getStringList("config.Heros")) { 
				if(Item.getItemMeta().getDisplayName().contains(s)) {
					main.Frdb2.set(p.getName()+".Power", s);
					
					
					
					
					ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
					SkullMeta HeadMeta = (SkullMeta) Head.getItemMeta();
					HeadMeta.setOwner(main.Frdb2.getString(p.getName()+".Power"));
					Head.setItemMeta(HeadMeta);
					p.getInventory().setHelmet(Head);
					p.sendMessage("Du hast nun die Power von "+s);
					p.getInventory().clear();
					
					p.teleport(p.getLocation());
					
					
					Powers =s;
					Changes = true;
					
				}
			}
			
			if(Changes) {
				int Count =0 ;
				for(PotionEffect potion: p.getActivePotionEffects()) {
					p.removePotionEffect(potion.getType());
				}
				for(String s : main.Frdb2.getStringList("Heros."+Powers+".Items")) {
					
						if(main.Frdb2.getBoolean("Heros."+Powers+".Item."+s+".hasEnchant")) {
							Items.ItemBuilder.Builder(true, Material.getMaterial(s), p,s,Count);
							
						}else {
							Items.ItemBuilder.Builder(false, Material.getMaterial(s), p,s,Count);
						}
					Count = Count +2;
				}
				
				
			}
		ev.setCancelled(true);
		}
		}
	}
	
	
	@EventHandler
	public void OnThrow(PlayerDropItemEvent ev) {
		if(ev.getItemDrop().getItemStack().getItemMeta().getDisplayName().contains("HeroWorld Menu")) {
			ev.setCancelled(true);
			
		}
	}
	
	
	@EventHandler
	public void MoveEvent(PlayerMoveEvent ev) {
		if(main.Frdb2.getBoolean(ev.getPlayer().getName()+".isAnHero")&&HeroWorld(ev.getPlayer())){
			
			String Power = main.Frdb2.getString(ev.getPlayer().getName()+".Power");
			if(Power.contains("CustomHero")) {
				String Custom = "";
				if(Power.contains("Cs1")) {
					Custom="Cs1";
				}else if(Power.contains("Cs2")) {
					Custom =  "Cs2";
				}else if(Power.contains("Cs3")) {
					Custom = "Cs3";
				}
				
				Integer File;
				for(String s:Main.main.Frdb2.getStringList("config.CustomHeros.Abilitys.List")) {
					if(Custom.equals("Cs1")) {
						 File = Main.main.Cs1.getInt(ev.getPlayer().getName()+".Abilitys."+s+".Count");
							}else if(Custom.equals("Cs2")) {
								 File = Main.main.Cs2.getInt(ev.getPlayer().getName()+".Abilitys."+s+".Count");
							}else{
								 File = Main.main.Cs3.getInt(ev.getPlayer().getName()+".Abilitys."+s+".Count");
							}
					if(Main.main.Frdb2.getBoolean("config.CustomHeros.Abilitys."+s+".Multi")) {
						if(!ev.getPlayer().hasPotionEffect(PotionEffectType.getByName(s))) {
						PotionEffect Poition = new PotionEffect(PotionEffectType.getByName(s) ,20000,File);
						ev.getPlayer().addPotionEffect(Poition);
						}
						
						
						
					}else {
						if(!ev.getPlayer().hasPotionEffect(PotionEffectType.getByName(s))) {
						PotionEffect Poition = new PotionEffect(PotionEffectType.getByName(s) ,20000,1);
						ev.getPlayer().addPotionEffect(Poition);
						
						}
						
						
					}
					
					
					
					
					
					
					
				}
				Player p = ev.getPlayer();
				String Owner;
				ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta HeadMeta = (SkullMeta) Head.getItemMeta();
				if(Power.contains("Cs1")) {
					 Owner = Main.main.Cs1.getString(p.getName()+".Owner");
				}else if(Power.contains("Cs2")) {

					 Owner = Main.main.Cs2.getString(p.getName()+".Owner");
				}else {

					 Owner = Main.main.Cs3.getString(p.getName()+".Owner");
				}
				HeadMeta.setOwner(Owner);
				Head.setItemMeta(HeadMeta);
				p.getInventory().setHelmet(Head);
				
				
			
				
				
				
			}else {
			if(main.Frdb2.getBoolean("Heros."+Power+".CanWalkOnWater")) {
				Player p = ev.getPlayer();
				if(p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY()-2, p.getLocation().getBlockZ()).getType()==Material.WATER||p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY()-2, p.getLocation().getBlockZ()).getType()==Material.LEGACY_STATIONARY_WATER) {
					if(p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ()).getType()==Material.WATER||p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ()).getType()==Material.LEGACY_STATIONARY_WATER) {
						Location L =p.getLocation();
						L.setY(p.getLocation().getY()+2);
						Vector vec = p.getVelocity();
						p.teleport(L);
						p.setVelocity(vec);
					}
						
					
		
					
				}
			}
			if(Power==null||Power=="") {
				ev.getPlayer().sendMessage(ChatColor.RED+"Du Besitzt Leider noch keine Power, bitte Such dir eine im Menu aus");
			ev.setCancelled(true);
			}else {
				boolean exist = false;
				for(String s : main.Frdb2.getStringList("config.Heros")) {
					if(s.equalsIgnoreCase(Power)) {
						exist = true;}}
				if(exist) {
					for(String s : main.Frdb2.getStringList("config.Power."+Power)) {
						Player p = ev.getPlayer();
						PotionEffect Poition = new PotionEffect(PotionEffectType.getByName(main.Frdb2.getString("Heros."+Power+"."+s+".Type")) ,20000,main.Frdb2.getInt("Heros."+Power+"."+s+".Power"));
						p.addPotionEffect(Poition);
						
						
					}}}}}}}
