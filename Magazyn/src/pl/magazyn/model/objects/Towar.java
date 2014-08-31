package pl.magazyn.model.objects;

public class Towar {

    private int id;

    private String nazwa;

    private String opis;

    private double cena;

    private int ilosc;

    private int kategoria;

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

    public String getOpis() {
        return opis;
    }

    public double getCena() {
        return cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public int getKategoria() {
        return kategoria;
    }

}
