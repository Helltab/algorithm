package com.helltab.DSA.vector;

import java.util.Arrays;

public class MergeSort {
    final static int[] org = {1, 5, 6, 64, 74, 2, 54, 87, 100};

    public static void main(String[] args) {
        merge(0, 5, 6);
    }

    public static void merge(int lo, int mi, int hi) {
        int[] A = new int[hi - lo + 1];
        for (int i = lo; i < hi + 1; A[i] = org[i++]) ;
        int lb = mi - lo;
        int lc = hi - mi + 1;
        int[] B = new int[lb];
        for (int i = 0; i < lb; B[i] = A[i++]) ;
        int[] C = new int[lc];
        for (int i = mi; i < hi + 1; C[i - mi] = A[i++]) ;
        for (int i = 0, j = 0, k = 0; (j < lb || k < lc); ) {
            if ((j < lb) && (k >= lc || B[j] <= C[k])) {
                A[i++] = B[j++];
            }
            if ((k < lc) && (j >= lb || C[k] < B[j])) {
                A[i++] = C[k++];
            }
        }
        Arrays.stream(A).forEach(System.out::println);
    }
}
