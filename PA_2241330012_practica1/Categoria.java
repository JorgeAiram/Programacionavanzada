package PA_2241330012_practica1;

public class Categoria {
    private String idCategoria;
    private String nombreCategoria;

    public Categoria(String idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public String getIdCategoria() { return idCategoria; }
    public String getNombreCategoria() { return nombreCategoria; }

    @Override
    public String toString() {
        return idCategoria + " - " + nombreCategoria;
    }
    
    public String toCSV() {
        return idCategoria + "," + nombreCategoria;
    }
}