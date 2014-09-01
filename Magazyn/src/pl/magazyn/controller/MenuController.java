package pl.magazyn.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "menu", eager = true)
@RequestScoped
public class MenuController {

    public String goToAddTowar() {
        return PageNamesConstants.PAGE_ADD;
    }

    public String goToBrowseTowar() {
        return PageNamesConstants.PAGE_BROWSE;
    }
}
