import java.io.*;
import java.util.*;

/**
 * given segment line containing trees at various positions, and m number of people to put around the trees
 * mimimize the summation of distance for all people to the nearest tree
 *
 * convert the problem to multi source bfs graph
 * put all trees in the queue, remove the tree and people around the tree in the queue
 */
public class Christmas_Trees_1283D {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = sc.nextInt();
        int m = sc.nextInt();
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> sol = new ArrayList<>();
        ArrayDeque<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int e = sc.nextInt();
            q.add(new Pair(e, e));
            visited.add(e);
        }
        long dist = 0;
        while (m > 0) {
            Pair poll = q.poll();
            if (!visited.contains(poll.a + 1)) {
                q.add(new Pair(poll.a + 1, poll.b));
                dist += Math.abs(poll.a + 1 - poll.b);
                m--;
                sol.add(poll.a + 1);
                visited.add(poll.a + 1);
            }
            if (m <= 0) {
                break;
            }
            if (!visited.contains(poll.a - 1)) {
                q.add(new Pair(poll.a - 1, poll.b));
                m--;
                dist += Math.abs(poll.a - 1 - poll.b);
                sol.add(poll.a - 1);
                visited.add(poll.a - 1);
            }
        }
        out.println(dist);

        for (Integer integer : sol) {
            out.print(integer + " ");
        }
        out.flush();
        out.close();
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
            return this.a - o.a;
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
