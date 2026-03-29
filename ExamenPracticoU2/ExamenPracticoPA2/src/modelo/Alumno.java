package modelo;

public class Alumno {

    // Matrícula del alumno
    private String matricula;

    // Nombre completo del alumno
    private String nombre;

    // Constructor vacío
    public Alumno() {
    }

    // Constructor con datos
    public Alumno(String matricula, String nombre) {
        this.matricula = matricula;
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}