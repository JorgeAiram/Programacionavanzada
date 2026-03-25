package Modelo;
import java.util.ArrayList;

public class GestionProductos {
    private ArrayList<Producto> lista;

    public GestionProductos() {
        lista = new ArrayList<>();
    }

    public void insertar(Producto p) {
        lista.add(p);
    }

    public Producto buscar(String sku) {
        for (Producto p : lista) {
            if (p.getSku().equals(sku)) {
                return p;
            }
        }
        return null;
    }

    public boolean existe(String sku) {
        return buscar(sku) != null;
    }

    public void actualizar(Producto pActualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getSku().equals(pActualizado.getSku())) {
                lista.set(i, pActualizado);
                break;
            }
        }
    }

    public boolean eliminar(String sku) {
        Producto p = buscar(sku);
        if (p != null) {
            lista.remove(p);
            return true;
        }
        return false;
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }
    
    public void setLista(ArrayList<Producto> nuevaLista) {
        this.lista = nuevaLista;
    }
}