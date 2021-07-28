package com.helltab.DSA.tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Topic 复习树的遍历
 *
 * @author helltab
 * @version 1.0
 * @date 2021/7/11 16:17
 */
public class Demo20210711 {


    public static void main(String[] args) {
        System.out.println(1 / 2);
        TNode root = BinTree.rebuildTreeByPreIn(
                new Object[]{5, 3, 2, 1, 4, 8, 6, 7, 9},
                new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, null
        );
        deepTraverse(root, TraverseMode.pre);
        deepTraverse(root, TraverseMode.in);
        deepTraverse(root, TraverseMode.post);
//        breadTraverse(root);
//        List<Object> serialTree = serialTree(root);
//        System.out.println(serialTree);
//        TNode newTree = rebuildBySerialTree(serialTree);
//        newTree.levelTraver(null);
//
//        root.data = 3;
//        System.out.println(sameTree(root, newTree));
//        TNode mirror = new TNode(5);
//        mirror.left = new TNode(4);
//        mirror.left.left = new TNode(3);
//        mirror.right = new TNode(4);
//        mirror.right.right = new TNode(3);
//        System.out.println(mirrorTree(mirror));
        preTraverse_morris(root);
        System.out.println();
        inTraverse_morris(root);
        TNode root1 = BinTree.rebuildTreeByPreIn(
                new Object[]{6, 2, 0, 4, 3, 5, 8, 7, 9},
                new Object[]{0, 2, 3, 4, 5, 6, 7, 8, 9}, null
        );
        TNode t = lowestCommonAncestor(root1, new TNode(2), new TNode(8));
        System.out.println("\n--------");
        deepTraverse(t, TraverseMode.pre);

    }

    /**
     * 思路: 遇到谁就访问谁, 把右边先压入栈中, 这样可以做到先左后右
     *
     * @param root
     */
    private static void preTraverse(TNode root) {
        if (null == root) return;
        Stack<TNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TNode pop = stack.pop();
            pop.visit();
            if (null != pop.right) {
                stack.push(pop.right);
            }
            if (null != pop.left) {
                stack.push(pop.left);
            }

        }
    }

    private static void preTraverse2(TNode root) {
        if (null == root) return;
        Stack<TNode> stack = new Stack<>();
        while (true) {
            // 1. 访问
            // 2. 沿着左子树深入
            while (null != root) {
                root.visit();
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) break;
            // 3. 右子树压入栈
            root = stack.pop().right;
        }
    }

    /**
     * 思路: 要保证左子树不存在或者已经访问了才能访问该节点
     * 1. 先深入到最左边
     * 2. 回溯
     * 3. 把右子树压入栈: 因为在不存在多次访问该节点的情况, 所以直接访问右子树
     *
     * @param root
     */
    private static void inTraverse(TNode root) {
        if (null == root) return;
        Stack<TNode> stack = new Stack<>();
        while (true) {
            // 1. 沿着左子树一直压入栈, 保证 root 没有左子树
            while (null != root) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) break;
            // 2. 开始回溯
            TNode pop = stack.pop();
            pop.visit();
            // 3. 最后访问右子树
            root = pop.right;
        }
    }

    /**
     * 思路: 要保证左子树和右子树都不存在或者访问过了才访问该节点
     * 1. 先深入到最左边
     * 2. 回溯
     * 3. 把右子树压入栈: 因为有多次访问的可能, 所以需要把已访问的右子树过滤掉
     *
     * @param root
     */
    private static void postTraverse(TNode root) {
        if (null == root) return;
        Set<Object> set = new HashSet<>();
        Stack<TNode> stack = new Stack<>();
        while (true) {
            // 1. 沿着左子树一直压入栈, 保证没有左子树
            while (null != root) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) break;

            // 2. 开始回溯
            TNode top = stack.peek();
            boolean visitRight = null == top.right || set.contains(top.right.data);
            boolean visitLeft = (null == top.left || set.contains(top.left.data));
            if (visitRight && visitLeft) {
                top.visit();
                set.add(top.data);
                stack.pop();
            }
            root = visitRight ? null : top.right;
        }
    }

    /**
     * 树的深度遍历
     *
     * @param root
     */
    public static void deepTraverse(TNode root, TraverseMode mode) {
        if (null == root) return;

        Stack<TNode> stack = new Stack<>();
        Set<Object> visitSet = new HashSet<>();
        while (true) {
            while (null != root) {
                if (mode == TraverseMode.pre) {
                    root.visit();
                }
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) break;

            TNode top = stack.peek();
            switch (mode) {
                case in:
                    top.visit();
                case pre:
                    stack.pop();
                    root = top.right;
                    break;
                default:
                    boolean visitLeft = null == top.left || visitSet.contains(top.left.data);
                    boolean visitRight = null == top.right || visitSet.contains(top.right.data);
                    if (visitLeft && visitRight) {
                        visitSet.add(top.data);
                        top.visit();
                        stack.pop();
                    }
                    root = visitRight ? null : top.right;
            }
        }
        System.out.println();
    }

    /**
     * 树的广度优先遍历
     *
     * @param root
     */
    public static void breadTraverse(TNode root) {
        if (null == root) return;
        Queue<TNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TNode node = queue.poll();
            node.visit();
            if (null != node.left) queue.add(node.left);
            if (null != node.right) queue.add(node.right);
        }
        System.out.println();
    }

    public static List<Object> serialTree(TNode root) {
        List<Object> list = new ArrayList<>();
        Queue<TNode> queue = new ArrayDeque<>();
        queue.add(root);
        list.add(root.data);
        while (!queue.isEmpty()) {
            List<TNode> collect = queue.stream().filter(node -> null != node.left || null != node.right).collect(Collectors.toList());
            if (collect.isEmpty()) break;
            Queue<TNode> temp = new ArrayDeque<>();
            for (TNode tNode : queue) {
                if (null != tNode.left) {
                    temp.add(tNode.left);
                    list.add(tNode.left.data);
                } else {
                    list.add(null);
                }
                if (null != tNode.right) {
                    temp.add(tNode.right);
                    list.add(tNode.right.data);
                } else {
                    list.add(null);
                }
            }
            queue = temp;
        }
        System.out.println();
        return list;
    }

    public static TNode rebuildBySerialTree(List<Object> list) {
        if (list.isEmpty()) return null;
        int index = 0;
        Object rootData = list.get(index++);
        TNode root = new TNode(rootData);
        Queue<TNode> deque = new ArrayDeque<>();
        deque.add(root);

        while (!deque.isEmpty()) {
            Queue<TNode> temp = new ArrayDeque<>();
            for (TNode node : deque) {
                int leftIndex = index++;
                int rightIndex = index++;
                if (leftIndex < list.size()) {
                    Object dataLeft = list.get(leftIndex);
                    if (null != dataLeft) {
                        node.left = new TNode(dataLeft);
                        node.left.parent = node;
                        temp.add(node.left);
                    }
                }
                if (rightIndex < list.size()) {
                    Object dataRight = list.get(rightIndex);
                    if (null != dataRight) {
                        node.right = new TNode(dataRight);
                        node.right.parent = node;
                        temp.add(node.right);
                    }
                }
            }
            deque = temp;
        }
        return root;
    }

    public static boolean sameTree(TNode a, TNode b) {
        if (null == a && null == b) return true;
        if (a.data.equals(b.data)) {
            return sameTree(a.left, b.left) && sameTree(a.right, b.right);
        }
        return false;
    }

    public static boolean mirrorTree(TNode mirrorT) {
        return mirrorTree(mirrorT, mirrorT);
    }

    private static boolean mirrorTree(TNode a, TNode b) {
        if (null == a && null == b) return true;
        if (null == a || null == b) return false;
        if (a.data.equals(b.data)) {
            return mirrorTree(a.left, b.right) && mirrorTree(a.right, b.left);
        }
        return false;
    }

    public enum TraverseMode {
        pre,
        in,
        post;
    }

    /**
     * morris 遍历, 充分利用叶子节点的空指针
     * 前序遍历
     *
     * @param root
     */
    public static void preTraverse_morris(TNode root) {
        if (null == root) return;
        TNode cur = root;
        while (null != cur) {
            // 左节点不为空, 需要找到左节点的最右节点
            if (null != cur.left) {
                TNode mostRight = cur.left;
                while (null != mostRight.right && cur != mostRight.right) mostRight = mostRight.right;
                // 建立左节点最右节点和当前节点的关系
                if (null == mostRight.right) {
                    cur.visit();
                    mostRight.right = cur;
                    // 沿着左节点, 把对应的关系都建立起来
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;

            } else {
                cur.visit();
            }
            cur = cur.right;
        }
    }
    /**
     * morris 遍历, 充分利用叶子节点的空指针
     * 中序遍历
     *
     * @param root
     */
    public static void inTraverse_morris(TNode root) {
        if (null == root) return;
        TNode cur = root;
        while (null != cur) {
            // 左节点不为空, 需要找到左节点的最右节点
            if (null != cur.left) {
                TNode mostRight = cur.left;
                while (null != mostRight.right && cur != mostRight.right) mostRight = mostRight.right;
                // 建立左节点最右节点和当前节点的关系
                if (null == mostRight.right) {
                    mostRight.right = cur;
                    // 沿着左节点, 把对应的关系都建立起来
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;

            }
            cur.visit();
            cur = cur.right;
        }
    }
    /**
     * morris 遍历, 充分利用叶子节点的空指针
     * 后序遍历
     *
     * @param root
     */
    public static void postTraverse_morris(TNode root) {
        if (null == root) return;
        TNode cur = root;
        while (null != cur) {
            // 左节点不为空, 需要找到左节点的最右节点
            if (null != cur.left) {
                TNode mostRight = cur.left;
                while (null != mostRight.right && cur != mostRight.right) mostRight = mostRight.right;
                // 建立左节点最右节点和当前节点的关系
                if (null == mostRight.right) {
                    mostRight.right = cur;
                    // 沿着左节点, 把对应的关系都建立起来
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;

            }
            cur.visit();
            cur = cur.right;
        }
    }

    public static TNode lowestCommonAncestor(TNode root, TNode p, TNode q) {
        if(root==null) return null;

//        if(root.data == p.data || root.data == q.data) {
//            return root;
//        }
        boolean ifLeft = deepSearch(root.left, p);
        boolean ifRight = deepSearch(root.right, q);
        if(ifLeft==ifRight) {
            return root;
        }else if(ifLeft) {
            return lowestCommonAncestor(root.left, p, q);
        }else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    public static boolean deepSearch(TNode root, TNode target) {
        if(root==null) return false;
        if(root.data == target.data) return true;
        return deepSearch(root.left, target) ||
        deepSearch(root.right, target);
    }

}
