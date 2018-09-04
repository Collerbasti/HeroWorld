package Main;

import java.awt.Menu;

import org.bukkit.Location;
import org.bukkit.Material;
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
				Count = Count+1;
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
		
		if(Item.getItemMeta().getDisplayName().contains("Shop")) {
			Shop.Spawner(p);
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
					Count = Count +1;
				}
				
				
			}
		ev.setCancelled(true);
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
		if(main.Frdb2.getBoolean(ev.getPlayer().getName()+".isAnHero")){
			String Power = main.Frdb2.getString(ev.getPlayer().getName()+".Power");
			if(main.Frdb2.getBoolean("Heros."+Power+".CanWalkOnWater")) {
				Player p = ev.getPlayer();
				if(p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY()-1, p.getLocation().getBlockZ()).getType()==Material.WATER||p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY()-1, p.getLocation().getBlockZ()).getType()==Material.LEGACY_STATIONARY_WATER) {
					if(p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ()).getType()==Material.WATER||p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ()).getType()==Material.LEGACY_STATIONARY_WATER) {
						Location L =p.getLocation();
						L.setY(p.getLocation().getY()+1);
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
						PotionEffect Poition = new PotionEffect(PotionEffectType.getByName(main.Frdb2.getString("Heros."+Power+"."+s+".Type")) ,10000,main.Frdb2.getInt("Heros."+Power+"."+s+".Power"));
						p.addPotionEffect(Poition);
						
						
					}}}}}}
