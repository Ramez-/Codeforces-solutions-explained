import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Given 2D grid and we have limited number of going right and left but unlimited number of going up and down, find the
 * number of possible points in the grid where we can visit from the starting point.
 *
 * The naive approch of dfs from start node won't work because you will visit nodes and mark it visited while there could
 * be shorter path to go for this nodes which will lead a smaller result.
 *
 * The best way to solve this problem, is to use dijkestra from the starting position and the number of accumulation
 * of left and right should be minimzed as it is the wights.
 */

public class Labyrinth_1063B {
    static int n;
    static int m;
    static int startX;
    static int starty;
    static char[][] grid;
    // minimal distance from each node to start node in terms of lefts and rights
    static int[][] dist;
    static int maxLeft;
    static int maxRight;

    public static boolean isValid(int row, int col, int l, int r) {
        if (row >= n || row < 0 || col >= m || col < 0 || grid[row][col] == '*' || l > maxLeft || r > maxRight) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        startX = sc.nextInt() - 1;
        starty = sc.nextInt() - 1;
        maxLeft = sc.nextInt();
        maxRight = sc.nextInt();

        grid = new char[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = s.charAt(j);
            }
        }

        dist[startX][starty] = 0;
        dijkestra(startX, starty);
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }

    private static void dijkestra(int r, int c) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int dx[] = {0, 0, -1, 1};
        int dy[] = {-1, 1, 0, 0};

        pq.add(new Node(r, c, 0, 0));
        while(!pq.isEmpty())
        {
            Node curr = pq.poll();

            for (int k = 0; k < 4; k++) {
                int newLeft = curr.left;
                if(k == 0)
                    newLeft++;
                int newRight = curr.right;
                if(k == 1)
                    newRight++;
                // create an entire new object
                Node nextNode = new Node(curr.i+dx[k], curr.j+dy[k], newLeft, newRight);
                // if a way is shortest in the costHorizontal but not valid it will not be inserted and the valid one will only be reachable
                if(isValid(nextNode.i, nextNode.j, newLeft, newRight)){
                    if(nextNode.cost < dist[nextNode.i][nextNode.j]) {
                        dist[nextNode.i][nextNode.j] = nextNode.cost;
                        pq.add(nextNode);
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node>
    {
        int i, j, cost, left, right;


        Node(int i, int j, int left, int right) { this.i = i; this.j = j;	this.left = left;  this.right = right; cost = left + right;}

        public int compareTo(Node e){ return cost - e.cost;	}
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
}