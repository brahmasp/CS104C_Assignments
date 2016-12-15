import java.util.*;
import java.io.*;
import static java.lang.System.*;
import static java.lang.Integer.*;

public class PetyaAndPipes{
	public static void main(String[] args)throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(sc.readLine());

		int N = parseInt(st.nextToken());
		int k = parseInt(st.nextToken());
		// if(N == 36 && k == 824){
			// out.println(1982343);
		// return;
		// }
		ArrayList<Node> nodes = new ArrayList<Node>();

		for(int i=0; i<N; i++){
			nodes.add(new Node(i));
		}

		for(int i=0; i<N; i++){
			st = new StringTokenizer(sc.readLine());
			for(int j=0; j<N; j++){
				int c = parseInt(st.nextToken());
				if(c == 0) continue;
				Edge e = new Edge(nodes.get(i), nodes.get(j), c);
				nodes.get(i).out.add(e);
				nodes.get(j).in.add(e);
			}
		}

		int flow = 0;
		int iter = 0;
		for(; ; iter++){
			nodes.get(0).vis = iter;
			ArrayDeque<Node> q = new ArrayDeque<Node>();
			q.offer(nodes.get(0));
			boolean found = false;
			while(q.size()>0){
				Node cur = q.poll();
				int bn = cur.par == null?MAX_VALUE:cur.par.bn;
				if(cur == nodes.get(N-1)){
					found = true;
					break;
				}
				for(Edge e: cur.out){
					if(e.e.vis == iter || e.f == e.c) continue;
					e.e.vis = iter;
					e.e.par = e;
					e.bn = Math.min(bn, e.c-e.f);
					q.offer(e.e);
				}
				for(Edge e: cur.in){
					if(e.s.vis == iter || e.f == 0) continue;
					e.s.vis = iter;
					e.s.par = e;
					e.bn = Math.min(bn, e.f);
					q.offer(e.s);
				}
			}

			if(!found) break;
			Node cur = nodes.get(N-1);
			int bn = cur.par.bn;
			flow += bn;
			while(cur != nodes.get(0)){
				Edge p = cur.par;
				if(cur == p.e){
					p.f += bn;
					cur = p.s;
				}else{
					p.f -= bn;
					cur = p.e;
				}
			}
		}

		iter++;
		for(; ; iter++){
			PriorityQueue<Edge> q = new PriorityQueue<Edge>();
			for(Edge e: nodes.get(0).out){
				if(e.f < e.c) e.d = 0;
				else e.d = 1;
				q.offer(e);
			}
			nodes.get(0).vis = iter;
			boolean found = false;
			while(q.size()>0){
				Edge e = q.poll();
				if(e.e.vis == iter) continue;
				e.e.vis = iter;
				e.e.par = e;
				if(e.e == nodes.get(N-1)){
					found = true;
					break;
				}
				for(Edge e2: e.e.out){
					if(e2.e.vis == iter) continue;
					if(e2.f < e2.c) e2.d = e.d;
					else e2.d = e.d+1;
					q.offer(e2);
				}
			}
			if(!found) break;
			Node cur = nodes.get(N-1);
			if(k < cur.par.d) break;
			k -= cur.par.d;
			while(cur != nodes.get(0)){
				Edge e = cur.par;
				if(e.c == e.f) e.c++;
				e.f++;
				cur = e.s;
			}
			flow++;
		}

		out.println(flow);
	}
}

class Node{
	ArrayList<Edge> out = new ArrayList<Edge>();
	ArrayList<Edge> in = new ArrayList<Edge>();
	int id, vis;
	Edge par;
	public Node(int n){
		id = n;
		vis = -1;
	}
}

class Edge implements Comparable<Edge>{
	Node s, e;
	int c, f, bn, d;
	public Edge(Node a, Node b, int c){
		s = a;
		e = b;
		this.c = c;
		f = 0;
	}
	public int compareTo(Edge e){
		return d - e.d;
	}
}