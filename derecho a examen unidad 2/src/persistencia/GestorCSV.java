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
                // tenemos 9 datos en nuestra  clase Producto
                if (c.length == 9) {
                    Producto p = new Producto(
                        c[0], // SKU
                        c[1], // Nombre
                        Double.parseDouble(c[2]), // Precio Compra
                        Double.parseDouble(c[3]), // Porcentaje Ganancia
                        Integer.parseInt(c[4]),   // Cantidad Almacen
                        c[5], // Ruta Foto
                        c[6], // Unidad
                        c[7], // Categoria
                        c[8]  // Subcategoria
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