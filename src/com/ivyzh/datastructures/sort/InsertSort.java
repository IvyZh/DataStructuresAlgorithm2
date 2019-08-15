package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 插入排序
 * <p>
 * 思想：把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
 * 把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 * <p>
 * 8W个数据
 * 排序前的时间：14:43:15
 * 排序后的时间：14:43:16
 */
public class InsertSort {
    public static void main(String[] args) {
        System.out.println("~~InsertSort~~");
//        int[] arr = {3, 1, 9, -1, 10, -2};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));


        // 演示性能测试
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date1Str = format.format(start);
        System.out.println("排序前的时间：" + date1Str);

        int num = 80000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * num);
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
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
     * 插入排序
     *
     * @param arr
     */
    private static void sort(int[] arr) {

        int insertIndex = 0;
        int insertValue = arr[1];

        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (i != insertIndex + 1) {
                arr[insertIndex + 1] = insertValue;//可以优化
            }
        }
    }

    /**
     * 插入排序-网上代码
     *
     * @param arr
     */
    private static void sort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }
}
