import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * count the number of cycles in connected components
 * 
 * just count the degree for each node in a connected components, if all nodes has degree 2 then it is a cycle
 */

public class Cyclic_Components_977E {
	static ArrayList<Integer> adjList[];
	static boolean visited[];

	static boolean dfs(int node) {
		if (visited[node])
			return true;
		if (adjList[node].size() != 2) {
			return false;
		}

		visited[node] = true;

		boolean ret = true;
		for (Integer i : adjList[node]) {
			ret = ret & dfs(i);
		}
		return ret;

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		adjList = new ArrayList[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int first = sc.nextInt() - 1;
			int second = sc.nextInt() - 1;
			adjList[first].add(second);
			adjList[second].add(first);
		}

		int res = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i] && adjList[i].size() > 0) {
				if (dfs(i)) {
					res++;
				}
			}
		}
		System.out.println(res);

	}

	// just another pair class if needed :D
	static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
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