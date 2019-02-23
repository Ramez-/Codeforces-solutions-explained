import java.io.*;
import java.util.*;

/**
 * Given a tree with some nodes colored red, blue or uncolored, find number of edges that can be cut so
 * that the niether sides of graph has both red and blue nodes
 *
 * In order to solve the problem we need to check for every node its subtree the number of blue and red nodes in it.
 * Then iterate over this list and count the nodes that has in it's subtree zero of one color and total of other color
 */

public class Tree_Cutting_1118_F1 {
    static ArrayList<Integer> adjList[];
    static int redCount = 0;
    static int blueCount = 0;
    static int n;
    static int res = 0;
    static boolean[] visited;
    static int[] color;
    static Pair [] pairs;

    /**
     * once you back trace you lose information about the count of red and blue
     * @param node
     * @return
     */
//    static void dfs(int node, int red, int blue) {
//        visited[node] = true;
//        for (Integer child : adjList[node]) {
//            if (!visited[child]) {
//                if (color[child] == 1)
//                    dfs(child, red + 1, blue);
//                else if (color[child] == 2)
//                    dfs(child, red, blue + 1);
//                else dfs(child, red, blue);
//            }
//        }
//
//        if (red == redCount && blue == 0) res++;
//        if (red == 0 && blue == blueCount) res++;
//    }

    static Pair dfs(int node) {
        visited[node] = true;
        int red = 0;
        int blue = 0;
        for (Integer child : adjList[node]) {
            if (!visited[child]) {
                Pair p = dfs(child);
                red += p.x;
                blue += p.y;
            }
        }
        if (color[node] == 1) {
            red++;
        }
        if (color[node] == 2) {
            blue++;
        }
        // return the pair so that I don't lose the count of my children subtree

        return  pairs[node] = new Pair(red, blue);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        adjList = new ArrayList[n + 1];
        pairs = new Pair[n + 1];

        color = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            int num = sc.nextInt();
            color[i] = num;
            if (num == 1) redCount++;
            else if (num == 2) blueCount++;
        }
        adjList[n] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adjList[a].add(b);
            adjList[b].add(a);
        }
        adjList[n].add(0);


        dfs(n);

        for (int i = 0; i < n; i++) {
            if (pairs[i].x * pairs[i].y == 0 && (redCount - pairs[i].x) * (blueCount - pairs[i].y) == 0) {
                res++;
            }
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
    static class Pair {
        int x;
        int y;

        Pair(int start, int end) {
            this.x = start;
            this.y = end;
        }
    }

}