import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SoldiersAndTraveling {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		int cities = sc.nextInt();
		int roads = sc.nextInt();
		
		int[][] graph = new int[2 * cities + 2][2 * cities + 2];
		int[][] sol = new int[cities][cities];
		// let source be 0
		// cities (value) is sink
		
		int sumOfInitial = 0;
		int sumOfFinal = 0;
		for(int i = 1; i <= cities; i++){
			int initialSold = sc.nextInt();
			graph[0][i] = initialSold; // connection to source
			graph[i][i + cities] = graph[0][i]; // connections to itself in other set infinity -- soliers can stay in same palce
			sumOfInitial+= initialSold;
			
		}
		
		for(int i = 1; i <= cities; i++){
			int result = sc.nextInt();
			graph[i + cities][2 * cities + 1] = result; // second set to sink
			sumOfFinal+=result;
		}
		
		for(int i = 1; i <= roads; i++){
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			graph[start][end + cities] = graph[0][start];
			graph[end][start + cities] = graph[0][end];
			

		}
		
		
		int maxFlow = fordFulkerson(graph, 0, graph.length - 1, sol, cities);
		if(sumOfInitial != maxFlow || sumOfFinal!= maxFlow){
			System.out.println("NO");
		} else {
			System.out.println("YES");
			for(int i = 0; i < sol.length; i++){
				for(int j = 0; j < sol[0].length; j++){
					System.out.print(sol[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		
		
	}
	
    public static boolean bfs(int[][] graph, int source, int sink, int[] previous, int[] maxFlow) {
        Arrays.fill(previous, -1);
        Arrays.fill(maxFlow, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        maxFlow[source] = Integer.MAX_VALUE;

        boolean foundSink = false;

        while (!queue.isEmpty()) {
            int location = queue.remove();
            int flow = maxFlow[location];

            foundSink |= (location == sink);

            for (int nextLocation = 0; nextLocation < graph.length; nextLocation++) {
                int capacity = graph[location][nextLocation];
                if (previous[nextLocation] == -1 && capacity > 0) {
                    previous[nextLocation] = location;
                    maxFlow[nextLocation] = Math.min(flow, capacity);

                    queue.add(nextLocation);
                }
            }
        }

        return foundSink;
    }

    // Note: edits the graph data structure
    public static int fordFulkerson(int[][] graph, int source, int sink, int[][] sol, int cities) {
        int flow = 0;

        // The max flow to every node.
        int[] maxFlow = new int[graph.length];
        int[] previous = new int[graph.length];

        while (bfs(graph, source, sink, previous, maxFlow)) {
            int currentFlow = maxFlow[sink];
            flow += currentFlow;

            int node = sink;
            while (node != source) {
                int previousNode = previous[node];
                // Update the residual graph
                graph[previousNode][node] -= currentFlow;
                if (previousNode != source && node != sink) {
                    if (previousNode < node) {
                        sol[previousNode - 1][node - cities - 1] += currentFlow;
                    } else {
                        sol[node - 1][previousNode - cities - 1] -= currentFlow;
                    }

                }
                graph[node][previousNode] += currentFlow;

                node = previousNode;
            }
        }

        return flow;
    }

}
