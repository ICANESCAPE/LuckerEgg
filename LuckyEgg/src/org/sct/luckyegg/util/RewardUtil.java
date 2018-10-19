package org.sct.luckyegg.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.sct.luckyegg.file.ConfigReader;

import java.util.List;
import java.util.Random;

public class RewardUtil {

    double chance;
    List<String> cmd;
    String typename;
    String eggname;

    public RewardUtil(double chance, List<String> cmd, String typename, String eggname) {
        this.chance = chance;
        this.cmd = cmd;
        this.typename = typename;
        this.eggname = eggname;
    }

    public static void addReward(ConfigurationSection cs, List<RewardUtil> list) {
        int id = 0;
        for(String key : cs.getKeys(false)) {
            List<String> award = cs.getStringList(key+".reward");
            double chance = cs.getInt(key+".chance")/100D;
            list.add(new RewardUtil(chance, award, key, cs.getName()));
            id++;
        }
    }

    public static void sendReward(Player player, RewardUtil reward) {
        if(reward.cmd.isEmpty()) { return; }
        Random random = new Random();
        String award = reward.cmd.get(random.nextInt(reward.cmd.size()))
                .replace("%player%", player.getName());
        String message = ConfigReader.getMessage()
                .replace("%player%", player.getName())
                .replace("%type%", reward.eggname)
                .replace("%quality%", reward.typename);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), award);
        player.getServer().broadcastMessage(message);
    }

    public static void luck(int i, Player p){
        if(i >= 0 && i < 27){
            //蛋A
            int index = LuckUtil.lottery(ConfigReader.getNormalEggList());
            if(index != -1){
                sendReward(p, ConfigReader.getNormalEggList().get(index));
            }
        }else if(i >= 27 && i < 53){
            //蛋B
            int index = LuckUtil.lottery(ConfigReader.getGoldenEggList());
            if(index != -1){
                sendReward(p, ConfigReader.getGoldenEggList().get(index));
            }
        }else if(i == 53){
            //蛋C
            int index = LuckUtil.lottery(ConfigReader.getLegendEggList());
            if(index != -1){
                sendReward(p, ConfigReader.getLegendEggList().get(index));
            }
        }
    }

}
