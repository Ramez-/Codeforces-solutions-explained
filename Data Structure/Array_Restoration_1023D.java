import java.io.*;
import java.util.*;


/**
 *
 * There are i quries from 1 till q where a segement is chosen (l, r) and all elements from l till r are filled with i
 * Given the final result and the number of queries, find if it is possible to create the array from the steps above.
 *
 * The main idea to be solved optimally is using binary index tree to store the seqences used in previous numbers, forexample
 * we start with 1 then we place all the values in the input array of 1's in binary index to to 1 then next number is 2
 * we check if 2 left and right range overwrite 1 then the answer is NO otherwise YES
 */

public class Array_Restoration_1023D {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];

        TreeMap<Integer, TreeSet<Integer>> tm = new TreeMap<>();
        boolean foundLast = false;
        int zero = -1;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] == q) foundLast = true;
            if (arr[i] == 0) zero = i;
        }
        if (!foundLast && zero == -1) {
            System.out.println("NO");
            return;
        }
        if (!foundLast) {
            arr[zero] = q;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] == 0) {
                arr[i] = arr[i - 1];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i] = arr[i + 1];
            }
        }

        for (int i = 0; i < n; i++) {
            if (tm.containsKey(arr[i])) {
                TreeSet<Integer> temp = tm.get(arr[i]);
                temp.add(i);
            } else {
                TreeSet<Integer> temp = new TreeSet<>();
                temp.add(i);
                tm.put(arr[i], temp);
            }
        }


        Bit bit = new Bit(n);
        for (Integer key : tm.keySet()) {
            TreeSet<Integer> ts = tm.get(key);
            // binary index tree should be 1 based
            if (bit.sum(ts.first() + 1, ts.last() + 1) > 0) {
                System.out.println("NO");
                return;
            }
            for (Integer integer : ts) {
                bit.update(integer + 1, 1);
            }
        }
        System.out.println("YES");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    static class Pair {
        int l;
        int r;

        Pair(int end, int index) {
            this.l = end;
            this.r = index;
        }
    }

    static class Bit {
        int n;
        int BIT[];
        Bit(int n) {
            this.n = n + 1;
            BIT = new int[n + 1];
        }

        public int sumTillIndex(int index) {
            int ans = 0;
            while (index != 0) {
                ans += BIT[index];
                index = index & (index - 1);
            }
            return ans;
        }

        public int sum(int index1, int index2) {
            return sumTillIndex(index2) - sumTillIndex(index1);
        }

        public void update(int index, int value) {
            while (index < n) {
                BIT[index] += value;
                index = index + (index & -index);
            }
        }
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream System) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(System));
//            String FILENAME = "level4.txt";
//            br = new BufferedReader(new FileReader(FILENAME));
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