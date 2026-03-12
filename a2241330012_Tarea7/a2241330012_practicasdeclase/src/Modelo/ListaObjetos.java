package Modelo;
import java.util.ArrayList;

public class ListaObjetos {
    private ArrayList<Object> lista;

    public ListaObjetos() {
        lista = new ArrayList<>();
    }

    public boolean Insertar(Object nodo) {
        if (this.buscar(nodo) == -1) {
            this.lista.add(nodo);
            return true;
        }
        return false;
    }

    public int buscar(Object nodoid) {
        int pos = -1;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).equals(nodoid)) {
                return i;
            }
        }
        return pos;
    }

    public ArrayList<Object> getLista() { return this.lista; }
}