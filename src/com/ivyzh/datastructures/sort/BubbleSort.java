package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序
 * 案例：
 * 我们举一个具体的案例来说明冒泡法。我们将五个无序的数：3, 9, -1, 10, -2 使用冒泡排序法将其排成一个从小到大的有序数列。
 * <p>
 * 规律：
 * 如果有N个待排数据，则需要进行N-1次大循环；
 * 每一趟的排序次数逐渐的减少；
 * 如果我们发现在某趟过程中，没有发生交换，则可以提前结束冒泡排序，这是优化。
 * 8W个数据
 * 排序前的时间：17:21:55
 * 排序后的时间：17:22:07
 */
public class BubbleSort {
    public static void main(String[] args) {
        System.out.println("~~BubbleSort~~");
//        int[] arr = {3, 9, -1, 10, -2};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 演示性能测试
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date1Str = format.format(start);
        System.out.println("排序前的时间：" + date1Str);

        int num = 8;
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
     * 冒泡排序
     */
    private static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int temp = 0;
        boolean isSwap = false;
        for (int i = 0; i < arr.length - 1; i++) {//大循环次数：N-1
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //System.out.println("第" + (i + 1) + "轮第" + (j + 1) + "次比较为" + arr[j] + "和" + arr[j + 1] + " " + Arrays.toString(arr));
                if (arr[j] > arr[j + 1]) {
                    //交换两个位置的值
                    temp = arr[j + 1];// 优化1.将temp抽取到方法外部定义
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                //System.out.println("这一轮没有发生交换直接break");
                break;
            } else {
                isSwap = false;// 优化2.如果我们发现在某趟过程中，没有发生交换，则可以提前结束冒泡排序
            }
        }
    }
}
