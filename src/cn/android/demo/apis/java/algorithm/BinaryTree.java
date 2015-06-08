package cn.android.demo.apis.java.algorithm;

import java.util.Stack;

import android.util.Log;

/**
 * 二叉树
 * 
 * @author Elroy
 * 
 */
public class BinaryTree {
	public TreeNode root = null;
	public BinaryTree() {
		root = new TreeNode(1, "rootNode(A)");
	}

	/** 创建一颗二叉树 */
	public void createBinTree(TreeNode root) {
		TreeNode newNodeB = new TreeNode(2, "B");
		TreeNode newNodeC = new TreeNode(3, "C");
		TreeNode newNodeD = new TreeNode(4, "D");
		TreeNode newNodeE = new TreeNode(5, "E");
		TreeNode newNodeF = new TreeNode(6, "F");

		root.leftChild = newNodeB;
		root.rightChild = newNodeC;
		root.leftChild.leftChild = newNodeD;
		root.leftChild.rightChild = newNodeE;
		root.rightChild.rightChild = newNodeF;

	}

	public boolean isEmpty() {
		return root == null;
	}

	public int getHeight() {
		return getHeight(root);
	}

	public int getHeight(TreeNode subTree) {

		if (subTree == null) {
			return 0;// 递归结束，空树高度为0
		} else {
			int i = getHeight(subTree.leftChild);
			int j = getHeight(subTree.rightChild);
			return (i < j) ? (j + 1) : (i + 1);
		}

	}

	public int getSize() {
		return getSize(root);
	}

	public int getSize(TreeNode subTree) {
		if (subTree == null) {
			return 0;
		} else {
			return getSize(subTree.leftChild) + getSize(subTree.rightChild) + 1;
		}
	}

	// 返回双亲结点
	public TreeNode parent(TreeNode element) {
		return (root == null || root == element) ? null : parent(root, element);
	}

	public TreeNode parent(TreeNode subTree, TreeNode element) {
		if (subTree == null) {
			return null;
		}
		if (subTree.leftChild == element || subTree.rightChild == element) {
			// 返回父结点
			return subTree;
		}

		TreeNode p = null;
		// 现在左子树中找，如果左子树没有找到，才到右子树去找
		if ((p = parent(subTree.leftChild, element)) != null) {
			return p;
		} else {
			return parent(subTree.rightChild, element);
		}

	}

	public TreeNode getLeftChildNode(TreeNode element) {
		return (element != null) ? element.leftChild : null;
	}

	public TreeNode getRightChildNode(TreeNode element) {
		return (element != null) ? element.rightChild : null;
	}

	public TreeNode getRoot() {
		return root;
	}

	// 在释放某个结点时，该结点的左右子树已经释放
	// 所以应该采用后续遍历，当访问某个结点时将该结点的储存空间释放

	public void destroy(TreeNode subTree) {
		// 删除根为subTree的子树
		if (subTree != null) {
			// 删除左子树
			destroy(subTree.leftChild);
			// 删除右子树
			destroy(subTree.rightChild);
			// 删除根结点
			subTree = null;

		}
	}

	public void traverse(TreeNode subTree) {
		System.out.println("key:" + subTree.key + "  --name:" + subTree.data);
		traverse(subTree.leftChild);
		traverse(subTree.rightChild);
	}

	// 前序遍历
	public void preOrder(TreeNode subTree) {
		if (subTree != null) {
			visted(subTree);
			preOrder(subTree.leftChild);
			preOrder(subTree.rightChild);
		}
	}

	// 中序遍历
	public void inOrder(TreeNode subTree) {
		if (subTree != null) {
			inOrder(subTree.leftChild);
			visted(subTree);
			inOrder(subTree.rightChild);

		}
	}

	// 后序遍历
	public void postOrder(TreeNode subTree) {
		if (subTree != null) {
			postOrder(subTree.leftChild);
			postOrder(subTree.rightChild);
			visted(subTree);

		}
	}

	// 前序遍历非递归实现
	public void nonRecPreOrder(TreeNode p) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = p;
		while (node != null || stack.size() > 0) {
			while (node != null) {
				visted(node);
				stack.push(node);
				node = node.leftChild;

			}

		}
	}

	// 中序遍历的非递归实现
	public void nonRecInOrder(TreeNode p) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = p;
		while (node != null || stack.size() > 0) {
			// 存在左子树
			while (node != null) {
				stack.push(node);
				node = node.leftChild;

			}
			// 栈非空
			if (stack.size() > 0) {
				node = stack.pop();
				visted(node);
				node = node.rightChild;
			}
		}
	}

	// 后序遍历的非递归实现
	public void nonRecPostOrder(TreeNode p) {
		Stack<TreeNode> stack = new Stack<BinaryTree.TreeNode>();
		TreeNode node = p;
		while (p != null) {
			// 左子树入栈
			for (; p.leftChild != null; p = p.leftChild) {
				stack.push(p);
			}
			// 当前结点无右子树或右子树已经输出
			while (p != null && (p.rightChild == null || p.rightChild == node)) {
				visted(p);
				// 纪录上一个已输出结点
				node = p;
				if (stack.empty())
					return;
				p = stack.pop();
			}
			// 处理右子树
			stack.push(p);
			p = p.rightChild;
		}
	}

	public void visted(TreeNode subTree) {
		subTree.isVisted = true;
		System.out.println("Key:" + subTree.key + " --name:" + subTree.data);
		Log.v("DDD", "Key:" + subTree.key + " --name:" + subTree.data);

	}

	class TreeNode {

		private int key = 0;
		private String data = null;
		private boolean isVisted = false;
		private TreeNode leftChild = null;
		private TreeNode rightChild = null;

		public TreeNode() {
		}

		/**
		 * @param key
		 *            层序编码
		 * @param data
		 *            数据域
		 */
		public TreeNode(int key, String data) {
			this.key = key;
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;

		}

	}
}
