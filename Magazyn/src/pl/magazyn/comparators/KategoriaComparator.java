package pl.magazyn.comparators;

import java.util.Comparator;

import pl.magazyn.controller.KategoriaController;
import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Towar;

public class KategoriaComparator implements Comparator<Towar> {

    private boolean reverseOrder;

    private KategoriaController kc;

    public KategoriaComparator(boolean reverseOrder) throws DbException {
        this.reverseOrder = reverseOrder;
        kc = new KategoriaController();
    }

    @Override
    public int compare(Towar o1, Towar o2) {
        try {
            String o1KategoriaName = kc.getKategoriaName(o1.getKategoria()).toLowerCase();
            String o2KategoriaName = kc.getKategoriaName(o2.getKategoria()).toLowerCase();
            int compareTo = o1KategoriaName.compareTo(o2KategoriaName);
            if (reverseOrder) {
                compareTo *= -1;
            }
            return compareTo;
        }
        catch (DbException e) {
            return 0;
        }
    }
}
