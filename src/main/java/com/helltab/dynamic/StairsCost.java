package com.helltab.dynamic;

import com.helltab.util.MyUtil;

/**
 * 746. 使用最小花费爬楼梯
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * <p>
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * <p>
 */
public class StairsCost {
    public static void main(String[] args) {
//        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int[] cost = {0, 0, 1, 2};
//        int[] cost = {0, 0, 1, 1};
//        int[] cost = {10, 15, 20};
//        int[] cost = {0, 1, 2, 2};
        MyUtil.test(a -> {
            System.out.println(solution1(cost));
        });
    }

    /**
     * 暴力法
     *
     * @param cost
     * @return
     */
    public static int solution1(int[] cost) {
        int len = cost.length;
        int[] minSum = new int[len + 1];
        if (len < 2) {
            return 0;
        }
        minSum[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < cost.length; i++) {
            minSum[i] = Math.min(minSum[i - 2] + cost[i - 1], minSum[i - 1] + cost[i]);
        }
        return minSum[len - 1];
    }
}
