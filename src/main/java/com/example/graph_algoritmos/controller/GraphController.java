package com.example.graph_algoritmos.controller;

import com.example.graph_algoritmos.model.Graph;
import com.example.graph_algoritmos.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
@Controller
public class GraphController {

    @Autowired
    private GraphService graphService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/add-edge")
    public String addEdge(@RequestParam String start, @RequestParam String end, @RequestParam int weight, Model model) {
        graphService.addEdge(start, end, weight);
        model.addAttribute("message", "Arista añadida exitosamente");
        return "index";
    }

    @PostMapping("/remove-node")
    public String removeNode(@RequestParam String node, Model model) {
        graphService.removeNode(node);
        model.addAttribute("message", "Nodo removido exitosamente");
        return "index";
    }

    @PostMapping("/reset-graph")
    public String resetGraph(Model model) {
        graphService.resetGraph();
        model.addAttribute("message", "Grafo reseteado exitosamente");
        return "index";
    }

    @GetMapping("/run-algorithm")
    public String runAlgorithm(@RequestParam String algorithm, @RequestParam(required = false) String start, @RequestParam(required = false) String end, Model model) {
        model.addAttribute("algorithm", algorithm);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "result";
    }

    @GetMapping("/graph-data")
    @ResponseBody
    public Map<String, Object> getGraphData() {
        return convertGraphToVisFormat(graphService.getGraph());
    }

    @PostMapping("/update-edge-weight")
    @ResponseBody
    public void updateEdgeWeight(@RequestBody Map<String, Object> payload) {
        String start = (String) payload.get("start");
        String end = (String) payload.get("end");
        int weight = (int) payload.get("weight");
        graphService.updateEdgeWeight(start, end, weight);
    }

    @GetMapping("/algorithm-result")
    @ResponseBody
    public Map<String, Object> getAlgorithmResult(@RequestParam String algorithm, @RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        switch (algorithm) {
            case "Kruskal":
                return convertKruskalResultToVisFormat(graphService.getKruskalMST());
            case "Dijkstra":
                if (start == null || end == null) {
                    throw new IllegalArgumentException("Nodos de inicio y de fin deben ser especificados para Dijkstra");
                }
                return convertDijkstraResultToVisFormat(graphService.getDijkstraShortestPaths(start, end));
            case "Ford-Fulkerson":
                if (start == null || end == null) {
                    throw new IllegalArgumentException("Nodos de inicio y de fin deben ser especificados para Ford-Fulkerson");
                }
                Map<String, Object> fordResult = graphService.getFordFulkersonMaxFlow(start, end);
                return convertFordFulkersonResultToVisFormat(graphService.getGraph(), fordResult);
            default:
                throw new IllegalArgumentException("Algoritmo desconocido: " + algorithm);
        }
    }

    private Map<String, Object> convertGraphToVisFormat(Graph graph) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        for (String node : graph.getNodes()) {
            nodes.add(Map.of("id", node, "label", node));
        }

        Set<String> addedEdges = new HashSet<>();
        for (Graph.Edge edge : graph.getAllEdges()) {
            String edgeId = edge.start + "-" + edge.end;
            if (addedEdges.add(edgeId)) {
                edges.add(Map.of(
                        "id", edgeId,
                        "from", edge.start,
                        "to", edge.end,
                        "label", String.valueOf(edge.weight),
                        "weight", edge.weight
                ));
            }
        }

        result.put("nodes", nodes);
        result.put("edges", edges);
        return result;
    }

    private Map<String, Object> convertKruskalResultToVisFormat(Graph graph) {
        Map<String, Object> result = convertGraphToVisFormat(graph);
        result.put("totalWeight", graph.getAllEdges().stream().mapToInt(e -> e.weight).sum());
        result.put("detailedEdges", graph.getAllEdges().stream().map(edge -> Map.of(
                "from", edge.start,
                "to", edge.end,
                "weight", edge.weight
        )).collect(Collectors.toList()));
        return result;
    }

    private Map<String, Object> convertDijkstraResultToVisFormat(Map<String, Object> dijkstraResult) {
        List<Map<String, String>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        Map<String, Integer> distances = (Map<String, Integer>) dijkstraResult.get("distances");
        List<String> path = (List<String>) dijkstraResult.get("path");

        distances.forEach((key, value) -> nodes.add(Map.of("id", key, "label", key + " (" + value + ")")));

        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            edges.add(Map.of(
                    "from", from,
                    "to", to,
                    "label", String.valueOf(distances.get(to) - distances.get(from))
            ));
        }

        return Map.of(
                "nodes", nodes,
                "edges", edges,
                "path", path,
                "totalWeight", distances.get(path.get(path.size() - 1))
        );
    }

    private Map<String, Object> convertFordFulkersonResultToVisFormat(Graph graph, Map<String, Object> fordResult) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        for (String node : graph.getNodes()) {
            nodes.add(Map.of("id", node, "label", node));
        }

        Map<String, Map<String, Integer>> flow = (Map<String, Map<String, Integer>>) fordResult.get("flow");
        flow.forEach((start, targets) -> {
            targets.forEach((end, f) -> {
                if (f > 0) {
                    edges.add(Map.of(
                            "from", start,
                            "to", end,
                            "label", String.valueOf(f),
                            "weight", f
                    ));
                }
            });
        });

        result.put("nodes", nodes);
        result.put("edges", edges);
        result.put("maxFlow", fordResult.get("maxFlow"));
        result.put("augmentingPaths", fordResult.get("augmentingPaths"));
        return result;
    }
}









