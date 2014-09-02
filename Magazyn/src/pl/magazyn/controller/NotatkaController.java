package pl.magazyn.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.DbHandler;
import pl.magazyn.model.objects.Notatka;

@ManagedBean(name = "notatka", eager = true)
@RequestScoped
public class NotatkaController {

    private DbHandler db;

    private List<Notatka> notatki;

    private Notatka notatka;

    private String nazwa;

    private String opis;

    public NotatkaController() throws DbException {
        db = new DbHandler();

        notatki = db.getNotatki();

        if (notatki.isEmpty()) {
            notatka = new Notatka(1, "a", "b");
        }
        else {
            notatka = notatki.get(0);
        }

        nazwa = notatka.getNazwa();
        opis = notatka.getOpis();
    }

    public String save() throws DbException {
        notatka.setNazwa(nazwa);
        notatka.setOpis(opis);

        if (notatki.isEmpty()) {
            db.addNotatka(notatka);
        }
        else {
            db.editNotatka(notatka);
        }

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

}
