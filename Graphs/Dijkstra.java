import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * Standard dijkstra algorithm 
 */
public class Dijkstra {
	static int n;
	static int[] prev;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<Pair> adjList[] = new ArrayList[n];

		for (int j = 0; j < n; j++) {
			adjList[j] = new ArrayList<>();
		}
		for (int j = 0; j < m; j++) {
			int source = sc.nextInt() - 1;
			int distnation = sc.nextInt() - 1;
			int wight = sc.nextInt();
			Pair p = new Pair(distnation, wight);
			Pair p2 = new Pair(source, wight);

			adjList[source].add(p);
			adjList[distnation].add(p2);
		}
		int res = dijkistra(adjList, 0, n - 1);
		if (res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			int number = n - 1;
			String str = "";
			while (number != 0) {
				str = (prev[number] + 1) + " " + str;
				number = prev[number];
			}

			System.out.print(str + " " + n);

		}

	}

	// pair of adjList present the destination node and the cost to go to it.
	public static int dijkistra(ArrayList<Pair> adjList[], int src, int dest) {
		PriorityQueue<Edge> edgesQueue = new PriorityQueue<>();
		int[] dist = new int[n]; // distance in terms of cost between the ith
									// node and the src
		prev = new int[n]; // in case you need to print the shortest path
							// in order

		Arrays.fill(dist, Integer.MAX_VALUE); // cost to reach to a node
		Arrays.fill(prev, -1);

		dist[src] = 0; // Destination from src to src is zero

		// adding dummy edge to the src as it is the start
		edgesQueue.add(new Edge(-1, src, 0));

		while (!edgesQueue.isEmpty()) {
			Edge smallestEdge = edgesQueue.poll();

			if (smallestEdge.cost > dist[smallestEdge.to])
				continue; // some other state reached better

			prev[smallestEdge.to] = smallestEdge.from;

			for (Pair newNode : adjList[smallestEdge.to]) {
				if (dist[newNode.to] > newNode.cost + dist[smallestEdge.to]) {
					dist[newNode.to] = newNode.cost + dist[smallestEdge.to];
					Edge newEdge = new Edge(smallestEdge.to, newNode.to, dist[newNode.to]);
					edgesQueue.add(newEdge);
				}
			}
		}
		return dist[dest];
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
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