package com.example.graph_algoritmos2.service;

import com.example.graph_algoritmos2.model.DijkstraAlgoritmo;
import com.example.graph_algoritmos2.model.FordFulkersonAlgoritmo;
import com.example.graph_algoritmos2.model.Graph;
import com.example.graph_algoritmos2.model.KruskalAlgoritmo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GraphService {

    private Graph graph;

    public GraphService() {
        this.graph = new Graph();
    }

    public void addEdge(String start, String end, int weight) {
        graph.addEdge(start, end, weight);
    }

    public void updateEdgeWeight(String start, String end, int weight) {
        graph.updateEdgeWeight(start, end, weight);
    }

    public void removeNode(String node) {
        graph.removeNode(node);
    }

    public void resetGraph() {
        this.graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }

    public Graph getKruskalMST() {
        return KruskalAlgoritmo.kruskalMST(graph);
    }

    public Map<String, Object> getDijkstraShortestPaths(String start, String end) {
        return DijkstraAlgoritmo.dijkstra(graph, start, end);
    }

    public Map<String, Object> getFordFulkersonMaxFlow(String source, String sink) {
        return FordFulkersonAlgoritmo.fordFulkerson(graph, source, sink);
    }
}







