import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Iterator;

/*
 * Problem: given number of tables in restaurant and number of reservations with number of people in group and
 * the price that each group will pay. Find the maximum money we can get.
 * 
 * Sort the tables ascendingly and sort the reservations based on money. Go through each reservation and try
 * to find the mimumal table than can hold the group.
 */

public class Booking_System_416C {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer inputData = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(inputData.nextToken());
		int cp[][] = new int[n][3];
		ArrayList<Pair> ts = new ArrayList<Pair>();

		for (int i = 0; i < n; i++) {
			inputData = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(inputData.nextToken());
			cp[i][0] = a;
			cp[i][1] = Integer.parseInt(inputData.nextToken());
			cp[i][2] = i + 1;
		}
		inputData = new StringTokenizer(reader.readLine());
		int k = Integer.parseInt(inputData.nextToken());
		inputData = new StringTokenizer(reader.readLine());
		for (int i = 0; i < k; i++) {
			int a = Integer.parseInt(inputData.nextToken());
			ts.add(new Pair(a, i + 1));
		}

		Arrays.sort(cp, (a, b) -> (b[1] != a[1] ? b[1] - a[1] : a[0] - b[0]));
		Collections.sort(ts);

		int sum = 0;
		int overall = 0;
		String s = "";
		for (int i = 0; i < n; i++) {
			int min = 10000000;
			Iterator<Pair> it = ts.iterator();
			Pair num = null;
			int index = -1;
			while (it.hasNext()) {
				num = it.next();
				if (cp[i][0] <= num.a) {
					min = num.a;
					index = num.b;
				} else {
					break;
				}
			}
			if (min != 10000000) {
				sum += cp[i][1];
				overall++;
				s = s + cp[i][2] + " " + index + '\n';
				for (int j = 0; j < k; j++) {
					if (ts.get(j).b == index) {
						ts.remove(j);
						break;
					}
				}

			}
		}
		System.out.println(overall + " " + sum);
		System.out.println(s);
	}
}

class Pair implements Comparable<Pair> {
	int a, b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return o.a - a;
	}
}