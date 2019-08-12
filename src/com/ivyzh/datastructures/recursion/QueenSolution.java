package com.ivyzh.datastructures.recursion;

import java.util.Arrays;

/**
 * 简书上面有一篇文章介绍八皇后问题的还不错：https://www.jianshu.com/p/65c8c60b83b8
 */
public class QueenSolution {
    static int count = 0;//记录共有多少种解法
    static int[][] array = new int[8][8];
    static int judgeCount = 0;//判断冲突的次数

    public static void main(String[] args) {
        findQueen(0);//寻找皇后节点 0-7

        System.out.println("QueenSolution 共有 " + count + " 种走法,判断冲突的次数" + judgeCount);

    }

    /**
     * 我们来重点看一下这段代码（这段代码虽短，但真的非常非常重要，是整个算法的核心和灵魂）：
     * 第一次进来，row=0，意思是要在第一行摆皇后，只要传进来的row参数不是8，表明还没出结果，就都不会走if里面的return，那么就进入到for循环里面，column从0开始，即第一列。此时第一行第一列肯定合乎要求（即check方法肯定通过），能放下皇后，因为还没有任何其他皇后来干扰。
     * 关键是check方法通过了之后，在if里面又会调用一下自己（即递归），row加了1，表示摆第二行的皇后了。第二行的皇后在走for循环的时候，分两种情况，第一种情况：for循环没走到头时就有通过check方法的了，那么这样就顺理成章地往下走再调用一下自己（即再往下递归），row再加1（即摆第三行的皇后了，以此类推）。第二种情况：for循环走到头了都没有通过check方法的，说明第二行根本一个皇后都摆不了，也触发不了递归，下面的第三行等等后面的就更不用提了，此时控制第一行皇后位置的for循环column加1，即第一行的皇后往后移一格，即摆在第一行第二列的位置上，然后再往下走，重复上述逻辑。
     * 注意，一定要添加清零的代码，它只有在皇后摆不下去的时候会执行清0的动作（避免脏数据干扰），如果皇后摆放很顺利的话从头到尾是不会走这个请0的动作的，因为已经提前走if里面的return方法结束了。
     * 总之，这段核心代码很绕，原理一定要想通，想个十几二十遍差不多就能理解其中的原理了，递归回溯的思想也就不言而喻了。
     *
     */
    private static void findQueen(int row) {
        if (row > 7) {//八皇后有解了
            count++;
            System.out.println("~~~~~~第" + count + "种解决方案~~~~~~");
            printArray();//打印八皇后
            return;
        }

        for (int column = 0; column < 8; column++) {//深度回溯，递归算法
            if (check(row, column)) {
                array[row][column] = 1;
                findQueen(row + 1);
                array[row][column] = 0;//清零，以免回溯的时候出现脏数据
            }
        }


    }

    // 判断是否可以放在row column这个位置上面
    private static boolean check(int row, int column) {
        judgeCount++;
        for (int i = 0; i < 8; i++) {
            if (array[i][column] == 1) {
                return false;
            }
        }

        for (int i = row - 1, m = column - 1; i >= 0 && m >= 0; i--, m--) {//检查左对角线
            if (array[i][m] == 1) {
                return false;
            }
        }
        for (int i = row - 1, m = column + 1; i >= 0 && m <= 7; i--, m++) {//检查右对角线
            if (array[i][m] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void printArray() {
        for (int[] arr : array) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
