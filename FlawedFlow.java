import java.util.*;
import java.io.*;


public class FlawedFlow {
    static  String s = "4 5\n" +
            "1 2 10\n" +
            "1 3 10\n" +
            "2 3 5\n" +
            "4 2 15\n" +
            "3 4 5";
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() {
        try {
            st.nextToken();
            return (int) st.nval;
        } catch (IOException ex) {
            return 0;
        }
    }
    public static void main(String[] args) {
        int n = nextInt();
        int m = nextInt();
        ArrayList<ArrayList<Edge>> ar = new ArrayList<>();
        for (int i=0;i<=n;i++){
            ar.add(new ArrayList<Edge>());
        }
        int[] f = new int[n+1];
        for (int c=0;c<m;c++){
            int a = nextInt();
            int b = nextInt();
            int d = nextInt();
            ar.get(a).add(new Edge(b, c, 0, d));
            ar.get(b).add(new Edge(a, c, 1, d));
            f[a]+=d;
            f[b]+=d;
        }
        for (int i=1;i<=n;i++){
            f[i]/=2;
        }
        ArrayList<Edge> so = ar.get(1);
        Queue<ArrayList<Edge>> q = new LinkedList<>();
        q.add(so);
        int[] direction = new int[m];
        boolean visited[] = new boolean[m];
        while (!q.isEmpty()){
            ArrayList<Edge> sv = q.poll();
            for (int i=0;i<sv.size();i++){
                Edge j = sv.get(i);
                if (!visited[j.ca]) {
                    visited[j.ca] = true;
                    direction[j.ca] = j.da;
                    f[j.ba]-=j.ea;
                    if (j.ba!=n&&f[j.ba]==0){
                        q.add(ar.get(j.ba));
                    }
                }
            }
        }
        for (int i=0;i<m;i++){
            System.out.println(direction[i]);
        }
    }

    private static class Edge {
        int ba,ca,da,ea;
        Edge(int b,int c,int d,int e){
            ba=b;ca=c;da=d;ea=e;
        }
    }
}