import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Disjoint set implementation that detects cycles in undirected graph.
 *
 * In the tree node, the root has the highest rank and it is the representitive of the set.
 * The root has itself as parent
 */

public class DisjointSet {

    HashMap<Integer, Node> nodeLocation = new HashMap<>();

    public void makeSet(int data) {
        Node node = new Node(data);
        node.parent = node;
        nodeLocation.put(data, node);
    }

    public void union(int data1, int data2) {
        Node node1 = nodeLocation.get(data1);
        Node node2 = nodeLocation.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        // both of them already in the same set
        if (parent1 == parent2) return;

        if (parent1.rank >= parent2.rank) {
            parent1.rank = parent1 == parent2 ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        }
        else {
            parent1.parent = parent2;
        }
    }

    // find representitive and connects nodes to the root directly for optimization
    public Node findSet(Node dataNode) {
        // something go wrong here?
        if (dataNode == dataNode.parent) {
            return dataNode;
        }
        dataNode.parent = findSet(dataNode.parent);
        // what is returned is the root, if dataNode is returned then it is the leaf not root
        return dataNode.parent;
    }

    public int findSet(int data) {
        return findSet(nodeLocation.get(data)).val;
    }

    public static void main (String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);
        ds.union(1 ,2);
        ds.union(3 ,2);
        ds.union(4 ,5);
        ds.union(6 ,7);
        ds.union(5 ,6);
        ds.union(3 ,7);

        System.out.println(ds.findSet(3));
    }

    class Node {
        Node parent;
        int val;
        int rank;
        public Node (int val) {
            this.val = val;
            rank = 0;
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

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}