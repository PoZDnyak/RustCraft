package PoZDnyak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BuingItem implements Listener {

    public BuingItem(Main main) {

    }


    //выбор в меню крафтинг и покупка в нем
    @EventHandler
    public void click(InventoryClickEvent e){
        if(!e.getInventory().getName().contains("Крафтинг")) return;
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory().getHolder() instanceof Player){
            return;
        }else e.setCancelled(true);
        if(e.getClickedInventory().getSize()==36){

            switch (e.getSlot()){
                case (19):
                    ItemStack log = new ItemStack (Material.LOG);
                    if (p.getInventory().containsAtLeast(log, 5)){
                        for (int i = 0; i<=36; i++){
                            if (p.getInventory().getItem(i)==null) continue;
                            if (p.getInventory().getItem(i).getType()==log.getType()){ //сравниваем тип
                                if (p.getInventory().getItem(i).getAmount()>=5){ //количество
                                    p.getInventory().getItem(i).setAmount(p.getInventory().getItem(i).getAmount()-5);
                                    p.getInventory().addItem(Methods.build);
                                    return;
                                } else if (p.getInventory().getItem(i).getAmount()<5){
                                    p.sendMessage(ChatColor.RED+"Недостаточно Дерева");
                                }
                            }
                        }
                    }else p.sendMessage(ChatColor.RED+"У вас нет дерева");
                    break;
                default:
                    break;

            }

        }


    }
    //запрет кликать в "план постройки", а у себя можно
    @EventHandler
    public void click1(InventoryClickEvent e){
        if(!e.getInventory().getName().contains("План постройки")) return;
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory().getHolder() instanceof Player){
            return;
        }else e.setCancelled(true);
    }
}
