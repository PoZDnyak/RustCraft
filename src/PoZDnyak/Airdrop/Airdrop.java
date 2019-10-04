package PoZDnyak.Airdrop;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Airdrop implements Listener {
    private static Plugin plugin;
    public Airdrop(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void air(PlayerInteractEvent e){
        Player p = e.getPlayer();
        /*ItemStack it = new ItemStack(Material.REDSTONE);
        ItemMeta met = it.getItemMeta();
        met.setDisplayName("Аирдроп");
        it.setItemMeta(met);
        p.getInventory().addItem(it);
        */
        if(!p.hasPermission("rc.air"))return;
        if(!p.getItemInHand().hasItemMeta())return;
        if(!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Аирдроп"))return;
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)return;
        Block b = e.getClickedBlock();
        Bukkit.broadcastMessage(Methods.Project_Name_Prefix+"Был выслан Airdrop (координаты: X("+b.getX()+") Z("+b.getZ()+")");
        new BukkitRunnable() {
            int block = 10;
            Location loc_block = b.getLocation();

            @Override
            public void run() {
                if(block!=10){
                    if(block!=1){
                    loc_block.getBlock().setType(Material.AIR);
                    loc_block.setY(loc_block.getY()-1);
                    loc_block.getBlock().setType(Material.CHEST);
                    block = block-1;
                    }else {
                        loc_block.getBlock().setType(Material.AIR);
                        loc_block.setY(loc_block.getY()-1);
                        loc_block.getBlock().setType(Material.CHEST);
                        Chest chest = (Chest) loc_block.getBlock().getState();
                        /*for(int i=0;i<96;i++) {
                            chest.getBlockInventory().addItem(new ItemStack(Material.IRON_INGOT));
                        }*/
                        Methods.addSulfurChest(5,chest);
                        cancel();
                    }

                }else {
                    loc_block.setY(b.getLocation().getY()+10);
                    block = block -1;
                    loc_block.getBlock().setType(Material.CHEST);
                }


            }
        }.runTaskTimer(plugin, 60, 20 * 4); //Эта допустим работает раз в 3 секунды..

    }



}
