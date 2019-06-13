package Lesson_4;

public class LinkIteratorApp {

    public static void main(String[] args) {

        LinkedList list = new LinkedListImpImpl();
        list.insertLeft(1);
        list.insertLeft(2);
        list.insertLeft(4);
        list.insertLeft(5);
        list.insertLeft(10);
        list.insertLeft(12);
        list.insertLeft(13);
        list.insertLeft(11);

        list.display();

        LinkedListIterator iterator = new LinkedListIterator(list);

        System.out.println("-------" + "\n" + "Testing iterator" + "\n");

        while (iterator.hasNext()) {
            iterator.next();
            // тестируем вставки элементов до и после на граничных случаях и в середине
            if (iterator.getCurrent() == 11 || iterator.getCurrent() == 1 || iterator.getCurrent() == 10) {
                iterator.insertBefore(100);
                iterator.insertAfter(100);
            }
        }
        list.display();
        //тестируем метод reset
        iterator.reset();

        while (iterator.hasNext()) {
            iterator.next();
            // тестируем метод atEnd
            System.out.println(iterator.atEnd());
        }

        iterator.reset();

        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.getCurrent() == 13 || iterator.getCurrent() == 12 || iterator.getCurrent() == 5) {
                iterator.deleteCurrent();
            }
        }
        list.display();
    }
}