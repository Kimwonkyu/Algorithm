package BOJ_TEST;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ_1504_특정최단경로 {
	static class Edge implements Comparable<Edge>{
		int vertex, distance;
	
		public Edge(int vertex, int distance) {
			this.distance = distance;
			this.vertex = vertex;
		}
		@Override
		public int compareTo (Edge o) {
			return this.distance - o.distance;
		}
		
	}
	
	static int N,M,A,B;
	static ArrayList<Edge>[] list;
	static int dist[];
	static boolean[] visited;
	static int MAX = Integer.MAX_VALUE;
	
	
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("src\\BOJ_TEST\\1504_sample_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		dist = new int [N+1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b,c));
			list[b].add(new Edge(a,c));
		}
		
		//반드시 거쳐야 하는 두 정점
		st = new StringTokenizer(br.readLine());
		 A = Integer.parseInt(st.nextToken());
		 B = Integer.parseInt(st.nextToken());
		

		int ans = solve(A,B);
		
       System.out.println(ans);
       br.close();
	}
	
	public static int solve(int r1, int r2) {
		int res1 = 0;
		int res2 = 0;
		
		res1 += dijk(1,r1);
		res1 += dijk(r1,r2);
		res1 += dijk(r2,N);
		
		res2 += dijk(1,r2);
		res2 += dijk(r2,r1);
		res2 += dijk(r1,N);
		
		if(res1 >=MAX && res2 >=MAX) return -1;
		else return Math.min(res1, res2);
		
	}
	
	public static int dijk(int start, int end) {
		Arrays.fill(dist, MAX);
		Arrays.fill(visited,false);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		
		pq.offer(new Edge(start, dist[start]));
		
		while(!pq.isEmpty()) {
			Edge curEdge = pq.poll();
			int here = curEdge.vertex;
			int cost = curEdge.distance;
			
			
			//if(cost > dist[here]) continue;
			if(visited[here] == true) continue;
			
			visited[here] = true;
			
			for (int i = 0; i < list[here].size(); i++) {
				int next = list[here].get(i).vertex;
				int nextCost = list[here].get(i).distance;
				
				if(visited[next] == false && dist[next] > (cost + nextCost)) {
					dist[next] = cost + nextCost;
					pq.add(new Edge(next,dist[next]));
				}
				
			}
			
		}
		
		return dist[end];
	}

}
