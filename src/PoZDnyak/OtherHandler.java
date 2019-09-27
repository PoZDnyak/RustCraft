package PoZDnyak;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OtherHandler implements Listener {
    public OtherHandler(Main main) {

    }
    //Запрет на установку левых блоков
    @EventHandler
    public void place(BlockPlaceEvent e){
        ItemStack hand = e.getItemInHand();
        switch (hand.getType()) {

            case STONE_PLATE:
                if(hand.hasItemMeta()&&hand.getItemMeta().getDisplayName()=="Фундамент"){
                    return;
                }

                break;
            default:
                e.setCancelled(true);
                break;
        }

    }
    //шляпа
    @EventHandler
    public void use(PlayerInteractEvent e){
        if(e.getAction()==Action.RIGHT_CLICK_AIR){
            e.getPlayer().openInventory(Methods.BuildingPlane);
        }
    }
}
