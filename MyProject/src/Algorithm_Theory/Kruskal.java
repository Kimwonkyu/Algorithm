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
			if(a == parent[a]) return a;//�ʱ�ȭ�� ����(������ ó�� ����)�̸� �ڱ� �ڽ��� �θ�
			parent[a] = find(parent[a]);//find �� ������ �θ�� �ֻ����θ� ����(���� ���)
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

	static int N;//������ ����
	static int E;//������ ����
	static PriorityQueue<Edge> pq;//���� ���� Min Heap ���� �ϴ� �켱���� ť 
	static int[] parent;//disjoint-set(union find)���� �ʿ��� �θ� ��带�����ϴ� �迭
	static boolean[] visit;//�湮 ���� �迭
	static int result;//��� �� ����
	
	
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
		}//��� ������ ���� [����,��,���] �� ���� Ŭ������ �켱���� ť�� add
		
		for (int i = 1; i <=N; i++) {
			parent[i] = i;
		}//union-find �� �ʱ�ȭ�� �ϴ� �ڱ� �ڽ��� �θ���� �ڱ� �ڽ����� ����
		
		int cnt =0 ;
		for (int i = 0; i <E; i++) {//��� ������ ���ؼ� Ȯ��
			Edge oneNode = pq.poll();//���� ť�� �ִ� ��� �ν��Ͻ� �� ����� ���� ���� ������ poll �ȴ�
			int start = oneNode.start;
			int end = oneNode.end;
			int a = find(start);//���� �� ������ �����ؼ� �����Ѵٰ� ���� �� ����Ŭ�� ����� �ȵǹǷ�
			int b = find(end);//������ ��Ʈ(�ֻ��Ǻθ�)��尡 �������� Ȯ���ϰ�
			if(a == b) continue;//���� ������ �������� �ʰ� �Ѿ��
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