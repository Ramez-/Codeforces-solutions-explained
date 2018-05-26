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
 * Problem: Sagheer wants to buy suvoniers from market where the cost of item is its base cost 
 * in addition to its index multiplied by the factor k, where k the number of elements bought from market.
 * Get the maximum number of suvoniers which is less than the budget. 
 * 
 * Solution: To solve this problem optimally we need to binary search over the number of elements to take from
 * the market, this is YES YES YES NO NO problem where we need to binary search to get the last YES as it donates
 * that sagheer bought maximum number of suvoniers which is less than the budget.
 * 
 * using this solution the code runs in O(n * log(n^2))
 */

public class Sagheer_Nubian_Market_812C {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer inputData = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(inputData.nextToken());
		int s = Integer.parseInt(inputData.nextToken());
		inputData = new StringTokenizer(reader.readLine());
		int p[] = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(inputData.nextToken());
		}

		long lo = 1;
		long hi = n;
		long res = 0;
		long mid = 0;
		// total cost to buy those suvoniers
		long theFinal = 0;
		// maximum number of suvoniers to buy
		long theFinalMid = 0;
		while (lo <= hi) {
			mid = lo + ((hi - lo) / 2);
			res = 0;
			long[] taken = new long[n];
			for (int i = 0; i < n; i++) {
				taken[i] = p[i] + mid * (i + 1);
			}
			Arrays.sort(taken);
			for (int i = 0; i < mid; i++) {
				res = res + taken[i];
			}
			if (res > s) {
				hi = mid - 1;
			} else if (res <= s) {
				lo = mid + 1;
				theFinal = res;
				theFinalMid = mid;
			}
		}
		System.out.println(theFinalMid + " " + theFinal);
	}
}