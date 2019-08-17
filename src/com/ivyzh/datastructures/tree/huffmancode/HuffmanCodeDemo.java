package com.ivyzh.datastructures.tree.huffmancode;


import java.util.*;

/**
 * 霍夫曼编码
 */
public class HuffmanCodeDemo {

    // 存放霍夫曼编码
    // 形式：  105(i)-> 101 (e): 1111
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    // 存储某个叶子节点的路径
    static StringBuilder path = new StringBuilder();

    public static void main(String[] args) {
        String strContent = "i like like like java do you like a java";
        System.out.println("原字符串：\n" + strContent);

        System.out.println("原字符串对应的byte[]数据：\n" + Arrays.toString(strContent.getBytes()));
        // 1. 创建字符串strContent对应的霍夫曼树
        Node root = createHuffmanTree(strContent);
        System.out.println("对应的霍夫曼树：");
        preOrder(root);

        // 2. 生成对应的霍夫曼编码
        System.out.println();
        generateHuffmanCode(root, "", path);
        System.out.println("对应的霍夫曼编码：");
        for (Byte key : huffmanCodes.keySet()) {
            String value = huffmanCodes.get(key);// key-->为字符对应byte
            char c = (char) key.byteValue();
            System.out.print(c + "(" + key + ")_" + value + " , ");
        }

        // 3. 将字符串依据霍夫曼编码压缩之后返回数据
        //传入数据 字符串对应的byte[]数据，霍夫曼编码表，返回byte[]
        byte[] zipByteData = zip(strContent.getBytes());

        System.out.println("zipByteData:\n" + Arrays.toString(zipByteData));
        // [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]

        // 4. 数据解压
        byte[] data = upZip(zipByteData);
        System.out.println("数据解压的ascii:\n" + Arrays.toString(data));
        for (byte b : data) {
            System.out.print((char) b);
        }


    }

    private static byte[] upZip(byte[] zipByteData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zipByteData.length; i++) {
            byte b = zipByteData[i];
            boolean flag = (i == zipByteData.length - 1);
            sb.append(byteToString(b, !flag));
        }
        System.out.println("字节数组转成对应的二进制形式的字符串：\n" + sb);

        Map<String, Byte> map = new HashMap<>();
        for (Byte key : huffmanCodes.keySet()) {
            String value = huffmanCodes.get(key);
            map.put(value, key);
        }

        System.out.println(map);

        // 遍历sb:"1010100010111111110010..."依次找到对应的byte

        ArrayList<Byte> list = new ArrayList<>();

        for (int i = 0; i < sb.length(); ) {
            boolean flag = true;
            int count = 1;
            Byte b = null;
            while (flag) {
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        byte[] bytes = new byte[list.size()];

        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }


    /**
     * 将一个byte转成二进制的字符串
     *
     * @param flag true：需要补高位，false：不需要补高位
     */
    private static String byteToString(byte b, boolean flag) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        return flag ? str.substring(str.length() - 8) : str;
    }

    private static byte[] zip(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (Byte b : bytes) {
            String code = huffmanCodes.get(b);
            sb.append(code);
        }

        System.out.println("字节数组转成字符串：\n" + sb.toString());
        //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        // sb = "1010100010111111..."  将sb转成byte
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        //或者直接这样一句
        //len = (sb.length() + 7) / 8;

        byte[] zipByteData = new byte[len];

        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {//大于8位
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            zipByteData[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return zipByteData;
    }


    private static Node createHuffmanTree(String strContent) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (Byte b : strContent.getBytes()) {
            Integer number = map.get(b);
            if (number == null) {
                map.put(b, 1);
            } else {
                number++;
                map.put(b, number);
            }
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for (Byte key : map.keySet()) {
            Node node = new Node(key, map.get(key));
            nodes.add(node);
        }
        Collections.sort(nodes);
        System.out.println("字符出现的对应次数：\n" + nodes);

        while (nodes.size() > 1) {
            Node node1 = nodes.remove(0);
            Node node2 = nodes.remove(0);
            Node node = new Node(null, node1.value + node2.value);
            node.left = node1;
            node.right = node2;
            nodes.add(node);
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }


    private static void generateHuffmanCode(Node node, String character, StringBuilder path) {
        StringBuilder sb = new StringBuilder(path);
        sb.append(character);
        if (node != null) {
            if (node.charByte == null) {//非叶子节点
                generateHuffmanCode(node.left, "0", sb);
                generateHuffmanCode(node.right, "1", sb);
            } else {//叶子节点
                huffmanCodes.put(node.charByte, sb.toString());
            }
        }
    }


    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }
    }


    static class Node implements Comparable<Node> {
        Node left;
        Node right;
        int value;//对应的权重
        Byte charByte;// 字符对应byte值，如a->97

        public Node(Byte charByte, int value) {
            this.value = value;
            this.charByte = charByte;
        }

        @Override
        public String toString() {
            if (charByte == null) {
                return "(NULL)_" + value;
            }
            char c = (char) charByte.byteValue();
            return c + "(" + charByte + ")_" + value;
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
