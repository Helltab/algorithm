package com.helltab.search;

import com.helltab.util.MyUtil;

import java.util.concurrent.atomic.AtomicInteger;

import static com.helltab.util.MyUtil.printArray;
import static com.helltab.util.RandomData.getSortList;
import static com.helltab.util.RandomData.randNum;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/3/31 21:16
 * @desc 二分查找
 */
public class BinarySearch {
    private static int[] sortList = getSortList(2000, 2500, 15, 12000);
//    private static int[] sortList = {31, 56, 67, 72, 74, 110, 119};

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int target = sortList[randNum(0, sortList.length - 1)];
            printArray(sortList);
            System.out.println("\n---------\tfind: " + target);
            AtomicInteger search = new AtomicInteger(-1);
            MyUtil.test(s -> {
                search.set(search(target, sortList));
                return true;
            }, "二分查找");
            System.out.println(search.get());
        }
    }

    public static int search(int target, int[] array) {
        int len = array.length;
        if (len == 0 || target < array[0] || target > array[len - 1]) return -1;
        return searchDetail(array, 0, len - 1, target);
    }

    public static int searchDetail(int[] array, int left, int right, int target) {
        if (left >= right) return -1;
        int mid = (left + right) >> 1;
        if (array[mid] > target) {
            return searchDetail(array, mid >> 1, mid, target);
        }
        if (array[mid] < target) {
            return searchDetail(array, mid + 1, right, target);
        }
        return mid;
    }
}
