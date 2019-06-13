package Lesson_4;

public class LinkedListStack implements Stack {

    LinkedList list;

    public LinkedListStack() {

        this.list = new LinkedListImpImpl();
    }

    @Override
    public int pop() {

        return list.removeLeft();
    }

    @Override
    public void push(int value) {

        list.insertLeft(value);
    }

    @Override
    public int peek() {

        return list.getLeft();
    }

    @Override
    public int getSize() {

        return list.getSize();
    }

    @Override
    public boolean isEmpty() {

        return list.isEmpty();
    }

    @Override
    public boolean isFull() {

        return false;
    }
}