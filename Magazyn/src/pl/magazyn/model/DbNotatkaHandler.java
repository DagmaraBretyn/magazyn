package pl.magazyn.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Notatka;

class DbNotatkaHandler {

    private static final String TABLE_NAME = "notatka";

    private static final String FIELD_ID = "id";

    private static final String FIELD_NAZWA = "nazwa";

    private static final String FIELD_OPIS = "opis";

    private Connection connection;

    public DbNotatkaHandler(Connection connection) {
        this.connection = connection;
    }

    public void addNatatka(Notatka obiekt) throws DbException {
        String sql = String.format("INSERT INTO %s.%s(%s,%s) VALUES ('%s','%s');", DbHandler.DB_NAME, TABLE_NAME,
            FIELD_NAZWA, FIELD_OPIS, obiekt.getNazwa(), obiekt.getOpis());

        try {
            connection.createStatement().executeUpdate(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z dodaniem nowej notatki. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public void editNotatka(Notatka notatka) throws DbException {
        String sql = String.format("UPDATE %s.%s SET %s='%s', %s='%s' WHERE %s=%d;", DbHandler.DB_NAME, TABLE_NAME,
            FIELD_NAZWA, notatka.getNazwa(), FIELD_OPIS, notatka.getOpis(), FIELD_ID, notatka.getId());

        try {
            connection.createStatement().executeUpdate(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z edycjπ notatki. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public void removeNotatka(Notatka notatka) throws DbException {
        String sql = String.format("DELETE FROM %s.%s WHERE %s=%d;", DbHandler.DB_NAME, TABLE_NAME, FIELD_ID,
            notatka.getId());

        try {
            connection.createStatement().execute(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z usuniÍciem notatki. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public List<Notatka> getNotatki() throws DbException {
        try {
            List<Notatka> notatki = new ArrayList<Notatka>();

            String sql = String.format("SELECT %s,%s,%s FROM %s.%s;", FIELD_ID, FIELD_NAZWA, FIELD_OPIS,
                DbHandler.DB_NAME, TABLE_NAME);
            ResultSet query = connection.createStatement().executeQuery(sql);
            // wykonaj zapytanie SQL i wynik otrzymujemy w resultSet

            while (query != null && query.next()) {
                notatki.add(new Notatka(query.getInt(1), query.getString(2), query.getString(3)));
            }

            return notatki;
        }
        catch (SQLException e) {
            String errorMsg = "Problem z pobraniem notatek. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public Notatka getNotatka(int id) throws DbException {
        try {
            Notatka notatka = new Notatka(0, "n/a", "n/a");
            String sql = String.format("SELECT %s,%s,%s FROM %s.%s WHERE %s = %d;", FIELD_ID, FIELD_NAZWA, FIELD_OPIS,
                DbHandler.DB_NAME, TABLE_NAME, FIELD_ID, id);

            ResultSet query = connection.createStatement().executeQuery(sql);

            if (query != null && query.next()) {
                notatka = new Notatka(query.getInt(1), query.getString(2), query.getString(3));
            }

            return notatka;
        }
        catch (SQLException e) {
            String errorMsg = "Problem z pobraniem notatki. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

}
