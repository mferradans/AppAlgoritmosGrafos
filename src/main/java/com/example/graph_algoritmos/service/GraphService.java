package com.example.graph_algoritmos.service;

import com.example.graph_algoritmos.model.DijkstraAlgoritmo;
import com.example.graph_algoritmos.model.FordFulkersonAlgoritmo;
import com.example.graph_algoritmos.model.Graph;
import com.example.graph_algoritmos.model.PrimAlgoritmo;
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
/*
    public void updateEdgeWeight(String start, String end, int weight) {
        graph.updateEdgeWeight(start, end, weight);
    }

    public void removeNode(String node) {
        graph.removeNode(node);
    }
*/
    public void resetGraph() {
        this.graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }

    public Graph getPrimMST(String startNode) {
        return PrimAlgoritmo.primMST(graph, startNode);
    }

    public Map<String, Object> getDijkstraShortestPaths(String start, String end) {
        return DijkstraAlgoritmo.dijkstra(graph, start, end);
    }

    public Map<String, Object> getFordFulkersonMaxFlow(String source, String sink) {
        return FordFulkersonAlgoritmo.fordFulkerson(graph, source, sink);
    }
}








