package PoZDnyak.Structures;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class CraftingPaper implements Listener {
    private static Plugin plugin;
    public CraftingPaper(Main plugin) {
        this.plugin = plugin;
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
    //Разрешение крафта определенных вещей
    @EventHandler
    public void oncraft(CraftItemEvent e) {
        if(!(e.getWhoClicked() instanceof Player))return;
        Player p = (Player) e.getWhoClicked();
            switch (e.getRecipe().getResult().getType()){
                case PAPER:{
                    ItemStack wood = new ItemStack(Material.LOG,4);
                    wood.getItemMeta().setDisplayName("Дерево");
                    Methods.craftItem("План постройки",p);
                    ArrayList lorepaper = new ArrayList();
                    lorepaper.add("Используйте его для строительства зданий.");
                    ItemStack paper = new ItemStack(Material.PAPER);
                    ItemMeta meta = paper.getItemMeta();
                    meta.setDisplayName("План постройки");
                    meta.setLore(lorepaper);
                    paper.setItemMeta(meta);
                    e.setCurrentItem(paper);
                    e.getInventory().remove(wood);
                    return;

                }

            default: {
                e.setCancelled(true);
                return;
            }
        }
    }
    //Менюха плана постройки
    @EventHandler
    public void interact(PlayerInteractEvent e){
        if(!e.getPlayer().getItemInHand().hasItemMeta())return;
        if(!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("План постройки"))return;
        if(!(e.getAction()!=Action.RIGHT_CLICK_BLOCK)||(e.getAction()!=Action.RIGHT_CLICK_AIR))return;
        Player p = e.getPlayer();
        p.openInventory(Methods.BuildingPlane);
    }
    //Выбор в менюхе плана постройки
    @EventHandler
    public void choose(InventoryClickEvent e){
        if(!(e.getWhoClicked()instanceof Player))return;
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory()==null)return;
        if(!e.getClickedInventory().getName().equalsIgnoreCase(Methods.Project_Name_Prefix+"План постройки"))return;
        if(!(e.getWhoClicked()instanceof Player))return;
        if(!e.getCurrentItem().hasItemMeta())return;
        switch (e.getCurrentItem().getItemMeta().getDisplayName()){
            case "Фундамент":{
                ItemStack item = p.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("План постройки"+" (Фундамент)");
                item.setItemMeta(meta);
                return;
            }
            case "Стена":{
                ItemStack item = p.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("План постройки"+" (Стена)");
                item.setItemMeta(meta);
                return;
            }
            case "Ступеньки":{
                ItemStack item = p.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("План постройки"+" (Ступеньки)");
                item.setItemMeta(meta);
                return;
            }
            default:{
                return;
            }
        }

    }
}
