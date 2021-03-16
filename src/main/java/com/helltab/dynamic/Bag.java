package com.helltab.dynamic;

/**
 * 0-1 背包问题
 */
public class Bag {
    final static int[][] arr = {{0, 1, 2, 5, 6, 7}, {0, 1, 6, 18, 22, 28}};
    final static int C = 11;

    public static void main(String[] args) {
        solution1();
    }

    /**
     * 动态规划, 二维表动态规划
     */
    public static void solution1() {
        int len = arr[0].length;
        int[] arr_w = arr[0];
        int[] arr_v = arr[1];
        int[][] temp = new int[6][C + 1];

        for (int i = 1; i < len; i++) {
            for (int j = 1; j < C + 1; j++) {
                // 决策: 能放才需要选择
                if (arr_w[i] <= j) {
                    // 这里就是在做最优解
                    temp[i][j] = Math.max(temp[i - 1][j], temp[i - 1][j - arr_w[i]] + arr_v[i]);
                } else {
                    temp[i][j] = temp[i - 1][j];
                }

            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < C + 1; j++) {
                System.out.print(temp[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(temp[5][11]);
    }
}
