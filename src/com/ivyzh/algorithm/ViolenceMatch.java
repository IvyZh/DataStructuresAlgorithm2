package com.ivyzh.algorithm;

/**
 * 有一个字符串 str1= "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好"，和一个子串 str2="尚硅谷你尚硅你"
 * 现在要判断 str1 是否含有 str2, 如果存在，就返回第一次出现的位置, 如果没有，则返回-1
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String s2 = "尚硅谷你尚硅你";
        System.out.println("indexOf : " + s1.indexOf(s2));

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int c1Length = c1.length;
        int c2Length = c2.length;
        int i = 0;
        int j = 0;

        while (i < c1Length && j < c2Length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == c2Length) {
            System.out.println("find..." + (i - j));
        } else {
            System.out.println("no find...");
        }

    }
}
