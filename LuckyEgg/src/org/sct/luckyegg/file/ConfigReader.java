package org.sct.luckyegg.file;

import me.myPlugin.LuckyEgg.LuckyEgg;
import org.bukkit.inventory.ItemStack;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.luckyegg.util.RewardUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class ConfigReader extends FileUtil {

    private static ConfigReader config;
    private static List<RewardUtil> normalEggList = new ArrayList<>();
    private static List<RewardUtil> goldenEggList = new ArrayList<>();
    private static List<RewardUtil> legendEggList = new ArrayList<>();
    private static Map<String, String> eggMap = new HashMap<>();
    private static String normal,golden,legend,normalTool,goldenTool,legendTool,GuiTitle,Prefix,Message;

    private ConfigReader() { super(LuckyEgg.INSTANCE, "config.yml"); }
    public static void reload() { config = new ConfigReader(); }

    public void check() {
        File file = new File(LuckyEgg.INSTANCE.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Message = config.getString("Message");
        ConfigurationSection normalEgg = config.getConfigurationSection("Reward.Normal");
        ConfigurationSection goldenEgg = config.getConfigurationSection("Reward.Golden");
        ConfigurationSection legendEgg = config.getConfigurationSection("Reward.Legend");
        ConfigurationSection eggTool = config.getConfigurationSection("Egg");
        normalEggList.clear();
        RewardUtil.addReward(normalEgg, normalEggList);
        goldenEggList.clear();
        RewardUtil.addReward(goldenEgg, goldenEggList);
        legendEggList.clear();
        RewardUtil.addReward(legendEgg, legendEggList);
        normal = LuckyEgg.getInstance().getConfig().getString("Egg.NormalEgg.Item");
        golden = config.getString("Egg.GoldenEgg.Item");
        legend = config.getString("Egg.LegendEgg.Item");
        normalTool = config.getString("Egg.NormalEgg.Tool");
        goldenTool = config.getString("Egg.GoldenEgg.Tool");
        legendTool = config.getString("Egg.LegendEgg.Tool");
        GuiTitle = config.getString("GuiTitle");
        Prefix = config.getString("Prefix");
    }

    public static String getTool(String item) { return eggMap.get(item); }
    public static String getGuiTitle() { return GuiTitle; }
    public static String getNormal() { return normal;}
    public static String getGolden() { return golden; }
    public static String getLegend() { return legend; }
    public static String getNormalTool() { return normalTool; }
    public static String getGoldenTool() { return goldenTool; }
    public static String getLegendTool() { return legendTool; }
    public static List<RewardUtil> getNormalEggList() { return normalEggList; }
    public static List<RewardUtil> getGoldenEggList() { return goldenEggList; }
    public static List<RewardUtil> getLegendEggList() { return legendEggList; }
    public static String getPrefix() { return Prefix.replace("&", "§"); }
    public static String getMessage() { return Message.replace("&", "§"); }

    public static boolean isContainEgg(ItemStack item) {
        if(item.equals(getLegend()) ||
        item.equals(getGolden()) ||
        item.equals(getNormal())) { return true; }
        return false;
    }
    public static boolean isContainTool(ItemStack item) {
        if(item.equals(getLegendTool()) ||
                item.equals(getGoldenTool()) ||
                item.equals(getNormalTool())) { return true; }
        return false;
    }

}
