package PoZDnyak.BlockingCraft;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static org.bukkit.Material.JUKEBOX;

public class All implements Listener {

    private static Plugin plugin;
    public All(Main plugin) {
        this.plugin = plugin;
    }
    //запрет на открытие верстака
    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType() == Material.WORKBENCH))
        {
            Player p = event.getPlayer();
            event.setCancelled(true);
            p.sendMessage(Methods.Project_Name_Prefix + ChatColor.GRAY + " Крафты отключены!");
        }
    }
    //камень при респавне
    @EventHandler
    public void respawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
                ItemStack rock = new ItemStack(Material.WOOD_PICKAXE);
                ItemMeta meta = rock.getItemMeta();
                meta.setDisplayName("Камень (Rock)");
                ArrayList lore = new ArrayList();
                lore.add("Самое простое оружие ближнего боя");
                lore.add("и инструмент добычи ресурсов.");
                meta.setLore(lore);
                rock.setItemMeta(meta);
                p.getInventory().addItem(rock);
    }
    //запрет на использование яйца
    @EventHandler
    public void WOOD_PICKAXE(PlayerInteractEvent e){
        Action act= e.getAction();
        if(e.getPlayer().getInventory().getItemInHand().getType()!=Material.WOOD_PICKAXE)return;
        if(!((act==Action.RIGHT_CLICK_BLOCK)||(act==Action.RIGHT_CLICK_AIR)))return;
        e.setCancelled(true);
    }
    //запрет на установку блока
    @EventHandler
    public void blockset(BlockPlaceEvent e){
        Block b = e.getBlockPlaced();
        switch (b.getType()){
            case JUKEBOX:{
                return;
            }

            default:{
                e.setCancelled(true);
                return;
            }
        }
    }

}
