package pl.magazyn.model.objects;

public class Notatka {
    private int id;

    private String nazwa;

    private String opis;

    public Notatka(int id, String nazwa, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
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

}
