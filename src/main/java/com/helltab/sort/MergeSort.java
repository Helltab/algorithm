package com.helltab.sort;

import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

import static com.helltab.util.MyUtil.printArray;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/1 20:15
 * @desc 归并排序
 */
public class MergeSort {
    private static int testVisitTime = 0;

    public static void main(String[] args) {
        int[] test = RandomData.randSetList(10, 15, 15, 120).stream().mapToInt(i -> i).toArray();
        MyUtil.test(s -> {
            printArray(test);
            System.out.println();
//            sort(test);
            sort_iterate(test);
            printArray(test);
            return true;
        }, "快排序1: " + testVisitTime);
    }

    /**
     * @param array
     * @return
     */
    public static void sort(int[] array) {
        int[] temp = new int[array.length];
        sort(array, 0, array.length - 1, temp);
    }

    /**
     * 分:
     * 递归分解, 类似完全二叉树
     * * 1  2   3   4   5
     *
     * @param array
     * @return
     */
    public static void sort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) >> 1;
            sort(array, left, mid, temp);
            sort(array, mid + 1, right, temp);
            System.out.println("left=" + left + " mid=" + mid + "   right=" + right);
            printArray(array);
            System.out.println();
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * 治理:
     * 如:
     * *  array     ->  temp
     * *  5678 1234 ->
     * *                1<5, 2<5, 3<5, 3<5, 6, 7, 8
     * *            ->  1234 5678
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, k = 0;
        // 治理过程
        while (i <= mid && j <= right) {
            temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
        }
        { // 剩余的全部复制到 temp 中
            while (i <= mid) {
                temp[k++] = array[i++];
            }
            while (j <= right) {
                temp[k++] = array[j++];
            }
        }

        // 全部复写回来
        k = 0;
        while (left <= right) {
            array[left++] = temp[k++];
        }
    }

    public static void visit(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            visit(array, left, mid);
            visit(array, mid + 1, right);
        } else {
            System.out.println(array[left] + ":" + left);
        }
    }

    /**
     * 迭代版本
     * <p>
     * * 9 8 6 5 4 3 2 1
     * * ^ ^
     * *     ^ ^
     * *         ^ ^
     * *             ^ ^
     * * 8 9 5 6 3 4 1 2
     * * ^ ^ ^ ^
     * *         ^ ^ ^ ^
     * * 5 6 8 9 1 2 3 4
     * * 1 2 3 4 5 6 8 9
     */
    public static void sort_iterate(int[] array) {
        int len = array.length;
        int[] temp = new int[len];
        // 遍历进度为 2 4 8 <(len << 1)
        // len << 1 是为了最后一次的统一治理
        for (int i = 2; i < len << 1; i <<= 1) {
            for (int j = 0; j < len; j += i) {
                int left = j;
                int right = Math.min(len - 1, left + i - 1);
                int mid = Math.min(len - 1, left + (i >> 1) - 1);
                merge(array, left, mid, right, temp);
            }
        }
    }
}


