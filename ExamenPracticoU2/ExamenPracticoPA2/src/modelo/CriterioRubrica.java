package modelo;

public class CriterioRubrica {

    // Nombre del criterio
    private String nombre;

    // Puntaje asignado
    private int puntaje;

    public CriterioRubrica() {
    }

    // Constructor con datos
    public CriterioRubrica(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}