package com.example.graph_algoritmos.model;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String start, String end, int weight) {
        adjacencyList.computeIfAbsent(start, k -> new ArrayList<>()).add(new Edge(start, end, weight));
        adjacencyList.computeIfAbsent(end, k -> new ArrayList<>());
    }

    public void updateEdgeWeight(String start, String end, int weight) {
        List<Edge> edges = adjacencyList.get(start);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.end.equals(end)) {
                    edge.weight = weight;
                    break;
                }
            }
        }
    }

    public void removeNode(String node) {
        adjacencyList.values().forEach(edges -> edges.removeIf(edge -> edge.start.equals(node) || edge.end.equals(node)));
        adjacencyList.remove(node);
    }

    public void removeEdge(String start, String end) {
        List<Edge> startEdges = adjacencyList.get(start);
        if (startEdges != null) {
            startEdges.removeIf(edge -> edge.end.equals(end));
        }
    }

    public List<Edge> getEdges(String node) {
        return adjacencyList.get(node);
    }

    public Set<String> getNodes() {
        return adjacencyList.keySet();
    }

    public List<Edge> getAllEdges() {
        Set<Edge> allEdges = new HashSet<>();
        for (List<Edge> edges : adjacencyList.values()) {
            allEdges.addAll(edges);
        }
        return new ArrayList<>(allEdges);
    }

    public static class Edge {
        public final String start;
        public final String end;
        public int weight;

        public Edge(String start, String end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight &&
                    Objects.equals(start, edge.start) &&
                    Objects.equals(end, edge.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, weight);
        }
    }
}


