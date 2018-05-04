import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * given a graph that each node has a color find the color that has maximum neighbour diversity
 * 
 * The key to solve this problem is to convert the nodes to color nodes this way the nodes with same color
 * will be combined, then count the neighbors of this colored node.
 * 
 * Take care that two nodes could have the same color can have vertex between them.
 * 
 * 
 */
public class Colorful_Graph_246D {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		String s = sc.nextLine();
		String[] sArray = s.split(" ");
		int nColor = sArray.length;
		int[] color = new int[nColor];
		int maximum = 0;
		for (int i = 0; i < nColor; i++) {
			color[i] = Integer.parseInt(sArray[i]);
			maximum = Math.max(maximum, color[i]);
		}

		// treeset to remove the duplicates
		TreeSet<Integer> adjList[] = new TreeSet[maximum + 1];

		for (int i = 0; i < maximum + 1; i++) {
			adjList[i] = new TreeSet<>();
		}

		for (int i = 0; i < nColor; i++) {
			if (adjList[color[i]].size() == 0)
				adjList[color[i]].add(-1);
		}

		for (int i = 0; i < m; i++) {
			int startNode = sc.nextInt() - 1;
			int endNode = sc.nextInt() - 1;

			if (color[startNode] == color[endNode])
				continue;

			adjList[color[startNode]].add(color[endNode]);
			adjList[color[endNode]].add(color[startNode]);
		}

		int max = -1;
		int col = -1;

		for (int i = 0; i < maximum + 1; i++) {
			if (adjList[i].size() > max) {
				max = adjList[i].size();
				col = i;
			}
		}
		System.out.println(col);

	}

	// just another pair class if needed :D
	static class Pair {
		int to;
		int cost;

		Pair(int to, int cost) {
			this.to = to;
			this.cost = cost;
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