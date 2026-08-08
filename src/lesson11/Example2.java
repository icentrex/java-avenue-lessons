package lesson11;

public class Example2 {
    public static void main(String[] args) {
        NumberHolder a = new NumberHolder(5);
        NumberHolder b = new NumberHolder(10);

        System.out.println("До метода: a = " + a.getValue() + ", b = " + b.getValue());
        swapNumbers(a, b);
        System.out.println("После метода: a = " + a.getValue() + ", b = " + b.getValue());
    }

    public static void swapNumbers(NumberHolder a, NumberHolder b) {
        int temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);

        System.out.println("В методе: a = " + a.getValue() + ", b = " + b.getValue());
    }
}
