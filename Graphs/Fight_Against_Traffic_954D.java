import java.io.*;
import java.util.*;

/*
 * Problem: given source and destination node in graph, find the number of pairs of nodes in this graph
 * where we can put an edge between them and the minimum path between source and target won't change
 * 
 * Idea: run BFS in order to get minimum path from any node to source node and destination node in two 
 * Separate arrays. Using these arrays we can calculate the distance from source node to node u and
 * add one to get new path to node v then add the distance from v to destination node. If this is 
 * bigger than minimum path from s to t then this addition of edge is valid.
 */
public class Fight_Against_Traffic_954D {

	static ArrayList<Integer> adjList[];
	static int n;

	static int[] bfs(int startNode) {
		Queue<Pair> q = new ArrayDeque<>();

		// putting pair of node and distance from startNode
		q.add(new Pair(startNode, 0));
		int[] arrDistance = new int[n];
		boolean[] visited = new boolean[n];
		arrDistance[startNode] = 0;
		visited[startNode] = true;
		while (!q.isEmpty()) {
			Pair node = q.poll();
			for (Integer nighbor : adjList[node.a]) {
				if (!visited[nighbor]) {
					q.add(new Pair(nighbor, node.b + 1));
					arrDistance[nighbor] = node.b + 1;
					visited[nighbor] = true;
				}

			}
		}
		return arrDistance;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		int m = sc.nextInt();
		int s = sc.nextInt() - 1;
		int t = sc.nextInt() - 1;

		adjList = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			adjList[a].add(b);
			adjList[b].add(a);
		}

		int[] distanceSource = bfs(s);
		int[] distanceTarget = bfs(t);

		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (!adjList[i].contains(j)) {
					if (distanceSource[i] + distanceTarget[j] + 1 >= distanceSource[t]
							&& distanceSource[j] + distanceTarget[i] + 1 >= distanceSource[t]) {

						res++;
					}

				}
			}
		}
		System.out.println(res);

	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream System) {
			br = new BufferedReader(new InputStreamReader(System));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public char nextChar() throws IOException {
			return next().charAt(0);
		}

		public Long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

		public void waitForInput() {
			for (long i = 0; i < 3e9; i++)
				;
		}
	}

}

class Pair implements Comparable<Pair> {
	int a;
	int b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return this.a - o.a;
	}
}
