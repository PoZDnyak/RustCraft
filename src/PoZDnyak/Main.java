package PoZDnyak;

import PoZDnyak.Airdrop.Airdrop;
import PoZDnyak.BlockingCraft.All;
import PoZDnyak.Breaking.BreakingBlocks;
import PoZDnyak.Privates.Privates;
import PoZDnyak.SpawnResources.SpawnResources;
import PoZDnyak.Structures.Foundation;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;


public class Main extends JavaPlugin {
    public File cfg = new File(getDataFolder()+ File.separator+"config.yml");

    public File customYml = new File(getDataFolder()+"/privates.yml");
    public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);


    @Override
    public void onEnable() {
        if(!cfg.exists()){
            getLogger().info("Creating config.yml...");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        if(!customYml.exists()) {
            getLogger().info("Creating privates.yml...");
            customConfig.set("Privates.value",0);
            Methods.saveCustomYml(customConfig, customYml);
        }

        getLogger().info("Enabled!");
        Bukkit.getPluginManager().registerEvents(new BuingItem(this),this);
        Bukkit.getPluginManager().registerEvents(new Privates(this),this);
        Bukkit.getPluginManager().registerEvents(new OtherHandler(this),this);
        Bukkit.getPluginManager().registerEvents(new BreakingBlocks(this),this);
        Bukkit.getPluginManager().registerEvents(new Foundation(this),this);
        Bukkit.getPluginManager().registerEvents(new All(this),this);
        Bukkit.getPluginManager().registerEvents(new SpawnResources(this),this);
        Bukkit.getPluginManager().registerEvents(new Airdrop(this),this);
        getCommand("rc").setExecutor(new Command(this));
        Methods.Addlore();
        Methods.gui();
        Methods.BuildPlane();
        SpawnResources.SpawnResources();
        Methods.saveCustomYml(customConfig,customYml);
        }


    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
        Methods.Clearlore();
        saveConfig();
    }
}
