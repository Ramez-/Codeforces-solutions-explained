import java.io.*;
import java.util.*;

/**
 * Given length of array you need to create, which follow the following condition
 * for certain intervals it should be sorted ascending, and for certain intervals
 * it should't be sorted.
 *
 * start by filling another array b, with ones where it should be sorted asendingly, then
 * start by filling our result array by putting n in the first cell and decrease the number
 * whenever we can and keep it the same whenever we should
 *
 */

public class Vasya_And_Array_1187C {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Pair> sorted = new ArrayList<>();
        ArrayList<Pair> notSorted = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int isSorted = sc.nextInt();
            if (isSorted == 1) {
                sorted.add(new Pair(sc.nextInt() - 1, sc.nextInt() - 1));
            }
            else {
                notSorted.add(new Pair(sc.nextInt() - 1, sc.nextInt() - 1));
            }
        }

        int [] b = new int [n];
        int [] res = new int[n];
        for (Pair p : sorted) {
            // take care we don't want to put one at the beginning of the interval because
            // we don't care its relation with the previous one
            for (int i = p.a + 1; i <= p.b; i++) {
                // if b is 1 then this means it should be equal or more than the before
                b[i] = 1;
            }
        }
        res[0] = n;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] - (b[i] == 1 ? 0 : 1);
        }
        // checking if this result is valid
        for (Pair p : notSorted) {
            boolean cool = false;
            for (int i = p.a; i < p.b; i++) {
                if (res[i] > res[i + 1]) {
                    cool = true;
                }
            }
            if (!cool) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("Yes");
        for (int i = 0; i < n; i++) {
            pw.print(res[i] + " ");
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

    static class Pair implements Comparable<Pair> {
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