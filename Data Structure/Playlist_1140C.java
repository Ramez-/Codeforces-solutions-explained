import java.io.*;
import java.util.*;

/**
 * Given a playlist of songs each song has beauty and length and integer k, find at most k songs with maximum
 * total beauty which is calclated by getting the mimimum beauty among the songs multiplied by the summation
 * of all length.
 *
 * first sort the array with beauty then iterate from the descending, putting the lengths in priority queue
 * which is min heap. Always add sums to sumSoFar then remove the smallest elements using the priority queue
 * from the sum
 */

public class Playlist_1140C {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Pair [] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sumSoFar = 0;
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            sumSoFar += arr[i].a;
            pq.add(arr[i].a);
            while (pq.size() > k) {
                // remove the small sums that were added so that only k items are in sumSoFar
                sumSoFar -= pq.poll();
            }
            res = Math.max(res, sumSoFar * arr[i].b);
        }
        System.out.println(res);
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
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return this.b - o.b;
        }
    }
}