import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * Given an array of the difference of people that happens in a bus at each stop, and the maximum capacity of the bus
 * 
 * find the number of possible ways how many people could be in the bus before the first bus stop
 * 
 * find the maximum and minimum that could happen from any subarray that is the maximum and minimum difference value 
 * that the bus will face. Just get the maximum of both maximum and math.abs(mimimum)
 */
public class Bus_Video_System_978E {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long w = sc.nextLong();

		int[] arr = new int[n];
		long mimal = Long.MAX_VALUE;
		long maximal = -Long.MAX_VALUE;
		int res = 0;

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();

		}

		for (int i = 0; i < n; i++) {
			res = 0;
			for (int j = i; j < n; j++) {
				res += arr[j];
				mimal = Math.min(res, mimal);
				maximal = Math.max(res, maximal);
			}
		}

		System.out.println(Math.max(0, w + 1 - Math.max(maximal, Math.abs(mimal))));

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

}