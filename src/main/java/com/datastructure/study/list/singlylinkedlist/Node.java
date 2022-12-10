package com.datastructure.study.list.singlylinkedlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

    private int data;
    private Node next;

    public Node() {

    }

    public Node(int data) {
        this.data = data;
    }
}
