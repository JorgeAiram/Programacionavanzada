package persistencia;
import Modelo.Producto;
import java.io.*;
import java.util.ArrayList;

public class GestorCSV {
    
    private final String RUTA_ARCHIVO = "productos.txt";

    public void exportarCSV(ArrayList<Producto> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Producto p : lista) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar TXT: " + e.getMessage());
        }
    }

    public ArrayList<Producto> importarCSV() {
        ArrayList<Producto> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                if (c.length == 9) {
                    Producto p = new Producto(
                        c[0], c[1], c[2], c[3], 
                        Double.parseDouble(c[4]), Double.parseDouble(c[5]), 
                        Integer.parseInt(c[6]), Integer.parseInt(c[7]), c[8]
                    );
                    lista.add(p);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar TXT: " + e.getMessage());
        }
        return lista;
    }
}