package Lesson_4;

public interface Stack {

    int pop();

    void push(int value);

    int peek();

    int getSize();

    boolean isEmpty();

    boolean isFull();
}