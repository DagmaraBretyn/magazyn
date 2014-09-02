package pl.magazyn.comparators;

import java.util.Comparator;

import pl.magazyn.model.objects.Towar;

public class NazwaComparator implements Comparator<Towar> {

    private boolean reverseOrder;

    public NazwaComparator(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    @Override
    public int compare(Towar o1, Towar o2) {
        int compareTo = o1.getNazwa().toLowerCase().compareTo(o2.getNazwa().toLowerCase());
        if (reverseOrder) {
            compareTo *= -1;
        }
        return compareTo;
    }

}
