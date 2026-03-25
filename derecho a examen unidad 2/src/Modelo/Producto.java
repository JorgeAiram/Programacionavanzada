package Modelo;

public class Producto {
    private String sku; 
    private String nombre;
    private double precioCompra;
    private double porcentajeGanancia; 
    private int cantidadAlmacen;
    private String rutaFoto; 
    private String unidadMedida; 
    private String categoriaPrincipal;
    private String subCategoria;

    public Producto(String sku, String nombre, double precioCompra, double porcentajeGanancia, 
                    int cantidadAlmacen, String rutaFoto, String unidadMedida, 
                    String categoriaPrincipal, String subCategoria) {
        this.sku = sku;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.porcentajeGanancia = porcentajeGanancia;
        this.cantidadAlmacen = cantidadAlmacen;
        this.rutaFoto = rutaFoto;
        this.unidadMedida = unidadMedida;
        this.categoriaPrincipal = categoriaPrincipal;
        this.subCategoria = subCategoria;
    }

    
    public double getPrecioVenta() {
        return precioCompra + (precioCompra * (porcentajeGanancia / 100.0));
    }

    
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }
    
    public double getPorcentajeGanancia() { return porcentajeGanancia; }
    public void setPorcentajeGanancia(double porcentajeGanancia) { this.porcentajeGanancia = porcentajeGanancia; }
    
    public int getCantidadAlmacen() { return cantidadAlmacen; }
    public void setCantidadAlmacen(int cantidadAlmacen) { this.cantidadAlmacen = cantidadAlmacen; }
    
    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }
    
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    
    public String getCategoriaPrincipal() { return categoriaPrincipal; }
    public void setCategoriaPrincipal(String categoriaPrincipal) { this.categoriaPrincipal = categoriaPrincipal; }
    
    public String getSubCategoria() { return subCategoria; }
    public void setSubCategoria(String subCategoria) { this.subCategoria = subCategoria; }

    @Override
    public String toString() {
        return sku + ";" + nombre + ";" + precioCompra + ";" + porcentajeGanancia + ";" + 
               cantidadAlmacen + ";" + rutaFoto + ";" + unidadMedida + ";" + 
               categoriaPrincipal + ";" + subCategoria;
    }
}