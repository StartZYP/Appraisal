package com.qq44920040.Minecraft.Appraisal.View;

import com.qq44920040.Minecraft.Appraisal.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AppraisalView {
    public static Inventory OpenAppraisalView(){
        Inventory inv = Bukkit.createInventory(null,54, Main.InvTitle);
        ItemStack itemStackup = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)4);
        ItemMeta itemMetaup = itemStackup.getItemMeta();
        itemMetaup.setDisplayName("§a格挡板");
        ItemStack itemStackdown = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)2);
        ItemMeta itemMetadown = itemStackdown.getItemMeta();
        itemMetadown.setDisplayName("§a格挡板");
        itemStackdown.setItemMeta(itemMetadown);
        itemStackup.setItemMeta(itemMetaup);

        ItemStack itemStackRight = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)3);
        ItemMeta itemMetaRight = itemStackRight.getItemMeta();
        itemMetaRight.setDisplayName("§d放入需要鉴定的武器");
        itemStackRight.setItemMeta(itemMetaRight);

        ItemStack itemStackLift = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)5);
        ItemMeta itemMetaLift = itemStackLift.getItemMeta();
        itemMetaLift.setDisplayName("§e放入鉴定卷轴");
        itemStackLift.setItemMeta(itemMetaLift);
        for (int i=0;i<=53;i++){
            if (i==0||i==1||i==2||i==9||i==11||i==18||i==19||i==20){
                inv.setItem(i,itemStackRight);
            }else if (i==6||i==7||i==8||i==15||i==17||i==24||i==25||i==26){
                inv.setItem(i,itemStackLift);
            }else if (i<=26){
                inv.setItem(i,itemStackup);
            }else {
                inv.setItem(i,itemStackdown);
            }
        }
        inv.setItem(11,null);
        inv.setItem(15,null);
        ItemStack button = new ItemStack(Material.LEVER,1);
        ItemMeta itemMetabutton = button.getItemMeta();
        itemMetabutton.setDisplayName("§c§l鉴定");
        button.setItemMeta(itemMetabutton);
        inv.setItem(40,button);
        return inv;
    }
}
