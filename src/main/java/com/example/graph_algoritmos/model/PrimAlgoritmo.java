package com.example.graph_algoritmos.model;

import java.util.Arrays;

public class PrimAlgoritmo {
    public static Graph primMST(Graph graph, String startNode) {
        int n = graph.getNodeCount();
        int[][] adjMatrix = graph.getAdjacencyMatrix();
        boolean[] inMST = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        int startIndex = graph.getNodeIndex(startNode);
        key[startIndex] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, inMST, n);
            inMST[u] = true;

            for (int v = 0; v < n; v++) {
                if (adjMatrix[u][v] != 0 && !inMST[v] && adjMatrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = adjMatrix[u][v];
                }
            }
        }

        Graph mst = new Graph(n);
        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                mst.addEdge(graph.getNode(parent[i]), graph.getNode(i), adjMatrix[i][parent[i]]);
            }
        }

        return mst;
    }

    private static int minKey(int[] key, boolean[] inMST, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < n; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }
}





