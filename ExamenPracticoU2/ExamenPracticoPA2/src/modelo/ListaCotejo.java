package modelo;

import java.util.List;

public class ListaCotejo {

    // Indicadores seleccionados
    private List<String> indicadoresSeleccionados;

    public ListaCotejo() {
    }

    public ListaCotejo(List<String> indicadoresSeleccionados) {
        this.indicadoresSeleccionados = indicadoresSeleccionados;
    }

    public List<String> getIndicadoresSeleccionados() {
        return indicadoresSeleccionados;
    }

    public void setIndicadoresSeleccionados(List<String> indicadoresSeleccionados) {
        this.indicadoresSeleccionados = indicadoresSeleccionados;
    }
}