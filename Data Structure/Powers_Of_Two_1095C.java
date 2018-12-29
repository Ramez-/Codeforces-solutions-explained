import java.io.*;
import java.util.*;

/**
 * Can you represent a number n by k numbers of powers of two such as 1,2,4,8,16,32,...
 *
 * In order to solve this problem optimally use a priority queue to put only the powers
 * of 2 that consturct n, then divide the bigger number always by 2 until we can reach number
 * of elements in the priority queue to k, otherwise print NO
 */

public class Powers_Of_Two_1095C {
    static int k;
    static int n;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i <= 31; i++) {
            long x = 1;
            x = x << i;
            if ((n & x) == 0) continue;
            pq.add(x);
        }

        while (pq.size() < k) {
            long number = pq.peek();
            if (number == 1) break;
            pq.poll();
            pq.add(number / 2);
            pq.add(number / 2);
        }

        if (pq.size() != k) {
            System.out.println("NO");
            return;
        }
        Stack<Long> res = new Stack<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        System.out.println("YES");
        while (!res.isEmpty()) {
            System.out.print(res.pop() + " ");
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