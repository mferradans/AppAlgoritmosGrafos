package com.example.graph_algoritmos.model;

import java.util.*;

public class FordFulkersonAlgoritmo {
    public static Map<String, Object> fordFulkerson(Graph graph, String source, String sink) {
        Map<String, Map<String, Integer>> residualGraph = new HashMap<>();
        Map<String, Map<String, Integer>> flow = new HashMap<>();
        List<String> augmentingPaths = new ArrayList<>();

        // Inicializar el grafo residual y el flujo con nodos del grafo original
        for (String node : graph.getNodes()) {
            residualGraph.put(node, new HashMap<>());
            flow.put(node, new HashMap<>());
        }

        // Inicializar las capacidades y flujos iniciales
        for (String node : graph.getNodes()) {
            for (Graph.Edge edge : graph.getEdges(node)) {
                residualGraph.get(node).put(edge.end, edge.weight);
                if (!residualGraph.containsKey(edge.end)) {
                    residualGraph.put(edge.end, new HashMap<>());
                }
                if (!flow.containsKey(edge.end)) {
                    flow.put(edge.end, new HashMap<>());
                }
                residualGraph.get(edge.end).putIfAbsent(node, 0); // Asegura que la arista inversa esté inicializada
                flow.get(node).put(edge.end, 0);
                flow.get(edge.end).putIfAbsent(node, 0);
            }
        }

        int maxFlow = 0;
        List<String> path = new ArrayList<>();

        // Mientras haya un camino aumentante desde la fuente hasta el sumidero
        while (dfs(residualGraph, source, sink, path, new HashSet<>())) {
            // Encontrar la capacidad mínima en el camino aumentante
            int pathFlow = Integer.MAX_VALUE;
            for (int i = 0; i < path.size() - 1; i++) {
                String u = path.get(i);
                String v = path.get(i + 1);
                pathFlow = Math.min(pathFlow, residualGraph.get(u).get(v));
            }

            // Registrar el camino aumentante y su flujo
            augmentingPaths.add(String.join("->", path) + ": " + pathFlow);

            // Actualizar las capacidades residuales en el grafo residual y el flujo en el grafo de flujo
            for (int i = 0; i < path.size() - 1; i++) {
                String u = path.get(i);
                String v = path.get(i + 1);
                residualGraph.get(u).put(v, residualGraph.get(u).get(v) - pathFlow);
                residualGraph.get(v).put(u, residualGraph.get(v).get(u) + pathFlow);
                flow.get(u).put(v, flow.get(u).get(v) + pathFlow);
                flow.get(v).put(u, flow.get(v).get(u) - pathFlow);
            }

            maxFlow += pathFlow;
            path.clear();
        }

        // Preparar el resultado final
        Map<String, Object> result = new HashMap<>();
        result.put("flow", flow);
        result.put("maxFlow", maxFlow);
        result.put("augmentingPaths", augmentingPaths);
        return result;
    }

    // Búsqueda en profundidad (DFS) para encontrar un camino aumentante
    private static boolean dfs(Map<String, Map<String, Integer>> residualGraph, String current, String sink, List<String> path, Set<String> visited) {
        path.add(current);
        visited.add(current);

        if (current.equals(sink)) {
            return true;
        }

        for (Map.Entry<String, Integer> neighbor : residualGraph.get(current).entrySet()) {
            if (!visited.contains(neighbor.getKey()) && neighbor.getValue() > 0) {
                if (dfs(residualGraph, neighbor.getKey(), sink, path, visited)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}











