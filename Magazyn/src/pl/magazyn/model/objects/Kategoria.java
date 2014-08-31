package pl.magazyn.model.objects;

public class Kategoria {
    private int id;

    private String nazwa;

    public Kategoria(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

}
