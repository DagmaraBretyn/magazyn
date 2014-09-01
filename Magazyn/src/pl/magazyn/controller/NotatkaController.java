package pl.magazyn.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Notatka;

@ManagedBean(name = "notatka", eager = true)
@RequestScoped
public class NotatkaController {

    private static Notatka notatka = new Notatka(1, "n1", "o1");

    public String save(String nazwa, String opis) throws DbException {
        notatka.setNazwa(nazwa);
        notatka.setOpis(opis);

        return "news";
    }

    public Notatka getNotatka() {
        return notatka;
    }
}
