package com.ivyzh.algorithm;

/**
 * 二分查找-非递归方式
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = search(18, arr, 0, arr.length);
        System.out.println("递归方式查找：");
        System.out.println(index);
        System.out.println("非递归方式查找：");
        int index2 = search2(8, arr);
        System.out.println(index2);
    }

    // 递归方式
    public static int search(int value, int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;

        if (value < arr[middle]) {
            right = middle - 1;
            return search(value, arr, left, right);
        } else if (value > arr[middle]) {
            left = middle + 1;
            return search(value, arr, left, right);
        } else {
            return middle;
        }
    }

    // 非递归方式
    public static int search2(int value, int[] arr) {
        int left = 0;
        int right = arr.length;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == value) {
                return middle;
            }
            if (value < arr[middle]) {
                right = middle - 1;
            } else if (value > arr[middle]) {
                left = middle + 1;
            }
        }
        return -1;
    }
}
