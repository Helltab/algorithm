package com.helltab.tree;

import java.util.Stack;

import static com.helltab.util.MyUtil.test;
import static com.helltab.util.MyUtil.visit;

public class BinTree {
    public TNode<Character> root;

    public BinTree() {
        root = new TNode<>('a');
        TNode<Character> b = new TNode<>('b');
        TNode<Character> c = new TNode<>('c');
        TNode<Character> d = new TNode<>('d');
        TNode<Character> e = new TNode<>('e');
        TNode<Character> f = new TNode<>('f');
        TNode<Character> g = new TNode<>('g');
        root.left = b;
        root.right = c;
        c.left = d;
        c.right = f;
        d.right = e;
        f.left = g;
    }


    public static void main(String[] args) {
        BinTree tree = new BinTree();

        test(s -> {
            preOrder(tree.root);
            return true;
        }, "递归-前序遍历");
        test(s -> {
            preOrder1(tree.root);
            return true;
        }, "非递归-前序遍历-立即访问左节点");
        test(s -> {
            inOrder(tree.root);
            return true;
        }, "递归-中序遍历");
        test(s -> {
            postOrder(tree.root);
            return true;
        }, "递归-后序遍历");

    }

    public static void preOrder(TNode<Character> root) {
        if (root == null) return;
        visit.accept(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 前序-非递归-遍历即访问
     *
     * @param root
     */
    public static void preOrder1(TNode<Character> root) {
        if (root == null) return;
        Stack<TNode<Character>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TNode<Character> node = stack.pop();
            visit.accept(node.data);
            if (node.hasRC()) stack.push(node.right);
            if (node.hasLC()) stack.push(node.left);
        }
    }

    /**
     * 前序-非递归-
     *
     * @param root
     */
    public static void preOrder2(TNode<Character> root) {
        if (root == null) return;
        Stack<TNode<Character>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TNode<Character> node = stack.pop();
            visit.accept(node.data);
            if (node.hasRC()) stack.push(node.right);
            if (node.hasLC()) stack.push(node.left);
        }
    }

    private void visitAlongLeft(TNode<Character> root) {

    }

    public static void inOrder(TNode<Character> root) {

    }

    public static void postOrder(TNode<Character> root) {
        if (root == null) return;
        preOrder(root.left);
        preOrder(root.right);
        visit.accept(root.data);
    }
}

class TNode<T> {
    public T data;
    public TNode<T> left;
    public TNode<T> right;

    public TNode(T a) {
        this.data = a;
    }

    public boolean hasLC() {
        return left != null;
    }

    public boolean hasRC() {
        return right != null;
    }
}
