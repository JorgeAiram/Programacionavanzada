package Modelo;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionProductos {
    private ArrayList<Producto> listaProductos;

    public GestionProductos() {
        listaProductos = new ArrayList<>();
    }

    public void setLista(ArrayList<Producto> lista) {
        this.listaProductos = lista;
    }

    public ArrayList<Producto> getLista() {
        return listaProductos;
    }

    public boolean existe(String id) {
        for (Producto p : listaProductos) {
            if (p.getId().equals(id)) return true;
        }
        return false;
    }

    public void insertar(Producto p) {
        if (!existe(p.getId())) {
            listaProductos.add(p);
        }
    }

    public Producto buscar(String id) {
        for (Producto p : listaProductos) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public void actualizar(Producto productoActualizado) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId().equals(productoActualizado.getId())) {
                listaProductos.set(i, productoActualizado);
                break;
            }
        }
    }

    public boolean eliminar(String id) {
        Iterator<Producto> iterador = listaProductos.iterator();
        while (iterador.hasNext()) {
            Producto p = iterador.next();
            if (p.getId().equals(id)) {
                iterador.remove(); 
                return true;
            }
        }
        return false;
    }
}