import java.io.*;
import java.util.*;

/**
 * calculate number of ways to remove exactly one substring from this
 * string in such a way that all remaining characters are equal
 *
 */

public class Substring_Removal_1096B {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        String s = sc.nextLine();
        long simFirst = 1;
        long simLast = 1;
        char first = s.charAt(0);
        char last = s.charAt(s.length() - 1);

        for (int i = 1; i < n; i++) {
            if (first != s.charAt(i)) break;
            simFirst++;
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            if (last != s.charAt(i)) break;
            simLast++;
        }

        if (first != last) {
            System.out.println((simFirst % 998244353 + simLast % 998244353 + 1) % 998244353);
            return;
        }


        // equation to select the number of elements of right side and for each element select another in the
        // left side
        System.out.println((simFirst + 1) * (simLast + 1) % 998244353);


    }

    static class Pair implements Comparable<Pair> {
        int count;
        int number;

        public Pair(int a, int b) {
            this.count = a;
            this.number = b;
        }

        @Override
        public int compareTo(Pair o) {
            return o.count - this.count;
        }
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream System) throws FileNotFoundException {
//            String FILENAME = "level2_1.in";
//			br = new BufferedReader(new FileReader(FILENAME));
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