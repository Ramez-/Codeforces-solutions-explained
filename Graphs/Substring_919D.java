import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Problem: Givena string where the ith charchter is attached to the ith node in a given graph. Find the maximum count of certain
 * charchter in any path of the graph. If there is a cycle in the graph return -1
 *
 * To solve this problem optimally we need to create Node object which has the maximum array of charchters that are in its sub graph
 * We can then loop over all nodes in the graph and return the maximum count for this node.
 */

public class Substring_919D {
    static class Node {
        int[] arr;
        boolean calc;
        char c;
        int id;
        ArrayList<Node> adjList;
        // useful to know the nodes that are root
        int incomeEdges;

        public Node(char c, int id) {
            this.c = c;
            this.id = id;
            adjList = new ArrayList<>();
            arr = new int[26];
        }

        /**
         *
         * @return the number of charchters that are equal to my node's charchter if calculated otherwise calclate it
         */
        public int count() {
            if (calc) return arr[c - 'a'];
            calc = true;
            calculate(this);
            return arr[c - 'a'];
        }

        private void calculate(Node n) {
            for (Node child : n.adjList) {
                if (!child.calc) {
                    child.count();
                }
                for (int i = 0; i < 26; i++) {
                    n.arr[i] = Math.max(n.arr[i], child.arr[i]);
                }
            }
            arr[c - 'a']++;
        }

    }

    static String s;

    public static void main (String [] args) throws IOException {
        Scanner sc =  new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        s = sc.next();
        Node [] nodesList = new Node[n];
        for (int i = 0; i < n; i++) {
            nodesList[i] = new Node(s.charAt(i), i);
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            if (a == b) {
                System.out.println(-1);
                return;
            }
            nodesList[a].adjList.add(nodesList[b]);
            nodesList[b].incomeEdges++;
        }

        // to check if there is a cycle in the graph
        // if a node has more than incoming edge then only visit it when all its incoming edges are visited,
        // therefore if any of the nodes has cycle it won't add it to the queue
        ArrayDeque<Node> q = new ArrayDeque<>();

        for (int i = 0;i < n; i++) {
            // check if the node is root of a graph
            if (nodesList[i].incomeEdges == 0) {
                q.add(nodesList[i]);
            }
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node child : node.adjList) {
                child.incomeEdges--;
                if (child.incomeEdges == 0) q.add(child);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nodesList[i].incomeEdges > 0) {
                System.out.println(-1);
                return;
            }
        }

        int maxRes = 1;
        for (int i = 0; i < n; i++) {
            maxRes = Math.max(maxRes, nodesList[i].count());
        }
        System.out.println(maxRes);
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