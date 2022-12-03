package com.datastructure.study.linked_list.singly_linked_list;

public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        System.out.println();
        System.out.println("=====> 마지막에 새로운 노드 추가 <=====");
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(5);
        linkedList.print();

        System.out.println();
        System.out.println("=====> 특정 인덱스에 새로운 노드 추가 <=====");
        linkedList.append(1, 10);
        linkedList.print();

        System.out.println();
        System.out.println("=====> 특정 인덱스의 노드 삭제 <=====");
        linkedList.remove(0);
        linkedList.print();

        System.out.println();
        System.out.println("=====> 특정 노드 삭제 <=====");
        linkedList.removeNode(linkedList.get(1));
        linkedList.print();

        System.out.println();
        System.out.println("=====> 특정 값을 기준으로 나누기 <=====");
        Node n = linkedList.partition(5);
        while (n.getNext() != null) {
            System.out.print(n.getData() + " -> ");
            n = n.getNext();
        }
        System.out.println(n.getData());
    }
}
