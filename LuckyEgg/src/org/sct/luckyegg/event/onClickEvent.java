package org.sct.luckyegg.event;

import me.myPlugin.LuckyEgg.Util.RewardUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sct.core.Core;
import org.sct.luckyegg.file.ConfigReader;
import org.sct.luckyegg.util.GuiUtil;

public class onClickEvent implements Listener {

    @EventHandler
    public void onClickGui(InventoryClickEvent e) {

        Inventory inventory = e.getInventory();
        if(! inventory.getName().equals(ConfigReader.getGuiTitle())
        || e.getInventory() == null) { Core.info("不同"); return; }

        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        ItemStack currentItem = e.getCurrentItem();
        if(currentItem.getType() == Material.AIR || currentItem == null) { return; }
        if(!ConfigReader.isContainEgg(currentItem)) { e.setCancelled(true); }
        if(e.getClick() == ClickType.SHIFT_LEFT){
            e.setCancelled(true);
            player.closeInventory();
            return;
        }

        if(GuiUtil.useItem(slot, player)) {
            if(inventory.getItem(slot) != null && inventory.getItem(slot).getType() != Material.AIR) {
                RewardUtil.luck(slot, player);
                inventory.setItem(slot, null);
            }
            player.sendMessage("砸蛋成功");
        } else {
            player.sendMessage("§c砸蛋失败!你没有足够的砸蛋工具");
            player.closeInventory();
            e.setCancelled(true);
        }

    }
}
