package com.helltab.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * Topic 找零钱问题
 *
 * @author helltab
 * @version 1.0
 * @date 2021/5/5 23:07
 */
public class Change {
    public static void main(String[] args) {
        List<Integer> coins = Arrays.asList(1, 2, 5);
        int solution = solution(coins, 11);
        System.out.println(solution);
    }

    public static int solution(List<Integer> coins, int money) {
        if (money == 0) return 0;
        if (money < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            int subProblem = solution(coins, money - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
