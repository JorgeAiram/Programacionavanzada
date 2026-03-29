package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import modelo.Evaluacion;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionPersistencia {

    // Archivo JSON donde se guardan las evaluaciones
    private static final String RUTA_ARCHIVO = "evaluaciones.json";

    private Gson gson;

    // Constructor
    public EvaluacionPersistencia() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // ==================================
    // Cargar la base de datos desde JSON
    // ==================================
    private BaseDatosEvaluaciones cargarBaseDatos() {

        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return new BaseDatosEvaluaciones(new ArrayList<>());
        }

        try (Reader reader = new FileReader(archivo)) {

            Type tipo = new TypeToken<BaseDatosEvaluaciones>() {}.getType();
            BaseDatosEvaluaciones bd = gson.fromJson(reader, tipo);

            if (bd == null || bd.getEvaluaciones() == null) {
                return new BaseDatosEvaluaciones(new ArrayList<>());
            }

            return bd;

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseDatosEvaluaciones(new ArrayList<>());
        }
    }

    // ===============================
    // Guardar la base de datos en JSON
    // ===============================
    private void guardarBaseDatos(BaseDatosEvaluaciones bd) {

        try (Writer writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(bd, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===============================
    // GUARDAR EVALUACIÓN
    // ===============================
    public boolean guardarEvaluacion(Evaluacion nueva) {

        BaseDatosEvaluaciones bd = cargarBaseDatos();
        List<Evaluacion> evaluaciones = bd.getEvaluaciones();

        // Evitar evaluaciones duplicadas por ID
        for (Evaluacion e : evaluaciones) {
            if (e.getId().equals(nueva.getId())) {
                return false;
            }
        }

        evaluaciones.add(nueva);
        guardarBaseDatos(bd);
        return true;
    }

    // ===============================
    // BUSCAR EVALUACIÓN
    // ===============================
    public Evaluacion buscarEvaluacion(String id) {

        BaseDatosEvaluaciones bd = cargarBaseDatos();

        for (Evaluacion e : bd.getEvaluaciones()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }

        return null;
    }

    // ===============================
    // ACTUALIZAR EVALUACIÓN
    // ===============================
    public boolean actualizarEvaluacion(Evaluacion evaluacionActualizada) {

        BaseDatosEvaluaciones bd = cargarBaseDatos();
        List<Evaluacion> evaluaciones = bd.getEvaluaciones();

        for (int i = 0; i < evaluaciones.size(); i++) {
            if (evaluaciones.get(i).getId().equals(evaluacionActualizada.getId())) {
                evaluaciones.set(i, evaluacionActualizada);
                guardarBaseDatos(bd);
                return true;
            }
        }

        return false;
    }

    // ===============================
    // ELIMINAR EVALUACIÓN
    // ===============================
    public boolean eliminarEvaluacion(String id) {

        BaseDatosEvaluaciones bd = cargarBaseDatos();
        List<Evaluacion> evaluaciones = bd.getEvaluaciones();

        boolean eliminado = evaluaciones.removeIf(e -> e.getId().equals(id));

        if (eliminado) {
            guardarBaseDatos(bd);
        }

        return eliminado;
    }

    // ===============================
    // OBTENER TODAS LAS EVALUACIONES
    // ===============================
    public List<Evaluacion> obtenerTodas() {
        return cargarBaseDatos().getEvaluaciones();
    }
}