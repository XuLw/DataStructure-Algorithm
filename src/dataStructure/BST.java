package dataStructure;

/*
 * 二分查找树
 */
public class BST<K extends Comparable, V> {

	private int count;
	private Node root;

	public BST() {
		this.count = 0;
		this.root = null;
	}

	public int size() {
		return this.count;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	public void insert(K key, V value) {
		insert(root, key, value);
	}

	public boolean contain(K key) {
		return contain(this.root, key);
	}

	public V search(K key) {
		return search(this.root, key);
	}

	private Node insert(Node node, K key, V value) {

		if (node == null) {
			count++;
			return new Node(key, value);
		}

		if (key.compareTo(node.key) > 0)
			node.right = insert(node.right, key, value);
		else if (key.compareTo(node.key) < 0)
			node.left = insert(node.left, key, value);
		else // 进行更改操作
			node.value = value;

		return node;
	}

	private boolean contain(Node node, K key) {
		if (node == null)
			return false;

		if (key.compareTo(node.key) == 0)
			return true;
		else if (key.compareTo(node.key) > 0)
			return contain(node.right, key);
		else
			return contain(node.left, key);
	}

	private V search(Node node, K key) {
		if (node == null)
			return null;

		if (key.compareTo(node.key) == 0)
			return node.value;
		else if (key.compareTo(node.key) > 0)
			return search(node.right, key);
		else
			return search(node.left, key);
	}

	private class Node {
		private K key;
		private V value;
		private Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = this.right = null;
		}
	}

	public void test() {
		// TODO
	}
}
