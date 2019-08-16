package com.ivyzh.datastructures.tree;

public class BinaryTree {
    public static void main(String[] args) {
        Hero hero1 = new Hero(1, "sj");
        Hero hero2 = new Hero(2, "wy");
        Hero hero3 = new Hero(3, "ljy");
        Hero hero4 = new Hero(4, "lc");
        Hero hero5 = new Hero(5, "gs");

        HeroTree tree = new HeroTree(hero1);
        hero1.left = hero2;
        hero1.right = hero3;
        hero3.left = hero5;
        hero3.right = hero4;

        System.out.println("---遍历---");
        tree.preOrder();//前序遍历
        System.out.println();
        tree.infixOrder();//中序遍历
        System.out.println();
        tree.postOrder();//后序遍历
        System.out.println();


        System.out.println("---查找---");
        Hero hero = tree.preOrderSearch(5);//前序查找，4次
        System.out.println("search hero -> " + hero);
        hero = tree.infixOrderSearch(5);//中序查找，3次
        System.out.println("search hero -> " + hero);
        hero = tree.postOrderSearch(5);//后序查找，2次
        System.out.println("search hero -> " + hero);

        System.out.println("---删除---");

        /**
         * 如果删除的节点是叶子节点，则删除该节点
         * 如果删除的节点是非叶子节点，则删除该子树.
         */
        System.out.println("删除前，前序遍历：");
        tree.preOrder();//前序遍历
        System.out.println();
        Hero node = tree.deleteNode(1);
        System.out.println("删除" + node + "后，前序遍历：");
        tree.preOrder();//前序遍历


    }

    static class HeroTree {
        Hero root;

        public HeroTree(Hero root) {
            this.root = root;
        }

        // 前序遍历
        public void preOrder() {
            if (root != null) {
                root.preOrder();
            } else {
                System.out.println("根节点为空，不能遍历");
            }
        }

        public void infixOrder() {
            if (root != null) {
                root.infixOrder();
            } else {
                System.out.println("根节点为空，不能遍历");
            }
        }

        public void postOrder() {
            if (root != null) {
                root.postOrder();
            } else {
                System.out.println("根节点为空，不能遍历");
            }
        }

        // 查找
        public Hero preOrderSearch(int no) {
            if (root != null) {
                return root.preOrderSearch(no);
            } else {
                System.out.println("根节点为空");
                return null;
            }
        }

        public Hero infixOrderSearch(int no) {
            if (root != null) {
                return root.infixOrderSearch(no);
            } else {
                System.out.println("根节点为空");
                return null;
            }
        }

        public Hero postOrderSearch(int no) {
            if (root != null) {
                return root.postOrderSearch(no);
            } else {
                System.out.println("根节点为空");
                return null;
            }
        }

        // 删除节点
        public Hero deleteNode(int no) {
            if (root != null) {
                if (root.no == no) {
                    Hero hero = root;
                    root = null;
                    return hero;
                } else {
                    return root.deleteNode(no);
                }

            } else {
                System.out.println("根节点为空");
                return null;
            }
        }
    }


    static class Hero {
        int no;
        String name;
        Hero left;
        Hero right;

        public Hero(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "{no=" + no + ", name='" + name + '\'' + '}';
        }

        // 前序遍历
        public void preOrder() {
            System.out.print(this + " -> ");//先输出自己
            if (left != null) {//如果左子树不为空，则继续递归前序遍历
                left.preOrder();
            }

            if (right != null) {//如果右子树不为空，则继续递归前序遍历
                right.preOrder();
            }

        }

        // 中序遍历
        public void infixOrder() {

            if (left != null) {//如果左子树不为空，则继续递归前序遍历
                left.infixOrder();
            }
            System.out.print(this + " -> ");//再输出自己
            if (right != null) {//如果右子树不为空，则继续递归前序遍历
                right.infixOrder();
            }
        }

        // 后序遍历
        public void postOrder() {
            if (left != null) {//如果左子树不为空，则继续递归前序遍历
                left.postOrder();
            }

            if (right != null) {//如果右子树不为空，则继续递归前序遍历
                right.postOrder();
            }
            System.out.print(this + " -> ");//再输出自己
        }


        // 前序遍历查找
        public Hero preOrderSearch(int no) {
            System.out.println("进入前序查找~~");
            if (this.no == no) {
                return this;
            }
            Hero hero = null;

            if (left != null) {//如果左子树不为空，则继续递归前序遍历查找
                hero = left.preOrderSearch(no);
            }
            if (hero != null) {
                return hero;
            }
            if (right != null) {//如果右子树不为空，则继续递归前序遍历查找
                hero = right.preOrderSearch(no);
            }
            return hero;
        }

        public Hero infixOrderSearch(int no) {

            Hero hero = null;
            if (left != null) {//如果左子树不为空，则继续递归前序遍历查找
                hero = left.infixOrderSearch(no);
            }
            if (hero != null) {
                return hero;
            }

            System.out.println("进入中序查找~~");

            if (this.no == no) {
                return this;
            }

            if (right != null) {//如果右子树不为空，则继续递归前序遍历查找
                hero = right.infixOrderSearch(no);
            }
            return hero;
        }

        public Hero postOrderSearch(int no) {
            Hero hero = null;
            if (left != null) {//如果左子树不为空，则继续递归前序遍历查找
                hero = left.postOrderSearch(no);
            }
            if (hero != null) {
                return hero;
            }
            if (right != null) {//如果右子树不为空，则继续递归前序遍历查找
                hero = right.postOrderSearch(no);
            }
            if (hero != null) {
                return hero;
            }

            System.out.println("进入后序查找~~");
            if (this.no == no) {
                return this;
            }
            return hero;
        }

        // 删除节点

        /**
         * 如果删除的节点是叶子节点，则删除该节点
         * 如果删除的节点是非叶子节点，则删除该子树.
         * 测试，删除掉 5号叶子节点 和 3号子树.
         */
        public Hero deleteNode(int no) {

            if (this.left != null && this.left.no == no) {//表示当前节点左子树是待删除节点
                Hero res = this.left;
                this.left = null;
                return res;
            }

            if (this.right != null && this.right.no == no) {//表示当前节点右子树是待删除节点
                Hero res = this.right;
                this.right = null;
                return res;
            }

            Hero hero = null;
            if (this.left != null) {
                hero = this.left.deleteNode(no);
            }

            if (hero != null) {
                return hero;
            }

            if (this.right != null) {
                hero = this.right.deleteNode(no);
            }
            return hero;
        }
    }
}
