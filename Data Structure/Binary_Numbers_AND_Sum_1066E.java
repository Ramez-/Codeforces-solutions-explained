import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem: given binary huge numbers a and b, keep doing a & b then divid b by 2 till b = 0
 *
 * To solve this problem efficiently we have to notice that the final sum will be summation of 2 ^ (indeces of a where bit = 1) when
 * indeces of b where 1, notice that the indecies of b will keep moving right because of division by 2, then this means we can just
 * count all the indeces before certain index of a which is one where b's indeces where 1 and do 2 ^(index of a) * collision with
 * index of b
 *
 * Don't forge that power will overflow in this problem.
 */

public class Binary_Numbers_AND_Sum_1066E {


    /**
     * It is important to take care of power overflow in this problem we can reach 2 ^ (10 ^ 5)
     *
     * @param a the base of power
     * @param b the power itself
     * @param MOD the modules to be taken
     * @return the a power b, but take modules in each step to prevent power overflow
     */
    private static long pow(long a, int b, long MOD) {
        long result = 1;
        while (b > 0) {
            if (b % 2 != 0) {
                result *= a;
                result %= MOD;
                b--;
            }
            a *= a;
            a %= MOD;
            b /= 2;
        }

        return result % MOD;

    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String a = sc.next();
        String b = sc.next();
        final long mod = 998244353;
        int maxLength = Math.max(n, m);
        StringBuilder sb = new StringBuilder();

        if (n > m) {
            for (int i = 0; i < n - m; i++) {
                sb.append(0);
            }
            sb.append(b);
            b = sb.toString();
        }
        if (m > n) {
            for (int i = 0; i < m - n; i++) {
                sb.append(0);
            }
            sb.append(a);
            a = sb.toString();
        }

        int[] arr = new int[maxLength];
        arr[0] = Integer.parseInt(b.charAt(0) + "");
        for (int i = 1; i < maxLength; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(b.charAt(i) + "");
        }
        int res = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            if (a.charAt(i) == '1')
                res = (int) ((res + (pow(2, ((maxLength - 1) - i), mod) * arr[i] % mod)) % mod);
        }
        System.out.print(res);
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