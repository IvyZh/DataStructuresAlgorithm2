package com.ivyzh.datastructures.linkedlist;

/**
 * 使用带head头的单向链表实现 –水浒英雄排行榜管理
 * 完成对英雄人物的增删改查操作， 注: 删除和修改,查找
 * 第一种方法在添加英雄时，直接添加到链表的尾部
 * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
 * (如果有这个排名，则添加失败，并给出提示)
 */
public class SingleLinkedListDemo {


    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "唐僧");
        HeroNode hero2 = new HeroNode(2, "孙悟空");
        HeroNode hero3 = new HeroNode(3, "猪八戒");
        HeroNode hero4 = new HeroNode(4, "沙僧");

        SingleLinkedList linkedList = new SingleLinkedList();
        // 按添加顺序插入数据
//        linkedList.add(hero1);
//        linkedList.add(hero2);
//        linkedList.add(hero3);
//        linkedList.add(hero4);

        // 按照no排序(从小到大顺序)插入
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero1);
        linkedList.show();


        // 修改数据
        System.out.println();
        HeroNode update = new HeroNode(4, "白骨精");
        linkedList.update(update);
        linkedList.show();

        // 查找数据
        System.out.println();
        HeroNode node = linkedList.find(12);
        System.out.println(node);

        // 删除数据
        linkedList.del(1);
        linkedList.show();

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

        // 添加节点方法-按no大小顺序插入
        public void addByOrder(HeroNode heroNode) {
            // 如果当前是一个空链表，则直接添加到header后面
            if (header.next == null) {
                header.next = heroNode;
                return;
            }

            HeroNode beforeNode = header;// 记录插入节点的前一个节点
            HeroNode currNode = header.next;// 作为比较的节点

            boolean isExist = false;

            while (true) {
                if (currNode != null) {
                    if (heroNode.no < currNode.no) {
                        break;
                    } else if (heroNode.no == currNode.no) {
                        isExist = true;
                        break;
                    }
                    currNode = currNode.next;
                    beforeNode = beforeNode.next;
                } else {
                    break;
                }

            }
            if (isExist) {
                System.out.println("数据已经存在了" + heroNode);
            } else {
                // 此时beforeNode节点就是应该插入的节点前面的节点
                beforeNode.next = heroNode;
                heroNode.next = currNode;
            }

        }


        // 删除节点方法
        public void del(int no) {
            // 如果当前是一个空链表，则直接添加到header后面
            if (header.next == null) {
                System.out.println("链接为空，不能删除节点 " + no);
                return;
            }

            HeroNode beforeNode = header;// 记录插入节点的前一个节点
            HeroNode currNode = header.next;// 作为比较的节点

            boolean isExist = false;

            while (true) {
                if (currNode != null) {
                    if (no == currNode.no) {
                        isExist = true;
                        break;
                    }
                    currNode = currNode.next;
                    beforeNode = beforeNode.next;
                } else {
                    break;
                }

            }
            if (isExist) {
                // 此时beforeNode节点就是应该插入的节点前面的节点
                beforeNode.next = currNode.next;
            } else {
                System.out.println("待删除节点不存在 " + no);
            }

        }


        // 修改节点方法-依据no
        public void update(HeroNode heroNode) {
            // 如果当前是一个空链表
            if (header.next == null) {
                System.out.println("当前链表为空，不能修改");
                return;
            }

            HeroNode currNode = header.next;// 作为比较的节点

            while (currNode != null) {
                if (currNode.no == heroNode.no) {
                    break;
                }
                currNode = currNode.next;
            }
            // 此时currentNode就是待修改的节点

            currNode.name = heroNode.name;
        }

        // 查找节点方法-依据no
        public HeroNode find(int no) {
            // 如果当前是一个空链表
            if (header.next == null) {
                System.out.println("当前链表为空，找不到节点 " + no);
                return null;
            }

            HeroNode currNode = header.next;// 作为比较的节点
            while (currNode != null) {
                if (currNode.no == no) {
                    break;
                }
                currNode = currNode.next;
            }
            // 此时currentNode就是待修改的节点

            return currNode;
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
