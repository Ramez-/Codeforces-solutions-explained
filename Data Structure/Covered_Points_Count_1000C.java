import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * Given a number line similar to x-axis with up to 10^18 numbers, given segments between numbers
 * Find for each number k up to n the number of elements covered by segments equal to k
 * 
 *  Using the segment method => where you add 1 to the beginning of the segment and -1 to the end
 *  of the segment + 1, but because of the big constraint, just save the beginning and end of segments
 *  in array list and sort them then iterate over them to get how many elements covered by each
 *  segement.
 */

public class Covered_Points_Count_1000C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Pair> segments = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			long first = sc.nextLong();
			long second = sc.nextLong();

			segments.add(new Pair(first, 1));
			segments.add(new Pair(second + 1, -1));
		}

		Collections.sort(segments);

		long lastPos = segments.get(0).a;
		long[] resArray = new long[n + 1];
		int resSofar = 1;

		for (int i = 1; i < segments.size(); i++) {
			resArray[resSofar] += segments.get(i).a - lastPos;
			if (segments.get(i).b == 1) {
				resSofar++;
			} else {
				resSofar--;
			}
			lastPos = segments.get(i).a;
		}

		for (int i = 1; i < n + 1; i++) {
			System.out.print(resArray[i] + " ");
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
