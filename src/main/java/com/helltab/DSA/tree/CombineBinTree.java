package com.helltab.DSA.tree;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * *           1                         2
 * *          / \                       / \
 * *         3   2                     1   3
 * *        /                           \   \
 * *       5                             4   7
 * 输出:
 * 合并后的树:
 * * 	     3
 * * 	    / \
 * * 	   4   5
 * * 	  / \   \
 * * 	 5   4   7
 */
public class CombineBinTree {
    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(3);
        tree1.left.left = new TreeNode(5);
        tree1.right = new TreeNode(2);
        TreeNode tree2 = new TreeNode(2);
        tree2.left = new TreeNode(1);
        tree2.left.right = new TreeNode(4);
        tree2.right = new TreeNode(3);
        tree2.right.right = new TreeNode(7);
        System.out.println("\n先序遍历: ");
        preOrder(tree1, System.out::print);
        System.out.println("\n先序遍历1: ");
        preOrder1(tree1, System.out::print);
        System.out.println("\n先序遍历2: ");
        preOrder2(tree1, System.out::print);
        System.out.println("\n中序遍历: ");
        inOrder(tree1, System.out::print);
        System.out.println("\n中序遍历1: ");
        inOrder1(tree1, System.out::print);
        System.out.println("\n后序遍历: ");
        postOrder(tree1, System.out::print);
        System.out.println();
    }

    private static TreeNode solution1(TreeNode treeNode1, TreeNode treeNode2) {
        if (treeNode2 == null) {
            return treeNode1;
        }
        if (treeNode1 == null) {
            return treeNode2;
        }
        TreeNode treeNode = new TreeNode(treeNode1.val + treeNode2.val);
        treeNode.left = solution1(treeNode1.left, treeNode2.left);
        treeNode.right = solution1(treeNode1.right, treeNode2.right);
        return treeNode;
    }

    /**
     * 先序遍历
     *
     * @param root
     * @param visit
     */
    static void preOrder(TreeNode root, Consumer<String> visit) {
        if (root == null) {
            return;
        }
        visit.accept(root.val + "\t");
        preOrder(root.left, visit);
        preOrder(root.right, visit);
    }

    // 递归解法 1
    static void preOrder1(TreeNode root, Consumer<String> visit) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        try {
        }catch (Exception e) {
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            visit.accept(node.val + "\t");
            if (node.hasRC()) stack.push(node.right);
            if (node.hasLC()) stack.push(node.left);
        }
    }

    // 递归解法 2
    static void preOrder2(TreeNode root, Consumer<String> visit) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            vistAloneLeftBrach(root, visit, stack);
            if (stack.isEmpty()) break;
            root = stack.pop();
        }
    }

    static void vistAloneLeftBrach(TreeNode root, Consumer<String> visit, Stack<TreeNode> stack) {
        while (root != null) {
            visit.accept(root.val + "\t");
            stack.push(root.right);
            root = root.left;
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     * @param visit
     */
    static void inOrder(TreeNode root, Consumer<String> visit) {
        if (root == null) {
            return;
        }
        inOrder(root.left, visit);
        visit.accept(root.val + "\t");
        inOrder(root.right, visit);
    }

    // 中序遍历1
    static void inOrder1(TreeNode root, Consumer<String> visit) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            goAlongLeftBrach(root, stack);
            if (stack.isEmpty()) break;
            root = stack.pop();
            visit.accept(root.val + "\t");
            root = root.right;
        }
    }

    static void goAlongLeftBrach(TreeNode root, Stack<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     * @param visit
     */
    static void postOrder(TreeNode root, Consumer<String> visit) {
        if (root == null) {
            return;
        }
        postOrder(root.left, visit);
        postOrder(root.right, visit);
        visit.accept(root.val + "\t");
    }

}

