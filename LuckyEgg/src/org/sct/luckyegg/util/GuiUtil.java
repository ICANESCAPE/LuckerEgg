package org.sct.luckyegg.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sct.core.file.FileTool;
import org.sct.core.util.ItemStackUtil;
import org.sct.luckyegg.file.ConfigReader;

public class GuiUtil {

    public static void openGui(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "测试GUI");
        ItemStack egg1 = FileTool.getItem(ConfigReader.getNormal());
        ItemStack egg2 = FileTool.getItem(ConfigReader.getGolden());
        ItemStack egg3 = FileTool.getItem(ConfigReader.getLegend());

        for(int index = 0; index <= 26; index++) { inventory.setItem(index, egg1); }
        for(int index = 27; index <= 52; index++) { inventory.setItem(index, egg2); }
        inventory.setItem(53, egg3);

        player.openInventory(inventory);
    }

    public static boolean useItem(int index, Player player) {
        ItemStack normal = FileTool.getItem(ConfigReader.getNormalTool());
        ItemStack golden = FileTool.getItem(ConfigReader.getGoldenTool());
        ItemStack legend = FileTool.getItem(ConfigReader.getLegendTool());

        if(index >= 0 && index < 27){
            return ItemStackUtil.TakePlayerItem(player, normal);
        }else if(index >= 27 && index < 53){
            return ItemStackUtil.TakePlayerItem(player, golden);
        }else if(index == 53){
            return ItemStackUtil.TakePlayerItem(player, legend);
        }
        return false;
    }

}
