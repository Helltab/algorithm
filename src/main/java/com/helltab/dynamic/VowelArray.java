package com.helltab.dynamic;

import com.helltab.util.MyUtil;

/**
 * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
 * <p>
 * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前
 * <p>
 * 输入：n = 1
 * 输出：5
 * 解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
 */
public class VowelArray {
    public static void main(String[] args) {
        int n = 1;
        MyUtil.test(a -> {
            System.out.println(solution1(n));
            System.out.println(solution1(2));
            System.out.println(solution1(3));
            System.out.println(solution1(4));
            System.out.println(solution1(5));
        });
        MyUtil.test(a -> {
            System.out.println(solution2(n));
            System.out.println(solution2(2));
            System.out.println(solution2(3));
            System.out.println(solution2(4));
            System.out.println(solution2(5));
        });
        MyUtil.test(a -> {
            System.out.println(solution3(n));
            System.out.println(solution3(2));
            System.out.println(solution3(3));
            System.out.println(solution3(4));
            System.out.println(solution3(5));
        });
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int solution1(int n) {
        int[][] dp = new int[n + 1][5];
        // n 为 1 的时候, 构成字串的选择为 [a,e,i,o,u]
        // 每个位置有 [1,1,1,1,1] 种选择
        // 用 dp[i] 来记录每个位置的和 [1+1+1+1+1, 1+1+1+1, 1+1+1, 1+1, 1]
        dp[1] = new int[]{5, 4, 3, 2, 1};
        for (int i = 2; i < n + 1; i++) {
            dp[i][4] = dp[i - 1][4];
            dp[i][3] = dp[i - 1][3] + dp[i][4];
            dp[i][2] = dp[i - 1][2] + dp[i][3];
            dp[i][1] = dp[i - 1][1] + dp[i][2];
            dp[i][0] = dp[i - 1][0] + dp[i][1];
        }
        return dp[n][0];
    }

    /**
     * 动态规划
     * 优化
     *
     * @param n
     * @return
     */
    public static int solution2(int n) {
        int[] dp = {5, 4, 3, 2, 1};
        // n 为 1 的时候, 构成字串的选择为 [a,e,i,o,u]
        // 每个位置有 [1,1,1,1,1] 种选择
        // 用 dp[i] 来记录每个位置的和 [1+1+1+1+1, 1+1+1+1, 1+1+1, 1+1, 1]
        for (int i = 2; i < n + 1; i++) {
            int t3 = dp[3] + dp[4];
            int t2 = dp[2] + t3;
            int t1 = dp[1] + t2;
            int t0 = dp[0] + t1;
            dp[0] = t0;
            dp[1] = t1;
            dp[2] = t2;
            dp[3] = t3;
        }
        return dp[0];
    }

    /**
     * 数学解法:
     * 1. 忽略字符串的顺序, 因为是按字典顺序, 所以可以认为顺序排好了
     * 2. 把字符串当做小球, 一个字符的是一个小球, 两个字符的是两个小球
     * 3. 把小球装进代表元音的 5 个盒子, 一共有多少种装法, 就是这道题的答案
     * 因为盒子可以为空, 比如 aaaa 代表四个小球都装在 a 盒子里面, aaab 代表有一个在 b...
     * 求 m 个盒子放 n 个小球的方法
     * -- 隔板法
     * 1. 考虑 n>= m, 盒子不能为空的隔板法, 就是从 n-1 个空隙中插入 m-1 个板子, 将其分割为 m 个部分
     * 2. 考虑一般情况, 应该将盒子里面都放上一个球, 那么总的球就是 n + m, comb(n+m-1, m-1)
     * 3. 再将每个盒子里面都拿出一个球, 放法种数不变
     * 因此:
     * 盒子可以为空 comb(n+m-1, m-1)
     * 盒子不能为空 comb(n-1, m-1)
     *
     * @param n
     * @return
     */
    public static int solution3(int n) {
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }
}
