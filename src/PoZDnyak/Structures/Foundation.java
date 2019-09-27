package PoZDnyak.Structures;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static PoZDnyak.Methods.Foundation_item;

public class Foundation implements Listener {

    public Foundation(Main main) {

    }
    //получание фундамента
    @EventHandler
    public void FoundationGet(InventoryClickEvent e){
        if(!(e.getHandlers()instanceof Player)) return;
        if(!e.getClickedInventory().getName().contains("План постройки"))return;
        if(e.getSlot()!=1)return;
        e.getWhoClicked().getInventory().addItem(Foundation_item);
    }


    //реализация процесса установки фундамента
    @EventHandler
    public void FoundationSet(PlayerInteractEvent e){
        if(e.getAction()!= Action.RIGHT_CLICK_BLOCK)return;
        Player p = e.getPlayer();
        Action act = e.getAction();
        ItemStack hand = e.getPlayer().getInventory().getItemInHand();
        if(hand==null) return;
        if(!hand.hasItemMeta())return;
        if(!hand.getItemMeta().getDisplayName().contains("Фундамент"))return;
            e.setCancelled(true);
            if(e.getClickedBlock()!= null) {
                Methods.X(e.getClickedBlock(), p);
            }else return;
    }


}
