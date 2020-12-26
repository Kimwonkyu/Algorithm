package Algorithm_Theory;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 7
 * 11
 * 1 2 3
 * 2 7 7
 * 7 6 9
 * 6 5 23
 * 5 4 1
 * 4 1 10
 * 1 3 3
 * 2 3 3
 * 3 7 4
 * 3 6 3
 * 3 5 6
 * 
 * */
public class Kruskal {
	
		public static int find(int a) {
			if(a == parent[a]) return a;//초기화된 상태(정점이 처음 등장)이면 자기 자신이 부모
			parent[a] = find(parent[a]);//find 할 때마다 부모는 최상위부모를 설정(성능 향상)
			return parent[a];
		}
		
		public static void union(int a, int b) {
			int aRoot = find(a);
			int bRoot = find(b);
			if(aRoot != bRoot) {
				parent[aRoot] = b;
			} else {
				return;
			}
		}

	static int N;//정점의 개수
	static int E;//간선의 개수
	static PriorityQueue<Edge> pq;//간설 값을 Min Heap 으로 하는 우선순위 큐 
	static int[] parent;//disjoint-set(union find)에서 필요한 부모 노드를저장하는 배열
	static boolean[] visit;//방문 여부 배열
	static int result;//결과 값 저장
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		parent = new int [N+1];
		visit = new boolean[N+1];
		result = 0;
		
		pq = new PriorityQueue<Edge>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < E; i++) {
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Edge(a,b,c));
		}//모든 간선에 대해 [시작,끝,비용] 을 가진 클래스로 우선순위 큐에 add
		
		for (int i = 1; i <=N; i++) {
			parent[i] = i;
		}//union-find 의 초기화는 일단 자기 자신의 부모노드는 자기 자신으로 설정
		
		int cnt =0 ;
		for (int i = 0; i <E; i++) {//모든 간선에 대해서 확인
			Edge oneNode = pq.poll();//현재 큐에 있는 모든 인스턴스 중 비용이 가장 작은 간선이 poll 된다
			int start = oneNode.start;
			int end = oneNode.end;
			int a = find(start);//만약 이 간선을 선택해서 연결한다고 했을 때 사이클이 생기면 안되므로
			int b = find(end);//양쪽의 루트(최상의부모)노드가 무엇인지 확인하고
			if(a == b) continue;//만약 같으면 선택하지 않고 넘어간다
			union(a,b);
			result += oneNode.weight;
			cnt++;
			if(cnt == E-1) break;
		}
		
		System.out.println(result);
		br.close();
	}

}

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int weight;
	public Edge (int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}
}