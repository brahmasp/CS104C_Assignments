import java.util.Scanner;

public class Wormholes {

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for(int t = 0; t < testCases; t++){
			int numNodes = sc.nextInt();
			int numEdges = sc.nextInt();	
			Edge[] edges = new Edge[numEdges];
			for(Edge e : edges){
				e = new Edge();
			}
			for(int i = 0; i < numEdges; i++){
				int srcN = sc.nextInt();
				int destN = sc.nextInt();
				int cost = sc.nextInt();

				if(edges[i] == null){
					edges[i] = new Edge(cost, new Node(srcN), new Node(destN));
				}				
			}
			bellmanFord(edges, numNodes);
		}	
	}

	private static void bellmanFord(Edge[] edges, int numNodes){

		boolean negativeCycle = false;
		int[] distances = new int[numNodes + 1];
		for(int i = 0; i < numNodes + 1; i++){
			distances[i] = Integer.MAX_VALUE;
		}
		distances[1] = 0; // src is always 1

		for(int i = 0; i < numNodes - 1; i++){ // iterating V - 1 times
			for(int j = 0; j < edges.length; j++){
				Edge edge = edges[j];
				Node src = edge.src;
				Node dest = edge.dest;
				int cost = edge.cost;

				if (distances[src.value] != Integer.MAX_VALUE && distances[src.value] + cost < distances[dest.value]){
					distances[dest.value] = distances[src.value] + cost;
				}
			}
		}

		for(int j = 0; j < edges.length; j++){
			Edge edge = edges[j];
			Node src = edge.src;
			Node dest = edge.dest;

			int cost = edge.cost;

			if (distances[src.value] != Integer.MAX_VALUE && distances[src.value] + cost < distances[dest.value]){
				System.out.println("UNSAFE AT ANY SPEED");
				negativeCycle = true;
			}
		}	
		if(!negativeCycle){
			printArr(distances, numNodes);
		}
	}

	private static void printArr(int dist[], int V) {
		for (int i = 1; i < V + 1; ++i){
			if(dist[i] != Integer.MAX_VALUE){
				System.out.print(dist[i] + " ");
			} else {
				System.out.print(" OUT ");
			}
		}
	}

	private static class Node {

		int value;
		public Node(){}

		public Node(int v){
			value = v;
		}
	}

	private static class Edge {
		int cost;
		Node src;
		Node dest;
		public Edge(){}

		public Edge(int cost, Node src, Node dest){
			this.cost = cost;
			this.src = new Node(src.value);
			this.dest = new Node(dest.value);
		}
	}
}
