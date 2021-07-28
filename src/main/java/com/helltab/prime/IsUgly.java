package com.helltab.prime;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/10 10:43
 * @desc 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsUgly {
    public static void main(String[] args) {
        System.out.println(isUgly(6));
    }

    public static boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        if (n <= 5) {
            return true;
        }
        while (n > 1 && (n & 1) == 0) {
            n >>= 1;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
