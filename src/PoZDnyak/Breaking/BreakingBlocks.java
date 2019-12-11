package PoZDnyak.Breaking;

import PoZDnyak.Main;
import PoZDnyak.Methods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;
import static org.bukkit.Material.IRON_ORE;

public class BreakingBlocks implements Listener {
    public BreakingBlocks(Main main) {

    }

    @EventHandler
    public void dig(BlockBreakEvent e){

        Player p = e.getPlayer();
        Block b = e.getBlock();
        ItemStack hand = p.getInventory().getItemInHand();
        //что ломает(дерево,сера,метал,камень)
        switch (b.getType()) {
            case LOG: {
                //каким инструментом ломает дерево(деревянный,каменный,железный)
                switch (hand.getType()) {
                    case STONE_AXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.DARK_GREEN + "+5 Дерева");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addWood(5,p);
                        break;
                    }
                    case IRON_AXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.DARK_GREEN + "+10 дерева");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addWood(10,p);
                        break;
                    }
                    case GOLD_AXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.DARK_GREEN + "+15 Дерева");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addWood(15,p);
                        break;
                    }
                    case WOOD_PICKAXE: {
                        if(!hand.getItemMeta().getDisplayName().equalsIgnoreCase("Камень (Rock)")) {e.setCancelled(true);break;}
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.DARK_GREEN + "+3 Дерева");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addWood(3,p);
                        break;
                    }
                    default: {
                        e.setCancelled(true);
                        break;
                    }
                }
                return;
            }
            case GOLD_ORE: {
                switch (hand.getType()) {
                    case STONE_PICKAXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GOLD + "+2 Серы");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addSulfur(2,p);
                        break;
                    }
                    case IRON_PICKAXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GOLD + "+3 Серы");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addSulfur(3,p);
                        break;
                    }
                    case GOLD_PICKAXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GOLD + "+4 Серы");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addSulfur(4,p);
                        break;
                    }
                    case WOOD_PICKAXE: {
                        if(!hand.getItemMeta().getDisplayName().equalsIgnoreCase("Камень (Rock)")) {e.setCancelled(true);break;}
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GOLD + "+1 Серы");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addSulfur(1,p);
                        break;
                    }
                    default: {
                        e.setCancelled(true);
                        break;
                    }

                }
                return;
            }
            case IRON_ORE: {
                switch (hand.getType()) {
                    case STONE_PICKAXE: {
                        double chanse = Math.random()*100;
                        if(chanse>=80){
                            double rand = (Math.random() * +2) + 1;
                            int rand1 = (int)rand;
                            p.sendMessage(ChatColor.RED+"------------------");
                            p.sendMessage(ChatColor.GRAY + "+"+rand1+" High Quality Metal");
                            p.sendMessage(ChatColor.RED+"------------------");
                            Methods.addHQM(rand1,p);
                        }
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+3 Железной руды");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addIron(3,p);
                        break;
                    }
                    case IRON_PICKAXE: {
                        double chanse = Math.random()*100;
                        if(chanse>=80){
                            double rand = (Math.random() * +2) + 1;
                            int rand1 = (int)rand;
                            p.sendMessage(ChatColor.RED+"------------------");
                            p.sendMessage(ChatColor.GRAY + "+"+rand1+" High Quality Metal");
                            p.sendMessage(ChatColor.RED+"------------------");
                            Methods.addHQM(rand1,p);
                        }
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+5 Железной руды");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addIron(5,p);
                        break;
                    }
                    case GOLD_PICKAXE: {
                        double chanse = Math.random()*100;
                        if(chanse>=80){
                            double rand = (Math.random() * +2) + 1;
                            int rand1 = (int)rand;
                            p.sendMessage(ChatColor.RED+"------------------");
                            p.sendMessage(ChatColor.GRAY + "+"+rand1+" High Quality Metal");
                            p.sendMessage(ChatColor.RED+"------------------");
                            Methods.addHQM(rand1,p);
                        }
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+7 Железной руды");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addIron(7,p);
                        break;
                    }
                    case WOOD_PICKAXE: {
                        if(!hand.getItemMeta().getDisplayName().equalsIgnoreCase("Камень (Rock)")) {e.setCancelled(true);break;}
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+2 Железной руды");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addIron(2,p);
                        break;
                    }
                    default: {
                        e.setCancelled(true);
                        break;
                    }

                }
                return;
            }
            case COBBLESTONE: {
                switch (hand.getType()) {
                    case STONE_PICKAXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+5 Камня");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addStone(5,p);
                        break;
                    }
                    case IRON_PICKAXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+10 Камня");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addStone(10,p);
                        break;
                    }
                    case GOLD_PICKAXE: {
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+15 Камня");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addStone(15,p);
                        break;
                    }
                    case WOOD_PICKAXE: {
                        if(!hand.getItemMeta().getDisplayName().equalsIgnoreCase("Камень (Rock)")) {e.setCancelled(true);break;}
                        e.setCancelled(true);
                        b.setType(AIR);
                        p.sendMessage(ChatColor.RED + "------------------");
                        p.sendMessage(ChatColor.GRAY + "+3 Камня");
                        p.sendMessage(ChatColor.RED + "------------------");
                        Methods.addStone(3,p);
                        break;
                    }
                    default: {
                        e.setCancelled(true);
                        break;
                    }

                }
                return;
            }
            default: {
                e.setCancelled(true);
                return;
            }
        }
    }
}
