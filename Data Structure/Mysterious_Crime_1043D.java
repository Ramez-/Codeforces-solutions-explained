import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem: given m arrays of size n, find the number of combination of elements that have the same order, in all arrays.
 *
 * To solve this problem optimally we will create array after, that will contain information of the element after me, it
 * will be overwritten with -2 in case this doesn't match in any of the arrays. Calclate the length of numbers in the same order
 * in all arrays and accumulate the combination of it.
 *
 * Note: You need to start calcluation of length from the number that is in the beginning of the common sequence and go forwards
 */

public class Mysterious_Crime_1043D {

    public static void main (String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] after = new int[n];
        Arrays.fill(after, -1);
        for (int i = 0; i < m; i ++) {
            int prev = sc.nextInt() - 1;
            for (int j = 1; j < n; j++) {
                int now = sc.nextInt() - 1;
                if (after[prev] == -1) {
                    after[prev] = now;
                }
                else {
                    if(after[prev] != now) {
                        after[prev] = -2;
                    }
                }
                prev = now;
            }
            // last element doesn't have any element after it
            after[prev] = -2;
        }
        long res = n;

        boolean [] middleInSequence = new boolean[n];
        for (int number : after) {
            if (number >= 0) middleInSequence[number] = true; // marking all numbers in middle of sequence by true
        }

        for (int i = 0; i < n; i++) {
            if (!middleInSequence[i]) {
                long length = 1;
                int j = i;
                while (after[j] >= 0) {
                    length++;
                    j = after[j];
                }
                res += (length * (length - 1)) >> 1;
            }
        }
        System.out.println(res);

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;
        public Scanner(InputStream System) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(System));
//			String FILENAME = 'level4.txt';
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