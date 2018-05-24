import java.io.*;
import java.util.*;

/*
 * Problem: given a directed graph find the lower costed node in a strongly connected components and the number
 * of nodes that have that minimal value in the connected components
 * 
 * Using Tarjan to get the strongly connected components
 */
public class Checkposts_427C {

	static ArrayList<Integer> adjList[];
	// component present array of array lists of connected components nodes
	static ArrayList<Integer> component[];
	static int n;
	static int[][] state;
	static boolean[] visited;
	static int depthNumber = 0;
	static int[] lowLink;
	static int[] depth;
	static boolean[] inStack;
	static int[] cost;
	static Stack<Integer> stack;

	static void tarjan(int node) {
		lowLink[node] = depth[node] = depthNumber++;
		stack.push(node);
		inStack[node] = true;

		for (Integer child : adjList[node]) {
			// if not visited
			if (depth[child] == -1) {
				tarjan(child);
				// check if the child have reached to other node that cause
				// cycle,this node will have lower lowLink so the parent should
				// also have this low link
				lowLink[node] = Math.min(lowLink[node], lowLink[child]);
			} else {
				// node is child is visited before and it is in the stack
				// which means it is parent in cycle
				if (inStack[child]) {
					// if depth of child could be lower because it could be
					// parent and visited before current node
					// we also guarantee that only one node in a connected
					// Components that has same depth number as lowLimit
					lowLink[node] = Math.min(lowLink[node], depth[child]);
				}
			}
		}

		// if this node is a root of strongly connected component
		if (lowLink[node] == depth[node]) {

			int x = -1;

			// while the depth of node not equal to the l
			while (x != node) {
				x = stack.pop();
				// because after finishing the recursion this node won't be
				// parent of any other node
				inStack[x] = false;
				component[node].add(x);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		n = sc.nextInt();
		cost = new int[n];
		lowLink = new int[n];
		depth = new int[n];
		inStack = new boolean[n];
		stack = new Stack<>();

		Arrays.fill(depth, -1);

		adjList = new ArrayList[n];
		component = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			cost[i] = sc.nextInt();
			adjList[i] = new ArrayList<>();
			component[i] = new ArrayList<>();
		}

		int m = sc.nextInt();

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			adjList[a].add(b);
		}

		for (int i = 0; i < n; i++) {
			if (depth[i] == -1) {
				tarjan(i);
			}
		}

		long res = 0;
		long count = 1;
		for (int i = 0; i < n; i++) {
			if (component[i].size() > 0) {
				long minumCost = Integer.MAX_VALUE;

				for (Integer compon : component[i]) {
					if (minumCost > cost[compon]) {
						minumCost = cost[compon];
					}
				}
				long freq = 1;

				for (Integer compon : component[i]) {
					if (minumCost == cost[compon]) {
						freq = (freq + 1) % 1000000007;
					}
				}

				res += minumCost;
				count = (count * ((freq - 1) % 1000000007)) % 1000000007 ;
			}
		}
		System.out.println(res + " " + count);
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