package PoZDnyak.Structures;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class Foundation implements Listener {

    public Foundation(Main main) {
    }



    /*@EventHandler
    public void FoundationSet(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType() != Material.PAPER) return;
        Player p = e.getPlayer();
        if (!p.getItemInHand().hasItemMeta()) return;
        if (!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("План постройки (Фундамент)")) return;
        Block b = p.getTargetBlock(null, 5);
        Location loc = b.getLocation();
        if(b.getType()==Material.AIR)return;
        Action act = e.getAction();
        if((act== Action.LEFT_CLICK_AIR)||(act== Action.LEFT_CLICK_BLOCK)){
            if(Methods.checkFoundation(b,p)!=true)return;
            Methods.setFoundation(b,p);
            return;
        }else return;

    }
    */
}
