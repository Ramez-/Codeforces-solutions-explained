import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

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

//		int hi = n;
//		int lo = countUnique;
//		int mid = (hi + lo) / 2;
//		boolean found = false;
//		int smallestPossibleMid = n;
//
//		while (hi > lo) {
//			mid = (hi + lo) / 2;
//			TreeMap<Character, Integer> treeMap = new TreeMap<>();
//
//			int pointerOne = 0;
//			int pointerTwo = mid - 1;
//
//			for (int i = pointerOne; i <= pointerTwo; i++) {
//				if (treeMap.containsKey(arr[i])) {
//					treeMap.put(arr[i], treeMap.get(arr[i]) + 1);
//				} else {
//					treeMap.put(arr[i], 1);
//				}
//
//			}
//
//			found = false;
//			if (treeMap.size() == countUnique) {
//				found = true;
//			}
//
//			while (pointerTwo < n - 1) {
//				pointerOne++;
//				pointerTwo++;
//
//				if (treeMap.containsKey(arr[pointerOne]) && treeMap.get(arr[pointerOne]) > 1) {
//					treeMap.put(arr[pointerOne], treeMap.get(arr[pointerOne]) - 1);
//				} else {
//					treeMap.remove(arr[pointerOne]);
//				}
//
//				if (treeMap.containsKey(arr[pointerTwo])) {
//					treeMap.put(arr[pointerTwo], treeMap.get(arr[pointerTwo]) + 1);
//				} else {
//					treeMap.put(arr[pointerTwo], 0);
//				}
//
//				if (treeMap.size() == countUnique) {
//					found = true;
//					break;
//				}
//			}
//
//			if (found) {
//				hi = mid;
//				smallestPossibleMid = Math.min(mid, smallestPossibleMid);
//			} else {
//				lo = mid + 1;
//			}
//
//		}
//
//		System.out.println(smallestPossibleMid);

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
