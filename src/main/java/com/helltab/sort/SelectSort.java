package com.helltab.sort;

import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

import static com.helltab.util.MyUtil.printArray;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/1 20:15
 * @desc 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int a = 97;
        int b = 97;
        System.out.println(a + ":" + b);
        int[] test = RandomData.randSetList(10, 20, 15, 102).stream().mapToInt(i -> i).toArray();
        MyUtil.test(s -> {
            printArray(test);
            System.out.println();
            printArray(sort(test));
            return true;
        }, "选择排序");

    }

    /**
     * 选择排序
     * 每次都选择最小的一个数和当前游标数相替换
     * T(n) = O(n2)
     *
     * @param array
     * @return
     */
    public static int[] sort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                array[i] = array[i] ^ array[minIndex];
                array[minIndex] = array[i] ^ array[minIndex];
                array[i] = array[i] ^ array[minIndex];
            }
        }
        return array;
    }
}
