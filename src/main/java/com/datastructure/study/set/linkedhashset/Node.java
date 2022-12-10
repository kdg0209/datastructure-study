package com.datastructure.study.set.linkedhashset;


public class Node<E> {

    final int hash;   // 해시값
    final E key;      // 데이터
    Node<E> prevLink; // 이전 노드의 링크
    Node<E> nextLink; // 다음 노드의 링크

    public Node(int hash, E key, Node<E> nextLink) {
        this.hash = hash;
        this.key = key;
        this.prevLink = null;
        this.nextLink = nextLink;
    }
}
