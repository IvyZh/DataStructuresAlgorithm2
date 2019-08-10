package com.ivyzh.datastructures.linkedlist;

/**
 * Josephu(约瑟夫、约瑟夫环)  问题
 * 设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，
 * 它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 * <p>
 * 提示：用一个不带头结点的循环链表来处理Josephu 问题：先构成一个有n个结点的单循环链表，然后由k结点起从1开始计数，
 * 计到m时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从1开始计数，直到最后一个结点从链表中删除算法结束。
 * <p>
 * 假设n=5，k=1，m=2.则对应的出圈顺序为：2-4-1-5-3.
 */
public class JosephuSolution {
    public static void main(String[] args) {

        CircleLinkedList circleLinkedList = new CircleLinkedList();

        // 手动构建
//        Person p1 = new Person(1);
//        Person p2 = new Person(2);
//        Person p3 = new Person(3);
//        Person p4 = new Person(4);
//        Person p5 = new Person(5);
//        circleLinkedList.add(p1);
//        circleLinkedList.add(p2);
//        circleLinkedList.add(p3);
//        circleLinkedList.add(p4);
//        circleLinkedList.add(p5);

        // 传值构建
        circleLinkedList.add(5);

        circleLinkedList.show();
        System.out.println("共有小孩个数：" + circleLinkedList.getNumber());

        System.out.println("约瑟夫问题开始");


        int k = 1;
        int m = 2;
        circleLinkedList.josephu(k, m);

        System.out.println("程序结束");

    }

    static class CircleLinkedList {
        Person first;

        public CircleLinkedList() {
        }


        /**
         * 构建环形列表的方法
         *
         * @param number 孩子数量
         */
        public void add(int number) {
            if (number < 1) {
                System.out.println("输入的number值不正确");
                return;
            }

            for (int i = 1; i <= number; i++) {
                Person person = new Person(i);
                add(person);
            }

        }

        public void add(Person person) {
            if (first == null) {// 如果头节点为空，则自己为first，next指向自己
                first = person;
                first.next = first;
                return;
            }

            Person currPerson = first;
            while (true) {
                if (currPerson.next == first) {
                    break;
                } else {
                    currPerson = currPerson.next;
                }
            }

            currPerson.next = person;
            person.next = first;
        }


        public void show() {
            if (first == null) {
                System.out.println("队列为空");
                return;
            }
            Person currPerson = first;
            do {
                System.out.print(currPerson + " -> ");
                currPerson = currPerson.next;
            } while (currPerson != first);

            System.out.println();
        }


        /**
         * @param k 从第 k(1<=k<=n) 个小孩开始数数
         * @param m 数 m 下出列，然后下一个小孩从1开始继续数数，直到 m 下出列
         */
        public void josephu(int k, int m) {

            if (k < 0 || k > getNumber()) {
                System.out.println("k值输入错误");
                return;
            }

            Person helper = first;// helper为辅助指针，执行最后一个节点

            while (true) {
                if (helper.next == first)
                    break;
                helper = helper.next;
            }

            System.out.println("helper:" + helper);//此时helper为最后一个元素

            while (true) {
                if (helper == first)
                    break;

                //让helper和first移动m-1次之后，first就会指向第一个要移除的元素位置
                for (int i = 0; i < m - 1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                // 将first位置元素移除，然后helper.next位置设置为first

                System.out.print("小孩 " + first.no + " 出圈 -> ");
                first = first.next;
                helper.next = first;
            }

            System.out.println("最后 " + first.no + " 出圈");


        }


        // 返回孩子数量
        public int getNumber() {
            if (first == null)
                return 0;
            Person currPeron = first;
            int num = 1;
            while (true) {
                if (currPeron.next == first) {
                    break;
                }
                currPeron = currPeron.next;
                num++;
            }
            return num;
        }
    }

    static class Person {
        int no;
        Person next;

        public Person(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "no=" + no;
        }
    }
}
