package PoZDnyak.Structures;

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
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Wall implements Listener {

    public static HashMap<Player, Location> lastBlocks = new HashMap<>();

    private static Plugin plugin;
    public Wall(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void wallSet(PlayerMoveEvent e) {

        if (e.getPlayer().getItemInHand().getType() != Material.PAPER) return;
        Player p = e.getPlayer();
        if (!p.getItemInHand().hasItemMeta()) return;
        if (!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("План постройки (Стена)")) return;
        Block b = e.getPlayer().getTargetBlock(null,5);
        if(lastBlocks.containsKey(p)){
            if(lastBlocks.get(p).getBlock().getLocation().getX()== b.getLocation().getX()){
                if(lastBlocks.get(p).getBlock().getLocation().getY()== b.getLocation().getY()) {
                    if(lastBlocks.get(p).getBlock().getLocation().getZ()== b.getLocation().getZ()) {
                        return;
                    }
                }
            }
            lastBlocks.remove(p);
        }
        //Methods.clear(b,p);
        lastBlocks.put(p,b.getLocation());
        Location loc = b.getLocation();
        Location LocUp = loc;
        loc.setY(loc.getY()+1);
        switch (Methods.getDirection(p.getLocation().getYaw())){
            case SOUTH:{
               Methods.checkWallSouth(b,p);
                p.sendMessage(ChatColor.AQUA+"South");
                break;
            }
            case NORTH:{
                Methods.checkWallNorth(b,p);
                p.sendMessage(ChatColor.GRAY+"North");
                break;
            }
            case WEST:{
                Methods.checkWallWest(b,p);
                p.sendMessage(ChatColor.BLUE+"West");
                break;
            }
            case EAST:{
                Methods.checkWallEast(b,p);
                p.sendMessage(ChatColor.RED+"East");
                break;
            }
            default:{
                p.sendMessage("You are Debil");
                break;
            }
        }
    }

    @EventHandler
    public void leave(PlayerQuitEvent e){
        Methods.lastBlocks.remove(e.getPlayer());
        lastBlocks.remove(e.getPlayer());
    }

    @EventHandler
    public void inte(PlayerInteractEvent e) {
        Action act = e.getAction();
        if (e.getPlayer().getItemInHand().getType() != Material.PAPER) return;
        Player p = e.getPlayer();
        if (!p.getItemInHand().hasItemMeta()) return;
        if (!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("План постройки (Стена)")) return;
        if ((e.getAction().equals(Action.LEFT_CLICK_BLOCK)) || (e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            if (Methods.can.get(e.getPlayer()).booleanValue() == false) {
                e.setCancelled(true);
                p.sendMessage(Methods.Project_Name_Prefix + "Нельзя поставить");
                return;
            }
        }
        switch (Methods.getDirection(p.getLocation().getYaw())){
            case SOUTH:{
                Methods.buildWallSouth(Methods.lastBlocks.get(p).getBlock(),p);
                p.sendMessage(ChatColor.AQUA+"South");
                break;
            }
            case NORTH:{
                Methods.buildWallNorth(Methods.lastBlocks.get(p).getBlock(),p);
                p.sendMessage(ChatColor.GRAY+"North");
                break;
            }
            case WEST:{
                Methods.buildWallWest(Methods.lastBlocks.get(p).getBlock(),p);
                p.sendMessage(ChatColor.BLUE+"West");
                break;
            }
            case EAST:{
                Methods.buildWallEast(Methods.lastBlocks.get(p).getBlock(),p);
                p.sendMessage(ChatColor.RED+"East");
                break;
            }
            default:{
                p.sendMessage("You are Debil");
                break;
            }
        }
    }

}