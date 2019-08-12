package com.ivyzh.datastructures.stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();
        stack.list();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.list();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.list();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }

    static class LinkedListStack {

        Node header = new Node(-1);//-1 来表示头节点，值没有实际意义

        public LinkedListStack() {

        }

        public void list() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                return;
            }
            Node tempNode = header.next;
            while (tempNode != null) {
                System.out.print(tempNode.data + " -> ");
                tempNode = tempNode.next;
            }
            System.out.println();
        }

        public void push(int ele) {
            Node nodeEle = new Node(ele);

            Node tempNode = header;//辅助指针

            while (true) {
                if (tempNode.next == null) {
                    break;
                }
                tempNode = tempNode.next;
            }
            // while循环之后，temp为最后节点位置
            tempNode.next = nodeEle;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                throw new RuntimeException("stack是空的");
            }

            Node beforeNode = header;//辅助指针
            Node tempNode = header;//循环指针

            while (true) {
                if (tempNode.next == null) {
                    break;
                }
                beforeNode = tempNode;
                tempNode = tempNode.next;
            }
            // while循环之后，beforeNode为最后节点位置的前一个节点
            Node popNode = beforeNode.next;
            int value = popNode.data;
            beforeNode.next = null;
            return value;
        }

        public boolean isEmpty() {
            return header.next == null;
        }

    }


    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "data=" + data;
        }
    }
}
