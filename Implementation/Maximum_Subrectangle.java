import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem: given two arrays one representing row factor and one representing col factor, find the biggest
 * rectangle that have sum as big as possible but less than k
 *
 * To solve this question we need to revise that for row => 1,2,3 and col => 1,2,3 the summation of the squar
 * of 2 by 2 in top right corner is 1*2 + 2*1 + 1*1 + 2*2 which is equivalent to (1+2) * (1+2)
 *
 * we are going to construct array of sizes which has the minimal sum for each size of columns then we are
 * going to go throw every possible row segment and binary search to get the biggest sum that is within our
 * constraint
 */

public class Maximum_Subrectangle {
    static int [] minimalSum;
    static int n2;
    public static int binarySearch(int maximalAllowed) {
        int lo = 0;
        int hi = n2;
        int res = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (minimalSum[mid] <= maximalAllowed) {
                res = mid;
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        n2 = sc.nextInt();
        int [] row = new int[n1];
        int [] col = new int[n2];

        for (int i = 0; i < n1; i++) {
            row[i] = sc.nextInt();
        }

        for (int j = 0; j < n2; j++) {
            col[j] = sc.nextInt();
        }

        int k = sc.nextInt();

        int [] sumCol = new int[n2 + 1];
        for (int i = 1; i <= n2; i++) {
            sumCol[i] = sumCol[i-1] + col[i - 1];
        }
         // need to add the zero as when doing sumCol[j + 1] - sumCol[i] get the entire width not minus first one
        minimalSum = new int[n2 + 1];
        Arrays.fill(minimalSum, Integer.MAX_VALUE);
        minimalSum[0] = 0;
        for (int i = 0; i < n2; i++) {
            for (int j = i; j < n2; j++) {
                int size = j - i + 1;
                minimalSum[size] = Math.min(minimalSum[size], sumCol[j + 1] - sumCol[i]);
            }
        }
        int sum;
        int res = 0;
        for (int l = 0; l < n1; l++) {
            sum = 0;
            for (int r = l; r < n1; r++) {
                sum += row[r];
                int maximalAllowed = k / sum;
                // if the sum from the row doesn't exceed k
                if (maximalAllowed > 0)
                    // size of rows selected * size of cols selected
                    res = Math.max(res, (r - l + 1) * binarySearch(maximalAllowed));
            }
        }
        System.out.println(res);
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

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}