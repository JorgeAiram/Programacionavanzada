package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import Librerias.Libreria;
import Modelo.Asignaturas;
import Modelo.Carreras;
import Modelo.Materias;
import Modelo.ModeloTabla;
import vista.Vista_datos;

public class Cejemplotabla {
	String archivo = "ReporteInscripcionCalificaciones_2025_1.cvs";
    ModeloTabla modelo;
    Vista_datos vista;
    ArrayList<String[]> datos;
    TableRowSorter<javax.swing.table.DefaultTableModel> sorter;

    public Cejemplotabla() {
        datos = new ArrayList<>();
        
        if (Libreria.ExisteArchivo(archivo)) {
            datos = Libreria.LeerDatosCSV(archivo);
        } else {
            System.out.println("ERROR: No se encontró el archivo CSV en la ruta principal.");
        }

        modelo = new ModeloTabla(datos);
        vista = new Vista_datos();
       
        vista.getListacarrera().setModel(modelo.getModeloCarreras());
        if (modelo.getModeloCarreras().getSize() > 0) vista.getListacarrera().setSelectedIndex(0);
    
        vista.getTable().setModel(modelo.getModelo());
        sorter = new TableRowSorter<>(modelo.getModelo());
        vista.getTable().setRowSorter(sorter);

        vista.getListacarrera().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Carreras c = (Carreras) vista.getListacarrera().getSelectedValue();
                    if (c != null) {
                        vista.getListamateria().setModel(modelo.getModeloMaterias(c));
                        if (vista.getListamateria().getModel().getSize() > 0) {
                            vista.getListamateria().setSelectedIndex(0);
                        } else {
                            vista.getListaAsignatura().setModel(new javax.swing.DefaultListModel<>());
                        }
                    }
                }
            }
        });

        vista.getListamateria().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Materias m = (Materias) vista.getListamateria().getSelectedValue();
                    if (m != null) {
                        vista.getListaAsignatura().setModel(modelo.getModeloAsignaturas(m));
                        if (vista.getListaAsignatura().getModel().getSize() > 0) {
                            vista.getListaAsignatura().setSelectedIndex(0);
                        }
                    }
                }
            }
        });

        vista.getListaAsignatura().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Asignaturas asig = (Asignaturas) vista.getListaAsignatura().getSelectedValue();
                    if (asig != null) {
                        filtrarTabla(asig);
                    }
                }
            }
        });

        Carreras c = (Carreras) vista.getListacarrera().getSelectedValue();
        if (c != null) {
            vista.getListamateria().setModel(modelo.getModeloMaterias(c));
            if (vista.getListamateria().getModel().getSize() > 0) vista.getListamateria().setSelectedIndex(0);
        }

        vista.setVisible(true);
    }

    private void filtrarTabla(Asignaturas asig) {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();
        filtros.add(RowFilter.regexFilter("(?i)\\Q" + asig.getCarrera() + "\\E", 1));
        filtros.add(RowFilter.regexFilter("(?i)\\Q" + asig.getMateria() + "\\E", 4));
        filtros.add(RowFilter.regexFilter("(?i)\\Q" + asig.getProfesor() + "\\E", 3));
        filtros.add(RowFilter.regexFilter("(?i)\\Q" + asig.getGrupo() + "\\E", 2));
        
        sorter.setRowFilter(RowFilter.andFilter(filtros));
    }
}