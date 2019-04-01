package com.qq44920040.Minecraft.Appraisal.Listener;

import com.qq44920040.Minecraft.Appraisal.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

public class ViewListener implements Listener {
    @EventHandler
    public void InventoryClickevent(InventoryClickEvent event){
        InventoryView inventoryView = event.getView();
        if (inventoryView.getTitle().equalsIgnoreCase(Main.InvTitle)){
            int Solt = event.getRawSlot();
            System.out.println(Solt);
            if (Solt==40){
                event.setCancelled(true);
                Player player = (Player)event.getWhoClicked();
                ItemStack wuqi = inventoryView.getItem(10);
                ItemStack Tool = inventoryView.getItem(16);
                if (wuqi!=null&&Tool!=null){
                    if (wuqi.hasItemMeta()&&Tool.hasItemMeta()){
                        System.out.println("进入mate");
                        ItemMeta wuqimeta= wuqi.getItemMeta();
                        ItemMeta ToolMeta = Tool.getItemMeta();
                        if (wuqimeta.hasLore()&&ToolMeta.hasLore()){
                            System.out.println("两者有lore");
                            List<String> wuqiLores = wuqimeta.getLore();
                            List<String> ToolLores = ToolMeta.getLore();
                            Set<String> setkey = Main.AppraisalInfo.keySet();
                            System.out.println(wuqiLores.toString());
                            for (String key:setkey){
                                if (wuqiLores.contains(key)){
                                    System.out.println("武器上有关键字");
                                    String Toolkey = Main.AppraisalInfo.get(key);
                                    if (ToolLores.contains(Toolkey)){
                                        System.out.println("工具上有关键字");
                                        List<String> lorelist = Main.config.getStringList("AppraisalItem."+key+".RePlaceLore");
                                        int Index = wuqiLores.lastIndexOf(key);
                                        System.out.println("索引:"+Index);
                                        wuqiLores.remove(Index);
                                        System.out.println(lorelist.size()+"lore个数");
                                        int rundomnum = (int)(Math.random()*lorelist.size()+1);
                                        System.out.println(rundomnum+"随机数");
                                        System.out.println(lorelist.get(rundomnum-1));
                                        String[] MultilineLore = lorelist.get(rundomnum-1).replace("{Player}",player.getName()).split("\\|");
                                        int Maxnum = Index+MultilineLore.length-1;
                                        for (int a=Index;a<=Maxnum;a++){
                                            System.out.println(a);
                                            wuqiLores.add(Index,MultilineLore[a-Index]);
                                        }
                                        wuqimeta.setLore(wuqiLores);
                                        wuqi.setItemMeta(wuqimeta);
                                        player.getInventory().addItem(wuqi);
                                        inventoryView.setItem(11,null);
                                        inventoryView.setItem(15,null);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
                player.sendMessage("§c[系统]§a请检查道具放置是否正确");
            }else if(Solt==10||Solt==16){
                event.setCancelled(false);
            }else if (Solt<=53&&Solt>=0){
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void Inventorycloseevent(InventoryCloseEvent event){
        InventoryView inventoryView = event.getView();
        if (inventoryView.getTitle().equalsIgnoreCase(Main.InvTitle)){
            ItemStack wuqi = inventoryView.getItem(10);
            ItemStack Tool = inventoryView.getItem(16);
            if (wuqi!=null||Tool!=null){
                event.getPlayer().getInventory().addItem(wuqi);
                event.getPlayer().getInventory().addItem(Tool);
            }
        }
    }
}
