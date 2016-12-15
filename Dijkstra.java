
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
public class Dijkstra {

	public static void main(String[] args) throws IOException{

		//Scanner sc = new Scanner(System.in);
		//		long startTime = System.nanoTime();
		MyScanner sc = new MyScanner();
		getShortestPath(sc);
		//		long endTime = System.nanoTime();
		//		long elapsedTime = endTime - startTime; 
		//		double seconds = elapsedTime / 1.0E09;

		//System.out.println("Total execution time: " + (seconds) );

	}

	private static void getShortestPath(MyScanner sc) throws IOException{

		HashMap<Integer, Node> nodes = new HashMap<>();
		int numNodes = sc.nextInt();
		int numEdges = sc.nextInt();

		for(int i = 0; i < numEdges; i++){
			int first = sc.nextInt();
			int second = sc.nextInt();
			int cost = sc.nextInt();
			Node firstNode = new Node(first);
			Node secondNode = new Node(second);
			if(!nodes.containsKey(first)){
				nodes.put(first, firstNode);
			}
			nodes.get(first).neighbours.add(new Edge(cost, secondNode));
			if(!nodes.containsKey(second)){
				nodes.put(second, secondNode);
			}
			nodes.get(second).neighbours.add(new Edge(cost, firstNode));

		}


		if(nodes.get(1) == null || nodes.get(numNodes) == null){
			System.out.println("-1");
		} else {
			dijkstra(numNodes, nodes);
			if(nodes.get(1).prev == null){
				System.out.println("-1");
			} else {				
				printPath(nodes.get(1));

			}
		}


	}

	private static void dijkstra(int numNodes, HashMap<Integer, Node> nodes){

		Node startingNode = nodes.get(numNodes);
		PriorityQueue<Path> toVisit = new PriorityQueue<>();
		Path startingPath = getPath(nodes, startingNode.value);
		startingPath.dest = startingNode;
		toVisit.add(startingPath);
		startingNode.costFromStart = 0;
		boolean reached = false;
		while(!toVisit.isEmpty() && !reached){
			Path currentPath = toVisit.remove();
			Node currentNode = currentPath.dest;
			currentNode.visited = true;

			if(currentNode.value != 1){
				for(Edge e : currentNode.neighbours){
					int cost = e.cost;
					Node neighbour = nodes.get(e.dest.value);
					if(!e.dest.visited){
						if(currentPath.costFromStart + cost < neighbour.costFromStart){ 
							neighbour.costFromStart = currentNode.costFromStart + cost;
							neighbour.prev = currentNode;
							Path nextSpot = getPath(nodes, neighbour.value);
							nextSpot.dest = neighbour;
							toVisit.add(nextSpot);
						}
					}
				}
			} else {
				reached = true;
			}
		}
	}

	private static Path getPath(HashMap<Integer, Node> nodes, int dest) {
		Path result = new Path();
		Node end = nodes.get(dest);
		result.costFromStart = end.costFromStart;
		return result;
	}

	private static void printPath(Node dest) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		//OutputStream out = new BufferedOutputStream ( System.out );
		Node temp = dest;
		while(temp != null){
			//System.out.print(temp.value + " ");
			out.print(temp.value + " ");
			//out.write((temp.value + " ").getBytes());
			temp = temp.prev;
		}
		out.flush();
	}

	public static class Path implements Comparable<Path>{

		int costFromStart;
		LinkedList<Node> path;
		int number = 0;
		Node dest;
		public Path(){
			dest = new Node(-1);
			costFromStart = 0;
			path = new LinkedList<>();
		}

		public int numberNodes(){
			return path.size();
		}
		public void add(Node v) {
			path.addFirst(v);
		}


		@Override
		public int compareTo(Path other) {
			return costFromStart < other.costFromStart ? -1 : costFromStart > other.costFromStart ? 1 :0;
		}

	}

	private static class Node {

		int value;
		ArrayList<Edge> neighbours;
		int costFromStart = Integer.MAX_VALUE;
		Node prev = null;
		Node next = null;
		boolean visited;

		public Node(int v){
			value = v;
			neighbours = new ArrayList<Edge>();
			visited = false;
		}
	}

	private static class Edge {
		int cost;
		Node dest;

		public Edge(int cost, Node dest){
			this.cost = cost;
			this.dest = new Node(dest.value);

		}
	}

	public static class MyScanner {
		BufferedReader br;
		StringTokenizer st;

		public MyScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine(){
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

	}

}
