import java.io.*;
import java.util.*;

/**
 * Given source and distination 2D cartisian coordinates. Given a string denoting wind that moves a ship to direction
 * of the wind at a day. The string is rotational
 *
 * Find the number of days for the ship to go to the distination, if at each day it can move one step in one direction.
 *
 * If a ship can reach in x days then it can reach in x + 1 days by moving opposit to the wind.
 *
 * We will use binary search across all possible days. To check if in d days we will reach the distination we will go
 * through the string, adding cycles then we will add the rest of the days by modules opperation. Therefore, we will
 * have disination only by wind but we have d days to reach distination, so if we can reach the distination in d days
 * then we need to lower d otherwise we need to increase d.
 */

public class Magic_ship_1117C {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        int n = sc.nextInt();
        String s = sc.nextLine();
        char[] arr = s.toCharArray();
        long lo = 0;
        long hi = (long)1e18;
        long ans = -1;
        while (lo <= hi) {
            long mid = (lo + hi)>> 1;
            long windH = 0;
            long windV = 0;
            long cycles = mid / n;
            for (int i = 0; i < n && i < mid; i++) {
                if (arr[i] == 'R') {
                    windH += cycles;
                }
                else if (arr[i] == 'L') {
                    windH -= cycles;
                }
                else if (arr[i] == 'U') {
                    windV += cycles;
                }
                else {
                    windV -= cycles;
                }
            }
            long restOfCycle = mid % n;
            for (int i = 0; i < restOfCycle && i < n; i++) {
                if (arr[i] == 'R') {
                    windH += 1;
                }
                else if (arr[i] == 'L') {
                    windH -= 1;
                }
                else if (arr[i] == 'U') {
                    windV += 1;
                }
                else {
                    windV -= 1;
                }
            }

            // days need to reach distination from where the wind left me
            long daysNeeded = Math.abs(windH + x1 - x2) + Math.abs(windV + y1 - y2);
            if (mid < daysNeeded) {
                lo = mid + 1;
            }
            else {
                ans = mid;
                hi = mid - 1;
            }
        }
        System.out.println(ans);

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
    static class Pair {
        int x;
        int y;

        Pair(int start, int end) {
            this.x = start;
            this.y = end;
        }
    }

}