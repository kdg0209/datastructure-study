package com.datastructure.study.set.linkedhashset;

public class Main {

    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("TEST 1");
        linkedHashSet.add("TEST 2");
        linkedHashSet.add("TEST 3");
        linkedHashSet.add("TEST 4");
        linkedHashSet.add("TEST 5");
        linkedHashSet.add("0-42L");
        linkedHashSet.add("0-43-");  // 해시 충돌 발생 0-42L와 해시 같음
        linkedHashSet.print();

        linkedHashSet.clear();
        linkedHashSet.print();
    }
}
