import java.io.*;
import java.util.*;

/**
 * Problem: find the number of pairs in array where the sum of numbers between them is less than t
 * <p>
 * First thing to notice is that size of array is maximum 200000 while the numbers them selves could be 10^9 postive or negtive
 * so we can use coordination compression so that we can use binary index tree to count the number of indecies that are in particlar range
 * <p>
 * Second thing to notice is that if we called the prefix sum array arr then arr[r] - arr[l] < t holds which is equivalant to
 * arr[r] - t < arr[l] where r is right and l is left
 * <p>
 * Finally to solve the problem, we are going to loop over all elements in the array, first check the number of elements that are before
 * me that are smaller than arr[r] - t those are the elements that shouldn't be added to the res. Then we need to add this element to the
 * BIT. We are going to do coordination compression by putting all possible numbers which are the numbers and the numbers - t to the map
 */

public class Petya_and_Array_1042D {
    static int N = (int) 4e5 + 5;
    static int[] BIT = new int[N];

    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        long t = sc.nextLong();
        long[] arr = new long[n];
        TreeSet<Long> set = new TreeSet<>();
        set.add(0l);
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (i > 0)
                arr[i] += arr[i - 1];
            set.add(arr[i]);
            set.add(arr[i] - t);
        }
        // coordination compression
        int id = 1;
        for (long x : set)
            map.put(x, id++);

        add(map.get(0l));
        long ans = 0;

        for (int r = 0; r < n; r++) {
            // what we are looking for is arr[l] which should be bigger than arr[r] - t, so we see the number of elements that are smaller than arr[r] - t then subtract
            int numberOfElementsNotWork = get(map.get(arr[r] - t));
            ans += r + 1 - numberOfElementsNotWork; // array of all left - the ones that doesn't work
            add(map.get(arr[r])); // add element to BIT
        }

        pw.println(ans);

        pw.flush();
        pw.close();
    }

    static void add(int idx) {
        while (idx < N) {
            BIT[idx]++; // means we have additional element that is smaller than or equal current index
            idx += (idx & -idx);
        }
    }

    static int get(int idx) {
        int ret = 0;
        while (idx > 0) {
            ret += BIT[idx];
            idx -= (idx & -idx);
        }
        return ret;
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