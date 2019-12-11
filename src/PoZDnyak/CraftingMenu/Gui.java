package PoZDnyak.CraftingMenu;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Gui implements Listener {
    private static Plugin plugin;
    public Gui(Main plugin) { this.plugin = plugin;
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().contains(Methods.itemWithCrafts))return;
        p.getInventory().setItem(2,Methods.itemWithCrafts);
        ItemStack menu = p.getInventory().getItem(2);
        ItemMeta meta = menu.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA+"Меню крафтов");
        menu.setItemMeta(meta);

    }

    @EventHandler
    public void respawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        p.getInventory().setItem(2,Methods.itemWithCrafts);
        ItemStack menu = p.getInventory().getItem(2);
        ItemMeta meta = menu.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA+"Меню крафтов");
        menu.setItemMeta(meta);
    }
    @EventHandler
    public void open(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if(!p.getInventory().getItemInHand().hasItemMeta())return;
        if(!p.getInventory().getItemInHand().getItemMeta().hasDisplayName())return;
        if(!p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Меню крафтов"))return;
        p.openInventory(Methods.guiCrafts);
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        if(e.getInventory().getName().equals(Methods.Project_Name_Prefix+"Меню крафтов")){
            e.setCancelled(true);
            return;
        }
        Inventory inv = e.getClickedInventory();
        if(!(e.getWhoClicked() instanceof Player))return;
        Player p = (Player) e.getWhoClicked();
        if(inv.getHolder()==null)return;
        if(!inv.getHolder().equals(p))return;
        if(!e.getCurrentItem().hasItemMeta())return;
        if(!e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Меню крафтов"))return;
        e.setCancelled(true);
    }
    @EventHandler
    public void drop(PlayerDropItemEvent e){
        if(!e.getItemDrop().getItemStack().hasItemMeta())return;
        if(!e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Меню крафтов"))return;
        e.setCancelled(true);
    }
    @EventHandler
    public void click1(InventoryClickEvent e){
        Inventory inv = e.getClickedInventory();
        if(!(e.getWhoClicked() instanceof Player))return;
        Player p = (Player) e.getWhoClicked();
        if(!inv.getTitle().equals(Methods.Project_Name_Prefix+"Меню крафтов"))return;
        if(e.getCurrentItem().isSimilar(Methods.gray_glass)) {
            e.setCancelled(true);
            return;
        }
        switch (e.getCurrentItem().getType()){
            case PAPER:{
                if (!p.getInventory().contains(Material.LOG,10)) {
                        p.sendMessage(Methods.Project_Name_Prefix + "Недостаточно ресурсов");
                        break;
                }
                for(int i=1;i<=10;i++) {
                    p.getInventory().removeItem(Methods.resourceWood());
                }


                p.getInventory().addItem(Methods.itemBuildingPlane());
                break;
            }
            case IRON_HOE:{
                p.getInventory().addItem(Methods.itemKiyanka());
                break;
            }
            case CHEST:{
                p.getInventory().addItem(Methods.itemChest());
                break;
            }
            case STONE_AXE:{
                p.getInventory().addItem(Methods.itemStoneAxe());
                break;
            }
            case STONE_PICKAXE:{
                p.getInventory().addItem(Methods.itemStonePickAxe());
                break;
            }
            case DARK_OAK_DOOR_ITEM:{
                p.getInventory().addItem(Methods.itemMetalDoor());
                break;
            }
            case JUNGLE_DOOR_ITEM:{
                p.getInventory().addItem(Methods.itemHeavyDoor());
                break;
            }
            default:break;

        }
        e.setCancelled(true);

    }


}
