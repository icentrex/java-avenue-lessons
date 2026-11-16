package lesson34.example;

public class Main {
    public static void main(String[] args) {
        try {
            calculate(12,0);
            System.out.println("Программа после");
        }
        catch (ArithmeticException e){
            System.out.println("Произошла ошибка" );
        }
        finally {
            System.out.println("Программа завершила работу");
        }

    }

    private static void calculate(int a, int b) throws ArithmeticException {
        System.out.println("Начало вычислений");
        int res = a / b;
        System.out.println(res);
        System.out.println("Конец вычислений");
    }

}