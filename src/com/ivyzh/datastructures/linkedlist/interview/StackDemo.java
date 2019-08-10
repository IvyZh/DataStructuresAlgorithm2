package com.ivyzh.datastructures.linkedlist.interview;

import java.util.Stack;

/**
 * 栈：先进后出
 */
public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        String ele = stack.peek();
        System.out.println(ele);

    }
}
