package Algorithm_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Dijkstra {
//prioriryQueue로 구현한다
	static class Edge implements Comparable<Edge>{
		int vertex, distance;
		
		public Edge(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
		@Override
		public int compareTo(Edge o) {			
			return Integer.compare(this.distance, o.distance);
		}
		
	}
	
	static boolean[] visited;
	static int[] dist;
	static int[][] ad;
	static int E = 9, V =6;
	static int inf = 100000;
	
	public static void DijkstaMethod(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Edge(start,dist[start]));
		
		while(!pq.isEmpty()) {
			int cost = pq.peek().distance;
			int here = pq.peek().vertex;
			pq.poll();
			
			if(cost > dist[here]) {//현재 배열에 있는 값(최단거리)보다 cost(거리)가 더 길면 continue;
				continue;
			}
			
			System.out.println(here + " ");
			
			for (int i = 1; i <=V; i++) {
				if(ad[here][i] !=0 && dist[i] > (dist[here] + ad[here][i])) {
					dist[i] = dist[here] + ad[here][i];
					pq.offer(new Edge(i,dist[i]));
				}
			}
			
			System.out.println();
			
			for (int i = 1; i <=V; i++) {
				System.out.println(dist[i] + " ");
			}
		}

	}
			
	public static void main(String[] args) throws Exception{
		visited = new boolean[V+1];
		dist = new int[V+1];
		ad = new int[V+1][V+1];
		
		//거리는 큰 값으로 초기화 한다.
		for (int i = 1; i <= V; i++) {
			dist[i] = inf;
		}
		
        ad[1][2] = 8;
        ad[1][3] = 1;
        ad[1][4] = 2;
        ad[3][4] = 2;
        ad[3][2] = 5;
        ad[4][5] = 3;
        ad[4][6] = 5;
        ad[5][6] = 1;
        ad[6][1] = 5;
		
        DijkstaMethod(1);
	}

}
