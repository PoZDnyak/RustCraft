package PoZDnyak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static PoZDnyak.Methods.Crafting;
import static PoZDnyak.Methods.Project_Name_Prefix;

public class Command implements CommandExecutor {
    static List BuilPl = new ArrayList();
    static List Furnance = new ArrayList();
    static List Foundation = new ArrayList();
    static List Wall = new ArrayList();

    private Main plugin;
    public Command(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(!p.hasPermission("rc.admin")){
            p.sendMessage(Methods.Project_Name_Prefix+ChatColor.RED+"Нет доступа.");
            return true;
        }
        //((Player) sender).openInventory(Crafting);
        //вывод комманд
        if (args.length == 0) {
            sender.sendMessage(Methods.Project_Name_Prefix + ChatColor.GOLD + "/rc set sulfur" + ChatColor.WHITE + " установить спавн серы");
            sender.sendMessage(Methods.Project_Name_Prefix + ChatColor.GOLD + "/rc set stone" + ChatColor.WHITE + " установить спавн камня");
            sender.sendMessage(Methods.Project_Name_Prefix + ChatColor.GOLD + "/rc set iron" + ChatColor.WHITE + " установить спавн железа");
            return true;
        }
        //установка сульфура
        if((args.length == 2) && (args[0].equalsIgnoreCase("set"))&& (args[1]).equalsIgnoreCase("sulfur")){
            int x = (int) p.getLocation().getX();
            int y = (int) p.getLocation().getY();
            int z = (int) p.getLocation().getZ();
            int last = plugin.getConfig().getInt("Rustcraft."+"Value");
            int last_new = last+1;
            plugin.getConfig().set("Rustcraft."+"Value",last_new);
            plugin.getConfig().set("Rustcraft."+ last_new +" Sulfur.X",x);
            plugin.getConfig().set("Rustcraft."+ last_new +" Sulfur.Y",y);
            plugin.getConfig().set("Rustcraft."+ last_new +" Sulfur.Z",z);
            plugin.saveConfig();
            p.sendMessage(Project_Name_Prefix+"Вы установили точку спавна ресурсов.");
            return true;

        }
        if((args.length == 2) && (args[0].equalsIgnoreCase("set"))&& (args[1]).equalsIgnoreCase("stone")){
            int x = (int) p.getLocation().getX();
            int y = (int) p.getLocation().getY();
            int z = (int) p.getLocation().getZ();
            int last = plugin.getConfig().getInt("Rustcraft."+"Value");
            int last_new = last+1;
            plugin.getConfig().set("Rustcraft."+"Value",last_new);
            plugin.getConfig().set("Rustcraft."+ last_new +" Stone.X",x);
            plugin.getConfig().set("Rustcraft."+ last_new +" Stone.Y",y);
            plugin.getConfig().set("Rustcraft."+ last_new +" Stone.Z",z);
            plugin.saveConfig();
            p.sendMessage(Project_Name_Prefix+"Вы установили точку спавна ресурсов.");
            return true;

        }
        if((args.length == 2) && (args[0].equalsIgnoreCase("set"))&& (args[1]).equalsIgnoreCase("iron")){
            int x = (int) p.getLocation().getX();
            int y = (int) p.getLocation().getY();
            int z = (int) p.getLocation().getZ();
            int last = plugin.getConfig().getInt("Rustcraft."+"Value");
            int last_new = last+1;
            plugin.getConfig().set("Rustcraft."+"Value",last_new);
            plugin.getConfig().set("Rustcraft."+ last_new +" Iron.X",x);
            plugin.getConfig().set("Rustcraft."+ last_new +" Iron.Y",y);
            plugin.getConfig().set("Rustcraft."+ last_new +" Iron.Z",z);
            plugin.saveConfig();
            p.sendMessage(Project_Name_Prefix+"Вы установили точку спавна ресурсов.");
            return true;

        }
        return true;

    }
}
