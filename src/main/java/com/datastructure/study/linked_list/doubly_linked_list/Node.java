package com.datastructure.study.linked_list.doubly_linked_list;

import lombok.ToString;

import java.lang.annotation.Target;

@ToString
public class Node<E> {

    E data;
    Node<E> next; // 다음 노드를 가리키는 변수
    Node<E> prev; // 이전 노드를 가리키는 변수

    public Node(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
