package PoZDnyak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Methods extends Command  {
    private Main plugin;


    public static ItemStack build = new ItemStack(Material.PAPER);
    public static ItemStack furnance = new ItemStack(Material.FURNACE);
    public static ItemStack noth = new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 8);
    public static Inventory Crafting = Bukkit.createInventory(null,36, ChatColor.AQUA+"[RC] "+ChatColor.GREEN+"Крафтинг");
    ////айтемы в план постройки (мета)
    public static Inventory BuildingPlane = Bukkit.createInventory(null,9, ChatColor.AQUA+"[RC] "+ChatColor.GREEN+"План постройки");
    public static ItemStack Build_Menu_Foundation = new ItemStack(Material.PAPER);
    public static ItemStack Build_Menu_Wall = new ItemStack(Material.PAPER);
    ////айтемы для установки(вещь)
    public static ItemStack Foundation_item = new ItemStack(Material.STONE_PLATE);
    ////Название проекта
    public static String Project_Name_Prefix = ChatColor.RED + "[" + ChatColor.GOLD + ChatColor.BOLD + "RC" + ChatColor.RED + "] ";
    //КФГ шкафов


    public Methods(Main main) {
        super(main);
    }
    public static void Addlore(){
        //Фундамент
        BuilPl.add(0,"--------------------");
        BuilPl.add(1,"Для постройки дома");
        BuilPl.add(2,"Цена:");
        BuilPl.add(3,"5 дерева");


        Furnance.add(0,"--------------------");
        Furnance.add(1,"Для переплавки ресурсов");
        Furnance.add(2,"Цена:");
        Furnance.add(3,"30 дерева");
        Furnance.add(4,"15 камня");
        Furnance.add(5,"5 ТНК");


        Foundation.add(0,"--------------------");
        Foundation.add(1,"Фундамент");
        Foundation.add(2,"Цена:");
        Foundation.add(3,"200 дерева");
        Foundation.add(4,"150 камня");
        Foundation.add(5,"100 железа");


        Wall.add(0,"--------------------");
        Wall.add(1,"Стена");
        Wall.add(2,"Цена:");
        Wall.add(3,"150 дерева");
        Wall.add(4,"100 камня");
        Wall.add(5,"50 железа");
    }//добавление лора
    public static void Clearlore(){
        if (build.hasItemMeta()) {
            build.getItemMeta().getLore().clear();
        }else return;
        if(furnance.hasItemMeta()) {
            furnance.getItemMeta().getLore().clear();
        }else return;
    }//очистка лора
    public static void X(Block b,Player p){
        int x = b.getX();
        int y = b.getY();
        int z = b.getZ();
        Location loc = b.getLocation();
        int i;
        loc.setX(loc.getX()-3);
        for (i=0;i<5;i++){
            loc.setX(loc.getX()+1);
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.STONE);
        }
        int j;
        loc.setZ(loc.getZ()+1);
        loc.setX(loc.getX()-5);
        for(j=0;j<5;j++){
            loc.setX(loc.getX()+1);
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.STONE);
        }
        int q;
        loc.setZ(loc.getZ()+1);
        loc.setX(loc.getX()-5);
        for(q=0;q<5;q++){
            loc.setX(loc.getX()+1);
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.STONE);
        }
        int c;
        loc.setZ(loc.getZ()+1);
        loc.setX(loc.getX()-5);
        for(c=0;c<5;c++){
            loc.setX(loc.getX()+1);
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.STONE);
        }
        int m;
        loc.setZ(loc.getZ()+1);
        loc.setX(loc.getX()-5);
        for(m=0;m<5;m++){
            loc.setX(loc.getX()+1);
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.STONE);
        }

        p.sendMessage(ChatColor.AQUA+"Вы установили"+ChatColor.RED+" Фундамент");
        return;
    }//установка фундамента
    public static void gui(){
        //////создать гуи
        ////////создать айтемы
        ItemMeta build_meta = build.getItemMeta();
        ItemMeta furnance_meta = furnance.getItemMeta();
        ItemMeta nm1 = noth.getItemMeta();


        ///////создать айтемам название
        build_meta.setDisplayName(ChatColor.BLUE+"План постройки");
        build_meta.setLore(BuilPl);
        build.setItemMeta(build_meta);

        furnance_meta.setDisplayName(ChatColor.BLUE+"Печь");
        furnance_meta.setLore(Furnance);
        furnance.setItemMeta(furnance_meta);

        nm1.setDisplayName(ChatColor.RED+"...");
        noth.setItemMeta(nm1);
        Crafting.setItem(19,build);
        Crafting.setItem(20,furnance);

        for(int i=0;i<36;i++){
            if(Crafting.getItem(i)==null){
                Crafting.setItem(i,noth);

            }
        }
    }//Создание основного гуи и его аддонов
    public static void BuildPlane(){
        //берем меты
        ItemMeta Build_Menu_Meta_Foundation = Build_Menu_Foundation.getItemMeta();
        ItemMeta Build_Menu_Meta_Wall = Build_Menu_Wall.getItemMeta();
        ItemMeta nothink = noth.getItemMeta();
        ///////Установка Имени и описания
        Build_Menu_Meta_Foundation.setDisplayName(ChatColor.BLUE+"Фундамент");
        Build_Menu_Meta_Foundation.setLore(Foundation);
        Build_Menu_Foundation.setItemMeta(Build_Menu_Meta_Foundation);
        ///////Стенка
        Build_Menu_Meta_Wall.setDisplayName(ChatColor.BLUE+"Стена");
        Build_Menu_Meta_Wall.setLore(Wall);
        Build_Menu_Wall.setItemMeta(Build_Menu_Meta_Wall);


        nothink.setDisplayName(ChatColor.RED+"...");
        noth.setItemMeta(nothink);
        BuildingPlane.setItem(1,Build_Menu_Foundation);
        BuildingPlane.setItem(3,Build_Menu_Wall);

        for(int i=0;i<45;i++){
            if(BuildingPlane.getItem(i)==null){
                BuildingPlane.setItem(i,noth);

            }else return;
        }
    }//Создание гуи плана постройки
    public static void Foundation_create(){
        ItemMeta Foundation_Meta = Foundation_item.getItemMeta();
        if(Foundation_Meta.hasDisplayName()) return;
        Foundation_Meta.setDisplayName("Фундамент");
        Foundation_item.setItemMeta(Foundation_Meta);
    }
    public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }





