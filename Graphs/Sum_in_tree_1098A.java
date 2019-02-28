import java.io.*;
import java.util.*;

/**
 * Given a tree where only the even depth has the sum of all values from the node till the root, while odd
 * depth node has its sum values erased. Find the minimal sum of all nodes in the tree
 *
 * In order to get the minimal sum for each odd node we need to find the minimal child that it has and assign this value
 * to the odd depth node. Incase it is leaf then the value of odd node is zero
 */

public class Sum_in_tree_1098A {
    static int n;
    static int [] p;
    static int [] s;
    static ArrayList<Integer> adjList[];
    static boolean prob;

    // u is parent, v is node itself
    // sum at parent, could have actually did it with the u
    public static void dfs(int u, int v, int sum) {
        for (Integer node : adjList[v]) {
            if (node == u) continue;
            if (s[node] == -1) {
                int min = Integer.MAX_VALUE;
                for (Integer child : adjList[node]) {
                    if (child != v)
                        min = Math.min(s[child], min);
                }
                if (min < sum) {
                    prob = true;
                }
                else {
                    if (min != Integer.MAX_VALUE)
                        s[node] = min;
                    // leaf node equals to parent node, so the erased value is zero
                    else
                        s[node] = s[v];
                }
            }
            dfs(v, node, s[node]);
        }
    }

    public static long dfsRes(int u, int v) {
        long res = 0;
        for (Integer node : adjList[v]) {
            if (node == u) continue;

            res += (s[node] - s[v]) + dfsRes(v, node);

        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        p = new int[n];
        s = new int[n];
        p[0] = -1;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }


        for (int i = 1; i < n; i++) {
            p[i] = sc.nextInt() - 1;
            adjList[i].add(p[i]);
            adjList[p[i]].add(i);
        }

        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
        }

        dfs(-1, 0, s[0]);

        if (prob) {
            System.out.println(-1);
        } else {
            System.out.println(s[0] + dfsRes(-1, 0));
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