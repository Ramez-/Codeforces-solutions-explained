import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * A person divide rectangle glass, he keeps cutting horizontally and vertically, and he doesn't remove the sheets
 * after each cut get the biggest area in the sheets
 * 
 * 
 * 	Sets is a tree set that has were we cuted, using this treeset one can get the cuts to create the treeset of segments.
 *  Segments are arranged by length so that we can get the maximum in length in o(log(n)) and multiply maximum
 *  horizontal with maximum vertical
 */

public class Glass_Carving_627C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int w = sc.nextInt();
		int h = sc.nextInt();
		int n = sc.nextInt();

		// two sets in array for horizontal and vertical cutting
		TreeSet<Integer>[] sets = new TreeSet[2];
		sets[0] = new TreeSet<>();
		sets[1] = new TreeSet<>();
		TreeSet<Segments>[] seg = new TreeSet[2];
		seg[0] = new TreeSet<>();
		seg[1] = new TreeSet<>();
		sets[0].add(0);
		sets[0].add(w);
		sets[1].add(0);
		sets[1].add(h);
		seg[0].add(new Segments(0, w));
		seg[1].add(new Segments(0, h));

		for (int i = 0; i < n; i++) {
			int index = sc.nextChar() == 'H' ? 1 : 0;
			int cut = sc.nextInt();
			int lo = sets[index].lower(cut);
			int hi = sets[index].higher(cut);

			seg[index].remove(new Segments(lo, hi));
			seg[index].add(new Segments(lo, cut));
			seg[index].add(new Segments(cut, hi));

			sets[index].add(cut);
			out.println(1l * seg[index].first().len * seg[index ^ 1].first().len);

		}
		out.flush();

	}

	static class Segments implements Comparable<Segments> {

		int start;
		int end;
		int len;

		Segments(int start, int end) {
			this.start = start;
			this.end = end;
			len = end - start;
		}

		@Override
		public int compareTo(Segments o) {
			// TODO Auto-generated method stub
			if (len != o.len) {
				return o.len - len; // Descending
			}
			return start - o.start;

		}

	}

	// just another pair class if needed :D
	static class Pair {
		int to;
		int cost;

		Pair(int to, int cost) {
			this.to = to;
			this.cost = cost;
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