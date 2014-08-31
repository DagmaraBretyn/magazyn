package pl.magazyn.model;

import static org.junit.Assert.assertEquals;
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
import pl.magazyn.model.objects.Notatka;

public class DbNotatkaHandlerTest {

    private DbHandler testClass;

    private Statement statement;

    @Before
    public void setUp() throws Exception {
        DbConnector connector = mock(DbConnector.class);
        Connection connection = mock(Connection.class);
        statement = mock(Statement.class);

        when(connector.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        testClass = new DbHandler(connector);
    }

    @Test
    public void testAddNotatka_notatkaIsAdded() throws DbException, SQLException {
        // given
        Notatka notatka = new Notatka(0, "nazwaNotatki", "opisNotatki");
        String sql = "INSERT INTO magazyn.notatka(nazwa,opis) VALUES ('nazwaNotatki','opisNotatki');";

        // when
        testClass.addNotatka(notatka);

        // then
        verify(statement).executeUpdate(sql);
    }

    @Test
    public void testEditNotatka_notatkaIsEdited() throws DbException, SQLException {
        // given
        Notatka notatka = new Notatka(0, "nazwaNotatki", "opisNotatki");
        String sql = "UPDATE magazyn.notatka SET nazwa='nazwaNotatki', opis='opisNotatki' WHERE id=0;";
        // when
        testClass.editNotatka(notatka);

        // then
        verify(statement).executeUpdate(sql);
    }

    @Test
    public void testRemoveNotatka_notatkaIsEdited() throws DbException, SQLException {
        // given
        Notatka notatka = new Notatka(0, "nazwaNotatki", "opisNotatki");
        String sql = "DELETE FROM magazyn.notatka WHERE id=0;";

        // when
        testClass.removeNotatka(notatka);

        // then
        verify(statement).execute(sql);
    }

    @Test
    public void testGetNotatka_NoNotatkaIsFound() throws DbException, SQLException {
        // given
        Notatka expected = new Notatka(0, "n/a", "n/a");

        // when
        Notatka result = testClass.getNotatka(11);

        // then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNazwa(), result.getNazwa());
    }

    @Test
    public void testGetNotatka_OneNotatkaIsReceived() throws SQLException, DbException {
        // given
        Notatka expected = new Notatka(11, "nazwaNotatki", "opisNotatki");
        String sql = "SELECT id,nazwa,opis FROM magazyn.notatka WHERE id = 11;";
        ResultSet query = mock(ResultSet.class);

        when(statement.executeQuery(sql)).thenReturn(query);
        when(query.next()).thenReturn(true, false);
        when(query.getInt(1)).thenReturn(11);
        when(query.getString(2)).thenReturn("nazwaNotatki");
        when(query.getString(3)).thenReturn("opisNotatki");

        // when
        Notatka result = testClass.getNotatka(11);

        // then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNazwa(), result.getNazwa());
        assertEquals(expected.getOpis(), result.getOpis());
    }

    @Test
    public void testGetNotatki_OneNotatkaIsReceived() throws SQLException, DbException {
        // given
        Notatka expected = new Notatka(11, "nazwaNotatki", "opisNotatki");
        String sql = "SELECT id,nazwa,opis FROM magazyn.notatka;";
        ResultSet query = mock(ResultSet.class);

        when(statement.executeQuery(sql)).thenReturn(query);
        when(query.next()).thenReturn(true, false);
        when(query.getInt(1)).thenReturn(11);
        when(query.getString(2)).thenReturn("nazwaNotatki");
        when(query.getString(3)).thenReturn("opisNotatki");

        // when
        List<Notatka> result = testClass.getNotatki();

        // then
        assertEquals(expected.getId(), result.get(0).getId());
        assertEquals(expected.getNazwa(), result.get(0).getNazwa());
        assertEquals(expected.getOpis(), result.get(0).getOpis());
    }

}
