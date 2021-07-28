package com.helltab.sort;


import com.helltab.util.MyUtil;
import com.helltab.util.RandomData;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.helltab.util.MyUtil.printArray;
import static com.helltab.util.MyUtil.switchArrayData;

/**
 * @author helltab
 * @version 1.0
 * @date 2021/4/4 0:25
 * @desc 默写经典算法
 * <p>
 * 冒泡排序: 将最大的数通过依次比较交换到最后的位置, 过程就像是在冒泡
 * 选择排序: 遍历找出最小的游标, 放到最前面, 依次进行
 * 插入排序: 对当前值, 向后回溯, 找到合适的位置进行插入, 过程中要把大于当前的值像拨算盘一样依次往前拨
 * 希尔排序: 特殊的插入排序, 因为整体有序度会极大影响插入排序的效率, 因此可以对序列进行一些预处理
 * *       希尔预处理, 将序列分为 2 4 8 ... 2^n 组进行插入排序预处理
 * *       奇数预处理, 将序列分为 1 3 5 ... 2n-1 组进行插入排序预处理
 * 快排序: 分治思想, 先用二分法分割, 然后通过标记把序列治理为 [小于标记, 标记, 大于标记] 三部分
 * *      单标: 标记从最左的数开始, 依次把小于标记的数交换到左边(不能交换最左边的数, 即标记),
 * *           最后把最左的数和最后一个找到的数交换(把标记交换到中间)
 * *      双标:
 * *           左标记从最左开始, 右标记从最右边开始, 标记重合结束;
 * *           标记数选择左标记位置数, 先右后左, 左右交替进行判断, 标记重合将标记数填入标记中:
 * *             如果右边的数小于等于标记, 则右标记左移直到小于标记或者标记重合,
 * *                 如果小于标记, 则需要与左标记交换, 左标记右移一次, 交换给左边;
 * *             如果左边的数小于等于标记, 则左标记右移直到小于标记或者标记重合,
 * *                 如果大于标记, 则需要与右标记交换, 右标记左移一次, 交换给右边;
 * 归并排序: 分治思想, 先用二分法分割, 然后把序列治理为 [左边 中间 右边] 三部分, 类似快排序, 只是治理方法有区别
 * *        找到中间位置, 用一个临时数组来排序, 左游标从最左边开始, 右游标从中间 + 1 的位置开始
 * *        比较左右游标的值, 更小的放到临时数组中, 直到有一边被处理完, 将另一边的数全部存入临时数组
 * *        因为是像一颗完全二叉树的分割(分割为一个数字或者两个数字), 能保证序列左边和右边部分都是有序的
 */
public class AllSort {
    private static int[] orgArray = RandomData.randSetList(10, 15, 15, 120).stream().mapToInt(i -> i).toArray();
//    private static int[] orgArray = {106, 90, 83, 51, 91, 54};

    public static void main(String[] args) {
        test(AllSort::bubbleSort, "冒泡排序");
        test(AllSort::selectSort, "选择排序");
        test(AllSort::insertSort, "插入排序");
        test(AllSort::shellSort, "希尔排序");
        test(AllSort::shellSort_1, "希尔排序(奇数)");
        test(AllSort::quickSort, "快排序(单标)");
        test(AllSort::quickSort_1, "快排序(双标)");
        test(AllSort::mergeSort, "归并排序(递归)");
        test(AllSort::mergeSort_1, "归并排序(迭代)");
    }


    private static void test(Consumer<int[]> sortMethod, String title) {
        int[] test = Arrays.copyOf(orgArray, orgArray.length);
        MyUtil.test(s -> {
            printArray(test);
            System.out.println();
            sortMethod.accept(test);
            printArray(test);
            return true;
        }, "经典排序算法: " + title);
    }

    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int len = array.length;
        if (len < 2) {
            return;
        }
        for (int i = 0; i < len; i++) {
            // 依次比较, 慢慢增大(变小), 直到最后, 就像冒泡一样
            for (int j = 1; j < len - i; j++) {
                if (array[j - 1] > array[j]) {
                    switchArrayData(array, j - 1, j);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] array) {
        int len = array.length;
        if (len < 2) return;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            // 每次选择大于游标中最小的数
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 与游标比较交换, 就把最小的叠到左边了
            if (array[minIndex] < array[i]) {
                switchArrayData(array, minIndex, i);
            }
        }
    }

    /**
     * 插入排序
     * 选好标记, 回溯插入
     */
    public static void insertSort(int[] array) {
        int len = array.length;
        if (len < 2) return;
        for (int i = 0; i < len - 1; i++) {
            int temp = array[i + 1];
            int cur = i;
            // 找到 temp 的坑
            while (cur >= 0 && array[cur] > temp) {
                array[cur + 1] = array[cur];
                cur--;
            }
            // 填坑
            array[cur + 1] = temp;
        }
    }

    /**
     * 希尔排序
     * 预处理 2 4 8 2^n
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int len = array.length;
        if (len < 2) return;
        int gap = len >> 1;
        while (gap != 0) {
            for (int i = 0; i < len - gap; i += gap) {
                int temp = array[i + gap];
                int cur = i;
                while (cur >= 0 && array[cur] > temp) {
                    array[cur + gap] = array[cur];
                    cur -= gap;
                }
                array[cur + gap] = temp;
            }
            gap >>= 1;
        }
    }

    /**
     * 希尔排序
     * 预处理 1 3 5  n + 2
     *
     * @param array
     */
    public static void shellSort_1(int[] array) {
        int len = array.length;
        if (len < 2) return;
        // 强行变为奇数 - 2
        int gap = (len | 1) - 2;
        while (gap > 0) {
            for (int i = 0; i < len - gap; i += gap) {
                int temp = array[i + gap];
                int cur = i;
                while (cur >= 0 && array[cur] > temp) {
                    array[cur + gap] = array[cur];
                    cur -= gap;
                }
                array[cur + gap] = temp;
            }
            gap -= 2;
        }
    }

    /**
     * 快排序
     *
     * @param array
     */
    public static void quickSort(int[] array) {
        int len = array.length;
        if (len < 2) return;
        quickDetail(array, 0, len);
    }

    /**
     * 通过递归来分治
     * 单标: 标记递增, 依次将小于标准的移到左边, 最后将标记和最左边的数交换
     *
     * @param array
     * @param left
     * @param right
     */
    private static void quickDetail(int[] array, int left, int right) {
        if (left < right) {
            int mark = left;
            for (int i = left; i < right; i++) {
                if (array[i] < array[left]) {
                    // 把最左边的数(pivot)留出来
                    mark++;
                    switchArrayData(array, i, mark);
                }
            }
            // 将标记与最初选定的标记位置交换, 即把 pivot 换到小数和大数中间
            switchArrayData(array, mark, left);
            quickDetail(array, left, mark);
            quickDetail(array, mark + 1, right);
        }
    }

    /**
     * 快排序-双标
     *
     * @param array
     */
    public static void quickSort_1(int[] array) {
        int len = array.length;
        if (len < 2) return;
        quickDetail_1(array, 0, len - 1);
    }

    /**
     * 双标
     *
     * @param array
     * @param left
     * @param right
     */
    private static void quickDetail_1(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        // 最左边挖坑
        int pivot = array[left];
        int start = left, end = right;
        while (start < end) {
            while (start < end && array[end] >= pivot) {
                end--;
            }
            if (start < end) {
                // 右边挖坑填左边
                array[start++] = array[end];
            }
            while (start < end && array[start] <= pivot) {
                start++;
            }
            if (start < end) {
                // 左边挖坑填右边
                array[end--] = array[start];
            }
        }
        // 最后将 pivot 填入 start == end 的坑里面
        array[start] = pivot;
        // 跳过标记
        quickDetail_1(array, left, start - 1);
        quickDetail_1(array, start + 1, right);
    }

    /**
     * 归并排序
     * 二叉树的方式分治
     *
     * @param array
     */
    public static void mergeSort(int[] array) {
        int len = array.length;
        if (len < 2) return;
        int[] temp = new int[len];
        mergeDetail(array, 0, len - 1, temp);
    }

    private static void mergeDetail(int[] array, int left, int right, int[] temp) {
        if (left >= right) return;
        int mid = (left + right) >> 1;
        // 分
        mergeDetail(array, left, mid, temp);
        mergeDetail(array, mid + 1, right, temp);
        // 治
        merge(array, temp, left, right, mid);
    }

    /**
     * 归并排序(迭代)
     *
     * @param array
     */
    public static void mergeSort_1(int[] array) {
        int len = array.length;
        if (len < 2) return;
        int[] temp = new int[len];
        // 2 4 8 ...
        // 划分
        for (int i = 2; i < len << 1; i <<= 1) {
            for (int j = 0; j < len; j += i) {
                // 需要将迭代游标减一之后做治理
                int key = i - 1;
                int len_1 = Math.min(j + key, len - 1);
                int mid = Math.min(j + (key >> 1), len - 1);
                // 治理
                merge(array, temp, j, len_1, mid);
            }
        }
    }

    /**
     * 归并排序治理
     *
     * @param array
     * @param temp
     * @param j
     * @param len_1
     * @param mid
     */
    private static void merge(int[] array, int[] temp, int j, int len_1, int mid) {
        int left = j, right = mid + 1, t = j;
        while (left <= mid && right <= len_1) {
            temp[t++] = array[left] < array[right] ? array[left++] : array[right++];
        }
        while (left <= mid) {
            temp[t++] = array[left++];
        }
        while (right <= len_1) {
            temp[t++] = array[right++];
        }
        System.arraycopy(temp, j, array, j, len_1 + 1 - j);
    }
}
