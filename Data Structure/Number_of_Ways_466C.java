import java.io.*;
import java.util.*;

/**
 * Given an array find the number of ways we can divid this array into 3 parts all having same summation
 *
 * This means that each part of the array should have sum (n / 3), if the whole array is not divisable by 3
 * then return zero.
 *
 * Using prefix sum array we calculate in array l[] the times where the prefix sum is equal to the total sum / 3
 * doing the same for r[] for the end of the array. For the middle part it is automatically done because
 * if the first part and the end part of the array are divisable by 3 then the middle part as well.
 * Therefore we calculate the number of times their is part divisable by 3 in the begining of the array
 * and part in the end of the array divisable by 3.
 */

public class Number_of_Ways_466C {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long [] pref = new long[n];
        pref[0] = arr[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + arr[i];
        }
        if (pref[n - 1] % 3 != 0) {
            System.out.println(0);
            return;
        }
        int [] l = new int[n];
        int [] r = new int[n];

        for (int i = 0; i < n - 2; i++) {
            if (pref[i] == pref[n - 1] / 3) {
                l[i] = 1;
            }
        }

        for (int i = 2; i < n; i++) {
            if (pref[n - 1] - pref[i - 1] ==  pref[n - 1] / 3) {
                r[i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            l[i] = l[i - 1] + l[i];
        }
        long res = 0;
        for (int i = 2; i < n; i++) {
            res += r[i] * l[i - 2];
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

    static class Pair implements Comparable<Pair>{
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return this.b - o.b;
        }
    }
}