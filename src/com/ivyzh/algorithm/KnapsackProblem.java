package com.ivyzh.algorithm;

import java.util.Arrays;

/**
 * 分值算法-背包问题
 * (1)  v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是0
 * (2) 当w[i]> j 时：v[i][j]=v[i-1][j]   // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个单元格的装入策略
 * (3) 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
 */
public class KnapsackProblem {
    public static void main(String[] args) {

        int[] weight = {1, 4, 3};//每个物品的重量
        int[] value = {1500, 3000, 2000};//每个物品的重量对应的价值
        int capacity = 4;//背包容量
        int num = value.length;//物品的数量
        int[][] v = new int[num + 1][capacity + 1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        for (int[] arr : v) {
            System.out.println(Arrays.toString(arr));
        }

        // 分治算法开始
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                if (weight[i - 1] > j) {
                    //v[i][j]=v[i-1][j]
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
                    v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);
                }
            }
        }

        System.out.println("分治算法之后结果：");
        for (int[] arr : v) {
            System.out.println(Arrays.toString(arr));
        }

    }
}
