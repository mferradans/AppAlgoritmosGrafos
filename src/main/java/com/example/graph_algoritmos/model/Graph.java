package com.example.graph_algoritmos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, Integer> nodeIndex;
    private final List<String> indexToNode;
    private final int[][] adjacencyMatrix;
    private int nodeCount;

    public Graph(int maxNodes) {
        nodeIndex = new HashMap<>();
        indexToNode = new ArrayList<>();
        adjacencyMatrix = new int[maxNodes][maxNodes];
        nodeCount = 0;
    }

    public void addNode(String node) {
        if (!nodeIndex.containsKey(node)) {
            nodeIndex.put(node, nodeCount);
            indexToNode.add(node);
            nodeCount++;
        }
    }

    public void addEdge(String start, String end, int weight) {
        addNode(start);
        addNode(end);
        int startIndex = nodeIndex.get(start);
        int endIndex = nodeIndex.get(end);
        adjacencyMatrix[startIndex][endIndex] = weight;
        adjacencyMatrix[endIndex][startIndex] = weight; // Para grafo no dirigido
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public List<String> getNodes() {
        return indexToNode;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public String getNode(int index) {
        return indexToNode.get(index);
    }

    public int getNodeIndex(String node) {
        return nodeIndex.get(node);
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        boolean[][] visited = new boolean[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (adjacencyMatrix[i][j] != 0 && !visited[i][j]) {
                    totalWeight += adjacencyMatrix[i][j];
                    visited[i][j] = true;
                    visited[j][i] = true; // Marcar la arista en ambas direcciones como visitada
                }
            }
        }
        return totalWeight;
    }
}





