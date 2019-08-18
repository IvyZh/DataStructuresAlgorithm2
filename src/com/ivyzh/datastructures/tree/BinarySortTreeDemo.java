package com.ivyzh.datastructures.tree;

/**
 * 二叉排序树
 * 一个数组创建成对应的二叉排序树，并使用中序遍历二叉排序树，比如: 数组为 Array(7, 3, 10, 12, 5, 1, 9) ，
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};

        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            tree.add(node);
        }

        tree.infixOrder();//1 -> 3 -> 5 -> 7 -> 9 -> 10 -> 12 ->


        //1.删除叶子节点 (比如：2, 5, 9, 12)
        System.out.println("\n删除叶子节点：");
        tree.delNode(2);
        tree.infixOrder();
        //2.删除只有一颗子树的节点 (比如：1)
        System.out.println("\n删除只有一颗子树的节点：");
        tree.delNode(1);
        tree.infixOrder();


        //3.删除有两颗子树的节点. (比如：7, 3，10 )
        System.out.println("\n删除有两颗子树的节点：");
        tree.delNode(3);
        tree.infixOrder();


        //System.out.println("\n-----测试-----");
        //tree.delNode(2);
        //tree.delNode(5);
        //tree.delNode(9);
        //tree.delNode(12);
        //tree.delNode(7);
        //tree.delNode(3);
        //tree.delNode(10);
        //tree.delNode(1);
        //tree.infixOrder();

    }

    static class BinarySortTree {
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

        // 查找要删除的节点，有则返回，没有返回null
        public Node search(int value) {
            if (root == null) {
                return null;
            } else {
                return root.search(value);
            }
        }

        // 查找要删除的节点的父亲节点，有则返回，没有返回null
        public Node searchParent(int value) {
            if (root == null) {
                return null;
            } else {
                return root.searchParent(value);
            }
        }

        // 返回以node为根节点的二叉排序树的最小节点的值,从右子树中找到最小值
        public int delRightTreeMin(Node node) {
            Node targetNode = node;
            while (targetNode.left != null) {
                targetNode = targetNode.left;
            }
            delNode(targetNode.value);
            return targetNode.value;
        }

        // 删除节点
        public void delNode(int value) {
            if (root == null) {
                return;
            } else {
                Node targetNode = search(value);
                if (targetNode == null) {
                    return;
                }

                //如果只有root一个节点的情况
                if (root.left == null && root.right == null) {
                    root = null;
                    return;
                }


                Node parentNode = searchParent(value);
                // 判断parentNode和targetNode的关系

                if (targetNode.left == null && targetNode.right == null) {//是叶子节点
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = null;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        parentNode.right = null;
                    }
                } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的节点
                    //方案1：右子树中最小值
                    int minValue = delRightTreeMin(targetNode.right);
                    targetNode.value = minValue;
                    //方案2：左子树中最大值 TODO


                } else {//删除只有一个子树的节点
                    if (targetNode.left != null) {//左边不为空

                        if (parentNode != null) {
                            if (parentNode.left.value == value) {
                                parentNode.left = targetNode.left;
                            } else {
                                parentNode.right = targetNode.left;
                            }
                        } else {
                            root = targetNode.left;
                        }

                    } else {
                        if (parentNode != null) {
                            if (parentNode.left.value == value) {
                                parentNode.left = targetNode.right;
                            } else {
                                parentNode.right = targetNode.right;
                            }
                        } else {
                            root = targetNode.right;
                        }
                    }

                }

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
        }


        // 查找要删除的节点，有则返回，没有返回null
        public Node search(int value) {
            if (this.value == value) {
                return this;
            } else if (value < this.value) {
                if (this.left != null) {
                    return this.left.search(value);
                } else {
                    return null;
                }
            } else {
                if (this.right != null) {
                    return this.right.search(value);
                } else {
                    return null;
                }
            }

        }


        // 查找要删除的节点的父亲节点，有则返回，没有返回null
        public Node searchParent(int value) {
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (value < this.value && this.left != null) {
                    return this.left.searchParent(value);
                } else if (value > this.value && this.right != null) {
                    return this.right.searchParent(value);
                } else {
                    return null;
                }
            }
        }
    }
}
