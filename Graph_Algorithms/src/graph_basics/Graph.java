package graph_basics;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: jackpoit
 * @Date: 2021/08/30/9:48
 * 仅适用于无向无权图
 */
public class Graph {
	private int V;
	private int E;
	private TreeSet<Integer>[] adj;

	public Graph(String filename) {

		File file = new File(filename);

		try (Scanner scanner = new Scanner(file)) {
			V = scanner.nextInt();
			if (V < 0)
				throw new IllegalArgumentException("V must be non-negative");
			adj = new TreeSet[V];
			for (int i = 0; i < V; i++) {
				adj[i]=new TreeSet<>();
			}

			E = scanner.nextInt();
			if (E < 0)
				throw new IllegalArgumentException("E must be non-negative");

			for (int i = 0; i < E; i++) {
				int a = scanner.nextInt();
				validateVertex(a);
				int b = scanner.nextInt();
				validateVertex(b);

				if (a == b)
					throw new IllegalArgumentException("Self Loop is Detected");

				if (adj[a].contains(b))
					throw new IllegalArgumentException("Parallel Edges are Detected");

				adj[a].add(b);
				adj[b].add(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex" + v + "is invalid");
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	//v与w是否相连
	public boolean hasEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return adj[v].contains(w);
	}

	//与v相连的点的集合
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("V = %d, E = %d \n", V, E));
		for (int i = 0; i < V; i++) {
			for (Integer w : adj[i]) {
				sb.append(String.format("%d ",w));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Graph graph = new Graph("g.txt");
		System.out.println(graph);
	}

}


