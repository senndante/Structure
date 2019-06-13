package Lesson_4;

public class TwoWayLinkedListImpl extends LinkedListImpImpl implements TwoWayLinkedList {

    private Element rightElement;

    @Override
    public void insertRight(int value) {
        Element newElement = new Element(value);
        if (isEmpty()) {
            rightElement = newElement;
        } else {
            rightElement.setNextElement(newElement);
        }
        rightElement = newElement;
        size++;
    }

    @Override
    public void insertLeft(int value) {
        Element newElement = new Element(value);
        if (isEmpty()) {
            rightElement = newElement;
        }
        newElement.setNextElement(leftElement);
        leftElement = newElement;
        size++;
    }

    @Override
    public int removeLeft() {
        int value = super.removeLeft();
        if (isEmpty()) {
            rightElement = null;
        }
        return value;
    }

    @Override
    public Integer getRight() {
        return rightElement != null ? rightElement.getValue() : null;
    }
}