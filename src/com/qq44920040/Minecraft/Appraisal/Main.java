package com.qq44920040.Minecraft.Appraisal;

import com.qq44920040.Minecraft.Appraisal.Listener.ViewListener;
import com.qq44920040.Minecraft.Appraisal.View.AppraisalView;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Main extends JavaPlugin {
    public static HashMap<String,String> AppraisalInfo = new HashMap<>();
    public static String InvTitle;
    public static FileConfiguration config;

    @Override
    public void onDisable() {
        super.onDisable();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player &&label.equalsIgnoreCase("Appraisal")&&args.length==0){
            ((Player) sender).openInventory(AppraisalView.OpenAppraisalView());
        }else if (sender instanceof Player&&sender.isOp() &&label.equalsIgnoreCase("Appraisal")&&args.length==1){
            ReloadConfig();
            sender.sendMessage("§c§l重载成功");
        }

        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        config = getConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new ViewListener(),this);
        ReloadConfig();
        super.onEnable();
    }

    private void ReloadConfig() {
        reloadConfig();
        Set<String> mines = getConfig().getConfigurationSection("AppraisalItem").getKeys(false);
        for (String temp : mines) {
            AppraisalInfo.put(temp, getConfig().getString("AppraisalItem."+temp+".Value"));
        }
        InvTitle = getConfig().getString("Appraisal.ViewTitle");
    }

}
