package pl.magazyn.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Towar;

class DbTowarHandler {

    private static final String TABLE_NAME = "towar";

    private static final String FIELD_ID = "id";

    private static final String FIELD_NAZWA = "nazwa";

    private static final String FIELD_OPIS = "opis";

    private static final String FIELD_CENA = "cena";

    private static final String FIELD_ILOSC = "ilosc";

    private static final String FIELD_KATEGORIA = "kategoria";

    private Connection connection;

    public DbTowarHandler(Connection connection) {
        this.connection = connection;
    }

    public void addTowar(Towar towar) throws DbException {
        String sql = String.format("INSERT INTO %s.%s(%s,%s,%s,%s,%s) VALUES ('%s','%s',%s,%d,%d);", DbHandler.DB_NAME,
            TABLE_NAME, FIELD_NAZWA, FIELD_OPIS, FIELD_CENA, FIELD_ILOSC, FIELD_KATEGORIA, towar.getNazwa(),
            towar.getOpis(), formatCena(towar.getCena()), towar.getIlosc(), towar.getKategoria());

        try {
            connection.createStatement().executeUpdate(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z dodaniem nowego towaru. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    private String formatCena(double d) {
        return String.format("%.2f", d).replace(",", ".");
    }

    public void editTowar(Towar towar) throws DbException {
        String sql = String.format("UPDATE %s.%s SET %s='%s', %s='%s', %s=%s, %s=%d, %s=%d WHERE %s=%d;",
            DbHandler.DB_NAME, TABLE_NAME, FIELD_NAZWA, towar.getNazwa(), FIELD_OPIS, towar.getOpis(), FIELD_CENA,
            formatCena(towar.getCena()), FIELD_ILOSC, towar.getIlosc(), FIELD_KATEGORIA, towar.getKategoria(),
            FIELD_ID, towar.getId());

        try {
            connection.createStatement().executeUpdate(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z edycjπ notatki. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public void removeTowar(Towar towar) throws DbException {
        String sql = String.format("DELETE FROM %s.%s WHERE %s=%d;", DbHandler.DB_NAME, TABLE_NAME, FIELD_ID,
            towar.getId());

        try {
            connection.createStatement().execute(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z usuniÍciem towaru. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public List<Towar> getTowar() throws DbException {
        try {
            List<Towar> towar = new ArrayList<Towar>();

            String sql = String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s.%s;", FIELD_ID, FIELD_NAZWA, FIELD_OPIS,
                FIELD_CENA, FIELD_ILOSC, FIELD_KATEGORIA, DbHandler.DB_NAME, TABLE_NAME);
            ResultSet query = connection.createStatement().executeQuery(sql);

            while (query != null && query.next()) {
                towar.add(new Towar(query.getInt(1), query.getString(2), query.getString(3), query.getDouble(4), query
                    .getInt(5), query.getInt(6)));
            }

            return towar;
        }
        catch (SQLException e) {
            String errorMsg = "Problem z pobraniem towarÛw. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public Towar getTowar(int id) throws DbException {
        try {
            Towar towar = new Towar(0, "n/a", "n/a", 0, 0, 0);
            String sql = String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s.%s WHERE %s = %d;", FIELD_ID, FIELD_NAZWA,
                FIELD_OPIS, FIELD_CENA, FIELD_ILOSC, FIELD_KATEGORIA, DbHandler.DB_NAME, TABLE_NAME, FIELD_ID, id);

            ResultSet query = connection.createStatement().executeQuery(sql);

            if (query != null && query.next()) {
                towar = new Towar(query.getInt(1), query.getString(2), query.getString(3), query.getDouble(4),
                    query.getInt(5), query.getInt(6));
            }

            return towar;
        }
        catch (SQLException e) {
            String errorMsg = "Problem z pobraniem towaru. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }
}
