package com.helltab.sort;

import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/1 20:15
 * @desc 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        a = a - b;
        b = a + b;
        a = b - a;
        System.out.println(a + ":" + b);
        b = a ^ b;
        a = a ^ b;
        b = a ^ b;
        System.out.println(a + ":" + b);
//        int[] test = RandomData.randSetList(10, 20, 15, 102).stream().mapToInt(i -> i).toArray();
        int[] test = RandomData.randSetList(10000000, 15000000, 15, 120000000).stream().mapToInt(i -> i).toArray();
        MyUtil.test(s -> {
//            printArray(test);
//            System.out.println();
//            printArray(sort(test));
//            System.out.println();
//            printArray(sort1(test));
            sort(test);
            return true;
        }, "冒泡排序");

    }

    public static int[] sort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (array[i] > array[j]) {
                    array[i] = array[i] ^ array[j];
                    array[j] = array[i] ^ array[j];
                    array[i] = array[i] ^ array[j];
                }
            }
        }
        return array;
    }

    public static int[] sort1(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < len - i; j++) {
                if (array[j - 1] > array[j]) {
                    array[j - 1] = array[j - 1] ^ array[j];
                    array[j] = array[j - 1] ^ array[j];
                    array[j - 1] = array[j - 1] ^ array[j];
                }
            }
        }
        return array;
    }
}
