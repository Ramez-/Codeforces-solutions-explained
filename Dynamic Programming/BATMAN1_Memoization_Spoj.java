import java.io.*;
import java.util.*;

/*
 * given batches of weapons, each batch has cost of opening it, each weapon inside has its own cost
 * batman has limited money, he cannot exceed value. This is unbound version of knapsac with cost to
 * open a batch of weapons
 */

public class BATMAN1_Memoization_Spoj {
	static int rating[][];
	static int cost[][];
	static int ceil[];
	static int n;
	static int m;
	static int k;
	static int memo[][][][];

	static int dp(int i, int j, int money, int open) {
		if (i >= n || j >= m) {
			return 0;
		}
		// invalid case
		if (money < 0) {
			return -10000000;
		}
		if (memo[i][j][money][open] != -1) {
			return memo[i][j][money][open];
		}

		int choiceZero = -10000000;
		int choiceTwo = -10000000;
		int choiceOne = -10000000;
		if (open == 0) {
			choiceZero = dp(i, 0, money - ceil[i], 1);
		}
		if (open == 1) {
			choiceOne = rating[i][j] + dp(i, j, money - cost[i][j], open);
			choiceTwo = dp(i, j + 1, money, open);
		}

		int choiceThree = -10000000;
		if (i + 1 < n)
			choiceThree = dp(i + 1, 0, money, 0);
		return memo[i][j][money][open] = Math.max(Math.max(choiceZero, choiceOne), Math.max(choiceTwo, choiceThree));
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();

		while (t-- > 0) {
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			rating = new int[n][m];
			cost = new int[n][m];
			ceil = new int[n];
			memo = new int[n + 1][m + 1][k + 1][2];

			for (int i = 0; i < n + 1; i++) {
				for (int j = 0; j < m + 1; j++) {
					for (int l = 0; l < k + 1; l++) {
						for (int w = 0; w < 2; w++) {
							memo[i][j][l][w] = -1;
						}
					}
				}
			}

			for (int i = 0; i < n; i++) {
				ceil[i] = sc.nextInt();
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					cost[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					rating[i][j] = sc.nextInt();
				}
			}
			out.println(dp(0, 0, k, 0));
		}

		out.flush();

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