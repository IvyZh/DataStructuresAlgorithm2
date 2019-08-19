package com.ivyzh.datastructures.search;

/**
 * 二分查找-递归
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234, 7888};
        int index = find(arr, 0, arr.length - 1, 10);
        System.out.println("index = " + index);

        for (int i = 0; i < arr.length; i++) {
            int index2 = find(arr, 0, arr.length, arr[i]);
            System.out.println(arr[i] + " index = " + index2);
        }

    }

    private static int find(int[] arr, int left, int right, int num) {

        if (left > right) {
            return -1;
        }

        int middle = (right + left) / 2;

        if (num < arr[middle]) {
            return find(arr, left, middle - 1, num);
        } else if (num > arr[middle]) {
            return find(arr, middle + 1, right, num);
        } else {
            return middle;
        }

    }
}
