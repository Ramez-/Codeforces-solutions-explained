import java.io.*;
import java.util.*;

/*
 * Problem: Find maximum length of the subsequence of the given array that forms an 
 * increasing sequence of consecutive integers. 
 * 
 * Idea: for each number in the list 'n' find the maximum length of subsequence of n-1, but given that
 * the n-1 is before the given number in the sequence.
 * 
 *  Use treemap to model each number of the list and the maximum length of subsequence from previous numbers 
 */

public class Consecutive_Subsequence_977F {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int [] arr = new int[n];
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			if (tm.containsKey(arr[i] - 1)) {
				tm.put(arr[i], tm.get(arr[i] - 1) + 1);
			} else {
				tm.put(arr[i], 0);
			}

		}

		int max = 0;
		int biggestNumber = arr[0];
		for (Map.Entry<Integer, Integer> entry : tm.entrySet()) {
			if (max < entry.getValue()) {
				max = entry.getValue();
				biggestNumber = entry.getKey();
			}
		}

		int number = 0;
		ArrayList<Integer> res = new ArrayList<>();

		for (int i = n - 1; i >= 0; i--) {
			if (arr[i] == biggestNumber - number) {
				res.add(i + 1);
				number++;
			}
		}
		System.out.println(max + 1);

		for (int i = res.size() - 1; i >= 0; i--) {
			System.out.print(res.get(i) + " ");
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