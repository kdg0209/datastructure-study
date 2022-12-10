package com.datastructure.study.set.linkedhashset;

public class LinkedHashSet<E> implements Set<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;       // 노드의 총 개수
    private Node<E>[] tables; // 노드를 저장할 배열
    private Node<E> head;   // 노드의 가장 첫 부분
    private Node<E> tail;   // 노드의 가장 마지막 부분

    public LinkedHashSet() {
        this.tables = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    private static final int hash(Object key) {
        int hash;
        if (key == null) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    @Override
    public void add(E element) {
        add(hash(element), element);
    }

    private void appendToLastLink(Node<E> o) {
        Node<E> last = this.tail;
        this.tail = o;
        if (last == null) {
            this.head = o;
        } else {
            last.nextLink = o;
            o.prevLink = last;
        }
    }

    private void add(int hash, E element) {
        int index = hash % this.tables.length;
        Node<E> newNode = new Node<>(hash, element, null);

        // 해시 충돌이 발생하지 않은 경우
        if (this.tables[index] == null) {
            this.tables[index] = newNode;
        } else {
            // 해시 충돌이 발생한 경우
            Node<E> conflictNode = this.tables[index];
            Node<E> prev = null;

            while (conflictNode != null) {
                // 중복되는 요소라면 return
                if ((conflictNode.hash == hash) && (conflictNode.key == element) && (conflictNode.key.equals(element))) {
                    return;
                }
                prev = conflictNode;                  // 이전 노드 기억
                conflictNode = conflictNode.nextLink; // 다음노드로 이동
            }
            prev.nextLink = newNode;
        }
        this.size++;
        appendToLastLink(newNode);

        if (size >= DEFAULT_LOAD_FACTOR * tables.length) {
            resize();
        }
    }

    private void unlinkNode(Node<E> removedNode) {
        Node<E> prevNode = removedNode.prevLink; // 삭제할 노드의 이전 노드
        Node<E> nextNode = removedNode.nextLink; // 삭제할 노드의 다음 노드

        // 이전 노드가 없다면 삭제할 노드의 다음 노드가 head가 됩니다.
        if (prevNode == null) {
            this.head = nextNode;
        } else {
        // 이전 노드가 존재한다면 이전 노드의 다음 노드에 삭제할 노드의 다음 노드를 연결시켜줍니다.
        // 삭제할 노드의 이전 노드의 참조를 명시적으로 끊어줍니다.
            prevNode.nextLink = nextNode;
            removedNode.prevLink = null;
        }

        // 다음 노드가 없다면 이전 노드가 tail이 됩니다.
        if (nextNode == null) {
            this.tail = prevNode;
        } else {
        // 다음 노드가 존재한다면 다음 노드의 이전 노드에 삭제할 노드의 이전 노드를 연결시켜줍니다.
        // 삭제할 노드의 다음 노드의 참조를 명시적으로 끊어줍니다.
            nextNode.prevLink = prevNode;
            removedNode.nextLink = null;
        }
    }

    @Override
    public void remove(E element) {
        int hash = hash(element);
        int index  = hash % tables.length;
        Node<E> node = tables[index];
        Node<E> prevNode = null;

        if (node == null) {
            return;
        }
        while (node != null) {
            // 삭제하고자하는 데이터를 찾았다면
            if ((node.hash == hash) && (node.key == element) && (node.key.equals(element))) {
                Node<E> removedNode = node; // 삭제할 노드

                // 삭제할 노드의 이전 노드가 없다면 삭제할 노드의 다음 노드가 tables의 index에 위치하게 됩니다.
                if (prevNode == null) {
                    tables[index] = removedNode.nextLink;
                } else {
                    // 이전 노드의 다음 노드에 삭제할 노드의 다음 노드를 연결시켜줍니다.
                    prevNode.nextLink = removedNode.nextLink;
                }
                unlinkNode(removedNode);
                removedNode = null;
                size--;
                break;
            }
            prevNode = node;
            node = node.nextLink;
        }
    }

    @Override
    public boolean contains(E element) {
        int index = hash(element) % tables.length;
        Node<E> node = tables[index];

        while (node != null) {
            if ((element == node.key) || (element != null && node.key.equals(element))) {
                return true;
            }
            node = node.nextLink;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < tables.length; i++) {
            Node<E> node = tables[i];
            Node<E> nextNode;
            while (node != null) {
                nextNode = node;
                node = null; // help gc
                node = nextNode.nextLink;
            }
            tables[i] = null;
        }

        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof LinkedHashSet)) return false;

        try {
            LinkedHashSet<E> oSet = (LinkedHashSet<E>) obj;
            if (oSet.size != size) return false;

            for (int i = 0; i < oSet.tables.length; i++) {
                Node<E> oTable = oSet.tables[i];
                while (oTable != null) {
                    if (!contains((E) oTable)) {
                        return false;
                    }
                    oTable = oTable.nextLink;
                }
            }
        } catch (ClassCastException e) {
            return false;
        }
        return true;
    }

    private void resize() {
        int newCapacity = this.tables.length * 2;
        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < tables.length; i++) {
            Node<E> node = tables[i];

            if (node == null)
            tables[i] = null; // help gc

            Node<E> nextNode;
            while (node != null) {
                int index = node.hash % newCapacity;

                // 해시 충돌이 발생한 경우
                if (newTable[index] != null) {
                    Node<E> tail = newTable[index];
                    while (tail != null) {
                        tail = tail.nextLink;
                    }
                    node.nextLink = null;
                    tail.nextLink = node;
                } else {

                    // 해시 충돌이 발생하지 않은 경우
                    node.nextLink = null;
                    newTable[index] = node;
                }
                nextNode = node.nextLink;
                node.nextLink = null;
                node = nextNode;
            }
        }
        this.tables = null;
        this.tables = newTable;
    }

    public void print() {
        Node<E> node = this.head;
        while (node != null) {
            System.out.print(node.key + " -> ");
            node = node.nextLink;
        }
    }
}
