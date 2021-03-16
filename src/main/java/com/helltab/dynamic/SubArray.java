package com.helltab.dynamic;

import com.helltab.util.MyUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 */
public class SubArray {
    public static void main(String[] args) {
        String s = "adg", t = "ahbgdc";
        System.out.println(t.charAt(3));
        MyUtil.test(a -> {
            System.out.println(solution1(s, t));
        });
        MyUtil.test(a -> {
            System.out.println(solution2(s, t));
        });
        MyUtil.test(a -> {
            System.out.println(solution3(s, t));
        });
    }

    /**
     * 暴力破解
     *
     * @param s
     * @param t
     * @return
     */
    private static boolean solution1(String s, String t) {
        if ("".equals(s)) {
            return true;
        }
        List<String> chars = Arrays.asList(t.split(""));
        int pos = -1;
        for (String s1 : s.split("")) {
            if ((pos = chars.indexOf(s1)) != -1) {
                chars = chars.subList(pos + 1, chars.size());
            } else {
                return false;
            }
        }
        return pos >= 0;
    }

    /**
     * 双指针
     * 贪心算法
     *
     * @param s
     * @param t
     * @return
     */
    private static boolean solution2(String s, String t) {
        if ("".equals(s)) {
            return true;
        }
        int a = 0, b = 0;
        while (a < s.length() && b < t.length()) {
            if (s.charAt(a) == t.charAt(b)) {
                if (a == s.length() - 1) {
                    return true;
                }
                a++;
                b++;
            } else {
                b++;
            }
        }
        return false;
    }

    /**
     * 动态规划
     * 1. 贪心算法的缺点是, 每次判断都要对 t 进行查找, 如果 t 很大, 或者进行巨大量的判断, 会损失很多时间
     * 2. 因此可以将 t 中首次出现某字符的位置记录下来
     * --- 反向动态规划, 因为是需要优先判断第一次, 因此递推公式是
     * 存在: f[i][char] = i
     * 不存在: f[i][char] = f[i+1][char]
     * 全字符串都不存在: f[i][char] = f[tLen][char] = tLen
     *
     * @param s
     * @param t
     * @return
     */
    private static boolean solution3(String s, String t) {
        if ("".equals(s)) {
            return true;
        }
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[tLen + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[tLen][i] = tLen;
        }
        // 反向动态规划
        for (int i = tLen - 1; i > 0; i--) {
            for (int j = 0; j < 26; j++) {
                // 决策
                if (t.charAt(i) == j + 'a') {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        int pos = 0;
        for (int i = 0; i < sLen; i++) {
            if (dp[pos][s.charAt(i) - 'a'] == tLen) {
                return false;
            }
            pos = dp[pos][s.charAt(i) - 'a'] + 1;

        }
        return true;
    }

}
