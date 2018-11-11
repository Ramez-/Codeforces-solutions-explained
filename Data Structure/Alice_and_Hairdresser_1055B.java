import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Problem: given array of length n, for m queries of 2 types
 * one is return the number of segment of numbers that are greater than l.
 * The other is add a number to element with the index index.
 *
 * To solve this problem I created a treeSet of segments containing the start and the end
 * when an element in the array become bigger than l, I check the segment before and after
 * if they are just before the index or just after the index I add the element in the segments
 * otherwise I create a new segment. If it is part of prev segment and after segment we remove one of the
 * segments as it joins two segments
 */

public class Alice_and_Hairdresser_1055B {

    public static void main (String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();
        TreeSet<Segement> segements = new TreeSet<>();

        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        if (arr[0] > l) segements.add(new Segement(0, 0));
        for (int i = 1; i < n; i++) {
            if (arr[i] > l) {
                if(arr[i - 1] > l) {
                    segements.last().end = i;
                }
                else {
                    segements.add(new Segement(i, i));
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int query = sc.nextInt();
            if (query == 0) {
                System.out.println(segements.size());
            }
            else {
                int index = sc.nextInt() - 1;
                int increase = sc.nextInt();
                if (arr[index] > l) continue;
                arr[index] += increase;
                if (arr[index] > l) {
                    boolean flagAdded = false;
                    // since I am using compareTo only on start the end doesn't matter in floor
                    Segement smaller = segements.floor(new Segement(index, 0));
                    if (smaller != null && smaller.end == index - 1) {
                        smaller.end = index;
                        flagAdded = true;
                    }
                    Segement bigger = segements.ceiling(new Segement(index, 0));
                    if (bigger != null && bigger.start == index + 1) {
                        if (flagAdded) {
                            smaller.end = bigger.end;
                            segements.remove(bigger);
                        }
                        else{
                            bigger.start = index;
                            flagAdded = true;
                        }
                    }
                    if (!flagAdded) {
                        segements.add(new Segement(index, index));
                    }
                }
            }
        }



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
    static class Segement implements Comparable<Segement> {
        int start;
        int end;
        Segement(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segement o) {
            return this.start - o.start;
        }
    }
}
