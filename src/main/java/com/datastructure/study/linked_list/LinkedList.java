package com.datastructure.study.linked_list;

//public class LinkedList {
//
//    private Node head;
//
//    public LinkedList() {
//        this.head = new Node();
//    }
//
//    @Getter
//    static class Node {
//        private int data;
//        private Node next;
//
//        public Node() {
//        }
//
//        public Node(int data) {
//            this.data = data;
//        }
//    }
//
//    public void append(int data) {
//        Node tail = new Node(data);
//        Node n = this.head;
//
//        while(n.next != null) {
//            n = n.next;
//        }
//        n.next = tail;
//    }
//
//    public void delete(int data) {
//        Node n = this.head;
//
//        while(n.next != null) {
//            if (n.next.data == data) {
//                n.next = n.next.next;
//            } else {
//                n = n.next;
//            }
//        }
//    }
//
//    public void print() {
//        Node n = this.head.next;
//        while (n.next != null) {
//            System.out.print(n.data + " -> ");
//            n = n.next;
//        }
//        System.out.println(n.data);
//    }
//
//    void deleteDuplicated() {
//        Node n = this.head;
//
//        while (n != null && n.next != null) {
//            Node runner = n;
//            while (runner.next != null) {
//                if (n.data == runner.next.data) {
//                    runner.next = n.next.next;
//                } else {
//                    runner = runner.next;
//                }
//            }
//            n = n.next;
//        }
//    }
//
//    public Node firstNode() {
//        return this.head.next;
//    }
//
//    public Node kToLast(Node first, int k) {
//        Node n = first;
//        int total = 1;
//
//        while (n.next != null) {
//            total++;
//            n = n.next;
//        }
//
//        n = first;
//        int len = total -k + 1;
//        for (int i = 1; i < len; i++) {
//            n = n.next;
//        }
//        return n;
//    }
//}

public class LinkedList {

    private Node head;

    public LinkedList() {
        this.head = new Node();
    }

    /**
     * 마지막에 새로운 노드를 추가합니다.
     */
    public void append(int data) {
        Node first = this.head;
        Node tail = new Node(data);

        while (first.getNext() != null) {
            first = first.getNext();
        }
        first.setNext(tail);
    }

    /**
     * 특정 인덱스에 새로운 노드 추가
     */
    public void append(int index, int data) {
        Node node = this.head.getNext();
        Node newNode = new Node(data);
        if (index == 0) {
            this.head.setNext(newNode);
            newNode.setNext(node);
        } else {
            for (int i = 0; i < index -1; i++) {
                node = node.getNext();
            }
            newNode.setNext(node.getNext());
            node.setNext(newNode);
        }
    }

    /**
     * 특정 인덱스의 노드 삭제
     */
    public void remove(int index) {
        Node node = this.head.getNext();

        if (0 == index) {
            this.head.setNext(node.getNext());
        } else {
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            node.setNext(node.getNext().getNext());
        }
    }

    /**
     * 특정 노드 삭제
     */
    public void removeNode(Node node) {
        if (node == null || node.getNext() == null) {
            return;
        }
        Node next = node.getNext();
        node.setData(next.getData());
        node.setNext(next.getNext());
    }

    /**
     * 특정 인덱스의 Node 반환
     */
    public Node get(int index) {
        Node first = this.head.getNext();
        for (int i = 1; i <= index; i++) {
            first = first.getNext();
        }
        return first;
    }

    /**
     * 특정 값을 기준으로 나누기
     */
    public Node partition(int x) {
        Node n = this.head.getNext();
        Node head = n;
        Node tail = n;

        while (n != null) {
            Node next = n.getNext();
            if (n.getData() < x) {
                n.setNext(head);
                head = n;
            } else {
                tail.setNext(n);
                tail = n;
            }
            n = next;
        }
        tail.setNext(null); // 마지막임을 표시하기 위해
        return head;
    }

    /**
     * 현재 모든 노드 출력
     */
    public void print() {
        Node first = this.head.getNext();

        while (first.getNext() != null) {
            System.out.print(first.getData() + " -> ");
            first = first.getNext();
        }
        System.out.println(first.getData());
    }
}