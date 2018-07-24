import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/*
 * Given n candies Vasya eats k candies then Petya takes 10% of the left candies.
 * find the minimum k such that vasya eats at least n / 2 of the candies
 * 
 * Direct binary search to solve this problem
 * dealing with floating points is tricky in this question, so avoid them
 */
public class Candies_991C {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();

		long hi = n;
		long lo = 1;
		long finalRes = 0;
		while (hi >= lo) {
			long mid = (lo + hi) / 2;
			long res = 0;
			long candles = n;

			while (candles > 0) {
				res += Math.min(mid, candles);
				candles -= Math.min(mid, candles);
				candles -= (long) (candles / 10);
//				candles * 0.1 cause errors because of introducing the decimal
//				point therefore the candles/10 is better as it rounds down.
//				candles -=  (long)(candles*0.1);
			}

			// better that n/2
			if (res * 2 < n) {
				lo = mid + 1;

			} else {
				finalRes = mid;
				hi = mid - 1;

			}

		}
		System.out.println(finalRes);

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
