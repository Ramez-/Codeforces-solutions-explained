
public class TrieProblem {

	static class Trie {
		Node root;

		public Trie() {
			root = new Node();
		}

		public void insert(String s) {
			Node currentNode = root;
			for (int i = 0; i < s.length(); i++) {
				if (currentNode.getChild(s.charAt(i)) == null) {
					Node newNode = new Node();
					currentNode.setChild(newNode, s.charAt(i));
				}
				currentNode.getChild(s.charAt(i)).increment();

				currentNode = currentNode.getChild(s.charAt(i));
			}
			currentNode.end = true;
		}

		public int find(String s) {
			Node currentNode = root;

			for (int i = 0; i < s.length(); i++) {
				if (currentNode.getChild(s.charAt(i)) == null) {
					return -1;
				}
				currentNode = currentNode.getChild(s.charAt(i));
			}
			return currentNode.counter;
		}

		public void delete(String s) {
			Node currentNode = root;

			for (int i = 0; i < s.length(); i++) {
				currentNode.getChild(s.charAt(i)).counter--;
				currentNode = currentNode.getChild(s.charAt(i));
			}
		}
	}

	static class Node {
		char value;
		Node[] nodes;
		boolean end;
		int counter;

		Node() {
			nodes = new Node[27];
			counter = 0;
		}

		public Node getChild(char charchter) {
			return nodes[charchter - 'a'];
		}

		public void setChild(Node child, char c) {
			nodes[c - 'a'] = child;
		}

		public void increment() {
			counter++;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("hello");
		trie.insert("helloworld");
		trie.insert("helloworldthisisraj");
		trie.delete("hello");
		System.out.println(trie.find("hel"));
	}

}
