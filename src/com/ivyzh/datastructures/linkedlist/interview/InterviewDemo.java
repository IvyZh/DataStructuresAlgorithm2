package com.ivyzh.datastructures.linkedlist.interview;


import java.util.Stack;

/**
 * 单链表的常见面试题有如下:
 * 1.求单链表中有效节点的个数
 * 2.查找单链表中的倒数第k个结点 【新浪面试题】
 * 3.单链表的反转【腾讯面试题，有点难度】
 * 4.从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
 * 5.合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
 */
public class InterviewDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "唐僧");
        HeroNode hero2 = new HeroNode(2, "孙悟空");
        HeroNode hero3 = new HeroNode(3, "猪八戒");
        HeroNode hero4 = new HeroNode(4, "沙僧");
        HeroNode hero5 = new HeroNode(5, "白骨精");

        SingleLinkedList linkedList = new SingleLinkedList();
        // 按添加顺序插入数据
        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero4);
        linkedList.add(hero5);
        linkedList.show();

        // 1. 求单链表中有效节点的个数
        int length = linkedList.getLength();
        System.out.println("有效长度：" + length);

        // 2. 查找单链表中的倒数第k个结点 【新浪面试题】
        int k = 1;
        HeroNode node = linkedList.getLastIndex(k);
        System.out.println("单链表中的倒数第" + k + "个结点是：" + node);

        //3.单链表的反转【腾讯面试题，有点难度】
        //linkedList.reverse();
        linkedList.show();

        // 4.从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
        //方式1：反向遍历
//        linkedList.reverse();
        System.out.println("从尾到头打印单链表:");
        linkedList.show();

        linkedList.reverseByStack();


    }

    static class SingleLinkedList {

        HeroNode header = new HeroNode(0, "");//初始化头节点，不存放任何数据

        // 添加节点方法
        public void add(HeroNode heroNode) {
            HeroNode temp = header;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            // 此时temp就是最后的节点
            temp.next = heroNode;
        }

        public void show() {
            if (header.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode node = header.next;
            while (node != null) {
                System.out.print(node + " -> ");
                node = node.next;
            }
            System.out.println();
        }

        // 1.求单链表中有效节点的个数
        public int getLength() {
            if (header.next == null) {
                return 0;
            }
            int count = 0;
            HeroNode node = header.next;
            while (node != null) {
                count++;
                node = node.next;
            }
            return count;
        }

        public HeroNode getLastIndex(int k) {
            int length = getLength();
            if (k > length || k <= 0) {
                System.out.println("输入的index越界");
                return null;
            }
            if (length == 0) {
                return null;
            }

            HeroNode currentNode = header.next;
            int count = length - k;
            int i = 0;
            while (i != count) {
                currentNode = currentNode.next;
                i++;
            }
            return currentNode;

        }

        // 3.单链表的反转
        public void reverse() {
            // 如果链表为空或者只有一个节点则无需反转，直接返回
            if (header.next == null || header.next.next == null)
                return;
            HeroNode reverse = new HeroNode(0, "");
            HeroNode currentNode = header.next;// 运动节点，开始值为header后面的节点
            HeroNode nextNode = null;//保存当前节点的下一个节点
            while (currentNode != null) {
                nextNode = currentNode.next;
                currentNode.next = reverse.next;
                reverse.next = currentNode;
                currentNode = nextNode;
            }

            header.next = reverse.next;
        }

        //3.单链表的反转-通过栈
        public void reverseByStack() {
            // 如果链表为空或者只有一个节点则无需反转，直接返回
            if (header.next == null || header.next.next == null)
                return;
            Stack<HeroNode> stack = new Stack<>();

            HeroNode currentNode = header.next;// 运动节点，开始值为header后面的节点
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.next;
            }

            while (stack.size() > 0) {
                System.out.print(stack.pop() + " -> ");
            }
        }
    }

    static class HeroNode {
        int no;//英雄排名
        String name;//英雄名称
        HeroNode next;// 下一个节点

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "no=" + no + ", name='" + name + '\'';
        }
    }
}
