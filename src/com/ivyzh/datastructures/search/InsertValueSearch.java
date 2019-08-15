package com.ivyzh.datastructures.search;

/**
 * 插值查找
 */
public class InsertValueSearch {
    static int findCount = 0;

    public static void main(String[] args) {
        //int[] arr = {1, 8, 10, 89, 1000, 1234, 7888};
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int index = find(arr, 0, arr.length - 1, 1);
        System.out.println("index = " + index);
    }

    private static int find(int[] arr, int left, int right, int num) {
        if (left > right) {
            return -1;
        }
        findCount++;
        System.out.println("find..." + findCount);
//        int mid = (right + left) / 2;//二分查找
        int mid = left + (right - left) * (num - arr[left]) / (arr[right] - arr[left]);//插值查找mid计算方法
        if (num < arr[mid]) {
            return find(arr, left, mid - 1, num);
        } else if (num > arr[mid]) {
            return find(arr, mid + 1, right, num);
        } else {
            return mid;
        }
    }
}
