package com.helltab.labuladong;

import lombok.Data;

import java.util.LinkedList;

/**
 * Topic 链表数据结构
 * @author helltab
 * @version 1.0
 * @date 2021/5/15 20:25
 */
@Data
public class SingleLinkedNode {
    public int value;
    public SingleLinkedNode next;

    public SingleLinkedNode(int value) {
        this.value = value;
    }
    public SingleLinkedNode() {
    }

    public boolean hasNext() {
        return next !=null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
