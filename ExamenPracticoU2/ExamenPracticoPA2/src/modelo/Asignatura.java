package modelo;

import java.util.List;

public class Asignatura {

    // Nombre de la asignatura
    private String nombre;

    // Atributos de egreso asociados
    private List<AtributoEgreso> atributos;

    public Asignatura() {
    }

    public Asignatura(String nombre, List<AtributoEgreso> atributos) {
        this.nombre = nombre;
        this.atributos = atributos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AtributoEgreso> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributoEgreso> atributos) {
        this.atributos = atributos;
    }
}