package org.sct.luckyegg.util;

import org.sct.luckyegg.util.RewardUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuckUtil {

    public static int lottery(List<RewardUtil> gifts) {

        List<Double> orignalRates = new ArrayList<Double>();

        for (RewardUtil gift : gifts) {
            double probability = gift.chance;
            if (probability < 0) {
                probability = 0;
            }
            orignalRates.add(probability);
        }

        if (orignalRates == null || orignalRates.isEmpty()) {
            return -1;
        }

        int size = orignalRates.size();

        // 计算总概率，这样可以保证不一定总概率是1
        double sumRate = 0d;
        for (double rate : orignalRates) {
            sumRate += rate;
        }

        // 计算每个物品在总概率的基础下的概率情况
        List<Double> sortOrignalRates = new ArrayList<Double>(size);
        Double tempSumRate = 0d;
        for (double rate : orignalRates) {
            tempSumRate += rate;
            sortOrignalRates.add(tempSumRate / sumRate);
        }

        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        sortOrignalRates.add(nextDouble);
        Collections.sort(sortOrignalRates);

        return sortOrignalRates.indexOf(nextDouble);
    }

}
