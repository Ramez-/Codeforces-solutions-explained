import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * problem: given a tree find the number of paths between any two nodes that don't pass from node x to y
 * Note a path can go from node y to x normally.
 * 
 * The idea to solve this question is to subtract n * (n-1) from the calculated nodes that can't be paired. 
 * This is done by computing the number of nodes in subtrees of x and y 
 * 
 *	If we take vertex y as the root of the tree, we can see that every pair of vertices 
 *	that Kuro cannot choose begins from any node within the subtree of node x, and finishes at any node
 *	but within the subtree of node z, which z is a direct child of y lying on the shortest path from x to y.
 *	
 *	final equation  n·(n - 1) - s[x]·(s[y] - s[z])
 *	where s is the size of the subtree
 * 
 * Node: every node in a tree can be root of the tree if rotated.
 */

public class Kuro_and_Walking_Route_979C {
	static ArrayList<Integer> adjList[];
	static int x;
	static int y;
	static int n;
	static int z;
	static boolean visited[];
	static int subTreeSize[];
	// boolean checkSub that the sub tree at this node has x
	static boolean checkSub[];

	// this dfs put in array subTreeSize the size of the subtree of each node
	// in checkSub save which nodes are parent node of x
	static int dfsSubTree(int node) {
		// the root of the subtree is counted
		subTreeSize[node] = 1;

		visited[node] = true;

		if (node == x) {
			checkSub[node] = true;
		} else {
			checkSub[node] = false;
		}

		for (Integer i : adjList[node]) {
			if (!visited[i]) {
				subTreeSize[node] += dfsSubTree(i);
				// checksub at node is ORed with this node that might have x
				checkSub[node] |= checkSub[i];
			}

		}
		return subTreeSize[node];
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		x = sc.nextInt() - 1;
		y = sc.nextInt() - 1;
		adjList = new ArrayList[n];
		visited = new boolean[n];
		checkSub = new boolean[n];
		subTreeSize = new int[n];

		// Arrays.fill(visited, false);

		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			adjList[a].add(b);
			adjList[b].add(a);
		}
		dfsSubTree(y);
		long res = 0;

		// checking which neighbor node to y that has subtree which contains x
		for (Integer i : adjList[y]) {
			if (checkSub[i]) {
				res = subTreeSize[y] - subTreeSize[i];
				break;
			}
		}

		System.out.println((1L * n * (n - 1)) - (1L * res * subTreeSize[x]));

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

}