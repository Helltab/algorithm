package com.helltab.array;

import com.helltab.util.MyUtil;

public class RemoveNum {
    public static void main(String[] args) {
//        int[] arr = RandomData.randArray(0, 13, 100, 150);
        int[] arr = {3, 2, 2, 3};
        MyUtil.test(res -> {
            MyUtil.printArray(arr);
            System.out.println(removeElement(arr, arr[0]));
            MyUtil.printArray(arr);
            return true;
        }, "原位移除");
    }

    public static int removeElement(int[] nums, int val) {
        int ans = nums.length;
        if (ans == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                ans--;
                nums[left] = nums[right--];
            } else {
                left++;
            }
        }
        return ans;
    }
}
