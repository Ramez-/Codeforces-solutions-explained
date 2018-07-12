import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 
 * Problem: Given cites and roads and capital city. Add the least number of roads to make the capital
 * city connected to all nodes. The graph is directed.
 * 
 * One smart solution is to count the source nodes. Getting the source node is the nodes with zero in degree
 * Another solution which is allowed by constrains is to loop over all nodes not reachable from the capital city
 * and count the number of reachable nodes that was not visited before then loop over these nodes in non-increasing 
 * order and add a road with each node. 
 */
public class Reachability_from_Capital999E {

	static int n;
	static int m;
	static int s;
	static boolean visted[];
	static boolean vistedCount[];
	static int cnt;

	static ArrayList<Integer> adjList[];

	static void dfs(int node) {
		if (visted[node]) {
			return;
		}

		visted[node] = true;

		for (int newNode : adjList[node]) {
			dfs(newNode);
		}
	}

	static void dfsCount(int node) {
		vistedCount[node] = true;
		for (int newNode : adjList[node]) {
			if (!vistedCount[newNode] && !visted[newNode]) {
				cnt++;
				dfsCount(newNode);
			}

		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		s = sc.nextInt() - 1;

		adjList = new ArrayList[n];
		visted = new boolean[n];
		vistedCount = new boolean[n];
		ArrayList<Pair> countArray = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int first = sc.nextInt();
			int second = sc.nextInt();
			adjList[first - 1].add(second - 1);
		}

		dfs(s);

		for (int i = 0; i < n; i++) {
			if (!visted[i]) {
				cnt = 0;
				dfsCount(i);
				Arrays.fill(vistedCount, false);
				countArray.add(new Pair(i, cnt));
			}
		}

		Collections.sort(countArray);
		int res = 0;
		for (int i = 0; i < countArray.size(); i++) {
			if (!visted[countArray.get(i).a]) {
				res++;
				dfs(countArray.get(i).a);
			}
		}
		System.out.print(res);

	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream System) throws FileNotFoundException {
			br = new BufferedReader(new InputStreamReader(System));
//			String FILENAME = "level4.txt";
//			br = new BufferedReader(new FileReader(FILENAME));
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

	static class Pair implements Comparable<Pair> {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {
			return o.b - this.b;
		}
	}
}
