package com.helltab.tree.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import static com.helltab.util.MyUtil.test;
import static com.helltab.util.MyUtil.visit;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class PreTree {


    public static void main(String[] args) {
        String s1 = "1-2--3--4-5--6--7";
        String s2 = "1-401--349--90---88";
        String s3 = "1-2--3---4-5--6---7";

        final TreeNode[] treeNode = new TreeNode[3];

        test(s -> {
            treeNode[1] = recoverFromPreorder3(s3);
            order(treeNode[1]);
            return true;
        }, "其他人的");
        test(s -> {
            treeNode[1] = recoverFromPreorder4(s3);
            order(treeNode[1]);
            return true;
        }, "最好的");
        test(s -> {
            treeNode[2] = recoverFromPreorder(s3);
            order(treeNode[2]);
            return true;
        }, "自己的");
        test(s -> {
            treeNode[0] = recoverFromPreorder1(s3);
            order(treeNode[0]);
            return true;
        }, "官方");
    }

    public static void order(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        visit.accept(treeNode.val);
        order(treeNode.left);
        order(treeNode.right);
    }

    public static void levelTraver(TreeNode treeNode) {
        Deque<TreeNode> path = new LinkedList<>();
        path.push(treeNode);
        while (true) {

            treeNode = path.poll();
            visit.accept(treeNode.val == -1 ? "null" : treeNode.val);
            if (null != treeNode.left) {
                path.offer(treeNode.left);
                if (null == treeNode.right) {
                    path.offer(new TreeNode(-1));
                }
            }
            if (null != treeNode.right) {
                path.offer(treeNode.right);
            }
            if (path.isEmpty()) break;

        }
    }

    public static TreeNode recoverFromPreorder1(String S) {
        TreeNode result = null;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length(); ) {
            int deep = 0, val = 0;
            while (S.charAt(i) == '-') {
                ++deep;
                ++i;
            }
            while (i < S.length() && S.charAt(i) != '-') {
                val = val * 10 + (S.charAt(i) - '0');
                ++i;
            }
            TreeNode now = new TreeNode(val);
            while (stack.size() > deep) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result = now;
            } else {
                if (stack.peek().left == null) {
                    stack.peek().left = now;
                } else {
                    stack.peek().right = now;
                }
            }
            stack.push(now);

        }
        return result;
    }


    public static TreeNode recoverFromPreorder(String S) {
        int deep = 0;
        TreeNode result = null;
        Integer value = null;
        Stack<TreeNode> stack = new Stack<>();
        int cursor = 0;
        int len = S.length();
        for (int i = 0; i < len + 1; i++) {
            // 遇到最后值或者 '-', 都应进入深度优先讨论
            if (i == len || '-' == S.charAt(i)) {
                if (value != null) {
                    TreeNode now = new TreeNode(value);
                    if (deep > 0) {
                        if (deep > cursor) {
                            // 如果游标小于深度, 说明需要添加为当前节点到游标节点的左节点
                            stack.peek().left = now;
                        } else if (cursor == deep) {
                            // 如果左子树结束, 游标还等于深度, 说明哥哥节点已经不需要了
                            stack.pop();
                            stack.peek().right = now;
                        } else {
                            // 如果游标大于深度, 说明左子树完成, 需要回溯到新深度的父节点
                            while (cursor != deep - 1) {
                                stack.pop();
                                cursor--;
                            }
                            stack.peek().right = now;
                        }
                    } else {
                        result = now;
                    }
                    // 每步操作都将结果入栈
                    stack.push(now);
                    // 设置游标/重置值缓存/重置深度
                    cursor = deep;
                    value = null;
                    deep = 0;
                }
                deep++;
                continue;
            }
            if (value == null) {
                value = 0;
            }
            value = value * 10 + (S.charAt(i) - '0');
        }
        return result;
    }

    public static TreeNode recoverFromPreorder3(String S) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length(); ) {
            //查看在第几层，从0开始的，根节点是第0层
            int level = 0;
            while (S.charAt(i) == '-') {
                level++;
                i++;
            }

            //查看当前数字
            int val = 0;
            while (i < S.length() && S.charAt(i) != '-') {
                val = val * 10 + (S.charAt(i) - '0');
                i++;
            }

            //找到新结点的父节点
            while (stack.size() > level) {
                stack.pop();
            }
            //创建结点
            TreeNode node = new TreeNode(val);
            if (!stack.isEmpty()) {
                //如果节点只有一个子节点，那么保证该子节点为左子节点。
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            //入栈
            stack.add(node);
        }
        //除了根节点，其他子节点全部出栈
        while (stack.size() > 1) {
            stack.pop();
        }
        //返回根节点
        return stack.pop();
    }

    static int cur = 0;
    static int curD = 0;

    public static TreeNode recoverFromPreorder4(String S) {
        char[] nodes = S.toCharArray();
        return dfs(0, nodes);
    }

    public static TreeNode dfs(int depth, char[] nodes) {
        // 计算当前的结点值
        int val = 0;
        while (cur < nodes.length && nodes[cur] != '-') {
            val = val * 10 + nodes[cur] - '0';
            cur++;
        }
        // 计算深度;
        curD = 0;
        while (cur < nodes.length && nodes[cur] == '-') {
            cur++;
            curD++;
        }
        // 当前结点;
        TreeNode r = new TreeNode(val);
        if (curD > depth) {
            r.left = dfs(curD, nodes);

        }
        if (curD > depth) {
            r.right = dfs(curD, nodes);
        }
        return r;
    }
}
