package SDS_TEST;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 인강_파티_다익스트라 {
	static int N,M,K;
	static int[] dist;
	static boolean[]  visited;
	static ArrayList<Edge>[] list_1;
	static ArrayList<Edge>[] list_2;
	static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src\\SDS_TEST\\파티_sample_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int [N+1];
		visited = new boolean[N+1];
		list_1 = new ArrayList[N+1];
		list_2 = new ArrayList[N+1];
		
		for (int i = 0; i <=N; i++) {
			list_1[i] = new ArrayList<Edge>();
			list_2[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list_1[a].add(new Edge(b,c));
			list_2[b].add(new Edge(a,c));
		}
		
		//본인집 -> 철수네 
		int sum = dijk(list_1,K,N);
		//철수네 -> 본인집
		sum +=dijk(list_2,K,N);
		
		System.out.println(sum);
		
		
		
		br.close();
	}
	
	static int dijk (ArrayList<Edge>[] list,int start, int end) {
		int sum = 0;
		Arrays.fill(dist, MAX);
		Arrays.fill(visited, false);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		dist[start] = 0;
		
		pq.offer(new Edge(start, dist[start]));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			int curNode = edge.vertex;
			int curDistance = edge.distance;
			
			if(visited[curNode]==true) continue;
			
			visited[curNode] = true;
			
			for (int i = 0; i <list[curNode].size(); i++) {
				int nextNode = list[curNode].get(i).vertex;
				int nextDistance = list[curNode].get(i).distance;
				
				if(visited[nextNode] ==false && dist[nextNode] > (curDistance + nextDistance)) {
					dist[nextNode] = curDistance + nextDistance;
					pq.add(new Edge(nextNode,dist[nextNode]));
				}
				
			}

		}
		
		for (int i = 1; i <=N; i++) {
			if(dist[i] < MAX) {
				sum +=dist[i];
			}
		}
		
		return sum;
	}

}

class Edge implements Comparable<Edge>{
	int vertex; 
	int distance;
	public Edge (int vertex, int distance) {
		this.vertex = vertex;
		this.distance = distance;			
	}
	
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.distance - o.distance;
	}
}