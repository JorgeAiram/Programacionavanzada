package persistencia;

import java.util.List;

import modelo.Evaluacion;

public class BaseDatosEvaluaciones {

    // Lista de evaluaciones guardadas en el JSON
    private List<Evaluacion> evaluaciones;

    // Constructor vacío
    public BaseDatosEvaluaciones() {
    }

    // Constructor con datos
    public BaseDatosEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}