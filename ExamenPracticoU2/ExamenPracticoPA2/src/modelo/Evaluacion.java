package modelo;

import java.util.List;

public class Evaluacion {

    // ID único: Asignatura_Profesor_Grupo
    private String id;

    private String asignatura;
    private String profesor;
    private String grupo;

    private ProductoIntegrador productoIntegrador;
    private ListaCotejo listaCotejo;

    private List<Equipo> equipos;

    public Evaluacion() {
    }

    public Evaluacion(String id, String asignatura, String profesor, String grupo,
                      ProductoIntegrador productoIntegrador,
                      ListaCotejo listaCotejo,
                      List<Equipo> equipos) {
        this.id = id;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.grupo = grupo;
        this.productoIntegrador = productoIntegrador;
        this.listaCotejo = listaCotejo;
        this.equipos = equipos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public ProductoIntegrador getProductoIntegrador() {
        return productoIntegrador;
    }

    public void setProductoIntegrador(ProductoIntegrador productoIntegrador) {
        this.productoIntegrador = productoIntegrador;
    }

    public ListaCotejo getListaCotejo() {
        return listaCotejo;
    }

    public void setListaCotejo(ListaCotejo listaCotejo) {
        this.listaCotejo = listaCotejo;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}