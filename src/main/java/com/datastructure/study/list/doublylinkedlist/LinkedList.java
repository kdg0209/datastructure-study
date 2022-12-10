package com.datastructure.study.list.doublylinkedlist;

public class LinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);

        if (head != null) {
            head.prev = newNode;
        }
        if (tail == null) {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public void add(E value) {
        addLast(value);
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            addLast(value);
            return;
        }

        Node<E> prevNode = search(index -1);
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<>(value);

        prevNode.next = newNode;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        size++;
    }

    public void removeFirst() {
        Node<E> headNode = this.head;

        if (headNode == null) {
            throw new IllegalStateException();
        }

        Node<E> next = headNode.next;
        headNode.data = null;
        headNode.next = null;

        if (next != null) {
            next.prev = null;
        }
        head = next;
        size--;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        Node<E> prevNode = search(index -1); // 삭제할 노드의 이전 노드
        Node<E> removeNode = prevNode.next;        // 삭제할 노드
        Node<E> nextNode = removeNode.next;        // 삭제할 노드의 다음 노

        prevNode.prev = null;
        removeNode.data = null;
        removeNode.next = null;
        removeNode.prev = null;

        if (nextNode != null) {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        } else {
            tail = prevNode;
        }
        size--;
    }

    @Override
    public void remove(E value) {
        Node<E> headNode = this.head;
        Node<E> removeNode = null;
        while (headNode != null) {
            if (headNode.data.equals(value)) {
                removeNode = headNode;
                break;
            }
            headNode = headNode.next;
        }
        Node<E> prevNode = removeNode.prev; // 삭제할 노드의 이전 노드
        Node<E> nextNode = removeNode.next; // 삭제할 노드의 다음 노드

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }
        removeNode.data = null;
        removeNode.prev = null;
        removeNode.next = null;
        size--;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> findNode = search(index);
        findNode.data = value;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        Node<E> node = this.head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(value)) {
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }

    public int lastIndexOf(E value) {
        int index = 0;
        Node<E> node = this.head;
        for (int i = size; i > 0; i--) {
            if (node.data.equals(value)) {
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        Node<E> next = this.head;
        while (next.next != null) {
            Node<E> removeNode = next.next;
            removeNode.prev = null;
            removeNode.next = null;
            removeNode.data = null;
            next = next.next;
        }
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // 뒤에서부터 시작
        if (index > (size / 2)) {
            Node<E> n = tail;
            for (int i = size -1; i > index; i--) {
                n = n.prev;
            }
            return n;
        } else {
            // 앞에서부터 시작
            Node<E> n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n;
        }
    }

    public void print() {
        Node<E> next = head;

        while (next != null && next.next != null) {
            System.out.print(next.data + " -> ");
            next = next.next;
        }
        if (next != null) {
            System.out.println(next.data);
        }
    }
}
