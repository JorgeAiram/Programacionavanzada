package modelo;

public class Grupo {

    // Clave del grupo (G, H, I, 4A, etc.)
    private String clave;

    public Grupo() {
    }

    public Grupo(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}