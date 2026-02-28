package PA_2241330012_practica2;

public class Categoria {
    private String idCategoria;
    private String nombre;

    public Categoria(String id, String nom) { 
        this.idCategoria = id; 
        this.nombre = nom; 
    }
    
    public String getIdCategoria() { return idCategoria; }
    public String getNombre() { return nombre; }
    
    @Override 
    public String toString() { return idCategoria + " - " + nombre; }
}