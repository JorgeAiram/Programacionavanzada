package Tarea4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;

public class GestorArchivos {
    
    public static Examen cargarExamen(File archivo, boolean preguntarAporB) {
        List<String[]> registros = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String titulo = br.readLine(); // Línea 1: Título
            String encabezados = br.readLine(); // Línea 2: Encabezados
            
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    registros.add(partes);
                }
            }
            
            // Validación de Mínimo de Registros
            if (registros.size() < 5) {
                JOptionPane.showMessageDialog(null, 
                    "El archivo debe tener al menos 5 registros.", 
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            List<Pregunta> preguntas = new ArrayList<>();
            Random rand = new Random();
            
            // Convertir registros a objetos Pregunta
            for (int i = 0; i < registros.size(); i++) {
                String terminoA = registros.get(i)[0].trim();
                String terminoB = registros.get(i)[1].trim();
                
                String enunciado = preguntarAporB ? terminoA : terminoB;
                String correcta = preguntarAporB ? terminoB : terminoA;
                
                // Generar 3 distractores únicos
                Set<String> opcionesSet = new HashSet<>();
                opcionesSet.add(correcta);
                
                while (opcionesSet.size() < 4) {
                    int r = rand.nextInt(registros.size());
                    String distractor = preguntarAporB ? registros.get(r)[1].trim() : registros.get(r)[0].trim();
                    opcionesSet.add(distractor);
                }
                
                List<String> opcionesList = new ArrayList<>(opcionesSet);
                Collections.shuffle(opcionesList); // Mezclar las 4 opciones
                
                preguntas.add(new Pregunta(enunciado, correcta, opcionesList.toArray(new String[0])));
            }
            
            return new Examen(preguntas);
            
        } catch (Exception e) {
            // Control de Errores
            JOptionPane.showMessageDialog(null, 
                "Error al procesar el archivo CSV. Formato incorrecto o corrupto.\nDetalles: " + e.getMessage(), 
                "Error Crítico", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}