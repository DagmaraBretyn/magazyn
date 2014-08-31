package pl.magazyn.model;

import java.sql.Connection;
import java.util.List;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Kategoria;
import pl.magazyn.model.objects.Notatka;
import pl.magazyn.model.objects.Towar;

public class DbHandler {

    public static final String DB_NAME = "magazyn";

    private DbConnector connector = new DbConnector();

    private DbKategoriaHandler kategoriaHandler;

    private DbNotatkaHandler notatkaHandler;

    private DbTowarHandler towarHandler;

    public DbHandler() throws DbException {
        initDB(connector);
    }

    /**
     * for unit testing
     */
    public DbHandler(DbConnector connector) throws DbException {
        initDB(connector);
    }

    private void initDB(DbConnector connector) throws DbException {
        Connection connection = connector.getConnection();

        kategoriaHandler = new DbKategoriaHandler(connection);
        notatkaHandler = new DbNotatkaHandler(connection);
        towarHandler = new DbTowarHandler(connection);
    }

    /*
     * KATEGORIE
     */

    public void addKategoria(Kategoria kategoria) throws DbException {
        kategoriaHandler.addKategoria(kategoria);
    }

    public void editKategoria(Kategoria kategoria) throws DbException {
        kategoriaHandler.editKategoria(kategoria);
    }

    public void removeKategoria(Kategoria kategoria) throws DbException {
        kategoriaHandler.removeKategoria(kategoria);
    }

    public List<Kategoria> getKategorie() throws DbException {
        return kategoriaHandler.getKategorie();
    }

    public Kategoria getKategoria(int id) throws DbException {
        return kategoriaHandler.getKategoria(id);
    }

    /*
     * NOTATKI
     */

    public void addNotatka(Notatka notatka) throws DbException {
        notatkaHandler.addNatatka(notatka);
    }

    public void editNotatka(Notatka notatka) throws DbException {
        notatkaHandler.editNotatka(notatka);
    }

    public void removeNotatka(Notatka notatka) throws DbException {
        notatkaHandler.removeNotatka(notatka);
    }

    public List<Notatka> getNotatki() throws DbException {
        return notatkaHandler.getNotatki();
    }

    public Notatka getNotatka(int id) throws DbException {
        return notatkaHandler.getNotatka(id);
    }

    /*
     * TOWARY
     */

    public void addTowar(Towar towar) throws DbException {
        towarHandler.addTowar(towar);
    }

    public void editTowar(Towar towar) throws DbException {
        towarHandler.editTowar(towar);
    }

    public void removeTowar(Towar towar) throws DbException {
        towarHandler.removeTowar(towar);
    }

    public List<Towar> getTowar() throws DbException {
        return towarHandler.getTowar();
    }

    public Towar getTowar(int id) throws DbException {
        return towarHandler.getTowar(id);
    }
}
