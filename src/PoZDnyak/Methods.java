package PoZDnyak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Methods extends Command {

    private static Main plugin;

    public Methods(Main plugin) {
        this.plugin = plugin;
    }

    //для апдейта
    public static HashMap<Player, Location> lastBlocks = new HashMap<>();
    public static HashMap<Player, Boolean> can = new HashMap<>();
    public static HashMap<Player, Integer> vector = new HashMap<>();
    public static HashMap<Player, Boolean> accessFoundation = new HashMap<>();
    public static HashMap<Player, Location> lastBlockFoundation = new HashMap<>();

    //вещи в план постройки для заполнения
    public static ItemStack wall = new ItemStack(Material.PAPER);
    public static ItemStack foundation = new ItemStack(Material.PAPER);
    public static ItemStack stairs = new ItemStack(Material.PAPER);

    //вещь для гуишки с крафтами
    public static ItemStack itemWithCrafts = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
    //инвентарь со всеми крафтами
    public static Inventory guiCrafts = Bukkit.createInventory(null, 9 * 6, ChatColor.RED + "[" + ChatColor.GOLD + ChatColor.BOLD + "RC" + ChatColor.RED + "] " + "Меню крафтов");


    public static ItemStack furnance = new ItemStack(Material.FURNACE);
    public static ItemStack gray_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
    ////айтемы в план постройки (мета)
    public static Inventory BuildingPlane = Bukkit.createInventory(null, 9 * 6, ChatColor.RED + "[" + ChatColor.GOLD + ChatColor.BOLD + "RC" + ChatColor.RED + "] " + "План постройки");
    ////Название проекта
    public static String Project_Name_Prefix = ChatColor.RED + "[" + ChatColor.GOLD + ChatColor.BOLD + "RC" + ChatColor.RED + "] ";
    //КФГ шкафов


    public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSulfur(int s, Player p) {
        ItemStack sulfur = new ItemStack(Material.GOLD_ORE);
        ItemMeta meta = sulfur.getItemMeta();
        meta.setDisplayName("Сера");
        sulfur.setItemMeta(meta);
        for (int i = 0; i < s; i++) {
            p.getInventory().addItem(sulfur);
        }
    }

    public static void addStone(int s, Player p) {
        ItemStack stone = new ItemStack(Material.COBBLESTONE);
        ItemMeta meta = stone.getItemMeta();
        meta.setDisplayName("Камень");
        stone.setItemMeta(meta);
        for (int i = 0; i < s; i++) {
            p.getInventory().addItem(stone);
        }
    }

    public static void addIron(int s, Player p) {
        ItemStack iron = new ItemStack(Material.IRON_ORE);
        ItemMeta meta = iron.getItemMeta();
        meta.setDisplayName("Железная Руда");
        iron.setItemMeta(meta);
        for (int i = 0; i < s; i++) {
            p.getInventory().addItem(iron);
        }
    }

    public static void addWood(int s, Player p) {
        ItemStack wood = new ItemStack(Material.LOG);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Дерево");
        wood.setItemMeta(meta);
        for (int i = 0; i < s; i++) {
            p.getInventory().addItem(wood);
        }
    }

    public static void addSulfurChest(int s, Chest p) {
        ItemStack sulfur = new ItemStack(Material.GOLD_ORE);
        ItemMeta meta = sulfur.getItemMeta();
        meta.setDisplayName("Сера");
        sulfur.setItemMeta(meta);
        for (int i = 0; i < s; i++) {
            p.getBlockInventory().addItem(sulfur);
        }
    }

    public static void addHQM(int s, Player p) {
        ItemStack HQM = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = HQM.getItemMeta();
        meta.setDisplayName("Руда Высокого Качества");
        HQM.setItemMeta(meta);
        for (int i = 0; i < s; i++) {
            p.getInventory().addItem(HQM);
        }
    }

    public static void craftItem(String item, Player p) {
        p.sendMessage(Methods.Project_Name_Prefix + " " + item + " был создан");
    }

    public static void recipes() {
        ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.PAPER));
        recipe.shape("EE ", "EE ", "   ");
        recipe.setIngredient('E', Material.LOG);
        Bukkit.addRecipe(recipe);
    }

    public static void fullBuildingPlane() {
        Inventory inv = Methods.BuildingPlane;

        ItemStack wall = Methods.wall;
        ItemStack foundation = Methods.foundation;
        ItemStack stairs = Methods.stairs;

        ItemMeta FoundationMeta = foundation.getItemMeta();
        ItemMeta WallMeta = wall.getItemMeta();
        ItemMeta StairsMeta = stairs.getItemMeta();

        FoundationMeta.setDisplayName("Фундамент");
        WallMeta.setDisplayName("Стена");
        StairsMeta.setDisplayName("Ступеньки");

        wall.setItemMeta(WallMeta);
        foundation.setItemMeta(FoundationMeta);
        stairs.setItemMeta(StairsMeta);

        inv.setItem(3, foundation);
        inv.setItem(4, wall);
        inv.setItem(11, stairs);


        for (int i = 0; i < 9 * 6; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, Methods.gray_glass);
            } else {
                continue;
            }
        }
    }

    public static void fullCraftsGui() {
        Inventory inv = Methods.guiCrafts;

        inv.setItem(10, Methods.itemBuildingPlane());
        inv.setItem(11, Methods.itemKiyanka());
        inv.setItem(12, Methods.itemChest());
        inv.setItem(13, Methods.itemStoneAxe());
        inv.setItem(14, Methods.itemStonePickAxe());
        inv.setItem(15, Methods.itemMetalDoor());
        inv.setItem(16, Methods.itemHeavyDoor());


        for (int i = 0; i < 9 * 6; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, Methods.gray_glass);
            } else {
                continue;
            }
        }
    }


    public static void setFoundation(Block b, Player p) {

        Location loc = b.getLocation();
        for (int a = -2; a < 3; a++) {
            if (a != 0)
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).setType(Material.LOG);
            else
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).setType(Material.WOOL);
        }
        for (int a = -2; a < 3; a++) {
            if (a != 0)
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 2).setType(Material.LOG);
            else
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 2).setType(Material.WOOL);
        }
        for (int a = -2; a < 3; a++) {
            if (a != 0)
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).setType(Material.LOG);
            else
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).setType(Material.WOOL);
        }
        for (int a = -2; a < 3; a++) {
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).setType(Material.LOG);
        }
        for (int a = -1; a < 2; a++) {
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 1).setType(Material.HAY_BLOCK);

        }
        for (int a = -1; a < 2; a++) {
            if (a != 0)
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).setType(Material.LOG);
            else
                Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).setType(Material.WOOL);

        }
        for (int a = -1; a < 2; a++) {
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ()).setType(Material.HAY_BLOCK);

        }
        for (int a = -1; a < 2; a++) {
            Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 1).setType(Material.HAY_BLOCK);

        }


        p.sendMessage(ChatColor.AQUA + "Вы установили" + ChatColor.RED + " Фундамент");
    }

    public static boolean checkFoundation(Block b, Player p) {

        Location loc = b.getLocation();
        for (int a = -2; a < 3; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }
        }
        for (int a = -2; a < 3; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 2).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 2).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }
        }
        for (int a = -2; a < 3; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + (a)).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }
        }
        for (int a = -2; a < 3; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }
        }
        for (int a = -1; a < 2; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 1).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 1).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }

        }
        for (int a = -1; a < 2; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() + 2).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }

        }
        for (int a = -1; a < 2; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }

        }
        for (int a = -1; a < 2; a++) {
            if ((Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 1).getType() == Material.AIR) || (Bukkit.getWorld("world").getBlockAt(loc.getBlockX() + (a), loc.getBlockY() + 1, loc.getBlockZ() - 1).getType() == Material.LONG_GRASS)) {
                continue;
            } else {
                p.sendMessage(Methods.Project_Name_Prefix + "Место не ровное");
                return (false);
            }

        }


        return (true);
    }


    public static BlockFace getDirection(float yaw) {
        BlockFace[] AXIS = {
                BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH
        };
        return AXIS[Math.round(yaw / 90f) & 0x3];
    }


    public static void checkWallWest(Block b, Player p) {
        if (lastBlocks.containsKey(p)) {//есть ли до этого блок
            Location nowLoc = b.getLocation();
            nowLoc.setY(b.getY() + 1);
            nowLoc.setX(nowLoc.getX() + 2);
            if (lastBlocks.get(p).getBlock().getLocation().getX() == nowLoc.getX()) {//проверка старая ли локация==новой
                if (lastBlocks.get(p).getBlock().getLocation().getZ() == nowLoc.getZ()) {
                    return;
                }
            }

            Location clear = lastBlocks.get(p);//берем старую локу
            clear.setX(clear.getX() - 4);
            clear.setX(clear.getX() - 1);
            for (int s = 0; s <= 4; s++) {
                if (s != 0) {
                    clear.setX(clear.getX() - 7);
                    clear.setY(clear.getY() + 1);
                }
                for (int i = 0; i <= 6; i++) {
                    clear.getBlock().getState().update(true);
                    clear.setX(clear.getX() + 1);
                }
            }

            Methods.vector.remove(p);
            lastBlocks.remove(p);
        }

        Location Up = b.getLocation();
        Up.setX(Up.getX() + 3);
        boolean check = true;
        for (int s = 0; s <= 3; s++) {
            Up.setY(Up.getY() + 1);
            Up.setX(Up.getX() - 5);
            for (int i = 0; i <= 4; i++) {
                if (!Up.getBlock().getType().equals(Material.AIR)) {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 14);
                    check = false;
                } else {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 5);
                }
                Up.setX(Up.getX() + 1);
            }
        }
        Methods.can.put(p, check);
        Up.setY(Up.getY() - 3);
        lastBlocks.put(p, Up);
        Methods.vector.put(p, 1);


    }

    public static void checkWallEast(Block b, Player p) {
        if (lastBlocks.containsKey(p)) {//есть ли до этого блок
            Location nowLoc = b.getLocation();
            nowLoc.setY(b.getY() + 1);
            nowLoc.setX(nowLoc.getX() + 2);
            if (lastBlocks.get(p).getBlock().getLocation().getX() == nowLoc.getX()) {//проверка старая ли локация==новой
                if (lastBlocks.get(p).getBlock().getLocation().getZ() == nowLoc.getZ()) {
                    return;
                }
            }

            Location clear = lastBlocks.get(p);//берем старую локу
            clear.setX(clear.getX() + 5);
            for (int s = 0; s <= 4; s++) {
                if (s != 0) {
                    clear.setX(clear.getX() + 7);
                    clear.setY(clear.getY() + 1);
                }
                for (int i = 0; i <= 6; i++) {
                    clear.getBlock().getState().update(true);
                    clear.setX(clear.getX() - 1);
                }
            }

            Methods.vector.remove(p);
            lastBlocks.remove(p);

        }

        Location Up = b.getLocation();
        Up.setX(Up.getX() - 3);
        boolean check = true;
        for (int s = 0; s <= 3; s++) {
            Up.setY(Up.getY() + 1);
            Up.setX(Up.getX() + 5);
            for (int i = 0; i <= 4; i++) {
                if (!Up.getBlock().getType().equals(Material.AIR)) {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 14);
                    check = false;
                } else {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 5);
                }
                Up.setX(Up.getX() - 1);
            }
        }
        Methods.can.put(p, check);
        Up.setY(Up.getY() - 3);
        lastBlocks.put(p, Up);
        Methods.vector.put(p, 2);


    }

    public static void checkWallSouth(Block b, Player p) {
        if (lastBlocks.containsKey(p)) {//есть ли до этого блок
            Location nowLoc = b.getLocation();
            nowLoc.setY(b.getY() + 1);
            nowLoc.setZ(nowLoc.getZ() + 2);
            if (lastBlocks.get(p).getBlock().getLocation().getZ() == nowLoc.getZ()) {//проверка старая ли локация==новой
                if (lastBlocks.get(p).getBlock().getLocation().getZ() == nowLoc.getZ()) {
                    return;
                }
            }

            Location clear = lastBlocks.get(p);//берем старую локу
            clear.setZ(clear.getZ() + 5);
            for (int s = 0; s <= 4; s++) {
                if (s != 0) {
                    clear.setZ(clear.getZ() + 7);
                    clear.setY(clear.getY() + 1);
                }
                for (int i = 0; i <= 6; i++) {
                    clear.getBlock().getState().update(true);
                    clear.setZ(clear.getZ() - 1);
                }
            }


            lastBlocks.remove(p);
        }

        Location Up = b.getLocation();
        Up.setZ(Up.getZ() - 3);
        boolean check = true;
        for (int s = 0; s <= 3; s++) {
            Up.setY(Up.getY() + 1);
            Up.setZ(Up.getZ() + 5);
            for (int i = 0; i <= 4; i++) {
                if (!Up.getBlock().getType().equals(Material.AIR)) {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 14);
                    check = false;
                } else {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 5);
                }
                Up.setZ(Up.getZ() - 1);
            }
        }
        Methods.can.put(p, check);
        Up.setY(Up.getY() - 3);
        lastBlocks.put(p, Up);
        Methods.vector.put(p, 1);


    }

    public static void checkWallNorth(Block b, Player p) {
        if (lastBlocks.containsKey(p)) {//есть ли до этого блок
            Location nowLoc = b.getLocation();
            nowLoc.setY(b.getY() + 1);
            nowLoc.setZ(nowLoc.getZ() - 2);
            if (lastBlocks.get(p).getBlock().getLocation().getZ() == nowLoc.getZ()) {//проверка старая ли локация==новой
                if (lastBlocks.get(p).getBlock().getLocation().getZ() == nowLoc.getZ()) {
                    return;
                }
            }

            Location clear = lastBlocks.get(p);//берем старую локу
            clear.setZ(clear.getZ() - 5);
            for (int s = 0; s <= 4; s++) {
                if (s != 0) {
                    clear.setZ(clear.getZ() - 7);
                    clear.setY(clear.getY() + 1);
                }
                for (int i = 0; i <= 6; i++) {
                    clear.getBlock().getState().update(true);
                    clear.setZ(clear.getZ() + 1);
                }
            }


            lastBlocks.remove(p);
            Methods.vector.remove(p);
        }
        Location Up = b.getLocation();
        Up.setZ(Up.getZ() + 3);
        boolean check = true;
        for (int s = 0; s <= 3; s++) {
            Up.setY(Up.getY() + 1);
            Up.setZ(Up.getZ() - 5);
            for (int i = 0; i <= 4; i++) {
                if (!Up.getBlock().getType().equals(Material.AIR)) {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 14);
                    check = false;
                } else {
                    p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 5);
                }
                Up.setZ(Up.getZ() + 1);
            }
        }
        Methods.can.put(p, check);
        Up.setY(Up.getY() - 3);
        lastBlocks.put(p, Up);
        Methods.vector.put(p, 4);


    }

    public static void buildWallWest(Block b, Player p) {
        Location clear = lastBlocks.get(p);//берем старую локу
        clear.setX(clear.getX() - 5);
        for (int s = 0; s <= 3; s++) {
            if (s != 0) {
                clear.setX(clear.getX() - 5);
                clear.setY(clear.getY() + 1);
            }
            for (int i = 0; i <= 4; i++) {
                clear.getBlock().setType(Material.HAY_BLOCK);
                clear.setX(clear.getX() + 1);
            }
        }
    }

    public static void buildWallEast(Block b, Player p) {
        Location clear = lastBlocks.get(p);//берем старую локу
        clear.setX(clear.getX() + 5);
        for (int s = 0; s <= 4; s++) {
            if (s != 0) {
                clear.setX(clear.getX() + 7);
                clear.setY(clear.getY() + 1);
            }
            for (int i = 0; i <= 6; i++) {
                clear.getBlock().setType(Material.HAY_BLOCK);
                clear.setX(clear.getX() - 1);
            }
        }
    }

    public static void buildWallSouth(Block b, Player p) {
        Location Up = b.getLocation();
        Up.setY(Up.getY() - 1);
        for (int s = 0; s <= 3; s++) {
            Up.setY(Up.getY() + 1);
            Up.setZ(Up.getZ() + 5);
            for (int i = 0; i <= 4; i++) {
                Up.getBlock().setType(Material.HAY_BLOCK);
                Up.setZ(Up.getZ() - 1);
            }
        }
    }

    public static void buildWallNorth(Block b, Player p) {
        Location Up = b.getLocation();
        Up.setY(Up.getY() - 1);
        for (int s = 0; s <= 3; s++) {
            Up.setY(Up.getY() + 1);
            Up.setZ(Up.getZ() - 5);
            for (int i = 0; i <= 4; i++) {
                Up.getBlock().setType(Material.HAY_BLOCK);
                Up.setZ(Up.getZ() + 1);
            }
        }
    }

    public static void clear(Block b, Player p) {
        if (!Methods.vector.containsKey(p)) return;
        switch (Methods.vector.get(p)) {
            case 1: {
                Location clear = lastBlocks.get(p);//берем старую локу
                clear.setX(clear.getX() - 4);
                clear.setX(clear.getX() - 1);
                for (int s = 0; s <= 4; s++) {
                    if (s != 0) {
                        clear.setX(clear.getX() - 7);
                        clear.setY(clear.getY() + 1);
                    }
                    for (int i = 0; i <= 6; i++) {
                        clear.getBlock().getState().update(true);
                        clear.setX(clear.getX() + 1);
                    }
                }
            }
            case 2: {
                Location clear = lastBlocks.get(p);//берем старую локу
                clear.setX(clear.getX() + 5);
                for (int s = 0; s <= 4; s++) {
                    if (s != 0) {
                        clear.setX(clear.getX() + 7);
                        clear.setY(clear.getY() + 1);
                    }
                    for (int i = 0; i <= 6; i++) {
                        clear.getBlock().getState().update(true);
                        clear.setX(clear.getX() - 1);
                    }
                }
            }
            case 3: {
                Location Up = b.getLocation();
                Up.setZ(Up.getZ() - 3);
                boolean check = true;
                for (int s = 0; s <= 3; s++) {
                    Up.setY(Up.getY() + 1);
                    Up.setZ(Up.getZ() + 5);
                    for (int i = 0; i <= 4; i++) {
                        if (!Up.getBlock().getType().equals(Material.AIR)) {
                            p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 14);
                            check = false;
                        } else {
                            p.sendBlockChange(Up, Material.STAINED_GLASS, (byte) 5);
                        }
                        Up.setZ(Up.getZ() - 1);
                    }
                }
            }
            case 4: {
                Location clear = lastBlocks.get(p);//берем старую локу
                clear.setZ(clear.getZ() - 5);
                for (int s = 0; s <= 4; s++) {
                    if (s != 0) {
                        clear.setZ(clear.getZ() - 7);
                        clear.setY(clear.getY() + 1);
                    }
                    for (int i = 0; i <= 6; i++) {
                        clear.getBlock().getState().update(true);
                        clear.setZ(clear.getZ() + 1);
                    }
                }
            }

        }
    }


    //выдача айтемов
    public static void sendBuildingPlane(Player p) {


    }

    //айтемы из GUI
    public static ItemStack itemBuildingPlane() {
        ItemStack buildingPlane = new ItemStack(Material.PAPER, 1);

        ItemMeta buildingPlaneMeta = buildingPlane.getItemMeta();

        buildingPlaneMeta.setDisplayName("План постройки");

        buildingPlaneMeta.setLore(Collections.singletonList("10 Дерева"));

        buildingPlane.setItemMeta(buildingPlaneMeta);

        return buildingPlane;

    }
    public static ItemStack itemKiyanka() {
        ItemStack kiyanka = new ItemStack(Material.IRON_HOE, 1);

        ItemMeta kiyankaMeta = kiyanka.getItemMeta();

        kiyankaMeta.setDisplayName("Киянка");

        kiyankaMeta.setLore(Collections.singletonList("20 Дерева"));

        kiyanka.setItemMeta(kiyankaMeta);
        return kiyanka;
    }
    public static ItemStack itemChest(){
        ArrayList chestLore = new ArrayList();
        chestLore.add("40 Дерева");
        chestLore.add("10 Железа");

        ItemStack chest = new ItemStack(Material.CHEST, 1);

        ItemMeta chestMeta = chest.getItemMeta();


        chestMeta.setDisplayName("Сундук");


        chestMeta.setLore(chestLore);


        chest.setItemMeta(chestMeta);

        return chest;


    }
    public static ItemStack itemStoneAxe(){
        ArrayList stoneAxeLore = new ArrayList();
        stoneAxeLore.add("30 Дерева");
        stoneAxeLore.add("15 Камня");

        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE, 1);

        ItemMeta stoneAxeMeta = stoneAxe.getItemMeta();

        stoneAxeMeta.setDisplayName("Каменный топор");

        stoneAxeMeta.setLore(stoneAxeLore);

        stoneAxe.setItemMeta(stoneAxeMeta);
        return stoneAxe;
    }
    public static ItemStack itemStonePickAxe(){
        ArrayList stonePickAxeLore = new ArrayList();
        stonePickAxeLore.add("30 Дерева");
        stonePickAxeLore.add("15 Камня");

        ItemStack stonePickAxe = new ItemStack(Material.STONE_PICKAXE, 1);

        ItemMeta stonePickAxeMeta = stonePickAxe.getItemMeta();

        stonePickAxeMeta.setDisplayName("Каменная кирка");

        stonePickAxeMeta.setLore(stonePickAxeLore);

        stonePickAxe.setItemMeta(stonePickAxeMeta);
        return stonePickAxe;
    }
    public static ItemStack itemMetalDoor(){
        ItemStack metalDoor = new ItemStack(Material.DARK_OAK_DOOR_ITEM, 1);

        ItemMeta metalDoorMeta = metalDoor.getItemMeta();

        metalDoorMeta.setDisplayName("Железная дверь");

        metalDoorMeta.setLore(Collections.singletonList("20 Металлических фрагментов"));

        metalDoor.setItemMeta(metalDoorMeta);
        return metalDoor;
    }
    public static ItemStack itemHeavyDoor(){
        ArrayList heavyDoorLore = new ArrayList();
        heavyDoorLore.add("10 МВК");
        heavyDoorLore.add("5 шестеренок");

        ItemStack heavyDoor = new ItemStack(Material.JUNGLE_DOOR_ITEM, 1);


        ItemMeta heavyDoorMeta = heavyDoor.getItemMeta();

        heavyDoorMeta.setDisplayName("Бронированная дверь");

        heavyDoorMeta.setLore(heavyDoorLore);

        heavyDoor.setItemMeta(heavyDoorMeta);
        return heavyDoor;
    }

    //ресурсы
    public static ItemStack resourceWood(){
        ItemStack wood = new ItemStack(Material.LOG);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Дерево");
        wood.setItemMeta(meta);
        return wood;
    }
}

