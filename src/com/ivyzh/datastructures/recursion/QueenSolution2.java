package com.ivyzh.datastructures.recursion;

import java.util.Arrays;

/**
 * 使用一维数组来实现
 */
public class QueenSolution2 {
    static int count = 0;
    static int judgeCount = 0;
    static int[] chess = new int[8];// 8x8 的棋盘

    public static void main(String[] args) {
        findQueen(0);
        System.out.println("QueenSolution2 共有 " + count + " 种走法,判断冲突的次数" + judgeCount);
    }

    //编写一个方法，放置第n个皇后
    private static void findQueen(int num) {
        if (num > 7) {
            count++;
            //System.out.println("~~~~~~第" + count + "种解决方案~~~~~~");
            printQueen();//打印八皇后
            return;
        }

        for (int column = 0; column < 8; column++) {
            chess[num] = column;
            if (judge(num)) {
                findQueen(num + 1);
            }
        }

//        for (int column = 0; column < 8; column++) {
//            if (judge(num, column)) {
//                chess[num] = column;
//                findQueen(num + 1);
//            }
//        }

    }

    private static boolean judge(int num, int column) {
        judgeCount++;
        for (int i = 0; i < num; i++) {
            if (chess[i] == column || Math.abs(num - i) == Math.abs(column - chess[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean judge(int num) {
        for (int i = 0; i < num; i++) {
            if (chess[i] == chess[num] || Math.abs(num - i) == Math.abs(chess[num] - chess[i])) {
                return false;
            }
        }
        return true;
    }

    private static void printQueen() {
        System.out.println(Arrays.toString(chess));
    }
}
