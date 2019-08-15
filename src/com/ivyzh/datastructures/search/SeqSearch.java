package com.ivyzh.datastructures.search;

import java.util.ArrayList;

/**
 * 线性查找算法
 * <p>
 * 有一个数列： {1,8, 10, 89, 1000, 1234} ，
 * 判断数列中是否包含此名称【顺序查找】 要求: 如果找到了，就提示找到，并给出下标值。
 * 思路：如果查找到全部符合条件的值。[思路分析.]
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234, 8};

        int index = find(arr, 8);
        System.out.println("index = " + index);
        ArrayList<Integer> list = find2(arr, 118);
        if (list.size() > 0) {
            System.out.println("find..." + list);
        } else {
            System.out.println("no find");
        }
    }

    private static int find(int[] arr, int num) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static ArrayList<Integer> find2(int[] arr, int num) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                ints.add(i);
            }
        }
        return ints;
    }
}
