import java.io.*;
import java.util.*;

/**
 * Given a string you can delete consective charchters that have the same value with the cost of 1, what is
 * the mimumum number operation needed to clear the string.
 *
 * Solve this problem using DP
 * imporant: you can use pointers to ease the problem instead of using substring method
 */

public class Clear_the_String_1132F {
    static int [][]dp;
    static char [] arr;
    public static int dp (int l, int r) {
        if (l > r) return 0;
        if (dp[l][r] != -1) return dp[l][r];

        int choiceTwo = Integer.MAX_VALUE;
        for(int i=l+1;i<=r;i++)
        {
            if(arr[l]==arr[i])
                // matching a charchter to its matching charchter and recusing on the middle and the end parts
                // dp(l + 1, i - 1) remove current charchter without a cost but you have to match it to charchter
                // and handle the middle part on its own.
                // this means that the middle part cannot match with end part
                choiceTwo=Math.min(choiceTwo, dp(l+1,i-1)+dp(i,r));
        }
        // removing element directly from the string
        int choiceOne = 1 + dp(l + 1, r);

        return dp[l][r] = Math.min(choiceOne, choiceTwo);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.nextLine();
        arr = s.toCharArray();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dp(0, n - 1));

    }

    static class Pair {
        String a;
        int b;

        Pair(String a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return b == pair.b &&
                    Objects.equals(a, pair.a);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
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

    static class Edge implements Comparable<Edge> {
        int to;
        int index;
        int w;

        public Edge(int to, int index, int w) {
            this.to = to;
            this.index = index + 1;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}