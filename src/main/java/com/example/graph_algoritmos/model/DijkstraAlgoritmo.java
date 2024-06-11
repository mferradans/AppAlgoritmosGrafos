package com.example.graph_algoritmos.model;

import java.util.*;

public class DijkstraAlgoritmo {
    public static Map<String, Object> dijkstra(Graph graph, String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<String> visited = new HashSet<>();

        // Inicializar distancias a infinito y la distancia del nodo inicial a 0
        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        priorityQueue.add(new Graph.Edge(start, start, 0));

        while (!priorityQueue.isEmpty()) {
            Graph.Edge edge = priorityQueue.poll();
            if (visited.contains(edge.end)) continue;

            visited.add(edge.end);
            for (Graph.Edge nextEdge : graph.getEdges(edge.end)) {
                if (!visited.contains(nextEdge.end)) {
                    // Asegurarse de que nextEdge.end está en el mapa de distancias
                    distances.putIfAbsent(nextEdge.end, Integer.MAX_VALUE);
                    int newDist = distances.get(edge.end) + nextEdge.weight;
                    if (newDist < distances.get(nextEdge.end)) {
                        distances.put(nextEdge.end, newDist);
                        previousNodes.put(nextEdge.end, edge.end);
                        priorityQueue.add(new Graph.Edge(edge.end, nextEdge.end, newDist));
                    }
                }
            }
        }

        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        Map<String, Object> result = new HashMap<>();
        result.put("distances", distances);
        result.put("path", path);
        return result;
    }
}


