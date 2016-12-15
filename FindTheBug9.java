import java.io.*;
import java.util.*;

// http://www.spoj.com/problems/POTHOLE/
class FindTheBug9 {

    // Edits the maxFlow array
    // returns if there is a path from source to sink
    public static boolean bfs(int[][] graph, int source, int sink, int[] previous) {


        
        boolean visited[] = new boolean[graph.length];
        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        previous[source] = -1;

        while (!queue.isEmpty()) {
            int location = queue.poll();

            for (int nextLocation = 0; nextLocation < graph.length; nextLocation++) {
                int capacity = graph[location][nextLocation];
                if (visited[nextLocation] == false && capacity > 0) {
                    queue.add(nextLocation);
                    previous[nextLocation] = location;
                    visited[nextLocation] = true;
                }
            }
        }

        return visited[sink];
    }

    // Note: edits the graph data structure
    public static int fordFulkerson(int[][] graph, int source, int sink) {
        

        int u, v;
        // The max flow to every node.


        int rGraph[][] = new int[graph.length][graph.length];
 
        for (u = 0; u < graph.length; u++)
            for (v = 0; v < graph.length; v++)
                rGraph[u][v] = graph[u][v];


        int[] previous = new int[graph.length];
        int flow = 0;

        while (bfs(rGraph, source, sink, previous)) {
            // int currentFlow = maxFlow[sink];
            // flow += currentFlow;
            int path_flow = Integer.MAX_VALUE;
            for (v=sink; v!=source; v=previous[v])
            {
                u = previous[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }


            for (v=sink; v != source; v=previous[v])
            {
                u = previous[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            
            flow += path_flow;
        }

        return flow;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numCases = scan.nextInt();

        while (numCases-- > 0) {
            int numChambers = scan.nextInt();
            int[][] graph = new int[numChambers][numChambers];

            for (int start = 0; start < numChambers - 1; start++) {
                int numConnections = scan.nextInt();

                for (int j = 0; j < numConnections; j++) {
                    int end = scan.nextInt();
                    end--;

                    // only one person can go through each edge.
                    graph[start][end] = 1;
                }
            }

            System.out.println(fordFulkerson(graph, 0, numChambers - 1));
        }
    }
}
