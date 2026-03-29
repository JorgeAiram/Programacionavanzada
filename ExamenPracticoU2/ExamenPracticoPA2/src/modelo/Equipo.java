package modelo;

import java.util.List;

public class Equipo {

    private List<Alumno> alumnos;
    private int calificacionRubrica;

    public Equipo() {
    }

    public Equipo(List<Alumno> alumnos, int calificacionRubrica) {
        this.alumnos = alumnos;
        this.calificacionRubrica = calificacionRubrica;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public int getCalificacionRubrica() {
        return calificacionRubrica;
    }

    public void setCalificacionRubrica(int calificacionRubrica) {
        this.calificacionRubrica = calificacionRubrica;
    }
}