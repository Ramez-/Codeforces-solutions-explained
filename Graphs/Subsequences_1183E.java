import java.io.*;
import java.util.*;

/**
 * Given n and k, size of a given string and the size of set you can create.
 * The set to be created need to have a subsequence of the given string
 * print the minimal cost to create the set where the cost is summation of  (n - substring length of each string)
 *
 * To solve this problem optimally we can use a bfs method, where at the beginning we push the whole string, then
 * we add to the queue the string where we remove ONE charachter from the string, we add strings only to our result set
 * when it is not in the set, we stop when the set has size k
 */

public class Subsequences_1183E {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.nextLine();
        int res = 0;
        Queue<String> q = new ArrayDeque<>();
        q.add(s);
        HashSet<String> hashSet = new HashSet<>();
        while (k > 0) {
            if (q.isEmpty()) {
                break;
            }
            String first = q.remove();
            res += n - first.length();
            k--;
            for (int i = 0; i < first.length(); i++) {
                String created = first.substring(0, i) + first.substring(i + 1);
                if (!hashSet.contains(created)) {
                    hashSet.add(created);
                    q.add(created);
                }
            }
        }
        if (k > 0) {
            System.out.println(-1);
        }
        else{
            System.out.println(res);
        }
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int size;

        public Pair(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public int compareTo(Pair o) {
            return this.size - o.size;
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