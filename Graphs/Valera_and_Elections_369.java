import java.io.*;
import java.util.*;

/**
 * Try to get the deepest node in the tree that has problem by using dfs. Deepest node will cover all above nodes
 */
public class Valera_and_Elections_369 {

    static ArrayList<Pair> adjList[];
    static ArrayList<Integer> res = new ArrayList<>();
    static boolean [] visited;
    static boolean dfs(int node) {
        visited[node] = true;
        boolean ret = false;
        for (Pair child : adjList[node]) {
            boolean got = false;
            if (!visited[child.a]) {
                got = dfs(child.a);

                if (child.b == 2 && !got) {
                    ret = true;
                    res.add(child.a);
                }
                if (got) ret = true;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        adjList = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int w = sc.nextInt();
            adjList[a].add(new Pair(b, w));
            adjList[b].add(new Pair(a, w));
        }
        dfs(0);
        System.out.println(res.size());
        for (Integer integer : res) {
            System.out.print((integer + 1) + " ");
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

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}