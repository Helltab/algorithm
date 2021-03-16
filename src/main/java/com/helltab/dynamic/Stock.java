package com.helltab.dynamic;

import com.helltab.util.MyUtil;

/**
 * 买股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 */
public class Stock {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7, 6, 4, 2};
        MyUtil.test(a -> {
            solution1(prices);
        });
        MyUtil.test(a -> {
            solution2(prices);
        });
    }


    /**
     * 暴力法
     */
    public static void solution1(int[] prices) {
        int len = prices.length;
        int profit = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                profit = Math.max(prices[j] - prices[i], profit);
            }
        }

        System.out.println(profit);
    }

    /**
     * 动态规划
     * 每天都想着, 我是在最低价格买入的, 那么我的收益是多少呢
     * minP = min(p(0)-p(i)): 根据前面的所有天数的价格来确定的
     * profit = max(p(i)-minP, profit): 根据最小值来确定的
     *
     * @param prices
     */
    private static void solution2(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return;
        }
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }

        System.out.println(profit);
    }

    static class Price {
        int init;
        int dif;
    }
}
