package Lesson_4;

import java.util.Iterator;

public class LinkedListImpImpl implements LinkedList {

    protected Element leftElement;
    protected int size;

    void setSize(int size) {
        this.size = size;
    }

    @Override

    public Element getLeftElement() {
        return leftElement;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new LinkedListIterator(this);
    }

    LinkedListImpImpl() {
        this.size = 0;
    }

    @Override
    public int removeLeft() {
        if (leftElement == null) {
            throw new IndexOutOfBoundsException("Попытка удаления из пустого списка");
        }
        int value = leftElement.getValue();
        Element nextElement = leftElement.getNextElement();
        leftElement.setNextElement(null);
        leftElement = nextElement;
        size--;
        return value;
    }

    @Override
    public void insertLeft(int value) {
        Element newElement = new Element(value);
        if (isEmpty()) {
            leftElement = newElement;
        } else {
            newElement.setNextElement(leftElement);
            leftElement = newElement;
        }
        size++;
    }

    @Override
    public boolean find(int value) {
        Element curerntElement = leftElement;
        while (curerntElement != null) {
            if (curerntElement.getValue() == value) {
                return true;
            }
            curerntElement = curerntElement.getNextElement();
        }
        return false;
    }

    @Override
    public boolean remove(int value) {
        Element currentElement = leftElement;
        Element previousElement = null;
        Element nextElement;
        while (currentElement != null) {
            if (currentElement.getValue() == value) {
                break;
            }
            previousElement = currentElement;
            currentElement = currentElement.getNextElement();
        }
        if (currentElement == null) return false;
        if (currentElement == leftElement) {
            leftElement = currentElement.getNextElement();
        } else {
            nextElement = currentElement.getNextElement();
            previousElement.setNextElement(nextElement);
            size--;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {

        return leftElement == null;
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public  void display() {
        Element currentElement = leftElement;
        while (currentElement != null) {
            System.out.println(currentElement);
            currentElement = currentElement.getNextElement();
        }
    }

    @Override
    public Integer getLeft() {
        return leftElement == null ? null : leftElement.getValue();
    }
}