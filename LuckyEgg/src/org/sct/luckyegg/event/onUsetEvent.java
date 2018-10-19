package org.sct.luckyegg.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.luckyegg.file.ConfigReader;
import org.sct.luckyegg.util.GuiUtil;

public class onUsetEvent implements Listener {

    @EventHandler
    public void onClickItem(PlayerInteractEvent e) {

        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            if ((e.getItem() == null) || (e.getItem().getType().equals(Material.AIR))) {
                return;
            }

            if (e.getItem().getItemMeta() == null) {
                return;
            }

            if (e.getItem().getItemMeta().getDisplayName() == null) {
                return;
            }

            if (e.getItem().equals(ConfigReader.getNormalTool()) ||
                    e.getItem().equals(ConfigReader.getGoldenTool()) ||
                    e.getItem().equals(ConfigReader.getLegendTool())) {
                GuiUtil.openGui(e.getPlayer());
            }

            if (e.getItem().equals(ConfigReader.getNormal()) ||
                    e.getItem().equals(ConfigReader.getGolden()) ||
                    e.getItem().equals(ConfigReader.getLegend())) {
                e.setCancelled(true);
                e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
            }
            return;
        }
    }
}
