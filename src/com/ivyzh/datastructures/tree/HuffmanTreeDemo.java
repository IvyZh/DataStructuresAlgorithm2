package com.ivyzh.datastructures.tree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);//前序遍历
        //67 -> 29 -> 38 -> 15 -> 7 -> 8 -> 23 -> 10 -> 4 -> 1 -> 3 -> 6 -> 13 ->
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空数，不能遍历");
        }
    }

    /**
     * 创建霍夫曼树
     */
    private static Node createHuffmanTree(int[] arr) {

        // 1. 排序
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        Collections.sort(nodes);
        System.out.println(nodes);//[1, 3, 6, 7, 8, 13, 29]

        // 2. 取出前面两个值生成新值，然后求和放到新的nodes中
        while (nodes.size() > 1) {
            Node node1 = nodes.remove(0);
            Node node2 = nodes.remove(0);
            Node node = new Node(node1.value + node2.value);
            node.left = node1;
            node.right = node2;
            nodes.add(node);
            Collections.sort(nodes);
            System.out.println("经过这一轮排序之后nodes为：" + nodes);
        }
        return nodes.get(0);
    }

    static class Node implements Comparable<Node> {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

        public void preOrder() {
            System.out.print(this + " -> ");
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
