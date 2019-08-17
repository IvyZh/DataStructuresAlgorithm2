package com.ivyzh.datastructures.tree.huffmancode;

public class Test {
    public static void main(String[] args) {
        String s = "a";
        byte[] bytes = s.getBytes();
        for (Byte b : bytes) {
            System.out.println(b);//97
            byte b1 = b.byteValue();
            char c = (char) b1;
            System.out.println(c);
        }

        for (byte b : bytes) {
            System.out.println(b);//97
            char c = (char) b;
            System.out.println(c);
        }
    }
}
