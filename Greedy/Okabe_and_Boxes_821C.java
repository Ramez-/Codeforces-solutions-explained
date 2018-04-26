
/*
 * Created by: Ramez Elbaroudy
 * 
 * Have a stack which you need to remove elments from it in order, give the least number of times you will reorder it 
 * 
 * in order to solve this problem in o(n) you need to clear the stack whenever you peek on value that is not the one expected
 * clearing the stack will mean that these elements are sorted, however if element that is in order is pushed then it can be poped easily without
 * any other reordering
 * 
 * The code runs in O(n) 
 */

import java.io.*;
import java.util.*;

public class Okabe_and_Boxes_821C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Stack<Integer> stack = new Stack<>();

		// number you expect to be removed
		int hope = 1;
		// solution
		int res = 0;
		for (int i = 0; i < 2 * n; i++) {
			String input = sc.next();

			if (input.equals("add")) {

				int val = sc.nextInt();
				stack.add(val);

			} else {
				if (!stack.empty()) {
					if (stack.peek() != hope) {
						stack.clear();
						res++;
					} else {
						stack.pop();
					}

				}
				hope++;
			}

		}
		System.out.println(res);

	}

	// bufferReader for fast scan
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
