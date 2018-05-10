import java.io.*;
import java.util.*;

/*
 * Given a string you can replace a character with the next character, print the string that will have
 * abcdefghijklmnpqrstuvwxyz as subsequence
 * 
 * Solution: greedily traverse the string and see what is the expected character and if it is there add one to the 
 * expected character.
 * 
 * Tip: without string builder this solution was time limit exceeding. Using string builder to construct
 * the solution was faster.
 *
 */
public class String_Transformation_946C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		char ans = '-';
		boolean start = false;
		boolean rightSolution = false;
		// very important for fast concatenation
		StringBuilder solution = new StringBuilder();

		char[] sArray = sc.nextLine().toCharArray();
		int length = sArray.length;
		int index = length;

		for (int i = 0; i < length; i++) {
			if (ans == 'z' + 1) {
				index = i;
				rightSolution = true;
				break;
			}
			// we can't start except when we find a as it is the intial character
			if (sArray[i] == 'a' && start == false) {
				start = true;
				ans = 'b';
				solution.append("a");
				continue;
			}
			if (start) {
				if (sArray[i] <= ans) {
					solution.append(ans);
					ans++;
					continue;

				}
			}
			solution.append(sArray[i]);

		}

		for (int i = index; i < length; i++) {
			solution.append(sArray[i]);
		}

		if (rightSolution || ans == 'z' + 1) {
			out.println(solution);
		} else {
			out.println(-1);
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