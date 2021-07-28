package com.helltab.sort;

import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

import java.util.Arrays;

import static com.helltab.util.MyUtil.printArray;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/1 20:15
 * @desc 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] test = RandomData.randSetList(10, 20, 15, 102).stream().mapToInt(i -> i).toArray();
        int[] test1 = Arrays.copyOf(test, test.length);
        MyUtil.test(s -> {
            printArray(test);
            System.out.println();
            printArray(sort(test));
            return true;
        }, "插入排序");
        MyUtil.test(s -> {
            printArray(test1);
            System.out.println();
            printArray(shellSort(test1));
            return true;
        }, "希尔插入排序");

    }

    /**
     * 插入排序
     * 选择游标的下一个数为插入数, 向前回溯找到第一个比插入数大(小)的即可
     * <p>
     * O(n)
     * T(n) = O(n2)
     * O(n2)
     *
     * @param array
     * @return
     */
    public static int[] sort(int[] array) {
        int len = array.length;
        int cur;
        for (int i = 0; i < len - 1; i++) {
            int preIndex = i;
            // 找到游标的下一个作为插入数
            cur = array[preIndex + 1];
            // 当回溯到最前或者遇到第一个比插入数大(小)的数, 即可确定位置
            while (preIndex >= 0 && array[preIndex] > cur) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            // 插入到回溯游标的下一个位置(刚好挪出来的), 最好就是不动位置, 最差就是每次都到第一个位置
            array[preIndex + 1] = cur;
        }
        return array;
    }

    /**
     * 插入排序-希尔排序: 非稳定排序, 值相同的元素可能会被换位置
     * 每次都缩减分组, 达到预处理
     * 通项公式 2^k
     * 另外:
     * Hibbard 1，3，7，15......
     * 通项公式 2^k-1
     * Sedgewick 1, 5, 19, 41, 109......
     * 通项公式 9*4^k - 9*2^k + 1 或者 4^k - 3*2^k + 1
     *
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;
        int cur, gap = len >> 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int preIndex = i - gap;
                // 找到游标 + gap 的值作为插入数
                cur = array[preIndex + gap];
                while (preIndex >= 0 && array[preIndex] > cur) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = cur;
            }
            gap >>= 2;
        }
        return array;
    }
}
