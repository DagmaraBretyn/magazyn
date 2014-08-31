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
import pl.magazyn.model.objects.Towar;

public class DbTowarHandlerTest {

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
    public void testAddTowar_towarIsAdded() throws DbException, SQLException {
        // given
        Towar towar = new Towar(0, "nazwaTowaru", "opisTowaru", 2.45, 2, 5);
        String sql = "INSERT INTO magazyn.towar(nazwa,opis,cena,ilosc,kategoria) VALUES ('nazwaTowaru','opisTowaru',2.45,2,5);";

        // when
        testClass.addTowar(towar);

        // then
        verify(statement).executeUpdate(sql);
    }

    @Test
    public void testEditTowar_towarIsEdited() throws DbException, SQLException {
        // given
        Towar towar = new Towar(0, "nazwaTowaru", "opisTowaru", 2.45, 2, 5);
        String sql = "UPDATE magazyn.towar SET nazwa='nazwaTowaru', opis='opisTowaru', cena=2.45, ilosc=2, kategoria=5 WHERE id=0;";
        // when
        testClass.editTowar(towar);

        // then
        verify(statement).executeUpdate(sql);
    }

    @Test
    public void testRemoveTowar_towarIsEdited() throws DbException, SQLException {
        // given
        Towar towar = new Towar(0, "nazwaTowaru", "opisTowaru", 2.45, 2, 5);
        String sql = "DELETE FROM magazyn.towar WHERE id=0;";

        // when
        testClass.removeTowar(towar);

        // then
        verify(statement).execute(sql);
    }

    @Test
    public void testGetTowar_NoTowarIsFound() throws DbException, SQLException {
        // given
        Towar expected = new Towar(0, "n/a", "n/a", 0, 0, 0);

        // when
        Towar result = testClass.getTowar(11);

        // then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNazwa(), result.getNazwa());
    }

    @Test
    public void testGetTowar_OneTowarIsReceived() throws SQLException, DbException {
        // given
        Towar expected = new Towar(11, "nazwaTowaru", "opisTowaru", 2.45, 2, 5);
        String sql = "SELECT id,nazwa,opis,cena,ilosc,kategoria FROM magazyn.towar WHERE id = 11;";
        ResultSet query = mock(ResultSet.class);

        when(statement.executeQuery(sql)).thenReturn(query);
        when(query.next()).thenReturn(true, false);
        when(query.getInt(1)).thenReturn(11);
        when(query.getString(2)).thenReturn("nazwaTowaru");
        when(query.getString(3)).thenReturn("opisTowaru");
        when(query.getDouble(4)).thenReturn(2.45);
        when(query.getInt(5)).thenReturn(2);
        when(query.getInt(6)).thenReturn(5);

        // when
        Towar result = testClass.getTowar(11);

        // then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNazwa(), result.getNazwa());
        assertEquals(expected.getOpis(), result.getOpis());
        assertEquals(expected.getCena(), result.getCena(), 0.01);
        assertEquals(expected.getIlosc(), result.getIlosc());
        assertEquals(expected.getKategoria(), result.getKategoria());
    }

    @Test
    public void testGetTowaru_OneTowarIsReceived() throws SQLException, DbException {
        // given
        Towar expected = new Towar(11, "nazwaTowaru", "opisTowaru", 2.45, 2, 5);
        String sql = "SELECT id,nazwa,opis,cena,ilosc,kategoria FROM magazyn.towar;";
        ResultSet query = mock(ResultSet.class);

        when(statement.executeQuery(sql)).thenReturn(query);
        when(query.next()).thenReturn(true, false);
        when(query.getInt(1)).thenReturn(11);
        when(query.getString(2)).thenReturn("nazwaTowaru");
        when(query.getString(3)).thenReturn("opisTowaru");
        when(query.getDouble(4)).thenReturn(2.45);
        when(query.getInt(5)).thenReturn(2);
        when(query.getInt(6)).thenReturn(5);

        // when
        List<Towar> result = testClass.getTowar();

        // then
        assertEquals(expected.getId(), result.get(0).getId());
        assertEquals(expected.getNazwa(), result.get(0).getNazwa());
        assertEquals(expected.getOpis(), result.get(0).getOpis());
        assertEquals(expected.getCena(), result.get(0).getCena(), 0.01);
        assertEquals(expected.getIlosc(), result.get(0).getIlosc());
        assertEquals(expected.getKategoria(), result.get(0).getKategoria());
    }

}
