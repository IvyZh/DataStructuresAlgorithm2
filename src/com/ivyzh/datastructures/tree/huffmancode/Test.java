package com.ivyzh.datastructures.tree.huffmancode;

public class Test {
    public static void main(String[] args) {
        String s = "10101000";
        int a = Integer.parseInt(s, 2);
        System.out.println(a);//168
        System.out.println((byte) Integer.parseInt(s, 2));//-88
    }
}
