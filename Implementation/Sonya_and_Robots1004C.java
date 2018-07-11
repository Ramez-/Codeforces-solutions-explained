import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * Problem: Given a list, with two pointers one in the end and one in the beginning find the number of 
 * combinations of unique pairs that can happen without both pointers meet.
 * 
 * To solve the problem we can create an array of unique items from the end to start, so that we
 * can easily add for unique elements from the beginning the elements that are unique.
 */

public class Sonya_and_Robots1004C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		TreeSet<Integer> tSet = new TreeSet<>();
		tSet.add(arr[n - 1]);
		int[] distinct = new int[n];
		distinct[n - 1] = 1;

		// creation of the array of number unique elements from the end
		for (int i = n - 2; i >= 0; i--) {
			if (tSet.contains(arr[i])) {
				distinct[i] = distinct[i + 1];
			} else {
				distinct[i] = distinct[i + 1] + 1;
				tSet.add(arr[i]);
			}
		}

		long res = 0;
		tSet = new TreeSet<>();

		for (int i = 0; i < n - 1; i++) {
			if (!tSet.contains(arr[i])) {
				res += distinct[i + 1];
				tSet.add(arr[i]);
			}
		}
		System.out.print(res);

	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream System) throws FileNotFoundException {
			br = new BufferedReader(new InputStreamReader(System));
//			String FILENAME = "level4.txt";
//			br = new BufferedReader(new FileReader(FILENAME));
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

	static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
