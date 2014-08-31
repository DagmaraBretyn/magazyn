package pl.magazyn.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Kategoria;

class DbKategoriaHandler {

    private static final String TABLE_NAME = "kategoria";

    private static final String FIELD_ID = "id";

    private static final String FIELD_NAZWA = "nazwa";

    private Connection connection;

    public DbKategoriaHandler(Connection connection) {
        this.connection = connection;
    }

    public void addKategoria(Kategoria kategoria) throws DbException {

// INSERT INTO table_name
// VALUES (value1,value2,value3,...);

        String sql = String.format("INSERT INTO %s.%s(%s) VALUES ('%s');", DbHandler.DB_NAME, TABLE_NAME, FIELD_NAZWA,
            kategoria.getNazwa());

        try {
            connection.createStatement().executeUpdate(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z dodaniem nowej kategorii. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public void editKategoria(Kategoria kategoria) throws DbException {

// UPDATE table_name
// SET column1=value1,column2=value2,...
// WHERE some_column=some_value;

        String sql = String.format("UPDATE %s.%s SET %s='%s' WHERE %s=%d;", DbHandler.DB_NAME, TABLE_NAME, FIELD_NAZWA,
            kategoria.getNazwa(), FIELD_ID, kategoria.getId());

        try {
            connection.createStatement().executeUpdate(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z edycjπ kategorii. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public void removeKategoria(Kategoria kategoria) throws DbException {

// DELETE FROM table_name
// WHERE some_column=some_value;

        String sql = String.format("DELETE FROM %s.%s WHERE %s=%d;", DbHandler.DB_NAME, TABLE_NAME, FIELD_ID,
            kategoria.getId());

        try {
            connection.createStatement().execute(sql);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z usuniÍciem kategorii. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public List<Kategoria> getKategorie() throws DbException {

// SELECT column_name,column_name
// FROM table_name;

        try {
            List<Kategoria> kategorie = new ArrayList<Kategoria>();

            String sql = String
                .format("SELECT %s,%s FROM %s.%s;", FIELD_ID, FIELD_NAZWA, DbHandler.DB_NAME, TABLE_NAME);
            ResultSet query = connection.createStatement().executeQuery(sql);

            while (query != null && query.next()) {
                kategorie.add(new Kategoria(query.getInt(1), query.getString(2)));
            }

            return kategorie;
        }
        catch (SQLException e) {
            String errorMsg = "Problem z pobraniem listy kategorii. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

    public Kategoria getKategoria(int id) throws DbException {

// SELECT column_name,column_name
// FROM table_name
// WHERE column_name operator value;

        try {
            Kategoria kategoria = new Kategoria(0, "n/a");

            String sql = String.format("SELECT %s,%s FROM %s.%s WHERE %s = %d;", FIELD_ID, FIELD_NAZWA,
                DbHandler.DB_NAME, TABLE_NAME, FIELD_ID, id);

            ResultSet query = connection.createStatement().executeQuery(sql);

            if (query != null && query.next()) {
                kategoria = new Kategoria(query.getInt(1), query.getString(2));
            }

            return kategoria;
        }
        catch (SQLException e) {
            String errorMsg = "Problem z pobraniem kategorii. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

}
