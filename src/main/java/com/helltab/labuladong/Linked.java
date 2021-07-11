package com.helltab.labuladong;

import lombok.ToString;
import lombok.extern.java.Log;

import static com.helltab.util.MyUtil.visitRecursion;

/**
 * Topic 链表相关
 *
 * @author helltab
 * @version 1.0
 * @date 2021/5/15 20:22
 */
public class Linked {
    public static void main(String[] args) {

//
        SingleLinkedNode head = genList();
//        long s = System.currentTimeMillis();
//        head = reverseLinkedList0(head);
//        System.out.println(System.currentTimeMillis() - s);
//        head = genList();
//        long s1 = System.currentTimeMillis();
//        head = reverseLinkedList1(head);
//        System.out.println(System.currentTimeMillis() - s1);
//        head = genList();
//        long s2 = System.currentTimeMillis();
//        head = reverseLinkedList3(head);
//        System.out.println();
////        visit(head);
//        System.out.println(System.currentTimeMillis() - s2);
        head = genList();
        head = reverseLinkedList1(head, 2, 4);
        System.out.println();
        visit(head);

        head = genList();
        head = reverseLinkedList3(head, 4);
        System.out.println();
        visit(head);

        head = genList();
        head = reverseLinkedList3(head, 2, 4);
        System.out.println();
        visit(head);
    }

    public static SingleLinkedNode genList() {
        SingleLinkedNode head0 = new SingleLinkedNode(0);
        SingleLinkedNode node = head0.next = new SingleLinkedNode(1);
        for (int i = 0; i < 1 << 3; i++) {
            SingleLinkedNode temp = new SingleLinkedNode(i + 2);
            node.next = temp;
            node = temp;
        }
        return head0;
    }

    public static void visit(SingleLinkedNode head) {
        while (head != null) {
            System.out.print(head + "\t");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 迭代翻转链表
     * 用 head 指向每两个一组的第一个节点
     * 用 pre 指向前一组的第二个节点, 初始化为 null
     * 最后 head 不为空, 则 head.nex = pre
     * head 为空, 则 head = pre
     */
    public static SingleLinkedNode reverseLinkedList0(SingleLinkedNode head) {
        SingleLinkedNode pre = null;
        while (head != null && head.hasNext()) {
            SingleLinkedNode temp1 = head.next;
            SingleLinkedNode temp2 = head.next.next;
            head.next.next = head;
            head.next = pre;
            pre = temp1;
            head = temp2;
        }
        if (head == null) {
            head = pre;
        } else {
            head.next = pre;
        }
        return head;
    }

    /**
     * 迭代翻转链表 2
     */
    public static SingleLinkedNode reverseLinkedList1(SingleLinkedNode head) {
        if (head == null) return null;
        SingleLinkedNode cur = head;
        while (head.hasNext()) {
            SingleLinkedNode temp = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = temp;
        }
        return cur;
    }

    /**
     * 迭代翻转链表 2
     */
    public static SingleLinkedNode reverseLinkedList1(SingleLinkedNode head, int m, int n) {
        if (head == null) return null;
        SingleLinkedNode pre = head;
        int i = 0;
        while (pre.hasNext() && i < m - 1) {
            pre = pre.next;
            i++;
        }
        SingleLinkedNode newHead = pre.next;
        SingleLinkedNode cur = newHead;
        while (newHead.hasNext() && n > m) {
            SingleLinkedNode temp = newHead.next.next;
            newHead.next.next = cur;
            cur = newHead.next;
            newHead.next = temp;
            n--;
        }
        pre.next = cur;
        return head;
    }

    /**
     * desc: 迭代实现会比较容易
     * 递归翻转链表
     */
    public static SingleLinkedNode reverseLinkedList3(SingleLinkedNode head) {
        visitRecursion(head, true);
        if (head.next == null) return head;
        SingleLinkedNode last = reverseLinkedList3(head.next);
        head.next.next = head;
        head.next = null;
        visitRecursion(head, false);
        return last;
    }

    static SingleLinkedNode successor = null;

    public static SingleLinkedNode reverseLinkedList3(SingleLinkedNode head, int n) {
        visitRecursion(head, true);
        if (n == 0) {
            successor = head.next;
            return head;
        }
        SingleLinkedNode last = reverseLinkedList3(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        visitRecursion(last, false);
        return last;
    }

    public static SingleLinkedNode reverseLinkedList3(SingleLinkedNode head, int m, int n) {
        if (m == 0) {
            return reverseLinkedList3(head, n);
        }
        head.next = reverseLinkedList3(head.next, m - 1, n - 1);
        return head;
    }

}
