package Lesson_4;

public interface LinkedList extends Iterable<Integer> {

    int removeLeft();

    void insertLeft(int value);

    boolean isEmpty();

    int getSize();

    void display();

    Integer getLeft();

    boolean find(int value);

    boolean remove(int value);

    Element getLeftElement();

}