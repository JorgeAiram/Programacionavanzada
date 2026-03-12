package Librerias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Libreria {

    public static boolean ExisteArchivo(String narchivo) {
        return new File(narchivo).exists();
    }

    public static ArrayList<String[]> LeerDatosCSV(String narchivo) {
        ArrayList<String[]> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(narchivo))) {
            String linea;
            lector.readLine(); 
            
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] datos = linea.split(",", -1);
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }
}