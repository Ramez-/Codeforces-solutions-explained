import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * Get the minimal consecutive unique charchters in string
 * 
 * Use two pointers, end and start, the end will keep moving right until it covers
 * all charchters are covered in the string between start and end, then move the start
 * to get the minumal string possible
 */
public class They_Are_Everywhere_701C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		String string = sc.next();

		char[] arr = string.toCharArray();
		TreeSet<Character> tSet = new TreeSet<>();

		for (int i = 0; i < arr.length; i++) {
			tSet.add(arr[i]);
		}

		int countUnique = tSet.size();

		int start = 0;
		int end = 0;
		HashMap<Character, Integer> hashMap = new HashMap<>();
		int ans = Integer.MAX_VALUE;

		while (start < n) {
			if (hashMap.size() < countUnique && end < n) {
				if (hashMap.containsKey(arr[end])) {
					hashMap.put(arr[end], hashMap.get(arr[end]) + 1);
				} else {
					hashMap.put(arr[end], 1);
				}
				end++;
			} else {
				if (hashMap.get(arr[start]) > 1) {
					hashMap.put(arr[start], hashMap.get(arr[start]) - 1);
				} else {
					hashMap.remove(arr[start]);
				}
				start++;
			}
			if (hashMap.size() == countUnique) {
				ans = Math.min(ans, end - start);
			}
		}

		System.out.println(ans);

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
