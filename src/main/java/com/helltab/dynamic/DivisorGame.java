package com.helltab.dynamic;

/**
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * <p>
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * <p>
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * <p>
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 False。假设两个玩家都以最佳状态参与游戏
 */
public class DivisorGame {
    public static void main(String[] args) {
        System.out.println(3 % 1000000007);
        int initNum = 4;
        System.out.println(solution1(initNum));
        System.out.println(solution2(initNum));
    }

    /**
     * 观察法, 偶数能赢, 奇数必输
     * 奇数: 该题意下, x必定是奇数, 下一个数字一定是偶数, 必输
     * 偶数: 必定让对面每次都选到奇数
     *
     * @param initNum
     * @return
     */
    private static boolean solution1(int initNum) {
        return (initNum & 1) == 0;
    }

    /**
     * 动态规划, 打一维表
     *
     * @param initNum
     * @return
     */
    private static boolean solution2(int initNum) {
        boolean[] dp = new boolean[initNum + 1];
        dp[2] = true;
        for (int i = 3; i <= initNum; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 满足游戏条件 i%j == 0
                // 满足必赢的条件 对手 dp[i-j] == false
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[initNum];
    }
}
