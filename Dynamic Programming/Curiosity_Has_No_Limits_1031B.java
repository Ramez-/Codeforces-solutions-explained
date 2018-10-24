import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Problem: Given two arrays a and b find, if possible, and array of elements ti, ti+1, .... such that
 * ti OR ti+1 = ai
 * ti AND ti+1 = bi
 *
 * To solve this problem we can apply dynamic programming approch where we try out all possible ti that are legal according to
 * the problem difintion.
 */

public class Curiosity_Has_No_Limits_1031B {
    static int n;
    static ArrayDeque<Integer> res;
    static int [] a;
    static int [] b;

    public static boolean dp (int index, int lastElement) {
        if (index >= n - 1) return true;
        boolean result = false;
        boolean failed = true;
        for (int i = 0; i <= 3; i++) {
            if((lastElement | i) == a[index] && (lastElement & i) == b[index]) {
                failed = false;
                res.add(i);
                result = result | dp(index + 1, i);
            }
        }
        if(failed)
            res.removeLast();
        return result;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int [n - 1];
        b = new int [n - 1];

        for (int i = 0; i < n - 1; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            b[i] = sc.nextInt();
        }



        for (int i = 0; i <= 3; i++) {
            res = new ArrayDeque<>();
            res.add(i);
            if (dp(0, i)) {
                System.out.println("YES");
                for (Integer integer : res) {
                    System.out.print(integer + " ");
                }
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