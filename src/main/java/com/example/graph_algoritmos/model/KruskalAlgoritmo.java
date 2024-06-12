package com.example.graph_algoritmos.model;

import java.util.*;

public class KruskalAlgoritmo {
    public static Graph kruskalMST(Graph graph) {
        Graph mst = new Graph();
        List<Graph.Edge> edges = new ArrayList<>(graph.getAllEdges());
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();

        for (String node : graph.getNodes()) {
            parent.put(node, node);
            rank.put(node, 0);
        }

        for (Graph.Edge edge : edges) {
            String root1 = find(parent, edge.start);
            String root2 = find(parent, edge.end);

            if (!root1.equals(root2)) {
                mst.addEdge(edge.start, edge.end, edge.weight);
                union(parent, rank, root1, root2);
            }
        }

        return mst;
    }

    private static String find(Map<String, String> parent, String node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent, parent.get(node)));
        }
        return parent.get(node);
    }

    private static void union(Map<String, String> parent, Map<String, Integer> rank, String root1, String root2) {
        if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }
}

