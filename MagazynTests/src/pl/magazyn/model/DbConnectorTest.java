package pl.magazyn.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import pl.magazyn.exceptions.DbException;

public class DbConnectorTest {

    private Connection connection;

    private DbConnector testClass;

    @Before
    public void setUp() throws Exception {
        connection = mock(Connection.class);

        testClass = new DbConnector(connection);
    }

    @Test
    public void testDisconnect_passCase() throws DbException, SQLException {
        // when
        testClass.disconnect();

        // then
        verify(connection).close();
        // powierd czy na mocki connection zostala wywolana metoda close()
    }

    @Test
    public void testCloseThrowsException_CustomExceptionIsThrown() throws SQLException {
        // given
        SQLException toBeThrown = new SQLException();
        doThrow(toBeThrown).when(connection).close();

        String errorMsg = "Problem z zamykaniem bazy danych. Sprawdü stan po≥πczenia.";

        try {
            // when
            testClass.disconnect();
            fail();
        }
        catch (DbException e) {
            // then
            assertEquals(errorMsg, e.getMessage());
            assertEquals(toBeThrown, e.getCause());
        }

    }

    @Test
    public void testGetConnection_ConnectionAlreadyEstablished() throws DbException {
        // when
        Connection result = testClass.getConnection();

        // then
        assertEquals(connection, result);
    }

}
