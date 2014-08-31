package pl.magazyn.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.magazyn.exceptions.DbException;
import pl.magazyn.model.objects.Kategoria;

public class DbKategoriaHandlerTest {

    private DbHandler testClass;

    private Statement statement;

    @Before
    public void setUp() throws Exception {
        DbConnector connector = mock(DbConnector.class);
        Connection connection = mock(Connection.class);
        statement = mock(Statement.class);

        when(connector.getConnection()).thenReturn(connection);
        // kiedy zostanie wykonana metoda connector.getConnection() mockito
        // zwroci nam connection
        when(connection.createStatement()).thenReturn(statement);

        testClass = new DbHandler(connector);
    }

    @Test
    public void testAddKategoria_kategoriaIsAdded() throws DbException, SQLException {
        // given
        Kategoria kategoria = new Kategoria(0, "nazwaKategorii");
        String sql = "INSERT INTO magazyn.kategoria(nazwa) VALUES ('nazwaKategorii');";

        // when
        testClass.addKategoria(kategoria);

        // then
        verify(statement).executeUpdate(sql);
    }

    @Test
    public void testWhenStatementThrowsException_CustomExceptionIsThrown() throws SQLException {
        // given
        Kategoria kategoria = new Kategoria(0, "nazwaKategorii");
        String sql = "INSERT INTO magazyn.kategoria(nazwa) VALUES ('nazwaKategorii');";
        String errorMsg = "Problem z dodaniem nowej kategorii. Sprawdü czy baza danych jest w≥πczona.";

        SQLException toBeThrown = new SQLException();
        doThrow(toBeThrown).when(statement).executeUpdate(sql);

        try {
            // when
            testClass.addKategoria(kategoria);
            fail();
        }
        catch (DbException e) {
            // then
            assertEquals(errorMsg, e.getMessage());
            assertEquals(toBeThrown, e.getCause());
        }
    }

    @Test
    public void testEditKategoria_kategoriaIsEdited() throws DbException, SQLException {
        // given
        Kategoria kategoria = new Kategoria(0, "nazwaKategorii");
        String sql = "UPDATE magazyn.kategoria SET nazwa='nazwaKategorii' WHERE id=0;";

        // when
        testClass.editKategoria(kategoria);

        // then
        verify(statement).executeUpdate(sql);
        // zweryfikuj czy na mocku statement zostala wykonana metoda
        // executeUpdate z parametrem sql
    }

    @Test
    public void testRemoveKategoria_kategoriaIsEdited() throws DbException, SQLException {
        // given
        Kategoria kategoria = new Kategoria(0, "nazwaKategorii");
        String sql = "DELETE FROM magazyn.kategoria WHERE id=0;";

        // when
        testClass.removeKategoria(kategoria);

        // then
        verify(statement).execute(sql);
    }

    @Test
    public void testGetKategoria_NoKategoriaIsFound() throws DbException, SQLException {
        // given
        Kategoria expected = new Kategoria(0, "n/a");
        // n/a - http://pl.wikipedia.org/wiki/N/A

        // when
        Kategoria result = testClass.getKategoria(11);

        // then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNazwa(), result.getNazwa());
    }

    @Test
    public void testGetKategoria_OneKategoriaIsReceived() throws SQLException, DbException {
        // given
        Kategoria expected = new Kategoria(11, "nazwaKategorii");
        String sql = "SELECT id,nazwa FROM magazyn.kategoria WHERE id = 11;";
        ResultSet query = mock(ResultSet.class);

        when(statement.executeQuery(sql)).thenReturn(query);
        when(query.next()).thenReturn(true, false);
        when(query.getInt(1)).thenReturn(11);
        when(query.getString(2)).thenReturn("nazwaKategorii");

        // when
        Kategoria result = testClass.getKategoria(11);

        // then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNazwa(), result.getNazwa());
    }

    @Test
    public void testGetKategorie_OneKategoriaIsReceived() throws SQLException, DbException {
        // given
        Kategoria expected = new Kategoria(11, "nazwaKategorii");
        String sql = "SELECT id,nazwa FROM magazyn.kategoria;";
        ResultSet query = mock(ResultSet.class);

        when(statement.executeQuery(sql)).thenReturn(query);
        when(query.next()).thenReturn(true, false);
        when(query.getInt(1)).thenReturn(11);
        when(query.getString(2)).thenReturn("nazwaKategorii");

        // when
        List<Kategoria> result = testClass.getKategorie();

        // then
        assertEquals(expected.getId(), result.get(0).getId());
        assertEquals(expected.getNazwa(), result.get(0).getNazwa());
    }

}
