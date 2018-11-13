import java.io.*;
import java.util.StringTokenizer;

/**
 * Given array representing candies type which has cost arr[i], also we have amount of money T. By looping though the array
 * find the array if the cost is not greater than T we take it otherwise we skip till we can no more afford any candie in array.
 *
 * The most optimal way is to loop through the array then calclate the candies that we can afford then do the equation
 * T = T Mod total cost of candies. Therefore we can quickly decrease the value of T, till it is smaller than the minimal value
 * in the array
 */

public class Berland_Fair {

    public static void main (String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long T = sc.nextLong();
        int min = Integer.MAX_VALUE;
        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            min = Math.min(arr[i], min);
        }
        long res = 0;
        while (T > 0) {
            long totalCost = 0;
            long numberCandies = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] <= T) {
                    numberCandies++;
                    totalCost += arr[i];
                    T -= arr[i];
                }
            }


            if (totalCost > 0) {
                res += numberCandies * (T / totalCost) + numberCandies;
                T = T % totalCost;
            }

            if (T < min) break;
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