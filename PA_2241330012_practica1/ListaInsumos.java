package PA_2241330012_practica1;

import java.util.ArrayList;

public class ListaInsumos {
    private ArrayList<Insumo> insumos;

    public ListaInsumos() {
        this.insumos = new ArrayList<>();
    }

    public boolean agregarInsumo(Insumo nodo) {
        if (buscarInsumo(nodo.getIdProducto()) == null) {
            insumos.add(nodo);
            return true;
        }
        return false;
    }

    public boolean eliminarInsumoPorId(String id) {
        Insumo insumo = buscarInsumoObj(id);
        if (insumo != null) {
            insumos.remove(insumo);
            return true;
        }
        return false;
    }

    public String buscarInsumo(String id) {
        for (Insumo i : insumos) {
            if (i.getIdProducto().equals(id)) return i.getProducto();
        }
        return null;
    }
    
    private Insumo buscarInsumoObj(String id) {
        for (Insumo i : insumos) {
            if (i.getIdProducto().equals(id)) return i;
        }
        return null;
    }

    public ArrayList<Insumo> getLista() { return insumos; }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (Insumo i : insumos) {
            txt.append(i.toString()).append("\n");
        }
        return txt.toString();
    }
}