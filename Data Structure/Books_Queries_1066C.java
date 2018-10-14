import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * given 3 different queries
 * Query one: add a book to the left of the shelve
 * Query two : add a book to the right of the shelve
 * Query three : find minimum books need to be removed from the left or right to reach book 
 * 
 * To solve this problem optimally we can have two variables left and right which are the 
 * Indices of the next book to be placed left of right. We place them in Hashmap so that we
 * can know in O(1) the position of the book from the left and right ends and return the minimum
 * of both of them.
 */
public class Books_Queries_1066C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HashMap<Integer, Integer> hMap = new HashMap<>();
		int l = -1;
		int r = 1;
		char symbol = sc.nextChar();
		int number = sc.nextInt();
		hMap.put(number, 0);
		for (int i = 1; i < n; i++) {
			symbol = sc.nextChar();
			number = sc.nextInt();
			if (symbol == 'L') {
				hMap.put(number, l);
				l--;
			} else if (symbol == 'R') {
				hMap.put(number, r);
				r++;
			} else {
				int position = hMap.get(number);
				System.out.println(Math.min(Math.abs(position - l), Math.abs(position - r)) - 1);
			}
		}

	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream System) throws FileNotFoundException {
			br = new BufferedReader(new InputStreamReader(System));
//			String FILENAME = 'level4.txt';
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

	static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

}