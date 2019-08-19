package com.ivyzh.algorithm;

public class HanoiTowerDemo {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    private static void hanoiTower(int num, char a, char b, char c) {
        if (num <= 1) {
            System.out.println(a + " -> " + c);
            return;
        }
        if (num == 2) {
            System.out.println(a + " -> " + b);
            System.out.println(a + " -> " + c);
            System.out.println(b + " -> " + c);
            return;
        }
        hanoiTower(num - 1, a, c, b);
        System.out.println(a + " -> " + c);
        hanoiTower(num - 1, b, a, c);

    }
}
