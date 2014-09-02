package pl.magazyn.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.magazyn.comparators.CenaComparator;
import pl.magazyn.comparators.IloscComparator;
import pl.magazyn.comparators.KategoriaComparator;
import pl.magazyn.comparators.NazwaComparator;
import pl.magazyn.comparators.OpisComparator;
import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.DbHandler;
import pl.magazyn.model.objects.Towar;

@ManagedBean(name = "towar", eager = true)
@SessionScoped
public class TowarController {

    private boolean refreshTowary = false;

    private DbHandler db;

    private String nazwa;

    private String opis;

    private double cena;

    private int ilosc;

    private int kategoria;

    private int kategoriaFilter;

    private String textFilter;

    private boolean towarAdded;

    private String towarAddedName;

    private Towar towar;

    private List<Towar> towary;

    private Comparator<Towar> comparator;

    public TowarController() throws DbException {
        db = new DbHandler();
    }

    public String add() throws DbException {
        Towar towar = new Towar(0, nazwa, opis, cena, ilosc, kategoria);
        db.addTowar(towar);
        towarAddedName = nazwa;
        towarAdded = true;

        nazwa = "";
        opis = "";
        cena = 0;
        ilosc = 0;
        refreshTowary = true;

        return null;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getKategoria() {
        return kategoria;
    }

    public void setKategoria(int kategoria) {
        this.kategoria = kategoria;
    }

    public int getKategoriaFilter() {
        return kategoriaFilter;
    }

    public void setKategoriaFilter(int kategoriaFilter) {
        this.kategoriaFilter = kategoriaFilter;
    }

    public String getTextFilter() {
        return textFilter;
    }

    public void setTextFilter(String textFilter) {
        this.textFilter = textFilter;
    }

    public boolean getTowarAdded() {
        return towarAdded;
    }

    public String getTowarAddedName() {
        return "Dodano towar " + towarAddedName + ".";
    }

    public List<Towar> getTowary() throws DbException {
        if (towary == null || refreshTowary) {
            towary = db.getTowary();
            refreshTowary = false;
        }
        if (comparator != null) {
            Collections.sort(towary, comparator);
        }
        return towary;
    }

    public String deleteTowar() throws DbException {
        if (towar != null) {
            towary.remove(towar);
            db.removeTowar(towar);
        }
        return null;
    }

    public String editTowar() throws DbException {
        if (towar != null) {
            towary.set(towary.indexOf(towar), towar);
            db.editTowar(towar);
            towar.setCanEdit(false);
        }
        return null;
    }

    public String enableEditTowar() {
        if (towar != null) {
            towar.setCanEdit(true);
        }
        return null;
    }

    public void setTowar(Towar towar) {
        this.towar = towar;
    }

    private boolean reverseOrderNazwa = false;

    public String setNazwaComparator() {
        if (comparator instanceof NazwaComparator) {
            reverseOrderNazwa = !reverseOrderNazwa;
        }
        comparator = new NazwaComparator(reverseOrderNazwa);
        return null;
    }

    private boolean reverseOrderOpis = false;

    public String setOpisComparator() {
        if (comparator instanceof OpisComparator) {
            reverseOrderOpis = !reverseOrderOpis;
        }
        comparator = new OpisComparator(reverseOrderOpis);
        return null;
    }

    private boolean reverseOrderCena = false;

    public String setCenaComparator() {
        if (comparator instanceof CenaComparator) {
            reverseOrderCena = !reverseOrderCena;
        }
        comparator = new CenaComparator(reverseOrderCena);
        return null;
    }

    private boolean reverseOrderIlosc = false;

    public String setIloscComparator() {
        if (comparator instanceof IloscComparator) {
            reverseOrderIlosc = !reverseOrderIlosc;
        }
        comparator = new IloscComparator(reverseOrderIlosc);
        return null;
    }

    private boolean reverseOrderKategoria = false;

    public String setKategoriaComparator() throws DbException {
        if (comparator instanceof KategoriaComparator) {
            reverseOrderKategoria = !reverseOrderKategoria;
        }
        comparator = new KategoriaComparator(reverseOrderKategoria);
        return null;
    }

    public String filterTowar() throws DbException {
        if (towary != null) {
            List<Towar> filteredList = new ArrayList<Towar>();
            for (Towar towar : db.getTowary()) {
                if (kategoriaFilter == 0 || towar.getKategoria() == kategoriaFilter) {
                    if (towar.getNazwa().toLowerCase().contains(textFilter.toLowerCase()) ||
                        towar.getOpis().toLowerCase().contains(textFilter.toLowerCase())) {
                        filteredList.add(towar);
                    }
                }

            }
            towary = filteredList;
        }
        return null;
    }
}
