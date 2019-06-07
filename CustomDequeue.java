package HW_3;

import java.util.NoSuchElementException;

public class CustomDequeue<Item> {
    private Object[] dequeue = new Object[2];
    private int size = 0;
    private int start = 0;
    private int end = 0;
    private int left = 0;
    private int right = 0;

    public int size() {return size;}

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = dequeue[(left + i) % dequeue.length];
        }
        dequeue = temp;
        start = dequeue.length - 1;
        end = size;
        left = 0;
        right = size - 1;
    }

    public void insertLeft(Item item) {
        if (size == dequeue.length)
            resize(size * 2);
        dequeue[start] = item;
        if (start == end) {
            end = cyclicToRight(end);
        }
        left = start;
        start = cyclicToLeft(start);
        size++;
    }

    public void insertRight(Item item) {
        if (size == dequeue.length)
            resize(size * 2);
        dequeue[end] = item;
        if (start == end) {
            start = cyclicToLeft(start);
        }
        right = end;
        end = cyclicToRight(end);
        size++;
    }

    public Item removeLeft() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = (Item) dequeue[left];
        dequeue[left] = null;
        size--;
        start = left;
        left = cyclicToRight(left);
        if (size > 0 && size == dequeue.length / 4) {
            resize(dequeue.length / 2);
        }
        return item;
    }

    public Item removeRight() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = (Item) dequeue[right];
        dequeue[right] = null;
        size--;
        end = right;
        right = cyclicToLeft(right);
        if (size > 0 && size == dequeue.length / 4) {
            resize(dequeue.length / 2);
        }
        return item;
    }

    public Item peekLeft() {
        if (isEmpty())
            throw new NoSuchElementException();
        return (Item) dequeue[left];
    }

    public Item peekRight() {
        if (isEmpty())
            throw new NoSuchElementException();
        return (Item) dequeue[right];
    }

    private int cyclicToLeft(int position) {
        return (position - 1 + dequeue.length) % dequeue.length;
    }

    private int cyclicToRight(int position) {
        return (position + 1) % dequeue.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(dequeue[(left + i) % dequeue.length]);
            sb.append(", ");
        }
        return sb.toString();
    }
}