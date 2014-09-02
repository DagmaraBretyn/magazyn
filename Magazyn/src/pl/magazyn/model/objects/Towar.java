package pl.magazyn.model.objects;

public class Towar {

    private int id;

    private String nazwa;

    private String opis;

    private double cena;

    private int ilosc;

    private int kategoria;

    private boolean canEdit;

    public Towar(int id, String nazwa, String opis, double cena, int ilosc, int kategoria) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
        this.ilosc = ilosc;
        this.kategoria = kategoria;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getKategoria() {
        return kategoria;
    }

    public void setKategoria(int kategoria) {
        this.kategoria = kategoria;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

}
