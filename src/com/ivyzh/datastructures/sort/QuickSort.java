package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序
 * 案例：
 * 快速排序（QuickSort）是对冒泡排序的一种改进。
 * 基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 800W个数据
 * 排序前的时间：18:21:32
 * 排序后的时间：18:21:33
 */
public class QuickSort {
    public static void main(String[] args) {
        System.out.println("~~QuickSort~~");
////        int[] arr = {-9, 78, 0, 23, -567, 70};
//        int[] arr = {6, 17, 8, 8, 14};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort(arr, 0, arr.length - 1);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 演示性能测试
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date1Str = format.format(start);
        System.out.println("排序前的时间：" + date1Str);

        int num = 8000000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * num);
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
//        System.out.println("排序后：" + Arrays.toString(arr));
        Date end = new Date();
        String date2Str = format.format(end);
        System.out.println("排序后的时间：" + date2Str);

        // 检验排序数组正确性
        boolean isOk = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                isOk = false;
                break;
            }
        }
        System.out.println("排序验证结果：" + isOk);

    }

    /**
     * 快速排序
     */
    private static void sort(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        int l = left;
        int r = right;
        int temp;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            }

            //交换left和right的值
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            // 如果交换后，发现这个值和pivot相等，可以r--,前移一步
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换后，发现这个值和pivot相等，可以l--,后移一步
            if (arr[r] == pivot) {
                l++;
            }

        }
        // 如果 l==r 还可以让 l++， r++下.StackOverflowError
        if (l == r) {
            l++;
            r--;
        }
        // StackOverflowError
        if (r > left) {
            // 左递归
            sort(arr, left, r);
        }
        if (l < right) {
            // 右递归
            sort(arr, l, right);
        }
    }

}
