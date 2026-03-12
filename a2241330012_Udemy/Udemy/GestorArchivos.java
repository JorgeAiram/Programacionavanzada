package Udemy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    private String rutaArchivo = "productos.txt";

    // Guardar la lista de productos en el TXT
    public void guardarProductos(List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Producto p : productos) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    // Leer los productos desde el TXT
    public List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        
        if (!archivo.exists()) return productos; // Retorna lista vacía si no existe

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    int stock = Integer.parseInt(datos[3]);
                    productos.add(new Producto(id, nombre, precio, stock));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return productos;
    }
}