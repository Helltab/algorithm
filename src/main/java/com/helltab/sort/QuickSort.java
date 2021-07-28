package com.helltab.sort;

import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

import java.util.Arrays;

import static com.helltab.util.MyUtil.printArray;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/1 20:15
 * @desc 快排序
 */
public class QuickSort {
    private static int testVisitTime = 0;

    public static void main(String[] args) {
        int[] test = RandomData.randSetList(10000000, 15000000, 15, 120000000).stream().mapToInt(i -> i).toArray();
        int[] testCopy = Arrays.copyOf(test, test.length);
//        MyUtil.test(s -> {
////            printArray(test);
////            System.out.println();
//            sort(test, 0, test.length - 1);
////            printArray(test);
//            return true;
//        }, "快排序1: " + testVisitTime);
        System.out.println(testVisitTime);
        testVisitTime = 0;
        MyUtil.test(s -> {
//            printArray(testCopy);
//            System.out.println();
            sort1(testCopy, 0, testCopy.length - 1);
//            printArray(testCopy);
            return true;
        }, "快排序2: " + testVisitTime);
        System.out.println(testVisitTime);
        MyUtil.test(s -> {
            int[] test_one = {1};
            printArray(test_one);
            System.out.println();
            sort(test_one, 0, test_one.length - 1);
            printArray(test_one);
            return true;
        }, "快排序");
    }

    /**
     * 单标
     *
     * @param array
     * @return
     */
    public static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIdx = partition(array, start, end);
        sort(array, start, pivotIdx);
        sort(array, pivotIdx + 1, end);
    }

    /**
     * 选择标定数, 将大于标定数的置于右边, 小于标定数的置于左边
     * 选择第一个为标记, 往后遍历:
     * 将标记位挖坑,
     * 遇到比标记小的, 将标记位加一, 并交换两者
     * 最后将标记位的数填入坑中, 将标记填入标记位中
     * 找到比标记小的:
     * 6   8   7    5
     * ^            ^
     * 标记位加一:
     * 6   8   7    5
     * +1  ^        ^
     * 交换两者:
     * 6   5   7    8
     * ^   ^
     * ...遍历找完...
     * 最后交换:
     * 5   6   8    7
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int mark = start;
        for (int i = start + 1; i < end + 1; i++) {
            if (array[i] < pivot) {
                mark++;
                int temp = array[mark];
                array[mark] = array[i];
                array[i] = temp;
                testVisitTime++;
            }

        }
        array[start] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    /**
     * 双标记
     * 从右往左: 找到第一个比标记小的, 与左标记交换, 左标记加一
     * 从左往右: 找到第一个比标记大的, 与右标记交换, 左标记加一
     *
     * @param array
     * @return
     */


    public static void sort1(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = array[l];
        int start = l, end = r;
        { // 找标记的过程
            while (start < end) {
                while (start < end && array[end] >= pivot) {
                    end--;
                    testVisitTime++;
                }
                if (start < end) {
                    array[start] = array[end];
                    start++;
                    testVisitTime++;
                }
                while (start < end && array[start] < pivot) {
                    start++;
                    testVisitTime++;
                }
                if (start < end) {
                    array[end] = array[start];
                    end--;
                    testVisitTime++;
                }
            }
            array[start] = pivot;
        }
        sort1(array, l, start - 1);
        sort1(array, start + 1, r);
    }
}
