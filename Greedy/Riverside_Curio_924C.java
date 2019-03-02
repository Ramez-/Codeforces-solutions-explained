import java.io.*;
import java.util.*;

/**
 * Each day the water level change, so each day person x puts a mark where the water level is. Find the minimal sum
 * number of lines under the water for each day.
 *
 * To minimaize we can minimize the overall number of lines for each day. See the comments for implementation
 */

public class Riverside_Curio_924C {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // represent overall number of lines for each day
        int [] t = new int[n];
        // first day there is a line where the water is
        t[0] = 1;
        // number of lines in a day is the maximal between the lines today and the lines of yesterday.
        for (int i = 1 ; i < n; i++) {
            t[i] = Math.max(t[i - 1], arr[i] + 1);
        }

        // Then insure that there is no lines missing by the above calculation
        for (int i = n - 2; i >= 0; i--) {
            t[i] = Math.max(t[i], t[i + 1] - 1);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (t[i] - (arr[i] + 1));
        }
        System.out.println(ans);
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
    static class Pair implements Comparable<Pair>{
        int x;
        int y;

        Pair(int start, int end) {
            this.x = start;
            this.y = end;
        }

        @Override
        public int compareTo(Pair o) {
            return o.y - this.y;
        }
    }

}