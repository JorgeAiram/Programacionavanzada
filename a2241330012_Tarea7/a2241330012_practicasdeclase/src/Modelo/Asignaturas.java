package Modelo;
import java.util.Objects;

public class Asignaturas {
    String carrera;
    String materia;
    String profesor;
    String grupo;

    public Asignaturas(String carrera, String materia, String profesor, String grupo) {
        this.carrera = carrera;
        this.materia = materia;
        this.profesor = profesor;
        this.grupo = grupo;
    }

    public String getCarrera() { return carrera; }
    public String getMateria() { return materia; }
    public String getProfesor() { return profesor; }
    public String getGrupo() { return grupo; }

    @Override
    public String toString() { return this.grupo + " - " + this.getProfesor(); }

    @Override
    public int hashCode() { return Objects.hash(carrera, grupo, materia); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Asignaturas other = (Asignaturas) obj;
        return Objects.equals(carrera, other.carrera) && 
               Objects.equals(grupo, other.grupo) && 
               Objects.equals(materia, other.materia);
    }
}