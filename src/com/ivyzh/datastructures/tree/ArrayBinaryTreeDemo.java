package com.ivyzh.datastructures.tree;

/**
 * 需求: 给你一个数组 {1,2,3,4,5,6,7}，要求以二叉树前序遍历的方式进行遍历。
 * 前序遍历的结果应当为 1,2,4,5,3,6,7
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
//        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int arr[] = {1, 3, 6, 8, 10, 14  };
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);

        tree.preOrder(0);
        System.out.println();
        tree.infixOrder(0);
        System.out.println();
        tree.postOrder(0);
    }

    static class ArrayBinaryTree {

        int[] arr;

        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        // 前序遍历
        public void preOrder(int index) {
            if (arr == null || arr.length == 0) {
                System.out.println("数组为空，不能遍历");
                return;
            }

            System.out.print(arr[index] + " -> ");
            // 左遍历
            if ((2 * index + 1) < arr.length) {
                preOrder(2 * index + 1);
            }
            if ((2 * index + 2) < arr.length) {
                preOrder(2 * index + 2);
            }
        }

        // 中序遍历
        public void infixOrder(int index) {
            if (arr == null || arr.length == 0) {
                System.out.println("数组为空，不能遍历");
                return;
            }
            if ((2 * index + 1) < arr.length) {
                infixOrder(2 * index + 1);
            }
            System.out.print(arr[index] + " -> ");
            if ((2 * index + 2) < arr.length) {
                infixOrder(2 * index + 2);
            }
        }

        // 后序遍历
        public void postOrder(int index) {
            if (arr == null || arr.length == 0) {
                System.out.println("数组为空，不能遍历");
                return;
            }
            if ((2 * index + 1) < arr.length) {
                postOrder(2 * index + 1);
            }

            if ((2 * index + 2) < arr.length) {
                postOrder(2 * index + 2);
            }
            System.out.print(arr[index] + " -> ");
        }
    }
}
