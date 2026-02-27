package PA_2241330012_practica1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivotxt {
    private String nombreArchivo;

    public Archivotxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardarString(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(texto);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public ArrayList<String[]> leerLineas() {
        ArrayList<String[]> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                lineas.add(elementos);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
        return lineas;
    }
}