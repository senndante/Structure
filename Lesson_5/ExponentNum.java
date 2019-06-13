package Lesson_5;

public class ExponentNum {
    public static void main(String[] args) {

        // Возведения в степень
        System.out.println("Возведение в степень");
        System.out.println(" 2 ^ 3 = " + exponentiation(2, 3));
        System.out.println(" 2 ^ 0 = " + exponentiation(2,0));
        System.out.println(" 5 ^ 2 = " + exponentiation(5, 2));
        System.out.println("-5 ^ 3 = " + exponentiation(-5,3));
        System.out.println("-4 ^ 2 = " + exponentiation(-4, 2));
        System.out.println(" 6 ^ -1 = " + exponentiation(6, -1));
        System.out.println(" 3 ^ -3 = " + exponentiation(3, -3));

        // Задача о рюкзаке
        Item[] arrOfItems = {new Item(632, 7), new Item(357, 5), new Item(296, 2)};
        Bagpack bagpack = new Bagpack(arrOfItems);
        int bagpackCapacity = 15; // вместимость рюкзака
        System.out.println("Задача о рюкзаке");
        System.out.println(bagpack.findBestSum(arrOfItems.length - 1, bagpackCapacity));

    }

    private static double exponentiation(double value, int power) {
        if (value == 0 && power == 0) {
            throw new ArithmeticException("Неопределенность");
        }
        if (power < 0) {
            if (power == -1) return 1 / value;
            return 1 / value * exponentiation(value, ++power);
        } else if (power == 0) {
            return 1;
        } else if (power == 1) {
            return value;
        } else {
            return value * exponentiation(value, --power);
        }
    }
}

