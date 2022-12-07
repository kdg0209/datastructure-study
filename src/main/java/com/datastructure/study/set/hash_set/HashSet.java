package com.datastructure.study.set.hash_set;

public class HashSet<E> implements Set<E> {

    private static final int DEFAULT_CAPACITY = 16;  // 최소 기본 용적
    private static final float LOAD_FACTOR = 0.75f;  // 3/4 이상 채워질 경우 resize하기 위한 변수
    private int size;                                // 데이터의 수
    private Node<E>[] tables;                          // 데이터의 정보를 담고 있는 배열

    @SuppressWarnings("unchecked")
    public HashSet() {
        this.tables = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        add(hash(element), element);
    }

    private void add(int hash, E key) {
        int index = hash % this.tables.length;

        if (tables[index] == null) {
            tables[index] = new Node<>(hash, key, null);
        } else {
            Node<E> conflictNode = tables[index];
            Node<E> prev = null;

            while (conflictNode != null) {

                // 동일한 데이터라면 넣지 않음
                if ((conflictNode.hash == hash) && (conflictNode.key == key) && (conflictNode.key.equals(key))) {
                    return;
                }
                prev = conflictNode;
                conflictNode = conflictNode.next;
            }
            prev.next = new Node<>(hash, key, null);
        }
        size++;

        // 데이터의 개수가 현재 용적의 75%를 넘어가는 경우 용적 재할당
        if (size >= LOAD_FACTOR * tables.length) {
            resize();
        }
    }

    @Override
    public void remove(E element) {
        remove(hash(element), element);
    }

    private void remove(int hash, E key) {
        int index = hash % this.tables.length;

        Node<E> node = tables[index];
        Node<E> prev = null;

        if (node == null) {
            return;
        }

        while (node != null) {
            if (node.hash == hash && (node.key == key && node.key.equals(key))) {
                if (prev == null) {
                    tables[index] = node.next;
                    node = null;
                } else {
                    prev.next = node.next;
                    node = null;
                }
                size--;
                break;
            }
            prev = node;
            node = node.next;
        }
    }

    @Override
    public boolean contains(E element) {
        int index = hash(element) % tables.length;
        Node<E> node = tables[index];

        while (node != null) {
            if (element == node.key || (element != null && (element.equals(node.key)))) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof HashSet)) {
            return false;
        }

        try {
            HashSet<E> set;
            set = (HashSet<E>) obj;
            if (set.size != size) {
                return false;
            }
            for (int i = 0; i < set.tables.length; i++) {
                Node<E> node = set.tables[i];
                while (node != null) {
                    if (!contains((E) node)) {
                        return false;
                    }
                    node = node.next;
                }
            }
        } catch (ClassCastException e) {
            return false;
        }
        return false;
    }

    @Override
    public void clear() {
        if (tables != null && size > 0) {
            for (int i = 0; i < tables.length; i++) {
                tables[i] = null;
            }
            size = 0;
        }
    }

    public void print() {
        for (int i = 0; i < tables.length; i++) {
            Node node = tables[i];
            while (node != null) {
                System.out.println(node.key);
                node = node.next;
            }
        }
    }

    private void resize() {
        int newCapacity = this.tables.length * 2;
        final Node<E>[] newTables = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < tables.length; i++) {
            Node<E> value = tables[i];

            if (value == null) continue;

            tables[i] = null; // help gc

            Node<E> nextNode;
            while (value != null) {
                int index = value.hash % newCapacity; // 새로운 인덱스 생성
                nextNode = value.next;
                // 새로 담을 index에 데이터가 존재하는 경우
                // 즉 새로담을 newTables에 index의 값이 겹치는 경우(해시 충돌)
                if (newTables[index] != null) {
                    Node<E> tail = newTables[index];

                    // while문을 사용하여 가장 마지막 노드로 이동
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    tail.next = value;
                } else {
                    // 해시 충돌이 발생하지 않았다면
                    newTables[index] = value;
                }
                value.next = null;
                value = nextNode; // 다음 노드로 이동
            }
        }
        this.tables = null; // 명시적 gc
        this.tables = newTables;
    }

    static final int hash(Object key) {
        int hash;
        if (key == null) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }
}
