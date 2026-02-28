package PA_2241330012_practica2;

import javax.swing.DefaultListModel;
import java.util.ArrayList;

public class ListaCategorias {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public void cargarCategorias(ArrayList<String[]> datos) {
        categorias.clear();
        for (String[] d : datos) {
            if (d.length >= 2) categorias.add(new Categoria(d[0].trim(), d[1].trim()));
        }
    }

    public DefaultListModel<Categoria> generarModelo() {
        DefaultListModel<Categoria> modelo = new DefaultListModel<>();
        for (Categoria c : categorias) modelo.addElement(c);
        return modelo;
    }

    public String buscarNombreCat(String id) {
        for (Categoria c : categorias) {
            if (c.getIdCategoria().equals(id)) return c.getNombre();
        }
        return "Desconocida";
    }

    public ArrayList<Categoria> getLista() { return categorias; }
}