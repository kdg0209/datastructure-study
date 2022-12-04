package com.datastructure.study.list.array_list;

import java.util.Arrays;

public class ArrayList<E> implements List<E>{

    private static final int DEFAULT_CAPACITY = 10; // 기본 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열
    private int size;                               // 데이터의 개수
    private Object[] elements;                      // 데이터를 담을 배열

    public ArrayList() {
        this.elements = EMPTY_ARRAY;
    }

    public ArrayList(int capacity) {
        this.elements = new Object[capacity];
    }

    private void addFirst(E value) {
        add(0, value);
    }

    private void addLast(E value) {
        if (this.size == this.elements.length) {
            resize();
        }
        this.elements[size] = value;
        size++;
    }

    @Override
    public void add(E value) {
        addLast(value);
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.size) {
            addLast(value);
        } else {
            for (int i = size; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = null;
        for (int i = index; i < size -1; i++) {
            elements[i] = elements[i + 1];
            elements[i + 1] = null;
        }
        size--;
    }

    @Override
    public void remove(E value) {
        int removeIndex = indexOf(value);
        remove(removeIndex);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    @Override
    public void set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        this.elements[index] = value;
    }

    @Override
    public boolean contains(E value) {
       return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        resize();
    }

    private void resize() {
        int arrayLength = this.elements.length;

        if (arrayLength == 0) {
            elements = new Object[DEFAULT_CAPACITY];
            return;
        }

        if (this.size == arrayLength) {
            int newCapacity = arrayLength * 2;
            elements = Arrays.copyOf(elements, newCapacity);
            return;
        }

        if (this.size < (arrayLength / 2)) {
            int newCapacity = arrayLength / 2;
            elements = Arrays.copyOf(elements, Math.max(newCapacity, DEFAULT_CAPACITY));
            return;
        }
    }

    public void print() {
        for (int i = 0; i < size -1; i++) {
            System.out.print(elements[i] + " -> ");
        }
        System.out.println(elements[size -1]);
    }
}
