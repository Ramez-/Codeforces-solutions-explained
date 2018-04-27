
/*
 * Created by: Ramez Elbaroudy
 * 
 * Problem: You have a number of queury wheather to add number to multiset or remove a number or ask for number
 * 			in the multiset that do the biggest xor with certain number input, the multiset will always have
 * 			zero in the set which means the minumum vale of xoring will be the value itself as 
 * 			value xor zero = value
 * 
 *  To solve this problem opitmally a smart idea is to use trie data structure to save add, search and remove elements
 *  in the multiset. The values will be insered as 32 bit binary to the trie 
 *  
 *  
 */

import java.io.*;
import java.util.*;

public class Vasiliys_Multiset_709D {

	// each Trie represent a node with is a bit in the binary number
	static class Trie {
		Trie[] child;
		// for counting how many binary numbers pass by this bit
		int count;

		Trie() {
			child = new Trie[2];
		}

		// index is the index of the bit in the binaryString to be added
		void insert(char[] binaryString, int index) {
			count++;
			if (binaryString.length <= index) {
				return;
			}
			int whichNode = binaryString[index] - '0';
			if (child[whichNode] == null)
				child[whichNode] = new Trie();
			child[whichNode].insert(binaryString, index + 1);
		}

		void delete(char[] binaryString, int index) {
			count--;
			if (binaryString.length <= index) {
				return;
			}
			int whichNode = binaryString[index] - '0';
			child[whichNode].delete(binaryString, index + 1);
		}

		// the returned string will be the one that gets the maximum value of
		// xor
		// getting the maximum value of xor is by trying to get the inverse of
		// the bit that is in the input
		// if no values are in the trie at the beginning the algorithm will be
		// also correct because
		// it will yeild the minumum value which is a number exactly the same as
		// the input
		// two equal numbers xored = 0
		String search(char[] binaryString, int index) {
			if (binaryString.length == index) {
				return "";
			}
			// we are looking for 0 if the input is 1 or 1 if the input is 0
			int whichNode = 1 - (binaryString[index] - '0');
			// no option but to take the one with the same bit value
			if (child[whichNode] == null || child[whichNode].count == 0) {
				whichNode = binaryString[index] - '0';
			}
			return whichNode + child[whichNode].search(binaryString, index + 1);
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Trie trie = new Trie();
		int emptyTri = 0;
		for (int i = 0; i < n; i++) {
			String operation = sc.next();
			int val = sc.nextInt();
			String s = Integer.toBinaryString(val);
			while (s.length() <= 32) {
				s = '0' + s;
			}
			char[] inputBinary = s.toCharArray();

			if (operation.charAt(0) == '+') {
				emptyTri++;
				trie.insert(inputBinary, 0);
			} else {
				if (operation.charAt(0) == '-') {
					emptyTri--;
					trie.delete(inputBinary, 0);
				} else {
					if (emptyTri > 0) {
						int maximumCandidate = Integer.parseInt(trie.search(inputBinary, 0), 2);
						System.out.println(Math.max(maximumCandidate ^ val, val));
					} else {
						System.out.println(val);
					}
				}
			}

		}

	}

	// bufferReader for fast scan
	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream System) {
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
