import java.io.*;
import java.util.*;


/**
 * Given a graph where some edges are black and others are red and an integer k, find the number of times where
 * we can write k nodes and there is a black edge between them.
 *
 * to solve this problem we calclate the ones which has no black edge between them and total options should be subtracted
 * from them.
 */

public class Edgy_Trees_1139C {
    static int n;
    static int k;
    static ArrayList<Pair> adjList[];
    static boolean visited[];

    static void dfs(int node, ArrayList<Integer> res) {
        visited[node] = true;
        for (Pair child : adjList[node]) {
            if (!visited[child.a] && child.b == 0) {
                res.add(child.a);
                dfs(child.a, res);
            }
        }
    }

    static long power(long x, long y, long mod) {
        // Initialize result
        long res = 1;

        // Update x if it is more
        // than or equal to p
        x = x % mod;

        while (y > 0) {
            // If y is odd, multiply x
            // with result
            if ((y & 1) == 1)
                res = (res * x) % mod;

            // y must be even now
            // y = y / 2
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        adjList = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int x = sc.nextInt();
            adjList[a].add(new Pair(b, x));
            adjList[b].add(new Pair(a, x));
        }
        long mod = (long) (1e9 + 7);
        long sol = 0;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> res = new ArrayList<>();
            if (!visited[i]) {
                res.add(i);
                dfs(i, res);
                sol = (sol + (power(res.size(), k, mod) % mod)) % mod;
            }
        }
        long total = power(n, k, mod) % mod;
        // total is not smaller than sol, the problem is when you use the mod, you lose property of who is bigger than who
        // so you need to add mod and take the mod of the res
        System.out.println((total - sol + mod) % mod);
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
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}