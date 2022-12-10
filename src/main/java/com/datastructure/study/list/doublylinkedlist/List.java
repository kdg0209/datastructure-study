package com.datastructure.study.list.doublylinkedlist;

public interface List<E> {

    /**
     *  리스트의 마지막에 데이터를 추가합니다.
     */
    void add(E value);

    /**
     * 특정 인덱스에 데이터를 추가합니다.
     */
    void add(int index, E value);

    /**
     * 특정 인덱스의 위치에 있는 데이터를 삭제합니다.
     */
    void remove(int index);

    /**
     * 특정 요소를 삭제합니다.
     * 만약 동일한 데이터가 있는 경우 첫번째로 발견한 데이터를 삭제합니다.
     */
    void remove(E value);

    /**
     * 특정 인덱스에 있는 데이터를 반환합니다.
     */
    E get(int index);

    /**
     * 특정 위치에 특정 데이터로 대체합니다.
     */
    void set(int index, E value);

    /**
     * 특정 데이터가 있는지 확인합니다.
     */
    boolean contains(E value);

    /**
     * 특정 데이터가 몇번째에 위치하여 있는지 반환합니다.
     * 일치하는 요소가 없다면 -1을 반환합니다.
     */
    int indexOf(E value);

    /**
     * 리스트의 데이터 개수를 반환합니다.
     */
    int size();

    /**
     * 리스트가 비어있는지 확인합니다.
     */
    boolean isEmpty();

    /**
     * 리스트의 모든 데이터를 삭제합니다.
     */
    void clear();
}
