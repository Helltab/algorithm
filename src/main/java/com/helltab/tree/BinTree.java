package com.helltab.tree;

import com.helltab.util.ColorCons;
import com.helltab.util.ColorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import static com.helltab.util.MyUtil.test;
import static com.helltab.util.MyUtil.visit;

public class BinTree {
    public TNode<Character> root;

    public BinTree() {
//        root = new TNode<>('a');
//        TNode<Character> b = new TNode<>('b');
//        TNode<Character> c = new TNode<>('c');
//        TNode<Character> d = new TNode<>('d');
//        TNode<Character> e = new TNode<>('e');
//        TNode<Character> f = new TNode<>('f');
//        TNode<Character> g = new TNode<>('g');
//        root.left = b;
//        root.right = c;
//        c.left = d;
//        c.right = f;
//        d.right = e;
//        f.left = g;
        root = new TNode<>('+');
        TNode<Character> b = new TNode<>('*');
        TNode<Character> c = new TNode<>('/');
        TNode<Character> d = new TNode<>('+');
        TNode<Character> e = new TNode<>('c');
        TNode<Character> f = new TNode<>('d');
        TNode<Character> g = new TNode<>('f');
        TNode<Character> h = new TNode<>('a');
        TNode<Character> i = new TNode<>('b');
        root.left = b;
        root.right = c;
        b.left = d;
        b.right = e;
        d.left = h;
        d.right = i;
        c.left = f;
        c.right = g;
    }

    public static void testTraverse() {
        BinTree tree = new BinTree();

        test(s -> {
            preOrder(tree.root);
            return true;
        }, "递归-前序遍历");
        reset(tree.root);
        test(s -> {
            preOrder1(tree.root);
            return true;
        }, "非递归-前序遍历-立即访问左节点");
        reset(tree.root);
        test(s -> {
            preOrder2(tree.root);
            return true;
        }, "非递归-前序遍历-深度优先");
        reset(tree.root);
        test(s -> {
            inOrder(tree.root);
            return true;
        }, "递归-中序遍历");
        reset(tree.root);
        test(s -> {
            inOrder1(tree.root);
            return true;
        }, "递归-中序遍历-深度优先");
        reset(tree.root);
        test(s -> {
            postOrder(tree.root);
            return true;
        }, "递归-后序遍历");
        reset(tree.root);
        test(s -> {
            postOrder2(tree.root);
            return true;
        }, "递归-后序遍历-深度优先");
    }

    public static void testRebuild() {
        char[] pre = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        char[] in = {'d', 'c', 'e', 'b', 'f', 'a', 'g'};
        char[] post = {'d', 'e', 'c', 'f', 'b', 'g', 'a'};
        test(s -> {
            TNode<Character> rebuildTree = rebuildTreeByPreIn(pre, in);
            TNode<Character> rebuildTree2 = rebuildTreeByPostIn(post, in);
            preOrder(rebuildTree);
            System.out.println();
            preOrder(rebuildTree2);
            return true;
        }, "重建二叉树");
    }

    public static void testSearchTree() {
        test(s -> {
            TNode<Integer> searchTree = new TNode<>(1);
            BiPredicate<Integer, Integer> compare = (a, b) -> b < a;
            searchTree.addSearchNode(new TNode<>(20), compare);
            searchTree.addSearchNode(new TNode<>(30), compare);
            searchTree.addSearchNode(new TNode<>(2), compare);
            searchTree.addSearchNode(new TNode<>(5), compare);
            searchTree.addSearchNode(new TNode<>(45), compare);
            searchTree.addSearchNode(new TNode<>(3), compare);
            searchTree.addSearchNode(new TNode<>(-5), compare);
            searchTree.addSearchNode(new TNode<>(88), compare);
            preOrderInt(searchTree);
            System.out.println();
            inOrderInt(searchTree);
            System.out.println();
            searchTree.delSearchNode(1);
            preOrderInt(searchTree);
            System.out.println();
            inOrderInt(searchTree);
            return true;
        }, "查找树");
    }

    public static void testAVLTree() {
        test(s -> {
            TNode<Integer> avlTree = new TNode<>(4);
            avlTree.addAVLNode(2);
            avlTree.addAVLNode(1);
            avlTree.addAVLNode(3);
            avlTree.addAVLNode(6);
            avlTree.addAVLNode(5);
            avlTree.addAVLNode(7);
//            System.out.println(avlTree);
//            preOrderInt(avlTree);
//            System.out.println();
//            inOrderInt(avlTree);
            avlTree = avlTree.leftRotation(avlTree.binSearch(2));
            preOrderInt(avlTree);
            System.out.println();
            avlTree.getRoot().addAVLNode(8);
            avlTree.getRoot().addAVLNode(9);
//            inOrderInt(avlTree);
//            avlTree = avlTree.rightRotation(avlTree);
//            preOrderInt(avlTree);
//            System.out.println();
//            inOrderInt(avlTree);
            avlTree.rightRotation(avlTree.binSearch(2));
//            avlTree.rightRotation(avlTree.binSearch(3));
            avlTree.leftRotation(avlTree.binSearch(8));
            avlTree.leftRotation(avlTree.binSearch(6));
            avlTree.rightRotation(avlTree.binSearch(9));
//            avlTree.rightRotation(avlTree.binSearch(7));
//            avlTree.leftRotation(avlTree.binSearch(7));
            preOrderInt(avlTree);
            System.out.println();
            inOrderInt(avlTree);
            System.out.println();
            System.out.println("层次遍历");
            avlTree.levelTraver(4);
            return true;
        }, "平衡树-左右旋转");
    }

    public static void main(String[] args) {
        testAVLTree();
    }

    public static void preOrderInt(TNode<Integer> root) {
        if (root == null) return;
        root.visit();
        preOrderInt(root.left);
        preOrderInt(root.right);
    }

    public static void inOrderInt(TNode<Integer> root) {
        if (root == null) return;
        inOrderInt(root.left);
        root.visit();
        inOrderInt(root.right);
    }

    public static void preOrder(TNode<Character> root) {
        if (root == null) return;
        root.visit();
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
            node.visit();
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
        while (true) {
            visitAlongLeft(root, stack);
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
        }
    }

    private static void visitAlongLeft(TNode<Character> root, Stack<TNode<Character>> stack) {
        while (root != null) {
            root.visit();
            stack.push(root.right);
            root = root.left;
        }
    }

    public static void inOrder(TNode<Character> root) {
        if (root == null) return;
        inOrder(root.left);
        root.visit();
        inOrder(root.right);
    }

    /**
     * 深度优先
     * 1. 记录左节点, 一直到叶子.
     * 2. 开始回溯, 记录左节点的右节点, 右节点重复第一步
     *
     * @param root
     */
    public static void inOrder1(TNode<Character> root) {
        Stack<TNode<Character>> stack = new Stack<>();
        while (true) {
            goAlongLeft(root, stack);
            if (stack.isEmpty()) {
                break;
            }
            TNode<Character> top = stack.pop();
            top.visit();
            root = top.right;
        }
    }

    private static void goAlongLeft(TNode<Character> root, Stack<TNode<Character>> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public static void postOrder(TNode<Character> root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        root.visit();
    }

    /**
     * 深度优先
     * 没有左右孩子即可访问
     *
     * @param root
     */
    public static void postOrder2(TNode<Character> root) {
        if (root == null) return;
        Stack<TNode<Character>> stack = new Stack<>();
        while (true) {
            goAlongLeft(root, stack);
            TNode<Character> top = stack.peek();
            if ((top.right == null || top.right.isVisit()) && (top.left == null || top.left.isVisit())) {
                top.visit();
                stack.pop();
            }
            root = top.right == null || top.right.isVisit() ? null : top.right;
            if (stack.isEmpty()) {
                break;
            }
        }
    }


    public static void reset(TNode<Character> root) {
        if (root == null) return;
        root.reset();
        reset(root.left);
        reset(root.right);
    }

    /**
     * 根据前序遍历和中序遍历还原树
     */
    public static TNode<Character> rebuildTreeByPreIn(char[] pre, char[] in) {
        if (pre == null || in == null || in.length == 0 || pre.length != in.length) return null;
        char root = pre[0];
        int rootIndex = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root) {
                rootIndex = i;
            }
        }
        if (rootIndex == -1) return null;
        //首先找到根节点
        TNode<Character> rootNode = new TNode<>(root);
        // 找到中序遍历的左子树
        char[] inLeft = Arrays.copyOfRange(in, 0, rootIndex);
        // !!! 左子树的数量肯定是相同的
        char[] preLeft = Arrays.copyOfRange(pre, 1, rootIndex + 1);

        char[] inRight = Arrays.copyOfRange(in, rootIndex + 1, in.length);
        char[] preRight = Arrays.copyOfRange(pre, rootIndex + 1, pre.length);

        rootNode.left = rebuildTreeByPreIn(preLeft, inLeft);
        rootNode.right = rebuildTreeByPreIn(preRight, inRight);
        return rootNode;
    }

    /**
     * 根据后序遍历和中序遍历还原树
     */
    public static TNode<Character> rebuildTreeByPostIn(char[] post, char[] in) {
        if (post == null || in == null || in.length == 0 || post.length != in.length) return null;
        char root = post[post.length - 1];
        int rootIndex = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root) {
                rootIndex = i;
            }
        }
        if (rootIndex == -1) return null;
        //首先找到根节点
        TNode<Character> rootNode = new TNode<>(root);
        // 找到中序遍历的左子树
        char[] inLeft = Arrays.copyOfRange(in, 0, rootIndex);
        // !!! 左子树的数量肯定是相同的
        char[] postLeft = Arrays.copyOfRange(post, 0, rootIndex);
        char[] inRight = Arrays.copyOfRange(in, rootIndex + 1, in.length);
        char[] postRight = Arrays.copyOfRange(post, rootIndex, rootIndex + 1);
        rootNode.left = rebuildTreeByPostIn(postLeft, inLeft);
        rootNode.right = rebuildTreeByPostIn(postRight, inRight);
        return rootNode;
    }

    /**
     * 构建二叉搜索树
     */
    public static void buildSearchTree() {

    }

}

class TNode<T> implements Comparable {
    public T data;
    public TNode<T> parent;
    public TNode<T> left;
    public TNode<T> right;
    private boolean visitFlag;

    @Override
    public String toString() {
//        return "TNode{" +
//                       "data=" + data +
//                       ", height=" + height +
//                       '}';
        return data.toString();
    }

    /**
     * 节点高度
     */
    int height;

    /**
     * 二叉搜索树
     *
     * @param node
     * @param compare
     */
    public void addSearchNode(TNode<T> node, BiPredicate<T, T> compare) {
        if (node.data.equals(data)) {
            return;
        }
        if (compare.test(data, node.data)) {
            if (hasLC()) {
                left.addSearchNode(node, compare);
            } else {
                System.out.println(node.data + "\t插入到\t" + data + "\t左节点");
                left = node;
                node.parent = this;
            }
        } else {
            if (hasRC()) {
                right.addSearchNode(node, compare);
            } else {
                System.out.println(node.data + "\t插入到\t" + data + "\t右节点");
                right = node;
                node.parent = this;
            }
        }
    }

    /**
     * 如果是叶子节点, 直接删
     * 如果是单叶子节点, 叶子替换
     * 如果是双叶子节点, 中序遍历, 用后继来替换
     */
    public void delSearchNode(T data) {
        Stack<TNode<T>> stack = new Stack<>();
        TNode<T> node = this;
        TNode<T> target = null;
        while (true) {
            goAlongLeft(node, stack);
            if (stack.isEmpty()) break;
            TNode<T> pop = stack.pop();
            if (target != null) {
                T temp = target.data;
                target.data = pop.data;
                pop.data = temp;
            }
            if (data.equals(pop.data)) {
                // 叶子节点
                if (!pop.hasLC() || !pop.hasRC()) {
                    if (pop.parent.left == pop) {
                        pop.parent.left = pop.left == null ? pop.right : pop.left;
                        System.out.println("从\t" + pop.parent.data + "\t删除\t" + pop.data);
                        return;
                    } else {
                        pop.parent.right = pop.right == null ? pop.left : pop.right;
                        System.out.println("从\t" + pop.parent.data + "\t删除\t" + pop.data);
                        return;
                    }
                } else {
                    target = pop;
                }
            }

            node = pop.right;
        }
    }

    private void goAlongLeft(TNode<T> node, Stack<TNode<T>> stack) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /**
     * 二叉搜索平衡树
     * <p>
     * 1. 左右子树的高度差不超过 1
     * 2. 所有的子树也是平衡二叉树
     *
     * @param data
     */
    public void addAVLNode(T data) {
        if (binSearch(data) != null) {
            return;
        }
        TNode<T> newNode = new TNode<>(data);
        if (this.compareTo(data) > 0) {
            if (hasLC()) {
                left.addAVLNode(data);
            } else {
                left = newNode;
                newNode.parent = this;
            }
        } else {
            if (hasRC()) {
                right.addAVLNode(data);
            } else {
                right = newNode;
                newNode.parent = this;
            }
        }
    }

    /**
     * 左旋
     * *          a                      c
     * *        /   \                  /   \
     * *      b      c      ->       a      e
     * *           /   \           /   \
     * *         d      e        b      d
     */
    public TNode<T> leftRotation(TNode<T> avlNode) {
        if (avlNode != null && avlNode.hasRC()) {
            TNode<T> right = avlNode.right;
            if (right.hasLC()) {
                avlNode.right = right.left;
                right.left.parent = avlNode;
            } else {
                avlNode.right = null;
            }
            right.parent = avlNode.parent;
            if (avlNode.parent != null) {
                if (avlNode.parent.left == avlNode) {
                    avlNode.parent.left = right;
                } else {
                    avlNode.parent.right = right;
                }
            }
            right.left = avlNode;
            avlNode.parent = right;
            System.out.println("\n以\t" + avlNode.data + "\t为节点左旋, 新节点为" + right.data);
            return right;
        }
        System.out.println("\n旋转节点不存在");
        return null;
    }

    /**
     * 右旋
     * *          a                      b
     * *        /   \                  /   \
     * *      b      c      ->       f      a
     * *    /   \                         /  \
     * *   f     d                      d     c
     */
    public TNode<T> rightRotation(TNode<T> avlNode) {
        if (avlNode != null && avlNode.hasLC()) {
            TNode<T> left = avlNode.left;
            // 迁移左节点的右孩子到当前节点的左孩子
            if (left.hasRC()) {
                avlNode.left = left.right;
                left.right.parent = avlNode;
            } else {
                avlNode.left = null;
            }
            // 重建父子关系
            left.parent = avlNode.parent;
            if (avlNode.parent != null) {
                if (avlNode.parent.left == avlNode) {
                    avlNode.parent.left = left;
                } else {
                    avlNode.parent.right = left;
                }
            }

            left.right = avlNode;
            avlNode.parent = left;
            System.out.println("\n以\t" + avlNode.data + "\t为节点右旋, 新节点为" + left.data);
            return left;
        }
        System.out.println("\n旋转节点不存在");
        return null;

    }

    /**
     * 查找
     */
    public TNode<T> search(T t) {
        TNode<T> root = getRoot();
        Stack<TNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TNode<T> pop = stack.pop();
            if (pop.data.equals(t)) {
                return pop;
            }
            if (pop.hasRC()) stack.push(pop.right);
            if (pop.hasLC()) stack.push(pop.left);
        }
        return null;
    }

    public TNode<T> getRoot() {
        TNode<T> root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    /**
     * 二叉查找
     */
    public TNode<T> binSearch(T t) {
        TNode<T> root = getRoot();
        while (root != null) {
            // 比当前节点小
            if (root.compareTo(t) > 0) {
                root = root.left;
            } else if (root.compareTo(t) < 0) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 层次遍历, 打印树
     *
     * @param data
     */
    public void levelTraver(T data) {
        // 根据值搜索出对应的节点
        TNode<T> node = search(data);
        if (node == null) return;
        Deque<TNode<T>> path = new LinkedList<>();
        path.push(node);
        List<List<TNode<T>>> all = new ArrayList<>();
        List<TNode<T>> temp = new ArrayList<>();
        all.add(temp);
        T maxData = data;
        // 层次遍历
        while (true) {
            node = path.poll();
            assert node != null;
            if (node.parent != null && (temp.contains(node.parent))) {
                temp = new ArrayList<>();
                all.add(temp);
            }
            temp.add(node);
            if (node.compareTo(maxData) > 0) {
                maxData = node.data;
            }
            if (null != node.left) {
                path.offer(node.left);
            }
            if (null != node.right) {
                path.offer(node.right);
            }
            if (path.isEmpty()) break;
        }
        // 补全为满二叉树
        for (int i = 0; i < all.size(); i++) {
            List<TNode<T>> tNodes = all.get(i);
            if (i == 0) continue;
            List<TNode<T>> pNodes = all.get(i - 1);
            if (tNodes.size() != 1 << pNodes.size()) {
                for (int i1 = 0; i1 < pNodes.size(); i1++) {
                    TNode<T> pNode = pNodes.get(i1);
                    if (pNode == null) {
                        tNodes.add(i1 << 1, null);
                        tNodes.add((i1 << 1) + 1, null);
                    } else {
                        if (!pNode.hasLC()) {
                            tNodes.add(i1 << 1, null);
                        }
                        if (!pNode.hasRC()) {
                            tNodes.add((i1 << 1) + 1, null);
                        }
                    }
                }
            }

        }
        // 计算出格式化的值长度
        int blackNum = (Math.max(maxData.toString().length(), 2) + 2) >> 1 << 1;
        StringBuffer divide = new StringBuffer();
        for (int i = 0; i < blackNum * all.get(all.size() - 1).size(); i++) {
            divide.append("- ");
        }
        System.out.println(ColorUtil.colorFont(divide.toString(), ColorCons.F_G));
        String blank = format(null, blackNum);
        int size = all.size();
        // 打印左边空格
        Consumer<Integer> printStart = num -> {
            System.out.println();
            for (int j = 0; j < ((1 << (size - num - 1)) - 1); j++) {
                System.out.print(blank);
            }
        };
        for (int i = 0; i < all.size(); i++) {
            List<TNode<T>> tNodes = all.get(i);

            // 画分支线
            if (i > 0) {
                printStart.accept(i);
                for (int i1 = 0; i1 < tNodes.size(); i1++) {
                    TNode<T> tNode = tNodes.get(i1);
                    // 处理补全二叉树的 null 值
                    if (tNode == null) {
                        if (i1 > 0) {
                            TNode<T> tPre = tNodes.get(i1 - 1);
                            // 如果 null 的左边节点是右节点, 需要补充打印一个间隔
                            //     8
                            // ╭┄┄┄┴┄┄┄╮(     )
                            // 7       9       NULL
                            if (tPre == null || tPre.parent.right == tPre) {
                                for (int j = 0; j < ((1 << (size - i)) - 1); j++) {
                                    System.out.print(blank);
                                }
                            }
                        }
                        if (i1 < tNodes.size() - 1 && tNodes.get(i1 + 1) == null) {
                            if (i1 > 0) {
                                TNode<T> tPre = tNodes.get(i1 - 1);
                                // 如果 null 的左边节点是右节点, 需要补充打印一个空串
                                //     8
                                // ╭┄┄┄┴┄┄┄╮      (    )
                                // 7       9       NULL
                                if (tPre == null || tPre.parent.right == tPre) {
                                    System.out.print(blank);
                                }
                            } else {
                                System.out.print(blank);
                            }
                        }
                    } else {
                        // 如果节点是右节点, 左边节点不是 null, 需要补齐间隔
                        //          2
                        //  ╭┄┄┄┄┄┄┄┴┄┄┄┄┄┄┄╮(             )
                        //  1               3               5
                        if (i1 > 0 && tNodes.get(i1 - 1) != null && tNode.parent.right == tNode) {
                            for (int j = 0; j < ((1 << (size - i)) - 1); j++) {
                                System.out.print(blank);
                            }
                        }
                        //  ╭┄┄┄┄┄┄┄┘        (   情况2   ) ╭┄┄┄┘
                        //  1            null             6
                        //                   (  情况1 )╭┄┄┄┘
                        //           null    null     5
                        if (i1 > 0 && tNodes.get(i1 - 1) == null && tNode.parent.left == tNode) {
                            if (i1 > 1 && tNodes.get(i1 - 2) == null) {
                                for (int j = 0; j < ((1 << (size - i))); j++) {
                                    System.out.print(blank);
                                }
                            } else {
                                for (int j = 0; j < ((1 << (size - i)) - 1); j++) {
                                    System.out.print(blank);
                                }
                            }
                        }
                        Consumer<String> printRemain_pre = s -> {
                            for (int j = 0; j < ((blackNum >> 1) - 1); j++) {
                                System.out.print(s);
                            }
                        };
                        Consumer<String> printRemain_post = s -> {
                            for (int j = 0; j < (blackNum >> 1); j++) {
                                System.out.print(s);
                            }
                        };
                        int num = ((1 << (size - i))) * blackNum;
                        // 根据父节点是否包含左右节点来打印分支
                        if (tNode.parent.hasLC() && tNode.parent.hasRC()) {
                            if (tNode.parent.left == tNode) {
                                printRemain_pre.accept(" ");
                                System.out.print(formatBranch(num, BranchType.Full));
                                printRemain_post.accept(" ");
                            }
                        } else if (!tNode.parent.hasRC()) {
                            printRemain_pre.accept(" ");
                            System.out.print(formatBranch(num, BranchType.Left));
                            printRemain_post.accept(" ");
                        } else {
                            printRemain_pre.accept(" ");
                            System.out.print(formatBranch(num, BranchType.Right));
                            printRemain_post.accept(" ");
                        }
                    }
                }
            }

            printStart.accept(i);
            // 打印数据
            for (TNode<T> tNode : tNodes) {
                if (tNode == null) {
                    System.out.print(ColorUtil.colorFont(format("null", blackNum), ColorCons.F_B_H));
                } else {
                    System.out.print(ColorUtil.colorFont(format(tNode.data.toString(), blackNum), ColorCons.F_P_H));
                }
                for (int j = 0; j < ((1 << (size - i)) - 1); j++) {
                    System.out.print(blank);
                }
            }

        }
        System.out.println();
        System.out.println();
        System.out.println(ColorUtil.colorFont(divide.toString(), ColorCons.F_G));
    }

    /**
     * 根据位数拼接空格
     *
     * @param data
     * @param blackNum
     * @return
     */
    public String format(String data, int blackNum) {
        return format(data, blackNum, " ", " ");
    }

    public String format(String data, int blackNum, String preBlank, String postBlank) {
        String org = data == null ? " " : data;
        StringBuffer target = new StringBuffer();
        int half = blackNum - org.length() >> 1;
        for (int i = 0; i < half; i++) {
            target.append(preBlank);
        }
        target.append(org);
        for (int i = 0; i < blackNum - org.length() - half; i++) {
            target.append(postBlank);
        }
        return target.toString();
    }


    enum BranchType {
        Full,
        Left,
        Right
    }

    public String formatBranch(int num, BranchType branchType) {
//        System.out.println("┻┓┗┛┏━━━━━━━━┻━━━━━━━┓");
        StringBuffer format = new StringBuffer();
        int l, r;
        num = Math.max(4, num);
        l = num >> 1;
        r = num - l;
//        String _l = "┏",
//                _h = "━",
//                _c = "┻",
//                _lc = "┛",
//                _rc = "┗",
//                _r = "┓",
//                _b = " ";
//        String _l = "┌",
//                _h = "┄",
//                _c = "┴",
//                _lc = "┘",
//                _rc = "└",
//                _r = "┐",
//                _b = " ";
        String _l = "╭",
                _h = "┄",
                _c = "┴",
                _lc = "┘",
                _rc = "└",
                _r = "╮",
                _b = " ";
        Consumer<StringBuffer> left_line_fun = sb -> {
            format.append(_l);
            for (int i = 0; i < l - 1; i++) {
                format.append(_h);
            }
        };
        Consumer<StringBuffer> right_line_fun = sb -> {
            for (int i = 0; i < r - 1; i++) {
                format.append(_h);
            }
            format.append(_r);
        };
        Consumer<StringBuffer> left_blank_fun = sb -> {
            for (int i = 0; i < l; i++) {
                format.append(_b);
            }
        };
        Consumer<StringBuffer> right_blank_fun = sb -> {
            for (int i = 0; i < r; i++) {
                format.append(_b);
            }
        };
        switch (branchType) {
            case Full:
                left_line_fun.accept(format);
                format.append(_c);
                right_line_fun.accept(format);
                break;
            case Left:
                left_line_fun.accept(format);
                format.append(_lc);
                right_blank_fun.accept(format);
                break;
            default:
                left_blank_fun.accept(format);
                format.append(_rc);
                right_line_fun.accept(format);
        }
        return format.toString();
    }


    public void visit() {
        visit.accept(data);
        visitFlag = true;
    }

    public void reset() {
        visitFlag = false;
    }

    public boolean isVisit() {
        return visitFlag;
    }

    public TNode(T a) {
        this.data = a;
        this.visitFlag = false;
        this.parent = null;
    }

    public boolean hasLC() {
        return left != null;
    }

    public boolean hasRC() {
        return right != null;
    }


    @Override
    public int compareTo(Object o) {
        if (data instanceof Integer) {
            return ((Integer) data) - ((Integer) o);
        }
        if (data instanceof Long) {
            return (int) (((Long) data) - ((Long) o));
        }
        if (data instanceof Float) {
            return (int) (((Float) data) - ((Float) o));
        }
        if (data instanceof Double) {
            return (int) (((Double) data) - ((Double) o));
        }
        if (data instanceof Character) {
            return ((Character) data) - ((Character) o);
        }
        return 0;
    }

}
