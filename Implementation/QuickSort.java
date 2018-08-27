import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {
	public static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int pindex = partition(arr, start, end);
			quickSort(arr, start, pindex - 1);
			quickSort(arr, pindex + 1, end);
		}
	}

	// in order to avoid the n^2 worst case scenario 
	public static void randomChoice(int[] arr, int start, int end) {
		int ran = (int) ((Math.random() * (end - start + 1)) + start);
		int temp = arr[ran];
		arr[ran] = arr[end];
		arr[end] = temp;
	}

	public static int partition(int[] arr, int start, int end) {
		randomChoice(arr, start, end);
		int pivot = arr[end];
		int pindex = start;
		// putting elements less than the pivot before pindex
		for (int i = start; i < end; i++) {
			if (pivot > arr[i]) {
				int temp = arr[pindex];
				arr[pindex] = arr[i];
				arr[i] = temp;
				pindex++;
			}
		}
		int temp = arr[pindex];
		arr[pindex] = arr[end];
		arr[end] = temp;
		return pindex;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int[] arr = { 2, 3, 6, 1, 5, 12, 4 };
		quickSort(arr, 0, arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
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
