package com.ivyzh.datastructures.sparsearray;

import java.util.Arrays;

/**
 * 功能：二维数组=>稀疏数组，稀疏数组=>二维数组
 * 作业：在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data，
 * 恢复原来的数组时，读取map.data 进行恢复
 */
public class SparseArray {

    public static void main(String[] args) {
        int chessArr[][] = new int[6][7];//二维数组
        //假定初始值
        chessArr[0][3] = 22;
        chessArr[0][6] = 15;
        chessArr[1][1] = 11;
        chessArr[1][5] = 17;
        chessArr[2][3] = -6;
        chessArr[3][5] = 39;
        chessArr[4][0] = 91;
        chessArr[5][2] = 28;

        System.out.println("打印原二维数组数据：");
        printArr(chessArr);

        // 定义稀疏数组(y+1)x3  y:表示二维数组的非零值个数
        int y = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    y++;
                }
            }
        }
        System.out.println("二维数组中非0值个数y = " + y);

        int sparseArr[][] = new int[y + 1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = y;
        int number = 1;//记录稀疏数组的存储行坐标
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[number][0] = i;
                    sparseArr[number][1] = j;
                    sparseArr[number][2] = chessArr[i][j];
                    number++;
                }
            }
        }

        System.out.println("打印二维数组对应稀疏数组数据：");
        printArr(sparseArr);


        // 稀疏数组恢复成二维数组
        int m = sparseArr[0][0];
        int n = sparseArr[0][1];
        int[][] chessArr2 = new int[m][n];
        for (int i = 1; i < sparseArr.length; i++) {//从第2行开始读
            int row = sparseArr[i][0];
            int column = sparseArr[i][1];
            int value = sparseArr[i][2];
            chessArr2[row][column] = value;
        }
        System.out.println("打印稀疏数组对应二维数组数据：");
        printArr(chessArr2);


        // 依次比较chessArr和chessArr2是否相等
        boolean flag = true;

        if (chessArr.length == chessArr2.length) {
            for (int i = 0; i < chessArr.length; i++) {
                for (int j = 0; j < chessArr[i].length; j++) {
                    if (chessArr[i][j] != chessArr2[i][j]) {
                        flag = false;
                        break;
                    }
                }
            }
        }

        System.out.println("chessArr == chessArr2:" + flag);


    }

    /**
     * 打印二维数组
     *
     * @param arr
     */
    private static void printArr(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
