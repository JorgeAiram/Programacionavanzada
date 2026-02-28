package PA_2241330012_practica2;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListaInsumos {
    private ArrayList<Insumo> insumos = new ArrayList<>();

    public boolean agregar(Insumo n) {
        for(Insumo i : insumos) if(i.getIdProducto().equals(n.getIdProducto())) return false;
        insumos.add(n); 
        return true;
    }

    public boolean eliminar(String id) {
        return insumos.removeIf(i -> i.getIdProducto().equals(id));
    }

    public void cargar(ArrayList<String[]> datos) {
        insumos.clear();
        for (String[] d : datos) {
            if (d.length >= 3) insumos.add(new Insumo(d[0].trim(), d[1].trim(), d[2].trim()));
        }
    }

    public String toTxt() {
        StringBuilder sb = new StringBuilder();
        for (Insumo i : insumos) sb.append(i.toLinea()).append("\n");
        return sb.toString();
    }

    public DefaultTableModel generarTabla(ListaCategorias lc) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Insumo", "Categoría"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        for (Insumo i : insumos) {
            modelo.addRow(new String[]{i.getIdProducto(), i.getProducto(), lc.buscarNombreCat(i.getIdCategoria())});
        }
        return modelo;
    }
}