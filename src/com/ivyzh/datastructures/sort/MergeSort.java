package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * 案例：
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
 * （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 * 800W个数据
 * 排序前的时间：21:35:09
 * 排序后的时间：21:35:11
 */
public class MergeSort {
    public static void main(String[] args) {
        System.out.println("~~MergeSort~~");
//        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort(arr);
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
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left == right)
            return;
        int mid = (left + right) / 2;
//        System.out.println("mergeSort -> " + left + " , " + right + " , " + mid);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * 合并
     *
     * @param right
     */
    private static void merge(int[] arr, int left, int mid, int right) {
//        System.out.println("合并 -> " + left + " , " + right + " , " + mid);
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= right) {
            //temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            // 分解
            if (arr[p1] < arr[p2]) {
                temp[i] = arr[p1];
                p1++;
            } else {
                temp[i] = arr[p2];
                p2++;
            }
            i++;
        }

        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[i++] = arr[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for (int j = 0; j < temp.length; j++) {
            arr[left + j] = temp[j];
        }

    }
}
