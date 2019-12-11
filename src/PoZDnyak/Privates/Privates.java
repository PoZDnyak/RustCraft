package PoZDnyak.Privates;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Privates implements Listener {
    private Main plugin;
    public Privates(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inte(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action act = e.getAction();
        //проверка нажатия на шкаф

        if ((act == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.JUKEBOX)) {
            int privates_value = plugin.customConfig.getInt("Privates.value");
            int new_privates_value = privates_value + 1;
            //проверяем privates_value раз(1)
            //i-номер привата
            if (privates_value == 0) {
                plugin.customConfig.set("Privates.value", new_privates_value);
                plugin.customConfig.set("Privates." + new_privates_value + ".X", e.getClickedBlock().getChunk().getX());
                plugin.customConfig.set("Privates." + new_privates_value + ".Z", e.getClickedBlock().getChunk().getZ());
                plugin.customConfig.set("Privates." + new_privates_value + ".UsersValue", 1);
                plugin.customConfig.set("Privates." + new_privates_value + ".Users.1", p.getName());
                p.sendMessage(Methods.Project_Name_Prefix + "Авторизовали");
                Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
            }
            for (int i = 1; i <= privates_value; i++) {
                //Проверяем для первого запуска
                if (plugin.customConfig.contains("Privates." + privates_value)) {
                    //Проверка есть ли чанк по данным координатам
                    if (plugin.customConfig.get("Privates." + i + ".X").equals(e.getClickedBlock().getChunk().getX()) && (plugin.customConfig.get("Privates." + privates_value + ".Z").equals(e.getClickedBlock().getChunk().getZ()))) {
                        int vval = plugin.customConfig.getInt("Privates." + privates_value + ".UsersValue");
                        int val = vval;
                        int nowi = vval + 1;
                        for (int j = 1; j <= nowi; j++) {
                            //Проверка каждого юзера на то, он ли вписывается
                            if (plugin.customConfig.getString("Privates." + i + ".Users." + j).equalsIgnoreCase(p.getName())) {
                                p.sendMessage(Methods.Project_Name_Prefix + "Вы авторизованы");
                                return;
                            } else if(vval!=j){
                                continue;
                            }else if(vval == j) {
                                plugin.customConfig.set("Privates." + privates_value + ".UsersValue", nowi);
                                plugin.customConfig.set("Privates." + privates_value + ".Users." + nowi, p.getName());
                                p.sendMessage(Methods.Project_Name_Prefix + "Авторизовали");
                                Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
                                return;
                            } else {
                                continue;
                            }
                        }

                    } else if (i != privates_value) {
                        continue;
                    } else {
                        plugin.customConfig.set("Privates.value", new_privates_value);
                        plugin.customConfig.set("Privates." + new_privates_value + ".X", e.getClickedBlock().getChunk().getX());
                        plugin.customConfig.set("Privates." + new_privates_value + ".Z", e.getClickedBlock().getChunk().getZ());
                        plugin.customConfig.set("Privates." + new_privates_value + ".UsersValue", 1);
                        plugin.customConfig.set("Privates." + new_privates_value + ".Users.1", p.getName());
                        p.sendMessage(Methods.Project_Name_Prefix + "Авторизовали");
                        Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
                        return;
                    }
                } else {
                    plugin.customConfig.set("Privates.value", new_privates_value);
                    plugin.customConfig.set("Privates." + new_privates_value + ".X", e.getClickedBlock().getChunk().getX());
                    plugin.customConfig.set("Privates." + new_privates_value + ".Z", e.getClickedBlock().getChunk().getZ());
                    plugin.customConfig.set("Privates." + new_privates_value + ".UsersValue", 1);
                    plugin.customConfig.set("Privates." + new_privates_value + ".Users.1", p.getName());
                    p.sendMessage(Methods.Project_Name_Prefix + "Авторизовали");
                    Methods.saveCustomYml(plugin.customConfig, plugin.customYml);
                    return;
                }
            }
        } else return;
    }


    //Запрет на установку шкафа в месте, где есть
    @EventHandler
    public void shkafSet(BlockPlaceEvent e){
        int privates_value = plugin.customConfig.getInt("Privates.value");
        if(!plugin.customConfig.contains("Privates." + privates_value + ".X"))return;
        if((plugin.customConfig.get("Privates." + privates_value + ".X").equals(e.getBlockPlaced().getChunk().getX()) && (plugin.customConfig.get("Privates." + privates_value + ".Z").equals(e.getBlockPlaced().getChunk().getZ())))){
            e.getPlayer().sendMessage(Methods.Project_Name_Prefix+"Здесь установлен шкаф");
            e.setCancelled(true);
            return;
        }
    }

}

