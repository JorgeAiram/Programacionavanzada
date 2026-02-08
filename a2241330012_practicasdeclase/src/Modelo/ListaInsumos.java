package Modelo;
import javax.swing.DefaultListModel;
import java.util.ArrayList;

public class ListaInsumos {

    private ArrayList<Insumo> lista;
    private DefaultListModel<String> modeloLista;

    public ListaInsumos() {
        lista = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
    }

    public void agregar(Insumo i) {
        lista.add(i);
        modeloLista.addElement(i.toString());
    }

    public void eliminar(int pos) {
        if (pos >= 0) {
            lista.remove(pos);
            modeloLista.remove(pos);
        }
    }

    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }
}