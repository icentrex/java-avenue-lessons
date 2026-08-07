package lesson11;

public class Example {
    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 10;
        System.out.println("До метода: a = " + a + ", b = " + b);
        swapNumbers(a, b);
        System.out.println("После метода: a = " + a + ", b = " + b);
    }

    public static void swapNumbers(Integer a, Integer b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("В методе: a = " + a + ", b = " + b);
    }
}
