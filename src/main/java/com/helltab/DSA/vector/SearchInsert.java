package com.helltab.DSA.vector;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchInsert {
    public static void main(String[] args) {
        List<Character> list = new ArrayList();
        for (int i = 0; i < 18; i++) {
            list.add((char) (65 + i));
        }
        System.out.println(list);
        MyVector.arraycopy(list, 2, list, 4, 1);
        System.out.println(list);
    }

    @Data
    public static class MyVector<E> implements Iterator<E> {
        int capacity;
        Object[] array;

        public MyVector() {
            this.array = new Object[32];
        }

        public MyVector(int capacity) {
            this.capacity = capacity;
        }

        public MyVector<E> extend() {
            MyVector<E> old = this;
            MyVector<E> newVector = new MyVector<>(capacity << 1);
            return this;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        public static native void arraycopy(Object src, int srcPos,
                                            Object dest, int destPos,
                                            int length);
    }
}
