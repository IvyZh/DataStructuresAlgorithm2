package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 案例：
 * 有一群牛 , 颜值分别是 101, 34, 119, 1 请使用选择排序从低到高进行排序 [101, 34, 119, 1]
 * <p>
 * 规律：
 * 1.	如果有N个待排数据，则需要进行N-1次大循环；
 * 2.	每一轮排序又是一个小循环，第一次是 0~n-1个数中找最小值，第i次是i-1~n-1个数中找最小值
 * <p>
 * 8W个数据
 * 排序前的时间：18:13:41
 * 排序后的时间：18:13:45
 */
public class SelectorSort {
    public static void main(String[] args) {
//        System.out.println("~~SelectorSort~~");
//        int[] arr = {1, 7, 0, 1, 1, 0, 7, 6};
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
    }

    private static void sort(int[] arr) {
        int minIndex = 0;
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;//默认最小值的坐标是起始位置
            for (int j = i + 1; j < arr.length; j++) {
//                System.out.println("第" + (i + 1) + "轮第" + (j + 1 - i) + "次比较为" + arr[j] + "和" + arr[j + 1] + " " + Arrays.toString(arr));
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
//            System.out.println("第" + (i + 1) + "轮排序中最小值小标为" + minIndex);

            if (minIndex != i) {
                // 交换位置0和minIndex位置对应的值
                temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
//                System.out.println("交换之后的数组：" + Arrays.toString(arr));
        }
    }
}
