package pl.magazyn.tests;

import org.junit.Test;

import pl.magazyn.model.DbHandler;
import pl.magazyn.model.objects.Notatka;

public class testCon {
    @Test
    public void testName() throws Exception {
        DbHandler db = new DbHandler();
        db.addNotatka(new Notatka(0, "test", "test"));
    }
}
