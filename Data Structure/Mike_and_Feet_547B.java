import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * Problem: given an array of size n. For groups of sizes from 1 till n, find the group that has 
 * smallest number the maximum of all groups with the same size
 * 
 * Solution consist of 2 steps
 * 
 * Step 1 (fill left and right array):
 * left array represent the index of each element in the array that is smaller than
 * the current element and is nearest to element to the left
 * 
 * right array represent the index of each element in the array that is smaller than
 * the current element and is nearest to element to the right
 * 
 *  Step 2 (maximum array):
 *  
 *  get the biggest possible group size of each element and put it in array, where the
 *  biggest number is in the group of the size it represent, then loop from end to start
 *  to make maximum partial sum
 */

public class Mike_and_Feet_547B {
	static int[] left;
	static int[] right;
	static int n;
	static int[] arr;

	public static void fillLeft() {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				left[i] = -1;
			} else {
				left[i] = stack.peek();
			}
			stack.push(i);
		}

	}

	public static void fillRight() {
		Stack<Integer> stack = new Stack<>();

		for (int i = n - 1; i >= 0; i--) {
			// pop any number bigger than or equal current value
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}

			// stack is empty means that the current value is very small that no value in
			// the array after current value smaller than it.
			if (stack.isEmpty()) {
				right[i] = n;
			} else {
				right[i] = stack.peek();
			}
			// inserting indices in the stack
			stack.push(i);
		}

	}

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		n = scanner.nextInt();
		left = new int[n];
		right = new int[n];
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		fillLeft();
		fillRight();

		int[] groupSize = new int[n + 1];

		for (int i = 0; i < n; i++) {
			int dist = right[i] - left[i] - 1;
			groupSize[dist] = Math.max(groupSize[dist], arr[i]);
		}

		for (int i = n - 1; i >= 1; i--) {
			groupSize[i] = Math.max(groupSize[i], groupSize[i + 1]);
		}

		for (int i = 1; i < n + 1; i++) {
			out.print(groupSize[i] + " ");
		}

		out.flush();
		out.close();

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

	static class Pair implements Comparable<Pair> {
		long a;
		int b;

		Pair(long a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {

			if (this.a != o.a) {
				// comparing long to integer
				return Long.compare(this.a, o.a);
			}
			return this.b - o.b;
		}
	}
}
