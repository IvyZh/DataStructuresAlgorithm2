package com.ivyzh.datastructures.tree.huffmancode;


import java.io.*;
import java.util.*;

/**
 * 霍夫曼编码压缩文件案例
 */
public class HuffmanZipFileDemo {
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder path = new StringBuilder();

    public static void main(String[] args) {
        String src = "C:\\Users\\Ivy\\Desktop\\123\\src.bmp";
        String dst = "C:\\Users\\Ivy\\Desktop\\123\\dst.zip";
        zipFile(src, dst);
        System.out.println("压缩文件ok~~");

        String zipFile = "C:\\Users\\Ivy\\Desktop\\123\\dst.zip";
        String dst2 = "C:\\Users\\Ivy\\Desktop\\123\\src2.bmp";
        unZipFile(zipFile, dst2);
        System.out.println("解压文件ok~~");

    }

    private static void unZipFile(String zipFile, String dst) {
        InputStream is = null;//定义文件输入流
        ObjectInputStream ois = null;//定义对象输入流
        OutputStream os = null;//定义文件输出流
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = upZip(huffmanBytes);
            os = new FileOutputStream(dst);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void zipFile(String src, String dst) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(src);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            byte[] data = huffmanZip(bytes);
            fos = new FileOutputStream(dst);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);//先将数据写进去
            oos.writeObject(huffmanCodes);//再将霍夫曼表写进去
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        Node root = createHuffmanTree(bytes);//1、创建霍夫曼树
        generateHuffmanCode(root, "", path);//2、生成霍夫曼编码
        return zip(bytes);//3、压缩
    }

    private static byte[] upZip(byte[] zipByteData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zipByteData.length; i++) {
            byte b = zipByteData[i];
            boolean flag = (i == zipByteData.length - 1);
            sb.append(byteToString(b, !flag));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Byte key : huffmanCodes.keySet()) {
            String value = huffmanCodes.get(key);
            map.put(value, key);
        }

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


    private static Node createHuffmanTree(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (Byte b : bytes) {
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

    }
}
