package com.ivyzh.datastructures.tree.avl;

/**
 * 平衡二叉树
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};//演示左旋转情况  4 1 3 -> 3 2 3
        //int[] arr = {10, 12, 8, 9, 7, 6};//演示右旋转情况  4 1 3 -> 3 2 2
//        int[] arr = {10, 11, 7, 6, 8, 9};  //运行原来的代码可以看到，并没有转成 AVL树.
        int[] arr = {2, 1, 6, 5, 7, 3}; // 运行原来的代码可以看到，并没有转成 AVL树

        AVLTree tree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            tree.add(node);
        }
        tree.infixOrder();//小到大
        System.out.println();
        System.out.println("树的高度：" + tree.root.height());// 4,3
        System.out.println("树的左子树高度：" + tree.root.leftHeight());//1,2
        System.out.println("树的右子树高度：" + tree.root.rightHeight());//3,3
        tree.infixOrder();//小到大

    }

    static class AVLTree {
        Node root;

        // 添加元素
        public void add(Node node) {
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        // 中序遍历
        public void infixOrder() {
            if (root == null) {
                System.out.println("BST为空，不能遍历");
            } else {
                root.infixOrder(root);
            }
        }
    }


    static class Node {
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

        //返回以当前节点为根节点的高度
        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }

        // 返回左子树的高度
        public int leftHeight() {
            if (left == null) {
                return 0;
            }
            return left.height();
        }

        // 返回右子树的高度
        public int rightHeight() {
            if (right == null) {
                return 0;
            }
            return right.height();
        }


        // 中序遍历
        public void infixOrder(Node node) {
            if (node.left != null) {
                infixOrder(node.left);
            }
            System.out.print(node + " -> ");
            if (node.right != null) {
                infixOrder(node.right);
            }
        }

        // 添加节点
        public void add(Node node) {
            if (node.value < this.value) {
                if (this.left != null) {
                    this.left.add(node);
                } else {
                    this.left = node;
                }
            } else {
                if (this.right != null) {
                    this.right.add(node);
                } else {
                    this.right = node;
                }
            }

            if (rightHeight() - leftHeight() > 1) {//右边比左边大于1的高度，则需要进行左旋转
                //先要判断，如果当前节点的右子树高度>左子树高度
                if (right != null && right.leftHeight() > right.rightHeight()) {
                    // 需要先对当前节点的右子树进行->右旋转
                    right.rightRotate();
                    //在对当前节点进行右旋转
                    leftRotate();
                } else {
                    //否则直接进行左旋转
                    leftRotate();
                }
                return;//!!!重要，必须要
            }

            if (leftHeight() - rightHeight() > 1) {//左边比右边大于1的高度，则需要进行右旋转
                //先要判断，如果当前节点的右子树高度>左子树高度
                if (left != null && left.rightHeight() > left.leftHeight()) {
                    // 需要先对当前节点的左子树进行->左旋转
                    left.leftRotate();
                    //在对当前节点进行右旋转
                    rightRotate();
                } else {
                    //否则直接进行右旋转
                    rightRotate();
                }
            }
        }

        //右旋转
        private void rightRotate() {
            System.out.println("进行右旋转");
            //步骤
            //1. 创建新的节点newNode值等于当前节点K来表示
            //2. newNode的右子树设置成K的右子树
            //3. newNode的左子树设置成K的左子树的右子树
            //4. K的值设成左子节点的值
            //5. K的左子树设置成K的左子树的左子树
            //6. K的右子树设成newNode
            Node newNode = new Node(value);
            newNode.right = right;
            newNode.left = left.right;
            value = left.value;
            left = left.left;
            right = newNode;
        }

        //左旋转
        private void leftRotate() {
            System.out.println("进行左旋转");
            //步骤
            //1. 创建新的节点newNode值等于当前节点K表示
            //2. newNode的左子树设置成K的左子树
            //3. newNode的右子树设置成K的右子树的左子树
            //4. K的值设成右子节点的值
            //5. K的右子树设置成K的右子树的右子树
            //6. K的左子树设成newNode
            Node newNode = new Node(value);
            newNode.left = left;
            newNode.right = right.left;
            value = right.value;
            right = right.right;
            left = newNode;
        }
    }
}
