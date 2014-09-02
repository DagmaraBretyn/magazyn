package pl.magazyn.comparators;

import java.util.Comparator;

import pl.magazyn.model.objects.Towar;

public class IloscComparator implements Comparator<Towar> {

    private boolean reverseOrder;

    public IloscComparator(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    @Override
    public int compare(Towar o1, Towar o2) {
        if (o1.getIlosc() == o2.getIlosc()) {
            return 0;
        }
        int compare = o1.getIlosc() > o2.getIlosc() ? 1 : -1;
        if (reverseOrder) {
            compare *= -1;
        }
        return compare;
    }
}
