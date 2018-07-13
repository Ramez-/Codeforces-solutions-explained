import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * Given a list of numbers of power of 2's as coins, find the the answer to q queries which are 
 * what is the minimum number of coins need to form a certain number given by query q 
 * 
 * In order to do this problem optimally we need to use this formula for each of the 30 possible coins
 * of the 2 ^ number
 * 
 * Min(qi / (2^number), number of 2^number in the array)
 */
public class Coins_and_Queries_1003D {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] arr = new int[n];

		HashMap<Integer, Integer> hMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			if (hMap.containsKey(arr[i])) {
				hMap.put(arr[i], hMap.get(arr[i]) + 1);
			} else {
				hMap.put(arr[i], 1);
			}
		}

		for (int i = 0; i <= 30; i++) {
			int pow = (int) Math.pow(2, i);
			if (!hMap.containsKey(pow)) {
				hMap.put(pow, 0);
			}
		}

		for (int i = 0; i < q; i++) {
			int query = sc.nextInt();
			int res = 0;
			for (int j = 30; j >= 0; j--) {
				if (query == 0) {
					break;
				}
				int power = (int) Math.pow(2, j);
				int numberOfCoinsToTake = Math.min(query / power, hMap.get(power));

				query -= numberOfCoinsToTake * power;
				res += numberOfCoinsToTake;
			}
			if (query == 0) {
				System.out.println(res);
			} else {
				System.out.println(-1);
			}
		}

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

	static class Pair implements Comparable<Pair> {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {
			return o.b - this.b;
		}
	}
}
