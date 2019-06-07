package HW_3;

import java.util.EmptyStackException;

public class CustomStack<Item> {
    private Object[] stack = new Object[1];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        if (size >= 0) System.arraycopy(stack, 0, temp, 0, size);
        stack = temp;
    }

    public void push(Item item) {
        if(size == stack.length) {
            resize(stack.length * 2);
        }
        stack[size++] = item;
    }

    public Item pop() {
        if (isEmpty())
            throw new EmptyStackException();
        Item item = (Item) stack[size-1];
        stack[size-- - 1] = null;
        if (size > 0 && size == stack.length / 4)
            resize(stack.length / 2);
        return item;
    }

    public Item peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return (Item) stack[size-1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(stack[i]);
            sb.append(", ");
        }
        return sb.toString();
    }
}