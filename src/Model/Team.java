package Model;

public class Team {
    private String nombre;

    public Team(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return String.format(
                "Team (nombre=%s)", this.nombre);
    }
}
