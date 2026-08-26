package lesson17;

import java.util.Comparator;

public class SortByYearComparator implements Comparator<Coin> {
    @Override
    public int compare(Coin o1, Coin o2) {
        //Сначала сортируем по году по убыванию
        if (o1.getYear() != o2.getYear()) {
            return o2.getYear() - o1.getYear();
        }

        //Потом по металлу по убыванию
        if (!o1.getMetalName().equalsIgnoreCase(o2.getMetalName())) {
            return o2.getMetalName().compareTo(o1.getMetalName());
        }

        //Потом по диаметру по возрастанию
        if (o1.getDiameter() != o2.getDiameter()) {
            return Double.compare(o1.getDiameter(), o2.getDiameter());
        }

        //Потом по номиналу по убыванию
        return o2.getNominal() - o1.getNominal();
    }
}
