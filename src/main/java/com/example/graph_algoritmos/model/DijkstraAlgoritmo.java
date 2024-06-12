package com.example.graph_algoritmos.model;

import java.util.*;

public class DijkstraAlgoritmo {
    // Implementa el algoritmo de Dijkstra para encontrar la ruta más corta desde 'start' hasta 'end'
    public static Map<String, Object> dijkstra(Graph graph, String start, String end) {
        // Distancias más cortas conocidas desde el nodo inicial
        Map<String, Integer> distances = new HashMap<>();
        // Nodo previo en la ruta más corta hacia cada nodo
        Map<String, String> previousNodes = new HashMap<>();
        // Cola de prioridad para seleccionar el nodo con la menor distancia acumulada
        PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        // Conjunto de nodos visitados
        Set<String> visited = new HashSet<>();

        // Inicializar distancias a infinito y la distancia del nodo inicial a 0
        graph.getNodes().forEach(node -> distances.put(node, Integer.MAX_VALUE));
        distances.put(start, 0);
        priorityQueue.add(new Graph.Edge(start, start, 0));

        // Procesar la cola de prioridad
        while (!priorityQueue.isEmpty()) {
            Graph.Edge edge = priorityQueue.poll();
            if (visited.contains(edge.end)) continue; // Saltar nodos visitados

            visited.add(edge.end);
            for (Graph.Edge nextEdge : graph.getEdges(edge.end)) {
                if (!visited.contains(nextEdge.end)) {
                    int newDist = distances.get(edge.end) + nextEdge.weight;
                    if (newDist < distances.get(nextEdge.end)) {
                        distances.put(nextEdge.end, newDist);
                        previousNodes.put(nextEdge.end, edge.end);
                        priorityQueue.add(new Graph.Edge(edge.end, nextEdge.end, newDist));
                    }
                }
            }
        }

        // Rastrear la ruta más corta desde 'end' hasta 'start'
        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        // Preparar el resultado final con distancias y la ruta más corta
        Map<String, Object> result = new HashMap<>();
        result.put("distances", distances);
        result.put("path", path);
        return result;
    }
}
