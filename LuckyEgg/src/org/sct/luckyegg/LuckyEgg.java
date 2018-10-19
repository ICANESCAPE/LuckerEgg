package org.sct.luckyegg;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.core.Core;
import org.sct.luckyegg.commands.newCommand;
import org.sct.luckyegg.event.onClickEvent;
import org.sct.luckyegg.event.onUsetEvent;
import org.sct.luckyegg.file.ConfigReader;

public class LuckyEgg extends JavaPlugin {

    public static LuckyEgg INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Core.info("砸蛋插件加载成功");
        Bukkit.getServer().getPluginManager().registerEvents(new onClickEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onUsetEvent(), this);
        Bukkit.getServer().getPluginCommand("luckyegg").setExecutor(new newCommand());
        ConfigReader.reload();
    }

    @Override
    public void onDisable() {
        Core.info("砸蛋卸载成功");
    }

    public static LuckyEgg getInstance() { return INSTANCE; }
}
