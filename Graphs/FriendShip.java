import java.io.*;
import java.util.*;

/**
 * Problem: check if all if node  a connected to another node b then a is connected to the nodes connected to b and b is
 * connected to all nodes connected to a
 *
 * Solved by decoupling the node from the frindship class
 */

public class FriendShip {

    static class Node {
        ArrayList<Node> adjList;
        int val;
        public Node(int val) {
            adjList = new ArrayList<>();
            this.val = val;
        }
    }

    private Node [] nodesList;
    private boolean [] visited;
    private FriendShip(int size) {
        nodesList = new Node[size];
        visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            nodesList[i] = new Node(i);
        }
    }

    private ArrayList<Node> dfs (Node node, ArrayList<Node> ret) {
        if (!visited[node.val]) {
            ret.add(node);
            visited[node.val] = true;
            for (Node child : node.adjList) {
                dfs(child, ret);
            }
        }
        return ret;
    }

    public static void main (String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        FriendShip friendShip = new FriendShip(n);
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            friendShip.nodesList[a].adjList.add(friendShip.nodesList[b]);
            friendShip.nodesList[b].adjList.add(friendShip.nodesList[a]);
        }
        for (int i = 0; i < n; i++) {
            if(!friendShip.visited[i]) {
                ArrayList<Node> something = new ArrayList<>();
                ArrayList<Node> ret = friendShip.dfs(friendShip.nodesList[i], something);
                for (int j = 0; j < ret.size(); j++) {
                    if (ret.get(j).adjList.size() < ret.size() - 1) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        System.out.println("YES");

    }

    static class Pair implements Comparable<Pair>{
        int count;
        int number;
        public Pair(int a, int b) {
            this.count = a;
            this.number = b;
        }

        @Override
        public int compareTo(Pair o) {
            return o.count - this.count;
        }
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream System) throws FileNotFoundException {
//            String FILENAME = "level2_1.in";
//			br = new BufferedReader(new FileReader(FILENAME));
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