package lesson17;

import java.util.Comparator;

public class SortByYearComparator implements Comparator<Coin> {
    @Override
    public int compare(Coin o1, Coin o2) {
        //По году по убыванию
        if (o1.getYear() != o2.getYear()) {
            return o2.getYear() - o1.getYear();
        }

        //По металлу по убыванию
        if (!o1.getMetalName().equalsIgnoreCase(o2.getMetalName())) {
            return o2.getMetalName().compareTo(o1.getMetalName());
        }

        //По диаметру по возрастанию
        if (o1.getDiameter() != o2.getDiameter()) {
            return Double.compare(o1.getDiameter(), o2.getDiameter());
        }

        //По номиналу по убыванию
        return o2.getNominal() - o1.getNominal();
    }
}
