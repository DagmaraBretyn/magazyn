package pl.magazyn.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.DbHandler;
import pl.magazyn.model.objects.Kategoria;

@ManagedBean(name = "kategoria", eager = true)
@RequestScoped
public class KategoriaController {

    private DbHandler db;

    public KategoriaController() throws DbException {
        db = new DbHandler();
    }

    public List<Kategoria> getKategorie() throws DbException {
        return db.getKategorie();
    }

    public String getKategoriaName(int id) throws DbException {
        return db.getKategoria(id).getNazwa();
    }

}
