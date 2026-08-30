package lesson17;

import java.util.Comparator;

public class SortByDiameterComparator implements Comparator<Coin> {
    @Override
    public int compare(Coin o1, Coin o2) {
        //По диаметру по убыванию
        if (o1.getDiameter() != o2.getDiameter()) {
            return Double.compare(o2.getDiameter(), o1.getDiameter());
        }

        //По году по возрастанию
        if (o1.getYear() != o2.getYear()) {
            return o1.getYear() - o2.getYear();
        }

        //По металлу по возрастанию
        if (!o1.getMetalName().equalsIgnoreCase(o2.getMetalName())) {
            return o1.getMetalName().compareTo(o2.getMetalName());
        }

        //По номиналу по возрастанию
        return o1.getNominal() - o2.getNominal();
    }
}
