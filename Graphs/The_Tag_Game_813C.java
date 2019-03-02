import java.io.*;
import java.util.*;

/**
 * Given a tree where Alice start from the root and Bob starts from node x, Alice try to catch Bob, find the maximum
 * steps where alice can reach bob, given that bob starts by moving.
 *
 * In order to solve this problem optimally, we are going to calculate the distance alice is from all nodes, then we
 * are going to dfs from x to all leaves making sure that we reach there before alice do.
 */

public class The_Tag_Game_813C {
    static int n;
    static ArrayList<Integer> adjList[];
    static int [] arr;
    static boolean[] visited;
    static int selectedLeaf;

    static void dfs(int node, int dist) {
        visited[node] = true;
        arr[node] = dist;
        for (Integer child : adjList[node]) {
            if (!visited[child]) {
                dfs(child, dist + 1);
            }
        }
    }

    static void dfs2(int node, int dist) {
        visited[node] = true; 

        for (Integer child : adjList[node]) {
            if (!visited[child]) {
                dfs2(child, dist + 1);
            }
        }
        // important: we need the maximum branch starting from alice, as it will be the maximum number of steps
        if (arr[node] > arr[selectedLeaf] && arr[node] > dist) {
            selectedLeaf = node;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int x = sc.nextInt() - 1;
        adjList = new ArrayList[n];
        arr = new int[n];


        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adjList[a].add(b);
            adjList[b].add(a);
        }

        visited = new boolean[n];
        dfs(0, 0);
        visited = new boolean[n];
        selectedLeaf = x;
        dfs2(x, 0);
        System.out.println(arr[selectedLeaf] * 2);

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