import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAlt {

	static List<Node> nodes;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = 0;
		int m = 0;
		n = sc.nextInt();
		m = sc.nextInt();

		nodes = new ArrayList<Node>();
		HashSet<Node> setNodes = new HashSet<Node>();
		for (int i = 0; i < n; i++) {
			nodes.add(new Node(i));
		}

		for (int i = 0; i < m; i++) {
			int source = sc.nextInt() - 1;
			int destination = sc.nextInt() - 1;
			int weight = sc.nextInt();
			nodes.get(source).addNeighbour(nodes.get(destination), weight);
			nodes.get(destination).addNeighbour(nodes.get(source), weight);
		}
		
		nodes.get(0).pathDistance = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(1, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.pathDistance - n2.pathDistance;
			}
		});

		// Dijkstra's algorithm
		pq.add(nodes.get(0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if (setNodes.contains(node)) continue;
			setNodes.add(node);
			for (Node neighbour : node.neighbours.keySet()) {
				// Evaluate shortest paths
				int distance = node.neighbours.get(neighbour);
				if (!setNodes.contains(neighbour) && neighbour.pathDistance > node.pathDistance + distance) {
					neighbour.pathDistance = node.pathDistance + distance;
					neighbour.previous = node;
					pq.add(neighbour);
				}
			}
		}

		Node currentNode = nodes.get(n - 1);
		if (currentNode.previous == null) {
			System.out.println("-1");
		}
		else {
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2;
			while (currentNode != null) {
				sb2 = new StringBuilder(String.valueOf(currentNode.number + 1)).append(" ");
				sb2.reverse();
				sb.append(sb2);
				currentNode = currentNode.previous;
			}
			System.out.println(sb.reverse().toString());
		}
	}
	
	public static class Node {
		public Map<Node, Integer> neighbours;
		public Node previous;
		public int pathDistance;
		public int number;
		public Node(int number) {
			neighbours = new HashMap<>();
			pathDistance = Integer.MAX_VALUE;
			this.number = number;
		}

		void addNeighbour(Node destination, int weight) {
			if (neighbours.containsKey(destination) && neighbours.get(destination) <= weight) {
				return;
			}
			neighbours.put(destination, weight);
		}
	}
}

