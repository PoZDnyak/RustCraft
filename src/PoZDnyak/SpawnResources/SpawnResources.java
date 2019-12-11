package PoZDnyak.SpawnResources;

import PoZDnyak.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class  SpawnResources implements Listener {
    private static Plugin plugin;
    public SpawnResources(Main plugin) {
        this.plugin = plugin;
    }

    public SpawnResources() {
    }

    public static void SpawnResources() {
        new BukkitRunnable() {
            @Override
            public void run() {
                int value = plugin.getConfig().getInt("Rustcraft."+"Value");
                 for(int i=0;i<=value;i++){
                     if(plugin.getConfig().contains("Rustcraft."+i+" Sulfur")){
                         int x = plugin.getConfig().getInt("Rustcraft."+i+" Sulfur.X");
                         int y = plugin.getConfig().getInt("Rustcraft."+i+" Sulfur.Y");
                         int z = plugin.getConfig().getInt("Rustcraft."+i+" Sulfur.Z");
                         Bukkit.getWorld("world").getBlockAt(x,y,z).setType(Material.GOLD_ORE);
                         Bukkit.getWorld("world").getBlockAt(x+1,y,z).setType(Material.GOLD_ORE);
                         Bukkit.getWorld("world").getBlockAt(x-1,y,z).setType(Material.GOLD_ORE);
                         Bukkit.getWorld("world").getBlockAt(x,y,z+1).setType(Material.GOLD_ORE);
                         Bukkit.getWorld("world").getBlockAt(x,y,z-1).setType(Material.GOLD_ORE);
                         Bukkit.getWorld("world").getBlockAt(x,y+1,z).setType(Material.GOLD_ORE);
                         continue;
                     }else if(plugin.getConfig().contains("Rustcraft."+i+" Stone")){
                         int x = plugin.getConfig().getInt("Rustcraft."+i+" Stone.X");
                         int y = plugin.getConfig().getInt("Rustcraft."+i+" Stone.Y");
                         int z = plugin.getConfig().getInt("Rustcraft."+i+" Stone.Z");
                         Bukkit.getWorld("world").getBlockAt(x,y,z).setType(Material.COBBLESTONE);
                         Bukkit.getWorld("world").getBlockAt(x+1,y,z).setType(Material.COBBLESTONE);
                         Bukkit.getWorld("world").getBlockAt(x-1,y,z).setType(Material.COBBLESTONE);
                         Bukkit.getWorld("world").getBlockAt(x,y,z+1).setType(Material.COBBLESTONE);
                         Bukkit.getWorld("world").getBlockAt(x,y,z-1).setType(Material.COBBLESTONE);
                         Bukkit.getWorld("world").getBlockAt(x,y+1,z).setType(Material.COBBLESTONE);
                     }else if(plugin.getConfig().contains("Rustcraft."+i+" Iron")){
                         int x = plugin.getConfig().getInt("Rustcraft."+i+" Iron.X");
                         int y = plugin.getConfig().getInt("Rustcraft."+i+" Iron.Y");
                         int z = plugin.getConfig().getInt("Rustcraft."+i+" Iron.Z");
                         Bukkit.getWorld("world").getBlockAt(x,y,z).setType(Material.IRON_ORE);
                         Bukkit.getWorld("world").getBlockAt(x+1,y,z).setType(Material.IRON_ORE);
                         Bukkit.getWorld("world").getBlockAt(x-1,y,z).setType(Material.IRON_ORE);
                         Bukkit.getWorld("world").getBlockAt(x,y,z+1).setType(Material.IRON_ORE);
                         Bukkit.getWorld("world").getBlockAt(x,y,z-1).setType(Material.IRON_ORE);
                         Bukkit.getWorld("world").getBlockAt(x,y+1,z).setType(Material.IRON_ORE);
                     }else continue;
                 }
            }
        }.runTaskTimer(plugin, 60, 20 * 10); //Эта допустим работает раз в 3 секунды..
    }
}
