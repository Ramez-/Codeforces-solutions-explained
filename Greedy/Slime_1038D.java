import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Given array of number any number can be subtracted by the number on its left or right
 * return the biggest possible number after performing the operation till one number is left
 * 
 * if the number of combination of positive numbers and negative number then the result is
 * the summation of all absolute values
 * 
 * In case it is only positive numbers or negative numbers then it is the summation of
 * all numbers except the minimal absolute value minus the minimal absolute value.
 * 
 * take care when n = 1
 */
public class Slime_1038D {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];

		int min = Integer.MAX_VALUE;
		int minmalIndex = 0;
		boolean allNegtive = true;
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			if (min > arr[i]) {
				min = arr[i];
				minmalIndex = i;
			}
			if (arr[i] > 0) {
				allNegtive = false;
			}
		}
		if (n == 1) {
			System.out.println(arr[0]);
			return;
		}

		long sumation = 0;
		int minNeg = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (arr[i] < 0 || i != minmalIndex) {
				sumation += Math.abs(arr[i]);
				minNeg = Math.min(Math.abs(arr[i]), minNeg);
			}
		}
		if (min == Integer.MAX_VALUE) {
			System.out.println(0);
			return;
		}

		if (min > 0)
			System.out.println(sumation - min);
		else if (allNegtive) {
			System.out.println(sumation - (2 * minNeg));
		}
		else {
			System.out.println(sumation);
		}

		
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream System) throws FileNotFoundException {
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

	static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

}