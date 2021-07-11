package com.helltab.DSA.tree;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode(int val) {
        this.val = val;
    }

    public boolean hasLC() {
        return this.left != null;
    }

    public boolean hasRC() {
        return this.right != null;
    }

}
