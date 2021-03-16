package com.helltab.dynamic;

import com.helltab.util.MyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 */
public class Stairs {
    public static void main(String[] args) {
//        MyUtil.test(t -> {
//            System.out.println(solution1(45));
//        });
        MyUtil.test(t -> {
            System.out.println(solution2(45, null));
        });
        MyUtil.test(t -> {
            System.out.println(solution2_1(45, null));
        });
        MyUtil.test(t -> {
            System.out.println(solution4(45));
        });
        MyUtil.test(t -> {
            System.out.println(solution5(45));
        });
    }

    /**
     * 动态规划
     * 假设爬 5 楼
     * 等于 f(4) + f(3)
     * 因此 f(n) = f(n-1) + f(n-2)
     * 这方法会超时
     *
     * @param n
     */
    public static long solution1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return solution1(n - 1) + solution1(n - 2);
    }

    /**
     * 超时
     *
     * @param n
     * @return
     */
    public static int solution2(int n, int[] arr) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (arr == null) {
            arr = new int[n];
            for (int i : arr) {
                arr[i] = 0;
            }
            arr[1] = 1;
            arr[2] = 2;
        }
        int n_1, n_2;
        if (arr[n - 1] == 0) {
            arr[n - 1] = n_1 = solution2(n - 1, arr);
            if (arr[n - 2] == 0) {
                arr[n - 2] = n_2 = solution2(n - 2, arr);
            } else {
                n_2 = arr[n - 2];
            }
        } else {
            n_1 = arr[n - 1];
            n_2 = arr[n - 2];
        }
        return n_1 + n_2;
    }

    /**
     * 记忆化递归
     *
     * @param n
     * @return
     */
    public static int solution2_1(int n, int[] arr) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (arr == null) {
            arr = new int[n + 1];
            for (int i : arr) {
                arr[i] = 0;
            }
            arr[1] = 1;
            arr[2] = 2;
        }
        if (arr[n] == 0) {
            arr[n] = solution2_1(n - 1, arr) + solution2_1(n - 2, arr);
        }
        return arr[n];
    }

    /**
     * 比数组还是慢了不少
     *
     * @param n
     * @return
     */
    public static int solution3(int n, Map<Integer, Integer> map) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (map == null) {
            map = new HashMap<>();
            map.put(1, 1);
            map.put(2, 2);
        }
        int n_1, n_2;
        if (map.containsKey(n - 1)) {
            n_1 = map.get(n - 1);
            n_2 = map.get(n - 2);
        } else {
            map.put(n - 1, n_1 = solution3(n - 1, map));
            if (map.containsKey(n - 2)) {
                n_2 = map.get(n - 2);
            } else {
                map.put(n - 2, n_2 = solution3(n - 2, map));
            }
        }
        return n_1 + n_2;
    }

    /**
     * 斐波那契解法
     *
     * @param n
     * @return
     */
    public static int solution4(int n) {
        if (n < 4)
            return n;
        int[] nn = new int[n - 1];
        nn[0] = 2;
        nn[1] = 3;
        for (int i = 2; i < n - 1; i++) {
            nn[i] = nn[i - 1] + nn[i - 2];
        }
        return nn[n - 2];
    }

    /**
     * 斐波那契解法2
     *
     * @param n
     * @return
     */
    public static int solution5(int n) {
        if (n < 4)
            return n;
        int pre = 2, now = 3, next = 5;
        for (int i = 5; i < n + 1; i++) {
            if ((i & 1) == 1) {
                pre = next;
            } else {
                now = next;
            }
            next = pre + now;
        }
        return next;
    }
}
