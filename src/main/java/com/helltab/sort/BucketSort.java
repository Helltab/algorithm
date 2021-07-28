package com.helltab.sort;

import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

import static com.helltab.util.MyUtil.printArray;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/1 20:15
 * @desc 桶排序
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] test = RandomData.randSetList(10, 20, 15, 102).stream().mapToInt(i -> i).toArray();
        MyUtil.test(s -> {
            printArray(test);
            System.out.println();
            sort(test);
            printArray(test);
            return true;
        }, "计数排序");

    }

    /**
     * 计数排序是计算序列中最大和最小的数的差值, 然后建立一个临时数组, 长度为这个差值
     * 遍历数组, 将数字的重复次数存入到这个临时数组中, 最后再根据重复次数来回填
     *
     * @param array
     * @return
     */
    public static void sort(int[] array) {
        int len = array.length;
        if (len < 2) {
            return;
        }
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < len; i++) {
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }
        int[] temp = new int[max - min + 1];
        for (int val : array) {
            temp[val - min]++;
        }
        int tempLen = temp.length;
        int k = 0;
        for (int i = 0; i < tempLen; i++) {
            while (temp[i] > 0) {
                temp[i]--;
                array[k++] = i + min;
            }
        }
    }

    /**
     * 桶排序
     * 将序列值根据大小放到连续的几个桶中
     *
     * @param array
     */
    public static void sort1(int[] array) {

    }

    /**
     * 基数排序
     * 将序列值按高位分桶
     *
     * @param array
     */
    public static void sort2(int[] array) {

    }


}
