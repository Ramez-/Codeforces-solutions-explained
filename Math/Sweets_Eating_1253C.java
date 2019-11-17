import java.io.*;
import java.util.*;

/**
 * Given array of sweets with sugar level and the maximum number of sweets to eat per day. Eating a
 * sweet is multiplied by the current day
 *
 * For each number of sweets eaten find the minumal sugar level
 *
 * To solve this problem calculate the first m sweets, then when you are at day k + m add the
 * sweets of the first day which will be from k + m till k, then go to res[k] and add the prefix
 * this way we add 1 to the coficent of all the previous days
 */

public class Sweets_Eating_1253C {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] arr = new int[n];
        long [] res = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        // prefix sum
        long [] prefix = new long[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = arr[i] + prefix[i - 1];
        }
        // calculate the first m
        for (int i = 0; i < m; i++) {
            res[i] = prefix[i];
        }

        // see previous k - m add prefix sum of k - m add (k - k-m)
        for (int i = m; i < n; i++) {
            long firstDay = prefix[i] - prefix[i - m];
            res[i] = res[i - m] + prefix[i - m] + firstDay;
        }

        for (long re : res) {
            System.out.print(re + " ");
        }

    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream System) throws FileNotFoundException {
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
