package com.datastructure.study.linked_list.doubly_linked_list;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.print();

        linkedList.clear();
        linkedList.print();
    }
}
