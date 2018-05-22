import java.io.*;
import java.util.*;

/*
 * Problem: Given a game: player can move a ship on a directed graph, if a user can't move the chip
 * he loses. For player p he is going to play for himself and his opponent, is it possible to win, or he 
 * can draw, playing for 10^6 steps and no one wins, or lose. If he wins print results.
 * 
 * Solution: In order to solve this problem, create a state (v,p) where v is the current vertex and p is
 * the parity which is zero or one value, zero present it is the initial player move. Using this state we
 * can save whether this vertex was visited before with the same parity which means don't explore vertex again
 * or this is visited for the fist time with this parity
 * 
 * In order to print the solution in case that a leave was reachable from start and it had parity one, then
 * use a stack to go inversally from the last element to the start element using the state
 * 
 * If player can't win, then check if he can draw if there is a cycle reachable from the start state
 * 
 * otherwise player loses
 * 
 */

public class Sleepy_Game_936B {

	static ArrayList<Integer> adjList[];
	static int n;
	// state to reach a node which is node and parity
	static int[][] state;
	static boolean[][] visited;
	static boolean isCycle;
	static int[] cyclicVisited;
	// array for leaves, I was thinking at the beginning of array list of leaves
	// but just array of boolean whether it is leave or not is faster
	static int[] dead;

	// checking if there is cycle reachable from start node
	static void dfs(int node) {
		cyclicVisited[node] = 2; // explored
		for (Integer i : adjList[node]) {
			if (cyclicVisited[i] == 2) {
				isCycle = true;
			}
			// not visited at all
			if (cyclicVisited[i] == 0) {
				dfs(i);
			}
		}
		// when a node discover all its children and they didn't cause any cycle
		// then mark this node to one, so that we don't discover it again
		cyclicVisited[node] = 1;
	}

	static int bfs(Pair startNode) {
		int last = -1;
		Queue<Pair> q = new ArrayDeque<>();
		q.add(startNode);
		visited[startNode.a][0] = true;
		while (!q.isEmpty()) {
			Pair node = q.poll();
			int parity = node.b;
			if (dead[node.a] == 1 && parity == 1) {
				last = node.a;
				break;
			}
			for (Integer nighbor : adjList[node.a]) {
				if (!visited[nighbor][parity ^ 1]) {
					visited[nighbor][parity ^ 1] = true;
					// saving in state what was the previous node that got me
					state[nighbor][parity ^ 1] = node.a;
					q.add(new Pair(nighbor, parity ^ 1));
				}
			}
		}
		return last;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		n = sc.nextInt();
		int m = sc.nextInt();
		visited = new boolean[n][2];
		cyclicVisited = new int[n];
		state = new int[n][2];

		adjList = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		dead = new int[n];
		for (int i = 0; i < n; i++) {
			int count = sc.nextInt();
			if (count == 0) {
				dead[i] = 1;
			}
			for (int j = 0; j < count; j++) {
				int a = sc.nextInt() - 1;
				adjList[i].add(a);
			}

		}

		int start = sc.nextInt() - 1;

		int lastNode = bfs(new Pair(start, 0));

		if (lastNode != -1) {
			System.out.println("Win");
			Stack<Integer> stack = new Stack<>();
			int parity = 0;
			int node = lastNode;

			while (node != start || parity != 1) {
				stack.push(node);
				parity = parity ^ 1;
				node = state[node][parity];
			}
			stack.push(start);
			while (!stack.isEmpty()) {
				out.print((stack.pop() + 1) + " ");
			}

		} else {
			dfs(start);
			if (isCycle) {
				System.out.println("Draw");
			} else {
				System.out.println("Lose");
			}
		}
		out.close();

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