import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Given an array is it possible to divid the array to two consective sub arrays where the sum of both parts is equal,
 * and you can move one element from the first array to the second array
 *
 * Move from the beginning till the end and from the end till the beginning
 * If the direction I am moving forward has bigger sum than the other side and 2 * diff is in the set then return YES
 */

public class Array_Division_808D {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        HashSet<Long> hset = new HashSet<>();
        long leftSide = 0;
        for (int i = 0; i < n; i++) {
            leftSide += arr[i];
            hset.add((long) (arr[i] * 2));
            // because there can't be empty set
            if (i == 0) continue;
            long rightSide = sum - leftSide;
            long diff = leftSide - rightSide;
            if (diff == 0 || (diff % 2 == 0 && hset.contains(diff))) {
                System.out.println("YES");
                return;
            }
        }

        hset = new HashSet<>();
        long righSide = 0;
        for (int i = n - 1; i >= 0; i--) {
            righSide += arr[i];
            hset.add((long) (arr[i] * 2));
            // because there can't be empty set
            if (i == n - 1) continue;
            leftSide = sum - righSide;
            long diff = righSide - leftSide;
            if (diff == 0 || (diff % 2 == 0 && hset.contains(diff))) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream System) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(System));
//			String FILENAME = "level4.txt";
//			br = new BufferedReader(new FileReader(FILENAME));
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