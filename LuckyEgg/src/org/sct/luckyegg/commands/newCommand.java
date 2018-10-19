package org.sct.luckyegg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.luckyegg.util.GuiUtil;

public class newCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                GuiUtil.openGui(player);
            }
        }
        return false;
    }
}
