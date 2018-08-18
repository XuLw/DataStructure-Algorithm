package dataStructure;

import java.util.LinkedList;
import java.util.Queue;

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
		root = insert(root, key, value);
	}

	// 先序遍历
	public void preOrder() {
		preOrder(root);
	}

	// 中序遍历
	public void inOrder() {
		inOrder(root);
	}

	// 后序遍历非递归实现
	public void postOrder() {
		// if (root == null)
		// return;
		// Stack<Node> nodeStack = new Stack();
		// Node temp = root;
		// do {
		// if (temp.left != null) {
		// nodeStack.push(temp);
		// temp = temp.left;
		// continue;
		// } else {
		// System.out.print(temp.toString() + " ");
		// temp = nodeStack.pop();
		// }
		//
		// if (temp.right != null) {
		// nodeStack.push(temp);
		// temp = temp.right;
		// continue;
		// }
		//
		// } while (!nodeStack.isEmpty());
		// TODO

		postOrder(root);
	}

	/*
	 * 层序遍历
	 */
	public void levelOrder() {
		Queue<Node> q = new LinkedList<>();

		if (root != null)
			q.add(root);

		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.left != null)
				q.add(temp.left);
			if (temp.right != null)
				q.add(temp.right);
			System.out.print(temp.toString() + " ");
		}
	}

	/*
	 * 返回最大节点值
	 */
	public K maxmum() {

		if (count == 0)
			return null;

		Node temp = root;

		while (temp != null) {

			if (temp.right == null)
				break;
			temp = temp.right;

		}
		return temp.key;
	}

	/*
	 * 返回最小节点值
	 * 
	 * 递归写法
	 */
	public K minimum() {

		if (count == 0)
			return null;

		return minimum(root).key;

	}

	/*
	 * 删除最大值
	 */
	public void removeMax() {

		if (root != null)
			root = removeMax(root);

	}

	/*
	 * 删除最小值
	 */
	public void removeMin() {

		if (root != null)
			root = removeMin(root);

	}

	/*
	 * Hubbard Deletion
	 */
	public void remove(K key) {
		root = remove(root, key);
	}

	public boolean contain(K key) {
		return contain(this.root, key);
	}

	/*
	 * 非递归实现 contain函数
	 */
	public boolean containV1(K key) {
		Node temp = this.root;
		while (temp != null) {
			if (key.compareTo(temp.key) == 0)
				return true;
			else if (key.compareTo(temp.key) > 0)
				temp = temp.right;
			else
				temp = temp.left;
		}
		return false;
	}

	public V search(K key) {
		return search(this.root, key);
	}

	/*
	 * 非递归实现 search
	 */
	public V searchV1(K key) {
		Node temp = this.root;
		while (temp != null) {
			if (key.compareTo(temp.key) == 0)
				return temp.value;
			else if (key.compareTo(temp.key) > 0)
				temp = temp.right;
			else
				temp = temp.left;
		}

		return null;
	}

	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.toString() + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.toString() + " ");
			inOrder(node.right);
		}
	}

	private void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.toString() + " ");
		}
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

	private Node minimum(Node node) {
		if (node.left == null)
			return node;

		return minimum(node.left);
	}

	private Node removeMax(Node node) {

		if (node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			count--;
			return leftNode;
		}

		node.right = removeMax(node.right);
		return node;
	}

	private Node removeMin(Node node) {

		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			count--;
			return rightNode;
		}

		node.left = removeMin(node.left);
		return node;
	}

	public Node remove(Node node, K key) {

		if (node == null)
			return null;

		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			return node;
		} else { // key == node->key

			// 待删除节点左子树为空的情况
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				count--;
				return rightNode;
			}

			// 待删除节点右子树为空的情况
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				count--;
				return leftNode;
			}

			// 待删除节点左右子树均不为空的情况

			// 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
			// 用这个节点顶替待删除节点的位置
			Node successor = minimum(node.right);
			count++;

			successor.right = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;
			count--;

			return successor;
		}
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

		public Node(Node node) {
			this.key = node.key;
			this.value = node.value;
			this.left = node.left;
			this.right = node.right;
		}

		@Override
		public String toString() {
			return "[" + key.toString() + " , " + value.toString() + "]";
		}
	}

}
