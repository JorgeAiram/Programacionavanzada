package Modelo;
import java.util.ArrayList;

public class Lista {
    private ArrayList<Cpersona> lista = new ArrayList<>();

    public void agregar(Cpersona p) {
        lista.add(p);
    }

    public ArrayList<Cpersona> getLista() {
        return lista;
    }

    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (Cpersona p : lista) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}