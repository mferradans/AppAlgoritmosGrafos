package com.example.graph_algoritmos;

import com.example.graph_algoritmos.model.DijkstraAlgoritmo;
import com.example.graph_algoritmos.model.FordFulkersonAlgoritmo;
import com.example.graph_algoritmos.model.Graph;
import com.example.graph_algoritmos.model.PrimAlgoritmo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GraphAlgoritmosApplication {

	public static void main(String[] args) {
		Graph graphA = new Graph(9);
		graphA.addEdge("S", "2", 3);
		graphA.addEdge("S", "3", 5);
		graphA.addEdge("S", "4", 5);
		graphA.addEdge("2", "7", 3);
		graphA.addEdge("2", "3", 9);
		graphA.addEdge("3", "4", 5);
		graphA.addEdge("3", "6", 5);
		graphA.addEdge("3", "8", 3);
		graphA.addEdge("4", "6", 15);
		graphA.addEdge("6", "8", 5);
		graphA.addEdge("6", "T", 8);
		graphA.addEdge("7", "5", 3);
		graphA.addEdge("7", "8", 5);
		graphA.addEdge("8", "T", 6);
		graphA.addEdge("5", "T", 5);

		Graph graphB= new Graph(8);
		graphB.addEdge("S", "2", 3);
		graphB.addEdge("S", "4", 5);
		graphB.addEdge("2", "3", 3);
		graphB.addEdge("2", "4", 9);
		graphB.addEdge("2", "T", 7);
		graphB.addEdge("2", "5", 8);
		graphB.addEdge("3", "4", 7);
		graphB.addEdge("3", "T", 7);
		graphB.addEdge("4", "6", 6);
		graphB.addEdge("4", "T", 18);
		graphB.addEdge("5", "T", 5);
		graphB.addEdge("6", "7", 5);
		graphB.addEdge("7", "T", 9);
		graphB.addEdge("6", "T", 8);

		Graph graphC = new Graph(12);
		graphC.addEdge("S", "2", 3);
		graphC.addEdge("S", "3", 3);
		graphC.addEdge("S", "5", 3);
		graphC.addEdge("2", "7", 9);
		graphC.addEdge("2", "4", 2);
		graphC.addEdge("2", "3", 9);
		graphC.addEdge("3", "4", 3);
		graphC.addEdge("3", "8", 3);
		graphC.addEdge("3", "6", 2);
		graphC.addEdge("3", "9", 7);
		graphC.addEdge("3", "5", 2);
		graphC.addEdge("4", "7", 2);
		graphC.addEdge("5", "9", 9);
		graphC.addEdge("6", "8", 1);
		graphC.addEdge("6", "9", 3);
		graphC.addEdge("7", "11", 9);
		graphC.addEdge("7", "10", 2);
		graphC.addEdge("7", "8", 5);
		graphC.addEdge("8", "T", 5);
		graphC.addEdge("8", "9", 5);
		graphC.addEdge("8", "10", 1);
		graphC.addEdge("9", "T", 8);
		graphC.addEdge("10", "T", 2);
		graphC.addEdge("11", "T", 9);

		Graph graphD = new Graph(6);
		graphD.addEdge("S", "6", 14);
		graphD.addEdge("S", "3", 9);
		graphD.addEdge("S", "2", 7);
		graphD.addEdge("2", "3", 10);
		graphD.addEdge("2", "T", 15);
		graphD.addEdge("3", "T", 11);
		graphD.addEdge("5", "6", 9);
		graphD.addEdge("T", "5", 6);

		Graph graphE = new Graph(7);
		graphE.addEdge("S", "A", 5);
		graphE.addEdge("S", "B", 7);
		graphE.addEdge("S", "C", 4);
		graphE.addEdge("A", "D", 3);
		graphE.addEdge("A", "B", 1);
		graphE.addEdge("B", "D", 4);
		graphE.addEdge("B", "E", 5);
		graphE.addEdge("B", "C", 2);
		graphE.addEdge("C", "E", 4);
		graphE.addEdge("D", "T", 9);
		graphE.addEdge("E", "D", 1);
		graphE.addEdge("E", "T", 6);

		Graph graphF = new Graph(7);
		graphF.addEdge("S", "2", 5);
		graphF.addEdge("S", "3", 6);
		graphF.addEdge("S", "4", 5);
		graphF.addEdge("2", "5", 3);
		graphF.addEdge("2", "3", 2);
		graphF.addEdge("3", "2", 2);
		graphF.addEdge("3", "5", 3);
		graphF.addEdge("3", "6", 7);
		graphF.addEdge("3", "4", 3);
		graphF.addEdge("4", "6", 5);
		graphF.addEdge("5", "T", 8);
		graphF.addEdge("5", "6", 1);
		graphF.addEdge("6", "5", 1);
		graphF.addEdge("6", "T", 7);

		Graph graphG = new Graph(6);
		graphG.addEdge("S", "A", 10);
		graphG.addEdge("S", "C", 10);
		graphG.addEdge("A", "B", 4);
		graphG.addEdge("A", "D", 8);
		graphG.addEdge("A", "C", 2);
		graphG.addEdge("B", "T", 10);
		graphG.addEdge("C", "D", 9);
		graphG.addEdge("D", "T", 10);
		graphG.addEdge("D", "B", 6);


		//-------- PRIM
		Graph mstA = PrimAlgoritmo.primMST(graphA, "S");
		Graph mstB = PrimAlgoritmo.primMST(graphB, "S");
		Graph mstC = PrimAlgoritmo.primMST(graphC, "S");
		Graph mstD = PrimAlgoritmo.primMST(graphD, "S");
		System.out.println("------------PRIM-----------");
		System.out.println("Debería darme:");
		System.out.println("A:32  -  B:34  -  C:29  -  D:42  ");
		System.out.println("Resultados que me dio:");
		System.out.println("Peso total del grafo A: " + mstA.getTotalWeight());
		System.out.println("Peso total del grafo B: " + mstB.getTotalWeight());
		System.out.println("Peso total del grafo C: " + mstC.getTotalWeight());
		System.out.println("Peso total del grafo D: " + mstD.getTotalWeight());

		//-------- FORD FULKERSON
		Map<String, Object> resultFord = FordFulkersonAlgoritmo.fordFulkerson(graphG, "S", "T");
		int maxFlow = (int) resultFord.get("maxFlow");
		System.out.println("----------FORD FULKERSON-------------");
		System.out.println("E:14  -  F:14  -  G:19");
		System.out.println("Max Flow: " + maxFlow);

		//-------- DIJKSTRA
		System.out.println("------------DIJKSTRA-----------");
		System.out.println("A:14  -  B:10  -  C:9  -  D:20  ");
		Map<String, Object> result = DijkstraAlgoritmo.dijkstra(graphD, "S", "T");
		Map<String, Integer> distances = (Map<String, Integer>) result.get("distances");
		List<String> path = (List<String>) result.get("path");

		// Imprimir los resultados
		System.out.println("Shortest path from A to F: " + String.join(" -> ", path));
		System.out.println("Distance: " + distances.get("T"));

		/*
		SpringApplication.run(GraphAlgoritmosApplication.class, args);
	*/
	}

}
