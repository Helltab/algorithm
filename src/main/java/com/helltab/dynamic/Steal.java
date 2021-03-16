package com.helltab.dynamic;

import com.helltab.util.MyUtil;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class Steal {
    public static void main(String[] args) {
//        int[] nums = {2, 7, 9, 3, 1};
//        int[] nums = {1, 2, 3, 1};
//        int[] nums = {1};
//        int[] nums = {155, 44, 52, 58, 250, 225, 109, 118, 211, 73, 137, 96, 137, 89, 174, 66, 134, 26, 25, 205, 239, 85, 146, 73, 55, 6, 122, 196, 128, 50, 61, 230, 94, 208, 46, 243, 105, 81, 157, 89, 205, 78, 249, 203, 238, 239, 217, 212, 241, 242, 157, 79, 133, 66, 36, 165};
        int[] nums = {226, 174, 214, 16, 218, 48, 153, 131, 128, 17, 157, 142, 88, 43, 37, 157, 43, 221, 191, 68, 206, 23, 225, 82, 54, 118, 111, 46, 80, 49, 245, 63, 25, 194, 72, 80, 143, 55, 209, 18, 55, 122, 65, 66, 177, 101, 63, 201, 172, 130, 103, 225, 142, 46, 86, 185, 62, 138, 212, 192, 125, 77, 223, 188, 99, 228, 90, 25, 193, 211, 84, 239, 119, 234, 85, 83, 123, 120, 131, 203, 219, 10, 82, 35, 120, 180, 249, 106, 37, 169, 225, 54, 103, 55, 166, 124};
//        int[] nums = {2, 7, 9};
//        MyUtil.test(a -> {
//            solution1(nums);
//        });
        MyUtil.test(a -> {
            solution3(nums);
        });
//        MyUtil.test(a -> {
//            solution2(nums);
//        });
        MyUtil.test(a -> {
            solution4(nums);
        });
    }

    /**
     * 暴力法,
     * 用树来存储,
     * 内存占用大, 并且慢
     */
    private static void solution1(int[] nums) {
        MyNode tree = new MyNode();
        recursion(nums, tree, 0);
        System.out.println(MyNode.getMax(tree));
    }

    /**
     * 暴力法,
     * 用树来存储,
     * 内存占用大, 并且慢
     */
    private static void solution2(int[] nums) {
        Set<Integer> result = new HashSet<>();
        recursion2(nums, 0, 0, 0, result);
        System.out.println(result.stream().max(Comparator.comparingInt(a -> a)).get());
    }

    /**
     * 动态规划
     * 思考, 如果选 a(i), 最大就是 f(i-2) + a(i)
     * 如果不选 a(i), 最大就是 f(i-1)
     * f(i) = max(f(i-2) + a(i), f(i-1))
     *
     * @param nums
     */
    private static void solution3(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            System.out.println(0);
        }
        if (len == 1) {
            System.out.println(nums[0]);
        }
        if (len == 2) {
            System.out.println(Math.max(nums[0], nums[1]));
        }
        int pre = nums[0], now = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            int temp1 = now;
            now = Math.max(pre + nums[i], now);
            pre = temp1;
        }

        System.out.println(now);
    }

    /**
     * 每次选择保存在奇偶不同的结果里, 保证了结果绝对不是相邻的
     * 选择: 保存当前奇偶性最大值 + a(i)
     * 不选择: 保存相对奇偶性的最大值
     *
     * @param nums
     */
    private static void solution4(int nums[]) {
        int sumOdd = 0; // 保存每次选择奇数的结果
        int sumEven = 0; // 保存每次选择偶数的结果
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                sumEven += nums[i];
                sumEven = Math.max(sumOdd, sumEven);
            } else {
                sumOdd += nums[i];
                sumOdd = Math.max(sumOdd, sumEven);
            }
        }
        System.out.println(sumEven);
        System.out.println(sumOdd);
    }

    private static void recursion2(int nums[], int cur, int maxL, int maxR, Set<Integer> result) {
        if (cur >= nums.length) {
            result.add(Math.max(maxL, maxR));
            return;
        }
        maxL += nums[cur];
        if (cur + 1 < nums.length) {
            maxR += nums[cur + 1];
        }
        recursion2(nums, cur + 2, maxL, maxR, result);
        recursion2(nums, cur + 3, maxL, maxR, result);
    }

    /**
     * 暴力法-递归建立树
     *
     * @param nums
     * @param node
     * @param cur
     */
    private static void recursion(int nums[], MyNode node, int cur) {
        if (cur >= nums.length) {
            return;
        }
        node.lNode = new MyNode();
        node.rNode = new MyNode();
        node.lNode.num = nums[cur];
        if (cur + 1 < nums.length) {
            node.rNode.num = nums[cur + 1];
        }
        recursion(nums, node.lNode, cur + 2);
        recursion(nums, node.rNode, cur + 3);
    }


    /**
     * 树形结构
     */
    private static class MyNode {
        MyNode lNode;
        MyNode rNode;
        int num;

        /**
         * 暴力法, 左序遍历得出结论
         *
         * @param node
         * @return
         */
        static int getMax(MyNode node) {
            if (node == null) {
                return 0;
            }
            int maxL = node.num + getMax(node.lNode);
            int maxR = node.num + getMax(node.rNode);
            return Math.max(maxL, maxR);
        }
    }

}
