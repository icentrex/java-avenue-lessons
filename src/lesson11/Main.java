package lesson11;

public class Main {
    public static void main(String[] args) {
        //Почему при смене чисел если они Integer они не меняются местами как если они были примитивными типами.
        // А вот если это отдельный класс, то изменения происходят
        //Ответ:
        // У Integer внутри поле value объявлено как private final int value.
        // Оно устанавливается один раз в конструкторе — и всё, больше никак.
        //  Ни сеттера, ни другого способа изменить это поле из публичного API нет.

        //1) Создать переменную ссылочного типа целого числа
        //2) Записать ее значение в переменную примитивного типа целого числа
        //3) Записать полученное значение в переменную строкового типа и вывести в консоль

        Integer intNumber = 33;
        int primitiveNumber = intNumber;
        String str = String.valueOf(primitiveNumber);

        //1) Создать переменную примитивного типа для НЕ целых чисел
        //2) Записать ее значение в переменную ссылочного типа НЕ целого числа
        //3) Создать еще одну переменную ссылочного типа с другим значением
        //4) Сравнить и вывести в консоль больше ли переменная 1, чем переменная 2.

        double primitiveDNumber = 34.44;
        Double dNumber1 = primitiveDNumber;
        Double dNumber2 = 3453.345;

        if (dNumber1.compareTo(dNumber2) < 0) {
            System.out.println(dNumber1 + " меньше " + dNumber2);
        } else if (dNumber1.compareTo(dNumber2) > 0) {
            System.out.println(dNumber1 + " больше " + dNumber2);
        } else {
            System.out.println(dNumber1 + " равно " + dNumber2);
        }
    }
}
