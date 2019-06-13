package Lesson_4;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Integer> {

    private LinkedList list;
    private boolean firstIteration;
    private Element currentElement;
    private Element previousElement;

    LinkedListIterator(LinkedList list) {
        this.list = list;
        this.firstIteration = true;
        this.previousElement = null;
        this.reset();
    }

    @Override
    public boolean hasNext() {
        if (firstIteration) {
            firstIteration = false;
            return list.getLeft() != null;
        } else {
            return currentElement.getNextElement() != null;
        }
    }

    @Override
    public Integer next() {
        if (currentElement == null) {
            currentElement = list.getLeftElement();
        } else {
            previousElement = currentElement;
            currentElement = currentElement.getNextElement();
        }
        return currentElement.getValue();
    }

    @Override
    public void remove() {
        list.remove(currentElement.getValue());
    }

    public Integer getCurrent() { // текущий элемент в итераторе
        return currentElement.getValue();
    }

    public void reset() { // перемещение в начало списка
        if (list.isEmpty()) {
            throw new IllegalStateException("Список пустой");
        } else {
            firstIteration = true;
            currentElement = null;
            previousElement = null;
        }
    }

    public boolean atEnd() { // проверка на конец списка

        return !hasNext();
    }

    public int deleteCurrent() { // удаление текущего элемента
        int deleted = currentElement.getValue();
        list.remove(deleted);
        return deleted;
    }

    public void insertAfter(int value) { // вставка после текущего положения итератора
        Element newElement = new Element(value);
        if (list.isEmpty()) {
            list.insertLeft(value);
            currentElement = newElement;
        } else {
            newElement.setNextElement(currentElement.getNextElement());
            currentElement.setNextElement(newElement);
            next();
        }
    }

    public void insertBefore(int value) { // вставка до текущего положения итератора
        Element newElement = new Element(value);
        if (previousElement == null) {
            newElement.setNextElement(list.getLeftElement());
            list.insertLeft(newElement.getValue());
        } else {
            newElement.setNextElement(previousElement.getNextElement());
            previousElement.setNextElement(newElement);
        }
    }
}
