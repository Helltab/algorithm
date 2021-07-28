package com.helltab.util;

import com.helltab.sort.AllSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/3/31 21:20
 * @desc 随机数生成
 */
public class RandomData {
    public static void main(String[] args) {
        System.out.println(randList(10, 12, 3, 4));
        System.out.println(randSet(10, 12, 3, 60));
        System.out.println(randSetList(10, 12, 3, 15));
    }

    public static String randName() {
        return null;
    }

    public static int randNum(int min, int max) {
        return (int) (Math.random() * (max - min) + (randBool() ? 1 : 0)) + min;
    }

    public static boolean randBool() {
        return Math.random() < 0.5;
    }

    /**
     * 获取有重复的列表
     *
     * @param minLen
     * @param maxLen
     * @param min
     * @param max
     * @return
     */
    public static List<Integer> randList(int minLen, int maxLen, int min, int max) {
        assert maxLen >= minLen;
        assert max >= min;
        int len = randNum(minLen, maxLen - minLen);
        List<Integer> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            list.add(randNum(min, max));
        }
        return list;
    }

    public static int[] randArray(int minLen, int maxLen, int min, int max) {
        assert maxLen >= minLen;
        assert max >= min;
        int len = randNum(minLen, maxLen - minLen);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = randNum(min, max);
        }
        return arr;
    }

    /**
     * 获取集合
     *
     * @param minLen
     * @param maxLen
     * @param min
     * @param max
     * @return
     */
    public static Set<Integer> randSet(int minLen, int maxLen, int min, int max) {
        assert maxLen >= minLen;
        assert max >= min;
        assert max - min - maxLen >= 0;
        int len = randNum(minLen, maxLen - minLen);
        Set<Integer> set = new HashSet<>(len);
        while (set.size() < len) {
            set.add(randNum(min, max));
        }
        return set;
    }

    /**
     * 返回各异的列表
     *
     * @param minLen
     * @param maxLen
     * @param min
     * @param max
     * @return
     */
    public static List<Integer> randSetList(int minLen, int maxLen, int min, int max) {
        return new ArrayList<>(randSet(minLen, maxLen, min, max));
    }

    /**
     * 获取排好序的列表
     *
     * @param minLen
     * @param maxLen
     * @param min
     * @param max
     * @return
     */
    public static int[] getSortList(int minLen, int maxLen, int min, int max) {
        int[] array = randSetList(minLen, maxLen, min, max).stream().mapToInt(i -> i).toArray();
        AllSort.mergeSort_1(array);
        return array;
    }

}
