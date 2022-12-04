package com.datastructure.study.list.array_list;

public interface List<E> {

    /**
     * 배열의 마지막에 요소를 추가합니다.
     */
    void add(E value);

    /**
     * 배열의 특정 인덱스에 요소를 추가합니다.
     */
    void add(int index, E value);

    /**
     * 배열의 특정 인덱스의 요소를 삭제합니다.
     */
    void remove(int index);

    /**
     * 배열의 특정 요소를 삭제합니다.
     */
    void remove(E value);

    /**
     * 배열의 특정 요소를 반환합니다.
     */
    E get(int index);

    /**
     * 배열의 특정 인덱스에 있는 요소를 찾아 요소를 변경합니다.
     */
    void set(int index, E value);

    /**
     * 배열의 특정 요소가 있는지 확인합니다.
     */
    boolean contains(E value);

    /**
     * 배열의 특정 요소가 위치한 인덱스를 반환합니다.
     */
    int indexOf(E value);

    /**
     * 배열의 개수를 반환합니다.
     */
    int size();

    /**
     * 배열이 비워있는지 확인합니다.
     */
    boolean isEmpty();

    /**
     * 배열의 모든 요소를 삭제합니다.
     */
    void clear();
}
