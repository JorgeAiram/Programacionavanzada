package Modelo;
import java.util.ArrayList;

public class Mejercicio02 {

    private ArrayList<String> lista = new ArrayList<>();

    public void agregar(String dato) {
        lista.add(dato);
    }

    public void eliminar(int index) {
        lista.remove(index);
    }

    public ArrayList<String> getLista() {
        return lista;
    }
}