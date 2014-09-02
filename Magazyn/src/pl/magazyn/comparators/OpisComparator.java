package pl.magazyn.comparators;

import java.util.Comparator;

import pl.magazyn.model.objects.Towar;

public class OpisComparator implements Comparator<Towar> {

    private boolean reverseOrder;

    public OpisComparator(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    @Override
    public int compare(Towar o1, Towar o2) {
        int compareTo = o1.getOpis().toLowerCase().compareTo(o2.getOpis().toLowerCase());
        if (reverseOrder) {
            compareTo *= -1;
        }
        return compareTo;
    }

}
