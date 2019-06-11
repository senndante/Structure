package Lesson_4;

public class Element {

    private final int value;
    private Element nextElement;

    public Element(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Element getNextElement() {
        return nextElement;
    }

    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}
