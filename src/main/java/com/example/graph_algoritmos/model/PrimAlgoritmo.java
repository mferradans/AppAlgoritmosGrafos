package com.example.graph_algoritmos.model;

import java.util.*;

public class PrimAlgoritmo {
    // Implementa el algoritmo de Prim para encontrar el Árbol de Expansión Mínima (MST)
    public static Graph primMST(Graph graph, String startNode) {
        Graph mst = new Graph(); // Grafo resultante para el MST
        Set<String> visited = new HashSet<>(); // Conjunto de nodos visitados
        PriorityQueue<Graph.Edge> edges = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight)); // Cola de prioridad para aristas

        // Comienza desde el nodo inicial
        visited.add(startNode);
        edges.addAll(graph.getEdges(startNode)); // Añadir aristas del nodo inicial a la cola de prioridad

        // Procesar aristas en orden de peso ascendente
        while (!edges.isEmpty() && visited.size() < graph.getNodes().size()) {
            Graph.Edge edge = edges.poll(); // Extrae la arista con el menor peso
            if (!visited.contains(edge.end)) {
                visited.add(edge.end); // Marca el nodo final como visitado
                mst.addEdge(edge.start, edge.end, edge.weight); // Añade la arista al MST
                // Añade todas las aristas del nuevo nodo visitado que no llevan a nodos ya visitados
                graph.getEdges(edge.end).stream().filter(nextEdge -> !visited.contains(nextEdge.end)).forEach(edges::add);
            }
        }

        return mst; // Devuelve el MST resultante
    }
}
