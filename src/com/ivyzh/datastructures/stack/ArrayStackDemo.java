package com.ivyzh.datastructures.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        stack.list();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.list();

        try {
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    static class ArrayStack {
        int[] arr;
        int maxSize;
        int top = -1;//默认是-1，每push一个值top++,pop一个值top--

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
        }

        public void list() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                return;
            }


            for (int i = 0; i <= top; i++) {
                System.out.print(arr[i] + " -> ");
            }
            System.out.println();
        }

        public void push(int ele) {
            if (isFull()) {
                System.out.println("stack已经满了");
                return;
            }
            top++;
            arr[top] = ele;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                throw new RuntimeException("stack是空的");
            }
            int value = arr[top];
            top--;
            return value;
        }


        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }


    }
}
