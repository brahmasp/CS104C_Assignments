import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Hurricane {

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();

		for(int i = 0; i < test; i++){

			// nodes are the intersections
			// edges are the roads

			int numInter = sc.nextInt(); // total nodes = numInter (includes source and sink)
			Node[] graph = new Node[numInter + 1]; 
			int numRoads = sc.nextInt();
			int numSafeInter = sc.nextInt();
			int numPeople = sc.nextInt(); // total people starting from source (UT - node 1)


			// ut located at 1
			for(int j = 0; j < numRoads; j++){ // m roads  -- forming graph here
				int start = sc.nextInt(); // starting pos 
				int end = sc.nextInt(); // ending vertex
				int capacity = sc.nextInt(); // max people allowed
				// create both nodes, both are unsafe for now
				// one way so no link back
				Node s = new Node(start, false);
				Node e = new Node(end, false);
				Edge edge = new Edge(capacity, e);
				if(graph[start] == null){
					graph[start] = s;
				} 
				graph[start].neighbours.add(edge); // one way so no link back
				if(graph[end] == null){ 
					graph[end] = edge.dest;
				}
				Edge backEnd = new Edge(0, graph[start]);
				graph[end].neighbours.add(backEnd);
			}
			Node sink = new Node(0, true);
			graph[0] = sink; // 0th position is the sink, common loc of all safe spots


			// isnt idea just loop through arguments with constant source
			// each iteration sink changes to those vertices that are "safe"
			// add up max people that can flow from start to given safe place 
			// if total sum >= total people, then fine right?
			for(int k = 0; k < numSafeInter; k++){ // for each safe intersection -- these are the sinks?
				int safeInterIndex = sc.nextInt(); // index of safe intersection - will range from 1 to N?
				int carsToSafeInter = sc.nextInt(); // max capacity of the intersection   --- can be ignored??
				graph[safeInterIndex].safe = true;
				graph[safeInterIndex].neighbours.add(new Edge(carsToSafeInter, sink)); // connecting to sink

			}
			int totalFlow = Integer.MAX_VALUE;
			totalFlow = fordFulkerson(graph, graph[1].start, sink.start, sink);
			if(totalFlow >= numPeople){
				System.out.println("YES");
			} else {
				System.out.println("NO " + (numPeople - totalFlow));
			}
		}
	}

	public static int fordFulkerson(Node[] graph, int src, int dest, Node sink){

		int u, v;
		Node[] resGraph = new Node[graph.length];
		resGraph[0] = sink;
		for(int i = 1; i < graph.length; i++){
			resGraph[i] = graph[i];
		}

		int[] parent = new int[graph.length];

		int maxFlow = 0;
		while(pathToSink(resGraph, src, dest, parent, graph)){

			int pathFlow = Integer.MAX_VALUE;

			for(v = dest; v != src; v = parent[v]){
				u = parent[v];
				Edge finalE = null;
				for(Edge e : resGraph[u].neighbours){
					if(e.dest.start == v){
						finalE = e;
						break;
					}
				}
				if(finalE != null){
					pathFlow = Math.min(pathFlow, finalE.capacity);
				}

			}

			for(v = dest; v != src; v = parent[v]){
				u = parent[v];

				for(Edge e : resGraph[u].neighbours){
					if(e.dest.start == v){

						e.capacity -= pathFlow;
						break;
					}
				}

				for(Edge e : resGraph[v].neighbours){
					if(e.dest.start == u){
						e.capacity += pathFlow;
						break;
					}
				}

			}
			maxFlow += pathFlow;
		}

		return maxFlow;
	}

	private static boolean pathToSink(Node[] rGraph, int s, int t, int[] parent, Node[] graph){


		boolean[] visited = new boolean[rGraph.length];
		Arrays.fill(visited, false);

		LinkedList<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		parent[s] = -1;

		while(!q.isEmpty()){
			int u = q.removeFirst();
			for(int v = 0; v < graph.length; v++){
				if(!visited[v]){
					Edge finalE = null;
					for(Edge e : rGraph[u].neighbours){
						if(e.dest.start == v){
							finalE = e;
							break;
						}
					}

					if(finalE != null && finalE.capacity > 0){
						q.add(v);
						parent[v] = u;
						visited[v] = true; 
					}

				}
			}
		}
		return visited[t];
	}

	private static void printGraph(Node[] graph){
		for(int k = 1; k < graph.length; k++){
			System.out.print("pos in array: " + k);
			System.out.println(" starting: " + graph[k].start);
			System.out.println("Neighbours: ");
			for(int p = 0; p < graph[k].neighbours.size(); p++){
				System.out.println("end: " + graph[k].neighbours.get(p).dest.start + " cap: " + graph[k].neighbours.get(p).capacity);
			}
		}
	}

	public static class Node {

		int start;
		ArrayList<Edge> neighbours = new ArrayList<>();
		boolean safe = false; // all safe ones are sinks -- end destinations

		public Node(){

		}

		public Node(int start, boolean safe){
			this.start = start;		
			this.safe = safe;
		}
	}

	private static class Edge {
		int capacity;
		Node dest;

		public Edge(int cost, Node dest){
			this.capacity = cost;
			this.dest = new Node(dest.start, dest.safe);
		}
	}

}
