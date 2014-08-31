package pl.magazyn.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DbConnectorTest.class, DbKategoriaHandlerTest.class, DbNotatkaHandlerTest.class,
    DbTowarHandlerTest.class })
public class AllModelTests {

}
