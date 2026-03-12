package Modelo;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla {
    DefaultTableModel modelo;
    String columnas[] = {"D.A.", "P.EDUCATIVO", "LETRA", "PROFESOR", "MATERIA", "Periodo Materia", "No.ACTA", "MATRICULA", "ALUMNO", "CALIFICACION", "OP.INS.", "OP.EXA.", "MES", "CICLO ESCOLAR"};
    ArrayList<String[]> datos;

    public ModeloTabla(ArrayList<String[]> datos) {
        this.datos = datos;
        modelo = new DefaultTableModel(columnas, 0);
        for (String[] fila : datos) {
            modelo.addRow(fila);
        }
    }

    public DefaultTableModel getModelo() { return modelo; }

    public DefaultListModel<Carreras> getModeloCarreras() {
        DefaultListModel<Carreras> modeloCarreras = new DefaultListModel<>();
        ListaObjetos lista = new ListaObjetos();
        
        for (String[] fila : datos) {
            Carreras c = new Carreras();
            c.setCarrera(fila[1]); // P.EDUCATIVO
            if (lista.Insertar(c)) {
                modeloCarreras.addElement(c);
            }
        }
        return modeloCarreras;
    }

    public DefaultListModel<Materias> getModeloMaterias(Carreras carreraSeleccionada) {
        DefaultListModel<Materias> modeloMaterias = new DefaultListModel<>();
        ListaObjetos lista = new ListaObjetos();
        
        for (String[] fila : datos) {
            if (fila[1].equals(carreraSeleccionada.getCarrera())) {
                Materias m = new Materias(fila[4], fila[1]); // MATERIA, P.EDUCATIVO
                if (lista.Insertar(m)) {
                    modeloMaterias.addElement(m);
                }
            }
        }
        return modeloMaterias;
    }

    public DefaultListModel<Asignaturas> getModeloAsignaturas(Materias materiaSeleccionada) {
        DefaultListModel<Asignaturas> modeloAsignaturas = new DefaultListModel<>();
        ListaObjetos lista = new ListaObjetos();
        
        for (String[] fila : datos) {
            if (fila[4].equals(materiaSeleccionada.getMateria()) && fila[1].equals(materiaSeleccionada.getCarrera())) {
                Asignaturas a = new Asignaturas(fila[1], fila[4], fila[3], fila[2]); // Carrera, Materia, Profesor, Letra/Grupo
                if (lista.Insertar(a)) {
                    modeloAsignaturas.addElement(a);
                }
            }
        }
        return modeloAsignaturas;
    }
}