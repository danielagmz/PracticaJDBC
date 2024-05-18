package Model;

public class Player {
    private int id;
    private String nom;
    private int alcada;
    private int pes;

    private int equip_actual;

    public Player(String nom, int alcada, int pes, int equip_actual) {
        this.nom = nom;
        this.alcada = alcada;
        this.pes = pes;
        this.equip_actual = equip_actual;
    }

    public Player() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAlcada() {
        return alcada;
    }

    public void setAlcada(int alcada) {
        this.alcada = alcada;
    }

    public int getPes() {
        return pes;
    }

    public void setPes(int pes) {
        this.pes = pes;
    }

    public int getEquip_actual() {
        return equip_actual;
    }

    public void setEquip_actual(int equip_actual) {
        this.equip_actual = equip_actual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "nom=%s alcada=%s pes=%s equip_actual=%s", this.nom, this.alcada, this.pes, this.equip_actual);
    }
}
