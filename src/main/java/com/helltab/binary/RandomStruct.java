package com.helltab.binary;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/10 15:10
 * @desc 随机结构, 根据默认给定的随机算法, 构造出需要的概率算法
 */
public class RandomStruct {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < (1 << 20); i++) {
            list.add(randomPBin_0());
        }
        Map<Integer, Long> collect = list.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect);
    }

    /**
     * 给定条件: 只等概率的返回 1-5 的数
     * 需要: 构造一个 1-7 等概率的算法
     *
     * @return
     */
    public static int random5() {
        return (int) (Math.random() * 5 + 1);
    }

    /**
     * 思路: 先用固定的随机算法, 得到等概率 0 1 的算法
     * 再根据 0 1 去构造目标
     *
     * @return
     */
    public static int random5_7() {
        int i = (random5Bin() << 2) + (random5Bin() << 1) + random5Bin();
        if (i == 0) return random5_7();
        return i;
    }

    private static int random5Bin() {
        int i = random5();
        if (i == 3) {
            return random5Bin();
        }
        return i < 3 ? 0 : 1;
    }

    /**
     * 给定条件: 以 p 概率返回 1, 1-p 返回 0
     * 需要: 构造概率的 1 0 算法
     *
     * @return
     */
    public static int randomPBin() {
        return Math.random() > .2 ? 1 : 0;
    }

    /**
     * 构造出 01 10 00(重来)
     * 01 返回 1
     * 10 返回 3
     *
     * @return
     */
    public static int randomPBin_0() {
        int temp = (randomPBin() << 1) + random5Bin();
        if (temp == 0) return randomPBin_0();
        return temp == 1 ? 1 : 0;
    }
}
