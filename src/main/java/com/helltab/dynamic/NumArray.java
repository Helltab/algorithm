package com.helltab.dynamic;

/**
 * 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * <p>
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 */
public class NumArray {
    int[] result;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            result = new int[nums.length + 1];
            result[1] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                result[i + 1] = result[i] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        try {
            return result[j + 1] - result[i];
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
//        int[][] nums = {{-2, 0, 3, -5, 2, -1}, {0, 2}, {2, 5}, {0, 5}};
//        solution1(nums);
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }

    /**
     * 用一个数组记录所有的总和, 然后用加减法来计算
     */
    public static void solution1(int[][] nums) {
        int len = nums[0].length;
        int len2 = nums.length;
        int[] temp = new int[len + 1];
        int[] result = new int[len2];
        temp[1] = nums[0][0];
        for (int i = 1; i < len; i++) {
            temp[i + 1] = temp[i] + nums[0][i];
            System.out.print(temp[i + 1] + "\t");
        }
        System.out.println();
        for (int i = 1; i < len2; i++) {
            result[i] = temp[nums[i][1] + 1] - temp[nums[i][0]];
            System.out.print(result[i] + "\t");
        }

    }
}
