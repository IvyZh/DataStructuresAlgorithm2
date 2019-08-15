package com.ivyzh.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * <p>
 * 800W个数据
 * 排序前的时间：22:31:17
 * 排序后的时间：22:31:19
 */
public class RadixSort {
    public static void main(String[] args) {
        System.out.println("~~RadixSort~~");
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 演示性能测试
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date1Str = format.format(start);
        System.out.println("排序前的时间：" + date1Str);

        int num = 18000000;//OutOfMemoryError

        // 11个数组
        // 每个int是4个字节
        // 1024过后是Kb
        // 1024过后是Mb
        // 1024过后是G
        // 8000W个数据：80000000 * 11 * 4 /1024/1024/1024=3.2G
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

    private static void sort(int[] arr) {
        //首先获取数组中最高数字的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];//定义10个桶，每个桶的容量为数组长度，以防止放不下
        int[] bucketNum = new int[10];//每个桶存放数据的数量bucketNum[3]表示第三个通存放了多少个数据

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;//个位。比如53->3 会放在3个桶里面
                bucket[digitOfElement][bucketNum[digitOfElement]] = arr[j];
                bucketNum[digitOfElement]++;
            }

            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            for (int k = 0; k < bucketNum.length; k++) {//10
                if (bucketNum[k] != 0) {//表示这第i个桶有数据
                    for (int l = 0; l < bucketNum[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketNum[k] = 0;
            }
            bucket = new int[10][arr.length];//定义10个桶，每个桶的容量为数组长度，以防止放不下
//            bucketNum = new int[10];//每个桶存放数据的数量bucketNum[3]表示第三个通存放了多少个数据
        }

    }

    /**
     * 基数排序
     */
    private static void sort2(int[] arr) {

        int[][] bucket = new int[10][arr.length];//定义10个桶，每个桶的容量为数组长度，以防止放不下

        int[] bucketNum = new int[10];//每个桶存放数据的数量bucketNum[3]表示第三个通存放了多少个数据
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] % 10;//个位。比如53->3 会放在3个桶里面
            bucket[index][bucketNum[index]] = arr[i];
            bucketNum[index]++;
        }
        // 针对这一轮之后，依次取出数据放入原来数组中

        int index = 0;
        for (int i = 0; i < bucketNum.length; i++) {//10
            if (bucketNum[i] != 0) {//表示这第i个桶有数据
                for (int j = 0; j < bucketNum[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
        }

        System.out.println("经过第一轮之后，数组排序为：" + Arrays.toString(arr));


        bucket = new int[10][arr.length];//定义10个桶，每个桶的容量为数组长度，以防止放不下
        bucketNum = new int[10];//每个桶存放数据的数量bucketNum[3]表示第三个通存放了多少个数据
        for (int i = 0; i < arr.length; i++) {
            index = arr[i] / 10 % 10;//个位。比如53->3 会放在3个桶里面
            bucket[index][bucketNum[index]] = arr[i];
            bucketNum[index]++;
        }
        // 针对这一轮之后，依次取出数据放入原来数组中

        index = 0;
        for (int i = 0; i < bucketNum.length; i++) {//10
            if (bucketNum[i] != 0) {//表示这第i个桶有数据
                for (int j = 0; j < bucketNum[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
        }
        System.out.println("经过第二轮之后，数组排序为：" + Arrays.toString(arr));

    }
}
