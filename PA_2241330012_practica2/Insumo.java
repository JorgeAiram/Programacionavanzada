package PA_2241330012_practica2;

public class Insumo {
    private String idProducto;
    private String producto;
    private String idCategoria;

    public Insumo(String id, String prod, String idCat) {
        this.idProducto = id; 
        this.producto = prod; 
        this.idCategoria = idCat;
    }
    
    public String getIdProducto() { return idProducto; }
    public String getProducto() { return producto; }
    public String getIdCategoria() { return idCategoria; }
    
    public String toLinea() { return idProducto + "," + producto + "," + idCategoria; }
}