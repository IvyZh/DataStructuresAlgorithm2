package com.ivyzh.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphDemo {
    public static void main(String[] args) {
//        String[] vertexs = {"A", "B", "C", "D", "E"};
//        Graph graph = new Graph(vertexs.length);
//        for (String s : vertexs) {
//            graph.insertVertex(s);
//        }
//        // ab ac bc bd be
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
//        // dfs:A -> B -> C -> D -> E ->
//        graph.show();
//
//        //System.out.println("图的深度遍历");
//        //graph.dfs();//A -> B -> C -> D -> E ->
//        System.out.println("图的广度度遍历");
//        graph.bfs();//A -> B -> C -> D -> E ->


        //图的深度优先VS 广度优先
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(vertexs.length);
        for (String s : vertexs) {
            graph.insertVertex(s);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.show();

//        System.out.println("图的深度遍历");
//        graph.dfs();//1 -> 2 -> 4 -> 8 -> 5 -> 3 -> 6 -> 7 ->
        System.out.println("图的广度度遍历");
        graph.bfs();//1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 ->


    }

    static class Graph {
        List<String> vertexList;//存放顶点集合
        int[][] edges;//存储图对应的邻接矩阵
        int numOfEdges;//存储边的数目
        boolean[] isVisited;


        public Graph(int n) {
            edges = new int[n][n];
            vertexList = new ArrayList<>();
            numOfEdges = 0;
            isVisited = new boolean[n];
        }

        public void insertVertex(String s) {
            vertexList.add(s);
        }

        public void insertEdge(int v1, int v2, int weight) {
            edges[v1][v2] = weight;
            edges[v2][v1] = weight;
            numOfEdges++;
        }

        public int getNumOfEdges() {
            return numOfEdges;
        }

        public int getNumOfVertex() {
            return vertexList.size();
        }

        public int getWight(int v1, int v2) {
            return edges[v1][v2];
        }


        public void show() {
            for (int[] arr : edges) {
                System.out.println(Arrays.toString(arr));
            }
        }

        // 深度优先遍历
        public void dfs() {
            dfs(isVisited, 0);
        }

        private void dfs(boolean[] isVisited, int index) {
            String c = getValueByIndex(index);
            System.out.print(c + " -> ");
            isVisited[index] = true;

            int w = getFirstNeighbor(index);
            while (w != -1) {
                if (!isVisited[w]) {
                    dfs(isVisited, w);
                }
                // 如果w节点已经被访问了，就寻找i-w路径
                w = getNextNeighbor(index, w);
            }
        }

        // 根据前面一个邻接节点的坐标获取下一个邻接节点
        private int getNextNeighbor(int v1, int v2) {

            for (int i = v2 + 1; i < vertexList.size(); i++) {
                if (edges[v1][i] > 0) {
                    return i;
                }
            }
            return -1;
        }

        // 返回下一个临近节点
        private int getFirstNeighbor(int index) {
            for (int i = 0; i < vertexList.size(); i++) {
                if (edges[index][i] > 0) {
                    return i;
                }
            }
            return -1;
        }

        private String getValueByIndex(int index) {
            return vertexList.get(index);
        }

        // 广度优先遍历
        public void bfs() {
            for (int i = 0; i < getNumOfVertex(); i++) {
                if (!isVisited[i]) {
                    bfs(isVisited, i);
                }
            }
        }

        // 广度优先遍历

        /**
         * 1.	访问初始结点v并标记结点v为已访问。
         * 2.	结点v入队列
         * 3.	当队列非空时，继续执行，否则算法结束。
         * 4.	出队列，取得队头结点u。
         * 5.	查找结点u的第一个邻接结点w。
         * 6.	若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
         * 1)	若结点w尚未被访问，则访问结点w并标记为已访问。
         * 2)	结点w入队列
         * 3)	查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
         */
        private void bfs(boolean[] isVisited, int index) {

            int u;//表示队列的头节点对应的下标
            int w;//邻接节点w

            LinkedList<Object> queue = new LinkedList<>();

            System.out.print(getValueByIndex(index) + " -> ");
            isVisited[index] = true;
            queue.addLast(index);
            while (!queue.isEmpty()) {
                u = (int) queue.removeFirst();
                w = getFirstNeighbor(u);
                while (w != -1) {
                    if (!isVisited[w]) {
                        System.out.print(getValueByIndex(w) + " -> ");
                        isVisited[w] = true;
                        queue.addLast(w);
                    }

                    w = getNextNeighbor(u, w);
                }
            }

        }
    }
}
