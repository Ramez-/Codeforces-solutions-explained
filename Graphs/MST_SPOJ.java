import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * Get the weight of the minimum spanning tree
 */

public class MST_SPOJ {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		long costOfTree = 0;
		ArrayList<Pair> adjList[] = new ArrayList[n];
		ArrayList<Edge> returnedEdges = new ArrayList<>();
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int f = sc.nextInt() - 1;
			int t = sc.nextInt() - 1;
			int w = sc.nextInt();
			adjList[f].add(new Pair(t, w));
			adjList[t].add(new Pair(f, w));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.add(new Edge(-1, 0, 0));

		while (!pq.isEmpty()) {

			Edge e = pq.poll();
			if (visited[e.to]) {
				continue;
			}
			visited[e.to] = true;
			costOfTree += e.w;

			if (e.to != 0) { // not to add the intial node
				returnedEdges.add(e); // in case you need the edges in the MST
										// for the problem
			}

			for (Pair candidateEdges : adjList[e.to]) {
				pq.add(new Edge(e.to, candidateEdges.a, candidateEdges.b));
			}
		}

		System.out.println(costOfTree);
	}

	static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int w;

		Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
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