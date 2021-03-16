package com.helltab.dynamic;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 输入: 2
 * 输出: [0,1,1]
 */
public class BitsCount {
    public static void main(String[] args) {
        int num = 5;
        solution1(num);
        solution2(num);
        solution3(num);
    }

    /**
     * 暴力解法
     */
    public static void solution1(int num) {
        int[] counts = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            counts[i] = countOf1(i);
        }
        for (int count : counts) {
            System.out.print(count + "\t");
        }
        System.out.println();
    }

    /**
     * 动态规划
     *
     * @param num
     * @return
     */
    public static int[] solution2(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        if (num == 1) {
            return new int[]{0, 1};
        }
        int[] counts = new int[num + 1];
        counts[1] = counts[2] = 1;
        for (int i = 3; i < num + 1; i++) {
            // 奇数, 等于右移 + 1
            // 偶数, 等于右移
            counts[i] = counts[i >> 1] + (i & 1);
        }
        for (int count : counts) {
            System.out.print(count + "\t");
        }
        System.out.println();
        return counts;
    }

    /**
     * 动态规划
     *
     * @param num
     * @return
     */
    public static int[] solution3(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        if (num == 1) {
            return new int[]{0, 1};
        }
        int[] counts = new int[num + 1];
        counts[1] = counts[2] = 1;
        for (int i = 3; i < num + 1; i++) {
            // 去掉最高位的 1
            counts[i] = counts[i & (i - 1)] + 1;
        }
        for (int count : counts) {
            System.out.print(count + "\t");
        }
        System.out.println();
        return counts;
    }


    private static int countOf1(int num) {
        int c = 0;
        while (num > 0) {
            num &= (num - 1);
            c++;
        }
        return c;
    }
}
