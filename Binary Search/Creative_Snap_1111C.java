import java.io.*;
import java.util.*;

/**
 * given array of 2 ^ n where some cells have avengers, calculate the min number of cost to destroy cells. There is
 * two choices to delete cells, either divide the cells by 2 or delete the contigous cells where the cost is A in case it is
 * empty otherwise cost equals B * length of cells * number of avengers in them
 *
 * To solve this problem we can think of backtracking solution, we have n substates so the algorithm run in O(n*k*log(k))
 *
 * To get number of avengers in contigous cells we can use binary search to get the left limit and the right limit
 */


public class Creative_Snap_1111C {
    static int n;
    static int k;
    static int a;
    static int b;
    static int[] arr;

    static long dp(int start, int end) {
        int partition = (start + end) / 2;
        int lo = 0;
        int hi = k - 1;
        int left = -1;
        int right = -1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (arr[mid] >= start) {
                left = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        lo = 0;
        hi = k - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= end) {
                right = mid;
                lo = mid + 1;
            } else
                hi = mid - 1;
        }
        long count = right - left + 1;
        if (left == -1 || right == -1) {
            count = 0;
        }

        if (count == 0 || right < left) {
            return a;
        }
        if (start == end)
            return b * count;
        long choice1 = dp(start, partition) + dp(partition + 1, end);
        long choice2 = b * count * (end - start + 1);
        return Math.min(choice1, choice2);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        arr = new int[k];
        for (int i = 0; i < k; i++) {
            int input = sc.nextInt();
            arr[i] = input;
        }
        Arrays.sort(arr);
        System.out.println(dp(1, 1 << n));

    }

    static class Pair {
        String start;
        String end;

        Pair(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream System) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(System));
//            String FILENAME = "level4.txt";
//            br = new BufferedReader(new FileReader(FILENAME));
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