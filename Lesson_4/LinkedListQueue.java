package Lesson_4;

public class LinkedListQueue implements Queue {

    private TwoWayLinkedList twoWayLinkedList;

    public LinkedListQueue() {
        this.twoWayLinkedList = new TwoWayLinkedListImpl();
    }

    @Override
    public int removeFirst() {
        return twoWayLinkedList.removeLeft();
    }

    @Override
    public void insertLast(int value) {
        twoWayLinkedList.insertRight(value);
    }

    @Override
    public int getSize() {
        return twoWayLinkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return twoWayLinkedList.isEmpty();
    }
}