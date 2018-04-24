/*
 * Created by: Ramez Elbaroudy
 * 
 * Problem question:
 * 		Given unsorded list you need to sort it. However you can only switch element at position i only with element at position i+1
 * 		
 * Input form
 * 	n -> number of elements
 * 	Array -> unsorted list
 * 	String -> the indices where you are allowed to do swaping
 * 
 * Idea:
 * 		Use prefix sum array for the string in order to avoid extra o(n) to know if there is a zero in the sequence of numbers
 * 		
 * The code run in O(n)
 */

import java.io.*;
import java.util.*;

public class Question {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			arr[i] = x - 1;
		}
		String s = sc.next();
		int[] prefixSum = new int[n];
		prefixSum[0] = 0;

		for (int i = 1; i < n; i++) {
			prefixSum[i] = prefixSum[i - 1] + Character.getNumericValue(s.charAt(i - 1));
		}

		for (int i = 0; i < n - 1; i++) {
			if (i != arr[i]) {
				// at the beginning I used .contains here to make sure there is no zero in the sequence but it made the code run in o(n^2)
				if (Math.abs(i - arr[i]) != Math.abs(prefixSum[i] - prefixSum[arr[i]])) {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");
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
