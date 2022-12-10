package com.datastructure.study.set.hashset;

public interface Set<E> {

    /**
     * 데이터가 Set에 없는 경우 데이터를 추가합니다.
     */
    void add(E element);

    /**
     * 특정 데이터를 Set에서 삭제합니다.
     */
    void remove(E element);

    /**
     * 특정 데이터가 포함되어 있는지 확인합니다.
     */
    boolean contains(E element);

    /**
     * 특정 데이터가 현재 집합과 같은지 여부를 확인합니다.
     */
    boolean equals(Object o);

    /**
     * 현재 Set의 모든 데이터를 삭제합니다.
     */
    void clear();
}
