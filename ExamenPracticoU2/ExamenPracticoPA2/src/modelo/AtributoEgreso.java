package modelo;

public class AtributoEgreso {

    // Descripción del atributo de egreso
    private String descripcion;

    public AtributoEgreso() {
    }

    public AtributoEgreso(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}