import java.io.*;
import java.util.*;


/**
 * we have go n station in a clockwise ciruclar train, m candies distubited among the stations,
 * and need to be transported to other station, calculate for each station how long it takes
 * to transport all candies if we start from that station. We can only pick one candy at a time
 * from a station.
 *
 * To solve this problem we will calculate for each station number of candies it has intially,
 * and the candy that will be transported the minimal distance.
 *
 * Then for each station we will move the train to all station and test how long it will take
 * to transport all candies at that station, we are going to select the maximum from all stations
 * as this station will have the last candy to transport.
 *
 */
public class Toy_Train_1129A2 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] at = new int[m];
        int [] to = new int[m];
        for (int i = 0; i < m; i++) {
            at[i] = sc.nextInt() - 1;
            to[i] = sc.nextInt() - 1;
        }

        int [] candiesAtStation = new int[n];
        int [] bestCandyAtStation = new int[n];
        Arrays.fill(bestCandyAtStation, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            candiesAtStation[at[i]]++;
            int dist = to[i] - at[i];
            // to calculate the distance in case of circular stations
            if (dist < 0) {
                dist += n;
                dist = dist % n;
            }

            bestCandyAtStation[at[i]] = Math.min(bestCandyAtStation[at[i]], dist);
        }
        for (int i = 0; i < n; i++) {
            if (bestCandyAtStation[i] == Integer.MAX_VALUE) {
                bestCandyAtStation[i] = 0;
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            int res = 0;
            for (int j = 0; j < n; j++) {
                int dist = j - i;
                if (dist < 0) {
                    dist += n;
                    dist = dist % n;
                }
                if (candiesAtStation[j] == 0) continue;
                res = Math.max(res, dist + ((candiesAtStation[j] - 1) * n) + bestCandyAtStation[j]);
            }
            pw.print(res + " ");
        }
        pw.close();
        pw.flush();
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
    static class Pair {
        int x;
        int y;

        Pair(int start, int end) {
            this.x = start;
            this.y = end;
        }
    }

}