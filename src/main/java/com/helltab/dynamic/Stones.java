package com.helltab.dynamic;

/**
 * 877. 石子游戏
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * <p>
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * <p>
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 * <p>
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 * <p>
 */
public class Stones {
    public static void main(String[] args) {
//        int[] piles = {5, 3, 4, 5};
//        int[] piles = {3, 2, 10, 4};
//        int[] piles = {3, 100, 4};
//        int[] piles = {8, 9, 7, 6, 7, 6};
        int[] piles = {6, 3, 9, 9, 3, 8, 8, 7};
        System.out.println(solution1(piles));
    }

    private static boolean solution1(int[] piles) {
        int len = piles.length;
        if (len == 0) {
            return false;
        }
        if (len < 3) {
            return true;
        }
        int pre = 0, next = 0;
        int pL = 0, pR = len - 1;
        int turn = 0;
        boolean flag;
        while (pL <= pR) {
            if (pR - pL < 3) {
                flag = piles[pL] > piles[pR];
            } else {

                flag = piles[pL] + piles[pR - 1] == piles[pR] + piles[pL + 1]
                               ? piles[pL] > piles[pR]
                               : piles[pL] + piles[pR - 1] > piles[pR] + piles[pL + 1];
            }
            if (flag) {
                if ((turn & 1) == 0) {
                    pre += piles[pL];
                } else {
                    next += piles[pL];
                }
                pL++;
            } else {
                if ((turn & 1) == 0) {
                    pre += piles[pR];
                } else {
                    next += piles[pR];
                }
                pR--;
            }
            turn++;
        }
        return pre > next;
    }
}
