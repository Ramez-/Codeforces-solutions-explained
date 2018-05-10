import java.io.*;
import java.util.*;

/*
 * Given a 4 equal parts of chess board but they are not colored in the right way, what is the minimal
 * number of changes to do for the chess board to become right when concatenating it
 */

public class Chessboard_961C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = sc.nextInt();

		// boolean to preset the chess board
		// Initially write the two types of "standard" chess boards that are
		// needed
		boolean[][] arrOne = new boolean[n][n];
		boolean[][] arrTwo = new boolean[n][n];
		boolean here = true;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arrOne[i][j] = here;
				arrTwo[i][j] = !here;
				here = !here;
			}
		}

		boolean[][][] grids = new boolean[4][n][n];

		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < n; i++) {
				String input = sc.next();
				for (int m = 0; m < n; m++)
					grids[k][i][m] = input.charAt(m) == '1';

			}
		}
		// find the number of moves needed to change one of the boards to the
		// standard board
		ArrayList<Integer> arrMin = new ArrayList<>();
		for (int k = 0; k < 4; k++) {
			int resOne = 0;
			int resTwo = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (grids[k][i][j] != arrOne[i][j]) {
						resOne++;
					}
					if (grids[k][i][j] != arrTwo[i][j]) {
						resTwo++;
					}
				}
			}
			arrMin.add(resOne);
			arrMin.add(resTwo);

		}
		int a = arrMin.get(0) + arrMin.get(2) + arrMin.get(5) + arrMin.get(7);
		int b = arrMin.get(1) + arrMin.get(3) + arrMin.get(4) + arrMin.get(6);
		int c = arrMin.get(0) + arrMin.get(3) + arrMin.get(5) + arrMin.get(6);
		int d = arrMin.get(1) + arrMin.get(2) + arrMin.get(4) + arrMin.get(7);
		int e = arrMin.get(1) + arrMin.get(2) + arrMin.get(6) + arrMin.get(5);
		int f = arrMin.get(0) + arrMin.get(3) + arrMin.get(4) + arrMin.get(7);

		int z = Math.min(Math.min(c, d), Math.min(e, f));

		// get the minimal combination where the 2 boards belong to one of the
		// standard and the
		// other two belong to the other standard.
		out.println(Math.min(z, Math.min(a, b)));

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