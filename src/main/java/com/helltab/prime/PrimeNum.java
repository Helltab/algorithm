package com.helltab.prime;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/10 10:21
 * @desc 找到
 */
public class PrimeNum {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(7));
        System.out.println(isPrime(4));
        System.out.println(numPrimeArrangements(5));
        System.out.println(countPrimeSetBits(10, 15));
        System.out.println(countPrimeSetBits(842, 888));
        System.out.println(countPrimeSetBits2(4, 888));
    }

    /**
     * 埃式筛选法
     *
     * @param n
     * @return
     */
    public static int primeNum(int n) {
        if (n < 2) return 0;
        int num = 0;
        int[] isPrime = new int[n + 1];
        for (int i = 2; i < n + 1; i++) {
            if (isPrime[i] == 0) {
                num++;
            }
            // 当前游标的倍数一定是合数
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = 1;
            }
        }
        return num;
    }

    /**
     * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
     * <p>
     * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
     * <p>
     * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/prime-arrangements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static int numPrimeArrangements(int n) {
        int mod = 1000000007;
        int primeNum = primeNum(n);
        int remianNum = n - primeNum;
        long result = 1;
        while (primeNum > 1) {
            result *= primeNum--;
            if (result > mod) {
                result %= mod;
            }
        }
        while (remianNum > 1) {
            result *= remianNum--;
            if (result > mod) {
                result %= mod;
            }
        }
        return (int) result;
    }

    /**
     * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
     * <p>
     * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param L
     * @param R
     * @return
     */
    public static int countPrimeSetBits(int L, int R) {
        int res = 0;
        for (int i = L; i < R + 1; i++) {
            int temp = i, num = 0;
            for (; temp > 0; num++) {
                // 每次清除右边的 1
                temp &= (temp - 1);
            }
            if (isPrime(num)) {
                res++;
            }
        }
        return res;
    }

    public static int countPrimeSetBits2(int L, int R) {
        int res = 0;
        for (int i = L; i <= R; i++) {
            res += 665772 >> Integer.bitCount(i) & 1;
        }
        return res;
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        int k = 2;
        while (k * k <= n) {
            if (n % k == 0) return false;
            k++;
        }
        return true;
    }
}
