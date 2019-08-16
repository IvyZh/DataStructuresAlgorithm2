package com.ivyzh.datastructures.tree;

/**
 * 应用案例说明：将下面的二叉树，进行中序线索二叉树。中序遍历的数列为 {8, 3, 10, 1, 14, 6}
 */
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        Hero hero1 = new Hero(1, "sj");
        Hero hero3 = new Hero(3, "wy");
        Hero hero6 = new Hero(6, "ljy");
        Hero hero8 = new Hero(8, "lc");
        Hero hero10 = new Hero(10, "gs");
        Hero hero14 = new Hero(14, "gs");
        //形成二叉树
        hero1.left = hero3;
        hero1.right = hero6;
        hero3.left = hero8;
        hero3.right = hero10;
        hero6.left = hero14;
        BinaryTree tree = new BinaryTree(hero1);


        /**
         // 接下来完成中序遍历、中序线索化、中序线索化遍历
         System.out.println("~~中序遍历、中序线索化、中序线索化遍历~~");
         tree.infixOrder();// 8 -> 3 -> 10 -> 1 -> 14 -> 6 ->
         tree.threadNodesInfixOrder();//线索化-中序
         // 验证线索化结果是否正确，以10节点为参考
         System.out.println();
         System.out.println(hero10.left + "--" + hero10.leftType);
         System.out.println(hero10.right + "--" + hero10.rightType);
         // 线索化之后就不能用原来的遍历了
         //tree.infixOrder();
         tree.threadListInfixOrder();
         */


        // 接下来完成前序遍历、前序线索化、前序线索化遍历
        System.out.println("~~前序遍历、前序线索化、前序线索化遍历~~");
        tree.preOrder();//1 -> 3 -> 8 -> 10 -> 6 -> 14 ->
        tree.threadNodesPreOrder();//线索化-前序
        // 验证线索化结果是否正确，以10节点为参考
        System.out.println();
        System.out.println(hero10.left + "--" + hero10.leftType + " 10 " + hero10.right + "--" + hero10.rightType);
        System.out.println(hero14.left + "--" + hero14.leftType + " 14 " + hero14.right + "--" + hero14.rightType);
        tree.threadListPreOrder();

        // 接下来完成后序遍历、后序线索化、后序线索化遍历
//        System.out.println("~~后序遍历、后序线索化、后序线索化遍历~~");


    }

    static class BinaryTree {
        Hero root;
        //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
        //在递归进行线索化时，pre 总是保留前一个结点
        Hero preNode;

        public BinaryTree(Hero root) {
            this.root = root;
        }

        public void infixOrder() {
            if (root == null) {
                System.out.println("二叉树为空不能遍历");
            } else {
                root.infixOrder();
            }
        }

        // 二叉树进行中序线索化的方法
        public void threadNodesInfixOrder(Hero node) {
            if (node == null) {
//                System.out.println("不能线索化");
                return;
            }

            //中序线索化
            // 1. 线索化左子树
            threadNodesInfixOrder(node.left);

            // 2. 线索化自己
            //处理当前结点的前驱结点
            //以8结点来理解,node = 8
            //8结点的.left = null , 8结点的.leftType = 1
            if (node.left == null) {
                node.left = preNode;
                node.leftType = 1;
            }

            //处理后继结点
            // 以3结点来理解  preNode = 8,node = 3
            if (preNode != null && preNode.right == null) {
                preNode.right = node;
                preNode.rightType = 1;
            }

            preNode = node;
            // 3. 线索化右子树
            threadNodesInfixOrder(node.right);

        }

        // 按照中序遍历线索化
        public void threadNodesInfixOrder() {
            this.threadNodesInfixOrder(root);
        }

        // 按照中序遍历线索化之后再进行中序遍历结果-自己写的
//        public void threadListInfixOrder() {
//            if (root == null) {
//                System.out.println("二叉树为空不能遍历");
//            } else {
//                root.threadListInfixOrder2();
//            }
//        }

        // 按照中序遍历线索化之后再进行中序遍历结果-程序给的
        public void threadListInfixOrder() {
            Hero node = root;
            while (node != null) {
                //循环的找到leftType == 1的结点，第一个找到就是8结点
                //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
                //处理后的有效结点
                while (node.leftType == 0) {
                    node = node.left;
                }
                //打印当前这个结点
                System.out.print(node + " -> ");

                //如果当前结点的右指针指向的是后继结点,就一直输出
                while (node.rightType == 1) {
                    //获取到当前结点的后继结点
                    node = node.right;
                    System.out.print(node + " -> ");
                }

                node = node.right;
            }
        }

        // 前序遍历
        public void preOrder() {
            if (root == null) {
                System.out.println("二叉树为空");
            } else {
                root.preOrder();
            }

        }

        //前序线索化
        public void threadNodesPreOrder() {
            this.threadNodesPreOrder(root);
        }

        //前序线索化
        private void threadNodesPreOrder(Hero node) {// step1. node = 8 //step5. node = 3
            if (node == null) {
                return;
            }

            //处理当前结点的前驱结点
            // 自己,以8为例子
            if (node.left == null) {// step2. 8.left->3
                node.left = preNode;
                node.leftType = 1;
            }

            //处理后继结点
            if (preNode != null && preNode.right == null) {//step6. preNode = 8,node = 3
                preNode.right = node;
                preNode.rightType = 1;
            }

            //每处理一个节点，当前节点是下一个节点的前驱节点
            preNode = node;// step0. preNode = 3; //step4. preNode = 8

            // 左子树
            if (node.leftType == 0) {
                threadNodesPreOrder(node.left);
            }
            // 右子树
            if (node.rightType == 0) {
                threadNodesPreOrder(node.right);
            }


        }

        // 前序遍历线索二叉树（按照后继线索遍历）
        public void threadListPreOrder() {
            threadListPreOrder(root);
        }

        //前序遍历线索二叉树（按照后继线索遍历）
        //1 -> 3 -> 8 -> 10 -> 6 -> 14
        private void threadListPreOrder(Hero node) {
            while (node != null) {
                while (node.leftType == 0) {
                    System.out.print(node + " -> ");
                    node = node.left;
                }
                System.out.print(node + " -> ");
                node = node.right;
            }
        }
    }

    static class Hero {
        int no;
        String name;
        Hero root;//记录root，方便中序遍历线索二叉树
        Hero left;
        Hero right;
        int leftType;//0表示左子树，1表示前驱节点
        int rightType;//0表示左子树，1表示前驱节点

        public Hero(int no, String name) {
            this.no = no;
            this.name = name;
        }


        // 中序遍历
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.print(this + " -> ");
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return no + "";
        }

        // 中序遍历-线索化之后调用此方法-自己写的
        public void threadListInfixOrder2() {
            if (this.left != null && this.leftType == 0) {
                this.left.threadListInfixOrder2();
            }
            System.out.print(this + " -> ");
            if (this.right != null && this.rightType == 0) {
                this.right.threadListInfixOrder2();
            }
        }

        // 前序遍历
        public void preOrder() {// 1 3 8 10 6 14
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
