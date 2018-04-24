
/*
 * Created by: Ramez Elbaroudy
 * 
 * Problem: move in a grid but be careful of the 'X' whenever you step on empty cell it turns to 'X'
 * 
 * just take care of corner case such as when the start is the end cell
 * 
 */

import java.io.*;
import java.util.*;

public class Ice_Cave_540C {

	static char grid[][];
	static int r2;
	static int c2;
	static int R;
	static int C;
	static boolean visted[][];

	private static boolean dfs(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		if (visted[r][c])
			return false;
		if (r == r2 && c == c2)
			return true;
		if (grid[r][c] == 'X')
			return false;
		
		
		visted[r][c] = true;
		
		return dfs(r - 1, c) | dfs(r, c - 1) | dfs(r + 1, c) | dfs(r, c + 1);
	}
	
	public static int besideCellEmpty(){
		int overall = 0;
		if (r2 > 0)
			overall += grid[r2 - 1][c2] == '.' ? 1 : 0;
		if (c2 > 0)
			overall += grid[r2][c2 - 1] == '.' ? 1 : 0;
		if (r2 < R - 1)
			overall += grid[r2 + 1][c2] == '.' ? 1 : 0;
		if (c2 < C - 1)
			overall += grid[r2][c2 + 1] == '.' ? 1 : 0;
		return overall;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		grid = new char[R][C];
		visted = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String inputLine = sc.next();
			for (int j = 0; j < C; j++) {
				grid[i][j] = inputLine.charAt(j);
			}
		}
		int r1 = sc.nextInt() - 1;
		int c1 = sc.nextInt() - 1;

		r2 = sc.nextInt() - 1;
		c2 = sc.nextInt() - 1;
		
		// the start cell could be the end cell
		if(r2 == r1 && c2 == c1){
			int overall = besideCellEmpty();
			if (overall > 0) {
				System.out.println("YES");
				return;
			}
		}
		grid[r1][c1] = '.';

		if (dfs(r1, c1)) {
			if (grid[r2][c2] == 'X') {
				System.out.println("YES");
				return;
			} else {
				int overall = besideCellEmpty();
				if (overall > 1) {
					System.out.println("YES");
					return;
				}
			}

		}
		System.out.println("NO");
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
