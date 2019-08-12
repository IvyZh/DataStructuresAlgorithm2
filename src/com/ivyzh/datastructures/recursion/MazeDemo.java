package com.ivyzh.datastructures.recursion;

import java.util.Arrays;

/**
 * 迷宫问题：
 * 用一个二维数组表示迷宫，
 * 0：还没走过
 * 1：表示墙
 * 2：可以走，假定该点可以走通
 * 3：走过，走不通，是死路
 * <p>
 * 策略：下->右->上->左 :10
 * 策略：下->右->上->左 :10
 */
public class MazeDemo {

    private static int count = 0;

    public static void main(String[] args) {
        int[][] maze = new int[8][7];// 迷宫8行7列

        // 设置墙体
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }

        // 设置挡板
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[1][4] = 1;
        maze[4][5] = 1;


        for (int[] arr : maze) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-----------------------");

//        setWay(maze, 1, 1);
//        for (int[] arr : maze) {
//            System.out.println(Arrays.toString(arr));
//        }
//        System.out.println("共走了 " + count + " 步");


        count = 0;
        setWay2(maze, 1, 1);
        for (int[] arr : maze) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("共走了 " + count + " 步");

    }

    /**
     * 0：还没走过
     * 1：表示墙
     * 2：可以走，假定该点可以走通
     * 3：走过，走不通，是死路
     */
    private static boolean setWay(int[][] maze, int x, int y) {
        if (maze[6][5] == 2) {
            return true;
        }
        if (maze[x][y] == 0) {//还没走过
            maze[x][y] = 2;//先假定该点可以走通
            // 策略：下->右->上->左
            if (setWay(maze, x + 1, y)) {
                count++;
                return true;
            } else if (setWay(maze, x, y + 1)) {
                count++;
                return true;
            } else if (setWay(maze, x, y - 1)) {
                count++;
                return true;
            } else if (setWay(maze, x - 1, y)) {
                count++;
                return true;
            } else {
                maze[x][y] = 3;
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean setWay2(int[][] maze, int x, int y) {

        if (maze[6][5] == 2) {
            return true;
        }
        if (maze[x][y] == 0) {//还没走过
            maze[x][y] = 2;//先假定该点可以走通
            // 策略：右->下->上->左
            if (setWay2(maze, x, y+1)) {
                count++;
                return true;
            } else if (setWay2(maze, x+1, y)) {
                count++;
                return true;
            } else if (setWay2(maze, x-1, y )) {
                count++;
                return true;
            } else if (setWay2(maze, x, y-1)) {
                count++;
                return true;
            } else {
                maze[x][y] = 3;
                return false;
            }
        } else {
            return false;
        }
    }
}
