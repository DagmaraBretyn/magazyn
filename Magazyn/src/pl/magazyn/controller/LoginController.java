package pl.magazyn.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "login", eager = true)
@SessionScoped
public class LoginController {

    private String username;

    private String password;

    private boolean isLoginFailed = false;

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "dagmara";

    public String validate() {
        String loginResponse;

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            loginResponse = PageNamesConstants.PAGE_NEWS;
        }
        else {
            isLoginFailed = true;
            loginResponse = PageNamesConstants.PAGE_LOGIN;
        }

        return loginResponse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsLoginFailed() {
        return isLoginFailed;
    }
}