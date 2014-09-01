package pl.magazyn.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginControllerTest {

    private static final String CORRECT_LOGIN = "admin";

    private static final String CORRECT_PASS = "dagmara";

    private LoginController testClass = new LoginController();

    @Test
    public void testLoginAndPasswordIsCorrect_SuccesIsReturned() {
        // given
        testClass.setUsername(CORRECT_LOGIN);
        testClass.setPassword(CORRECT_PASS);

        // when
        String validateResult = testClass.validate();

        // then
        assertEquals("news", validateResult);
        assertFalse(testClass.getIsLoginFailed());
    }

    @Test
    public void testOnlyUsernameIsCorrect_FailIsReturned() {
        // given
        testClass.setUsername(CORRECT_LOGIN);
        testClass.setPassword("notCorrect_password");

        // when
        String validateResult = testClass.validate();

        // then
        assertEquals("login", validateResult);
        assertTrue(testClass.getIsLoginFailed());
    }

    @Test
    public void testOnlyPasswordIsCorrect_FailIsReturned() {
        // given
        testClass.setUsername("notCorrect_username");
        testClass.setPassword(CORRECT_PASS);

        // when
        String validateResult = testClass.validate();

        // then
        assertEquals("login", validateResult);
        assertTrue(testClass.getIsLoginFailed());
    }

    @Test
    public void testPasswordAndUsernameIsNotCorrect_FailIsReturned() {
        // given
        testClass.setUsername("notCorrect_username");
        testClass.setPassword("notCorrect_password");

        // when
        String validateResult = testClass.validate();

        // then
        assertEquals("login", validateResult);
        assertTrue(testClass.getIsLoginFailed());
    }

}
