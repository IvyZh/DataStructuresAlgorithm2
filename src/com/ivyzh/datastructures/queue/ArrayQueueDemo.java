package com.ivyzh.datastructures.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean looper = true;
        while (looper) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据");
            System.out.println("g(get)：取出数据");
            System.out.println("h(head)：查看数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    looper = false;
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int ele = scanner.nextInt();
                    queue.addQueue(ele);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是：" + res);
                    }catch (RuntimeException e){
                        e.printStackTrace();
                    }

                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.println("查看的数据是：" + result);
                    }catch (RuntimeException e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
        System.out.println("程序退出~~");

    }


    static class ArrayQueue {
        int[] arr;
        int maxSize;
        int front;
        int rear;

        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
            front = -1;//是队列最前元素[不含]
            rear = -1;//是队列最后[含]
        }

        // 队列是否已经满
        public boolean isFull() {
            return maxSize - 1 == rear;
        }

        // 队列是否已经空
        public boolean isEmpty() {
            return front == rear;
        }

        // 添加元素
        public void addQueue(int ele) {
            if (isFull()) {
                System.out.println("队列已满，不能添加元素");
                return;
            }
            rear++;//让rear后移，因为初始值是-1
            arr[rear] = ele;
        }

        // 打印队列
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                return;
            }
            // 打印有效值
            System.out.println("打印有效值：");
            for (int i = front; i < rear; i++) {
                System.out.print(arr[i + 1] + "->");
            }
            System.out.println();
            System.out.println("打印完整队列：");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "->");
            }
            System.out.println();
        }

        // 查看队列头元素
        public int headQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                throw new RuntimeException("这一个空队列");
            }
            // 打印有效值
            return arr[front + 1];
        }

        // 出队列操作
        public int getQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                throw new RuntimeException("这一个空队列");
            }
            // 打印有效值
            int value = arr[front + 1];
            front++;
            return value;
        }
    }
}
