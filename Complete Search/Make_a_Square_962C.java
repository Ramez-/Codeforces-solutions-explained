import java.io.*;
import java.util.*;

/*
 * given a number not bigger than 2*10^9, remove the minimal number of digits from this number to make it 
 * Square of another number.
 * 
 * Although the number appears big it is not big for string complete search because 2*10^9 = 10 digits number
 */

public class Make_a_Square_962C {

	public static int minimalCount(String s, int i, String curr) {

		if (i == s.length()) {

			if (curr.length() > 0) {
				if (curr.charAt(0) == '0')
					// minimal number will never be bigger than 1000000s
					return 1000000;
				double x = Math.sqrt(Double.parseDouble(curr));
				if (x == (int) x) {
					return 0;
				} else {
					return 1000000;
				}
			} else {
				return 1000000;
			}
		}

		char c = s.charAt(i);
		int count1 = minimalCount(s, i + 1, curr + c);
		int count2 = 1 + minimalCount(s, i + 1, curr);

		return Math.min(count1, count2);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		String s = sc.next();
		int y = minimalCount(s, 0, "");
		if (y == 1000000) {
			System.out.println(-1);
			return;
		}
		out.println(y);

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