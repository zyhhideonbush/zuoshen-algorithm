package A3medium.class06;

// 找到一棵二叉树中，最大的搜索二叉子树，返回最大搜索二叉子树的节点个数(或者返回其头节点)
public class C03_BiggestSubBSTInTree {

	// public static class Node {
	// public int value;
	// public Node left;
	// public Node right;

	// public Node(int data) {
	// this.value = data;
	// }
	// }

	// public static Node getMaxBST(Node head) {
	// return process(head).maxBSTHead;
	// }

	// public static class ReturnType {
	// public Node maxBSTHead;
	// public int maxBSTSize;
	// public int min;
	// public int max;

	// public ReturnType(Node maxBSTHead, int maxBSTSize, int min, int max) {
	// this.maxBSTHead = maxBSTHead;
	// this.maxBSTSize = maxBSTSize;
	// this.min = min;
	// this.max = max;
	// }
	// }

	// public static ReturnType process(Node X) {
	// // base case : 如果子树是空树
	// // 最小值为系统最大
	// // 最大值为系统最小
	// if (X == null) {
	// return new ReturnType(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
	// }
	// // 默认直接得到左树全部信息
	// ReturnType lData = process(X.left);
	// // 默认直接得到右树全部信息
	// ReturnType rData = process(X.right);
	// // 以下过程为信息整合
	// // 同时以X为头的子树也做同样的要求，也需要返回如ReturnType描述的全部信息
	// // 以X为头的子树的最小值是：左树最小、右树最小、X的值，三者中最小的
	// int min = Math.min(X.value, Math.min(lData.min, rData.min));
	// // 以X为头的子树的最大值是：左树最大、右树最大、X的值，三者中最大的
	// int max = Math.max(X.value, Math.max(lData.max, rData.max));
	// // 如果只考虑可能性一和可能性二，以X为头的子树的最大搜索二叉树大小
	// int maxBSTSize = Math.max(lData.maxBSTSize, rData.maxBSTSize);
	// // 如果只考虑可能性一和可能性二，以X为头的子树的最大搜索二叉树头节点
	// Node maxBSTHead = lData.maxBSTSize >= rData.maxBSTSize ? lData.maxBSTHead :
	// rData.maxBSTHead;
	// // 利用收集的信息，可以判断是否存在可能性三
	// if (lData.maxBSTHead == X.left && rData.maxBSTHead == X.right && X.value >
	// lData.max && X.value < rData.min) {
	// maxBSTSize = lData.maxBSTSize + rData.maxBSTSize + 1;
	// maxBSTHead = X;
	// }
	// // 信息全部搞定，返回
	// return new ReturnType(maxBSTHead, maxBSTSize, min, max);
	// }

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			this.value = v;
		}
	}

	public static class ReturnType {
		public Node head;
		public int max;
		public int min;
		public int size;

		public ReturnType(Node n, int max, int min, int size) {
			this.head = n;
			this.max = max;
			this.min = min;
			this.size = size;
		}
	}

	public static Node getMaxBST(Node head) {
		if (head == null) {
			return null;
		}
		return process(head).head;
	}

	private static ReturnType process(Node head) {
		if (head == null) {
			return new ReturnType(head, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		}

		ReturnType leftReturn = process(head.left);
		ReturnType rightReturn = process(head.right);

		int max = Math.max(head.value, Math.max(leftReturn.max, rightReturn.max));
		int min = Math.min(head.value, Math.min(leftReturn.min, rightReturn.min));
		Node returnHead;
		int size;
		if (leftReturn.head == head.left && rightReturn.head == head.right && head.value > leftReturn.max
				&& head.value < rightReturn.min) {
			returnHead = head;
			size = leftReturn.size + rightReturn.size + 1;
		} else {
			returnHead = leftReturn.size > rightReturn.size ? leftReturn.head : rightReturn.head;
			size = Math.max(leftReturn.size, rightReturn.size);
		}

		return new ReturnType(returnHead, max, min, size);
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	// for test
	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	// for test
	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {

		Node head = new Node(6);
		head.left = new Node(1);
		head.left.left = new Node(0);
		head.left.right = new Node(3);
		head.right = new Node(12);
		head.right.left = new Node(10);
		head.right.left.left = new Node(4);
		head.right.left.left.left = new Node(2);
		head.right.left.left.right = new Node(5);
		head.right.left.right = new Node(14);
		head.right.left.right.left = new Node(11);
		head.right.left.right.right = new Node(15);
		head.right.right = new Node(13);
		head.right.right.left = new Node(20);
		head.right.right.right = new Node(16);

		printTree(head);
		Node bst = getMaxBST(head);
		printTree(bst);

	}

}
