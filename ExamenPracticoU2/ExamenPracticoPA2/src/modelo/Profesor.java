package modelo;

public class Profesor {

    // Nombre completo del profesor
    private String nombre;

    public Profesor() {
    }

    // Constructor con datos
    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}