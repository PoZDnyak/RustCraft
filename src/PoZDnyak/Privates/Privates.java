package PoZDnyak.Privates;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import PoZDnyak.SpawnResources.SpawnResources;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import javax.swing.*;
import java.io.File;

public class Privates implements Listener {
    private Main plugin;
    public Privates(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inte(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action act = e.getAction();
        //проверка нажатия на шкаф
        if((act== Action.RIGHT_CLICK_BLOCK)&&(e.getClickedBlock().getType()== Material.JUKEBOX)){
            int value = plugin.customConfig.getInt("Privates.value");
            int new_value = value+1;
            for(int i=0;i<=value;i++) {
                //Проверяем для первого запуска
                if (plugin.customConfig.contains("Privates."+value)) {
                    //Проверка есть ли чанк по данным координатам
                    if (plugin.customConfig.get("Privates." + value + ".X").equals(e.getClickedBlock().getChunk().getX()) && (plugin.customConfig.get("Privates." + value + ".Z").equals(e.getClickedBlock().getChunk().getZ()))) {
                        int vval = plugin.customConfig.getInt("Privates." + value + ".UsersValue");
                        int val = vval+1;

                        for (int j = 1; j <= vval; j++){
                            //Проверка каждого юзера на то, он ли вписывается
                            if (plugin.customConfig.getString("Privates." + value + ".Users."+j).equalsIgnoreCase(p.getName())) {
                                p.sendMessage(Methods.Project_Name_Prefix+"Вы авторизованы");
                                return;
                            } else if(vval==j) {
                                plugin.customConfig.set("Privates." + value + ".UsersValue",val);
                                plugin.customConfig.set("Privates." + value + ".Users."+val,p.getName());
                                p.sendMessage(Methods.Project_Name_Prefix + "Авторизовали");
                                Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
                                return;
                            }
                    }

                    }else {
                        plugin.customConfig.set("Privates." + new_value + ".X", e.getClickedBlock().getChunk().getX());
                        plugin.customConfig.set("Privates." + new_value + ".Z", e.getClickedBlock().getChunk().getZ());
                        plugin.customConfig.set("Privates." + new_value + ".Users.", "1 "+p.getName());
                        plugin.customConfig.set("Privates.value", new_value);
                        p.sendMessage(Methods.Project_Name_Prefix+"Авторизовали");
                        Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
                        return;
                    }
                }else {
                    plugin.customConfig.set("Privates." + new_value + ".X", e.getClickedBlock().getChunk().getX());
                    plugin.customConfig.set("Privates." + new_value + ".Z", e.getClickedBlock().getChunk().getZ());
                    plugin.customConfig.set("Privates." + new_value + ".UsersValue",1);
                    plugin.customConfig.set("Privates." + new_value + ".Users.1",p.getName());
                    plugin.customConfig.set("Privates.value", new_value);
                    p.sendMessage(Methods.Project_Name_Prefix+"Авторизовали");
                    Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
                    return;
                }
            }
            }else return;
        }
}

