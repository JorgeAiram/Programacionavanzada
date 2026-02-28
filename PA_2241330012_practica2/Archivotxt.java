package PA_2241330012_practica2;

import java.io.*;
import java.util.ArrayList;

public class Archivotxt {
    private String nombreArchivo;

    public Archivotxt(String nombreArchivo) { 
        this.nombreArchivo = nombreArchivo; 
    }
    
    public boolean existe() { 
        return new File(this.nombreArchivo).exists(); 
    }

    public void guardar(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(texto);
        } catch (IOException e) { 
            System.err.println("Error al guardar: " + e.getMessage()); 
        }
    }

    public ArrayList<String[]> cargar() {
        ArrayList<String[]> lineas = new ArrayList<>();
        if (this.existe()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    lineas.add(linea.split(","));
                }
            } catch (IOException e) { 
                System.err.println("Error al cargar: " + e.getMessage()); 
            }
        }
        return lineas;
    }
}