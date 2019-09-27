package PoZDnyak.BlockingCraft;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class All implements Listener {

    public All(Main main) {
    }
    //Запрет на крафт вещей
    @EventHandler
    public void onInteract(CraftItemEvent event)
    {
        event.setCancelled(true);
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

    @EventHandler
    public void l(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        p.sendMessage(String.valueOf(Bukkit.getWorld("World").getChunkAt(p.getLocation())));
        
    }

}
