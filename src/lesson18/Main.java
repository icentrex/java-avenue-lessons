package lesson18;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создадим мапу для хранения гейтов и соответствующих им рейсов");
        Map<Integer, String> gates = new HashMap<>();

        System.out.println("Добавим несколько записей");
        gates.put(7, "Москва - Ростов-на-дону");
        gates.put(3, "Москва - Ижевск");
        gates.put(1, "Москва - Стамбул");
        gates.put(6, "Москва - Коломбо");
        gates.put(5, "Москва - Белград");

        System.out.println("Пройдемся по каждой ячейке и выведем в консоль");

        System.out.println("Вариант 1");
        for (Map.Entry<Integer, String> entry : gates.entrySet()) {
            System.out.println("Посадка на рейс \"" + entry.getValue() + "\" происходит на выходе №" + entry.getKey());
        }

        System.out.println("Вариант 2");
        for (Integer key : gates.keySet()) {
            System.out.println("Посадка на рейс \"" + gates.get(key) + "\" происходит на выходе №" + key);
        }
    }
}
