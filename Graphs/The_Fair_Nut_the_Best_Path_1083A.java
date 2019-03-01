import java.io.*;
import java.util.*;

/**
 * Given a tree, find the maximal sum from one node to another where the value on the node is added and each edge
 * has a value that is subtracted. You can't have negtive sum on that path.
 *
 * We use DP to store at each node the value of the maximum sum from the node in one of its branches. In the same
 * dfs we check and try to add it to the maximal branch, this way the selected node is in the middle of the graph.
 */

public class The_Fair_Nut_the_Best_Path_1083A {
    static int n;
    static ArrayList<Pair> adjList[];
    static int[] gas;
    // maximum distance that I can reach from this node
    static long [] dp;
    static boolean[] visited;
    static long res = 0;

    static void dfs(int node, int p) {
        visited[node] = true;
        // minimal possible solution for node
        dp[node] = gas[node];
        for (Pair child : adjList[node]) {
            if (child.a == p) continue;
//            if (!visited[child.a]) {
                dfs(child.a, node);
                // getting the best result from all child branches and try to add it to another branch
                res = Math.max(res, dp[node] + dp[child.a] - child.b);
                // store in DP only the value of the max branch
                dp[node] = Math.max(dp[node], dp[child.a] - child.b + gas[node]);
//            }
        }
        //in case the a node has very big gas value
        res = Math.max(dp[node], res);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        adjList = new ArrayList[n];
        dp = new long[n + 1];
        gas = new int[n];


        for (int i = 0; i < n; i++) {
            gas[i] = sc.nextInt();
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int w = sc.nextInt();
            adjList[a].add(new Pair(b, w));
            adjList[b].add(new Pair(a, w));
        }

        visited = new boolean[n];
        dfs(0 ,-1);
        System.out.println(res);

    }

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
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