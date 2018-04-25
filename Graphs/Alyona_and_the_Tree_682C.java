
/*
 * Created by: Ramez Elbaroudy
 * 
 * Problem: given a tree every node has value and every edge as cost, try to make the graph happy by removing 
 * 			nodes that has cost of distance bigger than value of node to any of its parent nodes.
 * 
 * 
 * Idea: do dfs from the root and add the cost whenever the cost decrease than zero reset it to zero, then 
 * check for every node for the consective positive sum bigger than the current value of node, if it is bigger
 * then delete the entire sub tree
 * 
 */

import java.io.*;
import java.util.*;

public class Alyona_and_the_Tree_682C {

	static int cost[];
	static ArrayList<Pair> adjList[];
	static boolean visited[];
	static int res;

	
	// delete boolean to tell it if it should be deleted node and add one to res
	public static void dfs(int node, int prevSum, boolean delete) {
		if (visited[node])
			return;
		visited[node] = true;
		boolean willDelete = false;

		if (prevSum > cost[node] || delete) {
			res++;
			willDelete = true;

		}

		for (Pair p : adjList[node]) {
			if (prevSum + p.b > 0) {
				dfs(p.a, prevSum + p.b, willDelete);
			} else {
				dfs(p.a, 0, willDelete);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		cost = new int[n];
		visited = new boolean[n];
		res = 0;
		adjList = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			cost[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int node = sc.nextInt() - 1;
			int distance = sc.nextInt();
			adjList[node].add(new Pair(i + 1, distance));
			adjList[i + 1].add(new Pair(node, distance));
		}
		
		dfs(0, 0, false);
		
		System.out.println(res);

	}

	static class Pair {
		int a;
		int b;

		public Pair(int a, int b) {
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
