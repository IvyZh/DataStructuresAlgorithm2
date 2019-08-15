package com.ivyzh.datastructures.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = find(arr, 9);
        System.out.println("index = " + index);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " index = " + find(arr, arr[i]));
        }
    }

    private static int find(int[] arr, int num) {
        // 生成合适的斐波那契数组
        // 1 1 2 3 5 8 13
        int N = arr.length;//7

        int first = 1;
        int sec = 1;
        int temp = 0;
        int length = 2;
        while (first + sec < N) {
            length++;
            temp = sec;
            sec = first + sec;
            first = temp;
        }
        length++;//此时length即为应该生成的斐波那契数组长度
//        System.out.println(length);

        int[] fiboArray = new int[length];// 斐波那契数组
        fiboArray[0] = 1;
        fiboArray[1] = 1;
        for (int i = 2; i < length; i++) {
            fiboArray[i] = fiboArray[i - 1] + fiboArray[i - 2];
        }

//        System.out.println(Arrays.toString(fiboArray));


        int[] filledArray = Arrays.copyOf(arr, fiboArray[fiboArray.length - 1]);//填充数组
//        System.out.println(Arrays.toString(filledArray));
        // 将高位填充
        int fillValue = arr[arr.length - 1];
        for (int i = arr.length; i < filledArray.length; i++) {
            filledArray[i] = fillValue;
        }

//        System.out.println("arr        ->" + Arrays.toString(arr));
//        System.out.println("filledArray->" + Arrays.toString(filledArray));

        int left = 0;
        int right = arr.length-1;
//        int right = filledArray.length;
        int k = fiboArray.length - 1;// k = 6

        while (left <= right) {// 8<=9,8<=8
            int mid = left + fiboArray[k - 1] - 1;//mid = 7,8+f(3)-1=10,8+f(2)-1=9,8+f(1)+1=8
//            System.out.println("mid = " + mid);
            if (num < filledArray[mid]) {//左边 num = 8,arr[9]=9,
                k--;// k = 3,2
                right = mid - 1;// right = 9,8
            } else if (num > filledArray[mid]) {//右边
                k = k - 2;// k = 4
                left = mid + 1;// left = 8
            } else {
                if (mid <= right) {//8,8
                    return mid;
                } else {
                    return right;
                }
            }
        }

        return -1;
    }
}
