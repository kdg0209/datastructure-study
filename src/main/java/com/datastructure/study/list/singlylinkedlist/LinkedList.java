package com.datastructure.study.list.singlylinkedlist;

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
     * ???????????? ????????? ????????? ???????????????.
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
     * ?????? ???????????? ????????? ?????? ??????
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
     * ?????? ???????????? ?????? ??????
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
     * ?????? ?????? ??????
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
     * ?????? ???????????? Node ??????
     */
    public Node get(int index) {
        Node first = this.head.getNext();
        for (int i = 1; i <= index; i++) {
            first = first.getNext();
        }
        return first;
    }

    /**
     * ?????? ?????? ???????????? ?????????
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
        tail.setNext(null); // ??????????????? ???????????? ??????
        return head;
    }

    /**
     * ?????? ?????? ?????? ??????
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