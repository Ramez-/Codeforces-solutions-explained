import java.io.*;
import java.util.*;

/**
 * Given an array of elements, find the number of pair l and r where the distance between l and r is even and
 * the right half of the array xored is equal to the left half xored
 *
 * Important thing to notices is that if left half xored equal to right half xored then all elements between l and r
 * xored equal to zero
 *
 * Prefix xor work exactly the same as prefix sum in array
 *
 * for each even index in the array find the number of previous even indices that are equal
 * for each odd index in the array find the number of previous odd indices that are equal
 *
 */

public class Sasha_and_Bit_Relax_1109A {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int [] arr = new int[n];
        arr[0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
            int num = sc.nextInt();
            arr[i] = num ^ arr[i - 1];
        }
        HashMap<Integer, Integer> even = new HashMap<>();
        HashMap<Integer, Integer> odd = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                res += even.getOrDefault(arr[i], 0);
                even.put(arr[i], even.getOrDefault(arr[i], 0) + 1);
            }
            else {
                res += odd.getOrDefault(arr[i], 0);
                odd.put(arr[i], odd.getOrDefault(arr[i], 0) + 1);
                if (arr[i] == 0) res++;
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
}