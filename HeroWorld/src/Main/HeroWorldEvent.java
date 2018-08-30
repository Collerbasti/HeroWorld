package Main;

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
		if(main.Frdb.getStringList("config.GetWorlds").contains(p.getWorld().getName())) {
			main.Frdb.set(p.getName()+".isAnHero", true);
			
			ItemStack Lore = new ItemStack(Material.MINECART);
			ItemMeta MLore = Lore.getItemMeta();
			MLore.setDisplayName("HeroWorld Menu");
			Lore.setItemMeta(MLore);
			ev.getPlayer().getInventory().setItem(8, Lore);
			
			
			ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta HeadMeta = (SkullMeta) Head.getItemMeta();
			HeadMeta.setOwner(Main.main.Frdb.getString(ev.getPlayer().getName()+".Power"));
			Head.setItemMeta(HeadMeta);
			ev.getPlayer().getInventory().setHelmet(Head);
			int Count = 0; 
			for(String s : Main.main.Frdb.getStringList("Heros."+Main.main.Frdb.getString(ev.getPlayer().getName()+".Power")+".Items")) {
		
				if(Main.main.Frdb.getBoolean(Main.main.Frdb.getString("Heros."+ev.getPlayer().getName()+".Power")+".Item."+s+".hasEnchant")) {
					Items.ItemBuilder.Builder(true, Material.getMaterial(s), p,s,Count);
					
				}else {
					Items.ItemBuilder.Builder(false, Material.getMaterial(s), p,s,Count);
				}
				Count = Count+1;
			}
			
			
		}else {
			main.Frdb.set(p.getName()+".isAnHero", false);
		}
	}
	@EventHandler
	public void InteracktEvent(PlayerInteractEvent ev) {
		if(ev.getItem().getItemMeta().getDisplayName().equals("HeroWorld Menu")) {
			Menu.HeroMenu.Summon(ev.getPlayer());
		}
	}
	
	
	@EventHandler
	public void ItemUse(InventoryClickEvent ev) {
		Player p = (Player) ev.getWhoClicked();
		ItemStack Item = ev.getCurrentItem();
		
		if(Item.getItemMeta().getDisplayName().contains("Hero:")) {
			p.sendMessage(Item.getItemMeta().getDisplayName());
			boolean Changes = false;
			String Powers = null;
			for(String s: Main.main.Frdb.getStringList("config.Heros")) { 
				if(Item.getItemMeta().getDisplayName().contains(s)) {
					Main.main.Frdb.set(p.getName()+".Power", s);
					
					
					
					
					ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
					SkullMeta HeadMeta = (SkullMeta) Head.getItemMeta();
					HeadMeta.setOwner(Main.main.Frdb.getString(p.getName()+".Power"));
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
				for(String s : Main.main.Frdb.getStringList("Heros."+Powers+".Items")) {
					
						if(Main.main.Frdb.getBoolean("Heros."+Powers+".Item."+s+".hasEnchant")) {
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
		if(main.Frdb.getBoolean(ev.getPlayer().getName()+".isAnHero")){
			String Power = main.Frdb.getString(ev.getPlayer().getName()+".Power");
			
			if(Power==null||Power=="") {
				ev.getPlayer().sendMessage(ChatColor.RED+"Du Besitzt Leider noch keine Power, bitte Such dir eine im Menu aus");
			ev.setCancelled(true);
			}else {
				boolean exist = false;
				for(String s : main.Frdb.getStringList("config.Heros")) {
					if(s.equalsIgnoreCase(Power)) {
						exist = true;}}
				if(exist) {
					for(String s : main.Frdb.getStringList("config.Power."+Power)) {
						Player p = ev.getPlayer();
						PotionEffect Poition = new PotionEffect(PotionEffectType.getByName(main.Frdb.getString("Heros."+Power+"."+s+".Type")) ,10000,main.Frdb.getInt("Heros."+Power+"."+s+".Power"));
						p.addPotionEffect(Poition);
						
					}}}}}}
