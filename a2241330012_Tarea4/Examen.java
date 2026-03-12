package Tarea4;
import java.util.Collections;
import java.util.List;

public class Examen {
    private List<Pregunta> listaPreguntas;
    private int indiceActual;
    private int respuestasCorrectas;

    public Examen(List<Pregunta> preguntas) {
        this.listaPreguntas = preguntas;
        this.indiceActual = 0;
        this.respuestasCorrectas = 0;
    }

    public void barajar() {
        Collections.shuffle(listaPreguntas);
        indiceActual = 0;
        respuestasCorrectas = 0;
    }

    public Pregunta getPreguntaActual() {
        if (indiceActual < listaPreguntas.size()) {
            return listaPreguntas.get(indiceActual);
        }
        return null;
    }

    public void registrarRespuesta(boolean esCorrecta) {
        if (esCorrecta) respuestasCorrectas++;
    }

    public void avanzar() {
        indiceActual++;
    }

    public boolean hasNext() {
        return indiceActual < listaPreguntas.size() - 1;
    }

    public int getIndiceActual() { return indiceActual; }
    public int getTotal() { return listaPreguntas.size(); }
    public int getRespuestasCorrectas() { return respuestasCorrectas; }
}