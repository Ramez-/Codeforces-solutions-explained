import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {

	public static int[] sorted(int[] arr) {

		if (arr.length < 2) {
			return arr;
		}

		int mid = arr.length / 2;

		// additional memory for left and right arrays
		int[] left = new int[mid];
		int[] right = new int[mid];

		
		for (int i = 0; i < mid; i++) {
			left[i] = arr[i];
		}

		for (int i = 0; i < mid; i++) {
			right[i] = arr[i + mid];
		}

		left = sorted(left);
		right = sorted(right);

		int[] a = merge(left, right);

		return a;
	}

	public static int[] merge(int[] left, int[] right) {
		int[] arr = new int[left.length * 2];
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) {
			if (left[i] < right[j]) {
				arr[k] = left[i];
				i++;
			} else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}

		while (i < left.length) {
			arr[k] = left[i];
			i++;
			k++;
		}

		while (j < right.length) {
			arr[k] = right[j];
			j++;
			k++;
		}
		return arr;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int[] arr = { 1, 2, 3, 10, 22, 20, 5, 11 };
		int[] res = sorted(arr);

		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
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

}
