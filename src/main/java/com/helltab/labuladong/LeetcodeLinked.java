package com.helltab.labuladong;
/**
 * Topic 链表
 * @author helltab
 * @version 1.0
 * @date 2021/5/22 12:19
 */
public class LeetcodeLinked {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(1);
        l1.next.next.next.next = new ListNode(1);
        ListNode listNode = deleteDuplicates(l1);
        visit(listNode);
    }

    public static void visit(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
        System.out.println();
    }


    /**
     **  21. 合并两个有序链表
     **  将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode();
        ListNode result = temp;
        while (null != l1 && null != l2) {
            System.out.println(l1.val + ": " + l2.val);
            if (l1.val > l2.val) {
                temp.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                temp.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return result.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表。
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode result = head;
        while (null != head && null != head.next) {
            if(head.val == head.next.val) {
                head.next  = head.next.next;
            }else {
                head = head.next;
            }
        }
        return result;
    }

    /**
     * 141. 环形链表
     * 给定一个链表，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     * pingphung@163.com
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        return true;
    }
}
