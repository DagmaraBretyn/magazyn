package pl.magazyn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import pl.magazyn.model.objects.Kategoria;

@ManagedBean(name = "kategoria", eager = true)
@RequestScoped
public class KategoriaController {

    private List<Kategoria> kategorie = new ArrayList<Kategoria>(Arrays.asList(new Kategoria(1, "k1"), new Kategoria(2,
        "k2"), new Kategoria(3, "k3")));

    public List<Kategoria> getKategorie() {
        return kategorie;
    }

    public void setKategorie(List<Kategoria> kategorie) {
        this.kategorie = kategorie;
    }

}
