package pl.magazyn.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "towar", eager = true)
@RequestScoped
public class TowarController {

    public String add(String nazwa, String opis, String cena, String ilosc, String kategoria) {

        return PageNamesConstants.PAGE_BROWSE;
    }

}
