package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * <p>
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，
 * 每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * <p>
 * 2000W个数据-交换法
 * 排序前的时间：16:52:18
 * 排序后的时间：16:52:25
 * 800W个数据-交换法
 * 排序前的时间：18:20:16
 * 排序后的时间：18:20:19
 * <p>
 * 2000W个数据-移位法
 * 排序前的时间：16:53:41
 * 排序后的时间：16:53:48
 * 800W个数据-移位法
 * 排序前的时间：18:20:44
 * 排序后的时间：18:20:47
 */
public class ShellSort {
    public static void main(String[] args) {
        System.out.println("~~ShellSort~~");
//        int[] arr = {3, 1, 9, -1, 10, -2};
//
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * arr.length);
//        }
//
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort2(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));


//        // 演示性能测试
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date1Str = format.format(start);
        System.out.println("排序前的时间：" + date1Str);

        int num = 8000000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * num);
            //arr[i] = num--;
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        sort2(arr);
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
     * 希尔排序-交换法
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j - gap] > arr[j]) {
                    swap(arr, j, j - gap);
                    j = j - gap;
                }
            }
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    /**
     * 希尔排序-移位法
     *
     * @param arr
     */
    private static void sort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];

                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    //当退出wahile后，就给temp找到了插入位置
                    arr[j] = temp;
                }
            }

        }
    }
}
