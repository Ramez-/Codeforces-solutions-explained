import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * Binary index tree is used to get sum of the first x arrays in log(n) also when updating element
 * in the array it is done in log(n).
 * 
 * Idea: Instead of creating sum array of the entire array we do several checkpoint in irregular places
 * get the sum of the elements before that element, this way when updating the array with new element 
 * instead of updating all elements in the array we just update the one's in between the checkpoint
 * 
 * The binary index tree is a binary tree of indexes an example of a tree of length 8 is as following
 * 
 *      4
 *  2       6
 *1   3   5   7
 *
 * The interesting thing about the tree is that when the indexes are in binary form we observe very interesting 
 * observation which is that forexample the summation of the elements till index 5 is the summation of element 5
 * plus the summation of the previous 4 elements, the previous 4 elements summation is stored at the root at 4
 * just by removing the last set number in the 5 it turns from 101 to 100 which is 4 this way we get the sum from
 * till element number 5
 *      
 */

public class Binary_Index_Tree {
	static int n;
	static int arr[];
	static int BIT[];

	public static int sumTillIndex(int index) {
		int ans = 0;
		// index will be equal to zero when we reset the last set bit which is
		// in the root
		// forexample in 4 => 100 will turn to zero
		while (index != 0) {
			ans += BIT[index];
			// resetting the last set bit
			index = index & (index - 1);
		}
		return ans;
	}

	// in updating we try to update the index that covers the index that will
	// change
	// forexample if index 5 is updated index 6 should be updated because it
	// covers it.
	// This is done just by adding 1 to the last set bit
	// value is value to be added to elements
	public static void update(int index, int value) {
		while (index < n) {
			BIT[index] += value;
			index = index + (index & -index);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n];
		BIT = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			update(i, i);
		}
		update(5, 3);
		System.out.println(sumTillIndex(5));

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