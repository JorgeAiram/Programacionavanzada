package controlador;

import modelo.*;
import persistencia.EvaluacionPersistencia;
import persistencia.LectorDatosBase;
import persistencia.ReporteExcelEvaluacion;
import vista.VistaEvaluacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ControladorEvaluacion {

    private VistaEvaluacion vista;
    private EvaluacionPersistencia persistencia;
    private LectorDatosBase lector;
    private ReporteExcelEvaluacion reporteExcel;

    // Carpeta donde se guardan los Excel (puede cambiar con JFileChooser)
    private File carpetaReportes = new File(".");

    public ControladorEvaluacion(VistaEvaluacion vista, EvaluacionPersistencia persistencia) {
        this.vista       = vista;
        this.persistencia = persistencia;
        lector           = new LectorDatosBase();
        reporteExcel     = new ReporteExcelEvaluacion();

        cargarCombos();
        inicializarRubrica();
        eventos();
        actualizarSemaforo();
    }

    // ======================
    // EVENTOS
    // ======================
    private void eventos() {

        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnCargar.addActionListener(e -> cargar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnGenerarExcel.addActionListener(e -> generarExcel());
        vista.btnMarcarTodos.addActionListener(e -> marcarTodos());

        vista.comboAsignatura.addActionListener(e -> cargarListaCotejo());

        vista.modeloRubrica.addTableModelListener(e -> {
            calcularPromedios();
            actualizarSemaforo();
        });
    }

    // ======================
    // COMBOS
    // ======================
    private void cargarCombos() {

        vista.comboAsignatura.removeAllItems();
        vista.comboProfesor.removeAllItems();
        vista.comboGrupo.removeAllItems();

        lector.obtenerAsignaturas().forEach(a -> vista.comboAsignatura.addItem(a));
        lector.obtenerProfesores().forEach(p -> vista.comboProfesor.addItem(p));
        lector.obtenerGrupos().forEach(g -> vista.comboGrupo.addItem(g));
    }

    // ======================
    // RUBRICA
    // ======================
    private void inicializarRubrica() {

        DefaultTableModel modelo = vista.modeloRubrica;
        modelo.setRowCount(0);

        for (int i = 1; i <= 4; i++) {
            modelo.addRow(new Object[]{"Alumno " + i, 0, 0, 0, 0});
        }
    }

    private void calcularPromedios() {

        DefaultTableModel modelo = vista.modeloRubrica;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                int c1 = Integer.parseInt(modelo.getValueAt(i, 1).toString());
                int c2 = Integer.parseInt(modelo.getValueAt(i, 2).toString());
                int c3 = Integer.parseInt(modelo.getValueAt(i, 3).toString());
                modelo.setValueAt((c1 + c2 + c3) / 3, i, 4);
            } catch (Exception ignored) {}
        }
    }

    // ======================
    // LISTA DE COTEJO
    // ======================
    private void cargarListaCotejo() {

        vista.panelListaCotejo.removeAll();

        if (vista.comboAsignatura.getSelectedItem() == null) return;

        String asignatura = vista.comboAsignatura.getSelectedItem().toString();

        lector.obtenerAtributosPorAsignatura(asignatura)
                .forEach(a -> vista.panelListaCotejo.add(new JCheckBox(a)));

        vista.panelListaCotejo.revalidate();
        vista.panelListaCotejo.repaint();
        actualizarSemaforo();
    }

    private void marcarTodos() {
        for (Component c : vista.panelListaCotejo.getComponents()) {
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(true);
            }
        }
    }

    // ======================
    // SEMAFORO
    // ======================
    public void actualizarSemaforo() {

        // Verde: existe en JSON y tiene Excel generado
        String id = generarId();
        boolean enJson = persistencia.buscarEvaluacion(id) != null;

        // Amarillo: hay datos en pantalla pero no se ha guardado todavia
        boolean hayDatos = !vista.txtFecha.getText().trim().isEmpty()
                || rubricaTieneCalificaciones();

        if (enJson) {
            vista.lblSemaforo.setForeground(new Color(0, 160, 0)); // verde
            vista.lblSemaforo.setToolTipText("Verde: guardado en JSON");
        } else if (hayDatos) {
            vista.lblSemaforo.setForeground(new Color(220, 160, 0)); // amarillo
            vista.lblSemaforo.setToolTipText("Amarillo: datos sin guardar");
        } else {
            vista.lblSemaforo.setForeground(Color.RED);
            vista.lblSemaforo.setToolTipText("Rojo: sin datos");
        }
    }

    private boolean rubricaTieneCalificaciones() {
        DefaultTableModel m = vista.modeloRubrica;
        for (int i = 0; i < m.getRowCount(); i++) {
            try {
                if (Integer.parseInt(m.getValueAt(i, 4).toString()) > 0) return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    // ======================
    // CRUD
    // ======================
    private void guardar() {
        try {
            Evaluacion e = construirEvaluacion();

            if (!persistencia.guardarEvaluacion(e)) {
                JOptionPane.showMessageDialog(vista, "La evaluacion ya existe. Use Actualizar.");
                return;
            }

            JOptionPane.showMessageDialog(vista, "Evaluacion guardada correctamente.");
            actualizarSemaforo();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + ex.getMessage());
        }
    }

    private void cargar() {

        Evaluacion e = persistencia.buscarEvaluacion(generarId());

        if (e == null) {
            JOptionPane.showMessageDialog(vista, "No se encontro ninguna evaluacion con esa combinacion.");
            return;
        }

        cargarEnVista(e);
        actualizarSemaforo();
        JOptionPane.showMessageDialog(vista, "Evaluacion cargada correctamente.");
    }

    private void actualizar() {
        try {
            Evaluacion e = construirEvaluacion();

            if (!persistencia.actualizarEvaluacion(e)) {
                JOptionPane.showMessageDialog(vista, "No existe registro para actualizar. Use Guardar primero.");
                return;
            }

            JOptionPane.showMessageDialog(vista, "Evaluacion actualizada correctamente.");
            actualizarSemaforo();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {

        int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Seguro que deseas eliminar esta evaluacion?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        if (!persistencia.eliminarEvaluacion(generarId())) {
            JOptionPane.showMessageDialog(vista, "No se encontro la evaluacion.");
            return;
        }

        JOptionPane.showMessageDialog(vista, "Evaluacion eliminada.");
        limpiar();
        actualizarSemaforo();
    }

    // ======================
    // GENERAR EXCEL
    // ======================
    private void generarExcel() {
        try {
            // Punto extra: JFileChooser para elegir carpeta
            JFileChooser chooser = new JFileChooser(carpetaReportes);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setDialogTitle("Selecciona la carpeta donde guardar el reporte");

            int opcion = chooser.showSaveDialog(vista);
            if (opcion != JFileChooser.APPROVE_OPTION) return;

            carpetaReportes = chooser.getSelectedFile();

            Evaluacion e = construirEvaluacion();
            String rutaArchivo = carpetaReportes.getAbsolutePath()
                    + File.separator + e.getId() + ".xlsx";

            reporteExcel.generarExcel(e, rutaArchivo);

            JOptionPane.showMessageDialog(vista,
                    "Excel generado correctamente:\n" + rutaArchivo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al generar Excel: " + ex.getMessage());
        }
    }

    // ======================
    // CONSTRUIR EVALUACION
    // ======================
    private Evaluacion construirEvaluacion() {

        // Lista de cotejo
        List<String> seleccionados = new ArrayList<>();
        for (Component c : vista.panelListaCotejo.getComponents()) {
            if (c instanceof JCheckBox && ((JCheckBox) c).isSelected()) {
                seleccionados.add(((JCheckBox) c).getText());
            }
        }

        // Rubrica -> equipos
        List<Equipo> equipos = new ArrayList<>();
        DefaultTableModel modelo = vista.modeloRubrica;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String nombreAlumno = modelo.getValueAt(i, 0).toString();
            int promedio = Integer.parseInt(modelo.getValueAt(i, 4).toString());

            List<Alumno> alumnos = new ArrayList<>();
            alumnos.add(new Alumno("N/A", nombreAlumno));
            equipos.add(new Equipo(alumnos, promedio));
        }

        return new Evaluacion(
                generarId(),
                vista.comboAsignatura.getSelectedItem().toString(),
                vista.comboProfesor.getSelectedItem().toString(),
                vista.comboGrupo.getSelectedItem().toString(),
                new ProductoIntegrador(
                        vista.txtFecha.getText(),
                        Integer.parseInt(vista.txtCriterio1.getText().isEmpty() ? "0" : vista.txtCriterio1.getText()),
                        Integer.parseInt(vista.txtCriterio2.getText().isEmpty() ? "0" : vista.txtCriterio2.getText()),
                        Integer.parseInt(vista.txtCriterio3.getText().isEmpty() ? "0" : vista.txtCriterio3.getText()),
                        Integer.parseInt(vista.txtCriterio4.getText().isEmpty() ? "0" : vista.txtCriterio4.getText()),
                        Integer.parseInt(vista.txtCriterio5.getText().isEmpty() ? "0" : vista.txtCriterio5.getText()),
                        Integer.parseInt(vista.txtCriterio6.getText().isEmpty() ? "0" : vista.txtCriterio6.getText()),
                        vista.txtObservaciones.getText()
                ),
                new ListaCotejo(seleccionados),
                equipos
        );
    }

    // ======================
    // CARGAR EN VISTA (completo)
    // ======================
    private void cargarEnVista(Evaluacion e) {

        // Datos generales
        if (e.getAsignatura() != null)
            vista.comboAsignatura.setSelectedItem(e.getAsignatura());
        if (e.getProfesor() != null)
            vista.comboProfesor.setSelectedItem(e.getProfesor());
        if (e.getGrupo() != null)
            vista.comboGrupo.setSelectedItem(e.getGrupo());

        // Producto integrador
        ProductoIntegrador p = e.getProductoIntegrador();
        vista.txtFecha.setText(p.getFecha() != null ? p.getFecha() : "");
        vista.txtCriterio1.setText(String.valueOf(p.getCriterio1()));
        vista.txtCriterio2.setText(String.valueOf(p.getCriterio2()));
        vista.txtCriterio3.setText(String.valueOf(p.getCriterio3()));
        vista.txtCriterio4.setText(String.valueOf(p.getCriterio4()));
        vista.txtCriterio5.setText(String.valueOf(p.getCriterio5()));
        vista.txtCriterio6.setText(String.valueOf(p.getCriterio6()));
        vista.txtObservaciones.setText(p.getObservaciones() != null ? p.getObservaciones() : "");

        // Rubrica: recargar equipos guardados
        DefaultTableModel modelo = vista.modeloRubrica;
        modelo.setRowCount(0);

        if (e.getEquipos() != null) {
            for (Equipo eq : e.getEquipos()) {
                String nombre = eq.getAlumnos() != null && !eq.getAlumnos().isEmpty()
                        ? eq.getAlumnos().get(0).getNombre() : "Alumno";
                modelo.addRow(new Object[]{nombre, 0, 0, 0, eq.getCalificacionRubrica()});
            }
        }

        // Rellenar hasta 4 filas si faltan
        while (modelo.getRowCount() < 4) {
            int idx = modelo.getRowCount() + 1;
            modelo.addRow(new Object[]{"Alumno " + idx, 0, 0, 0, 0});
        }

        // Lista de cotejo: marcar los indicadores guardados
        List<String> guardados = e.getListaCotejo() != null
                ? e.getListaCotejo().getIndicadoresSeleccionados()
                : new ArrayList<>();

        for (Component c : vista.panelListaCotejo.getComponents()) {
            if (c instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) c;
                cb.setSelected(guardados != null && guardados.contains(cb.getText()));
            }
        }
    }

    // ======================
    // AUXILIARES
    // ======================
    private String generarId() {
        String asig = vista.comboAsignatura.getSelectedItem() != null
                ? vista.comboAsignatura.getSelectedItem().toString() : "X";
        String prof = vista.comboProfesor.getSelectedItem() != null
                ? vista.comboProfesor.getSelectedItem().toString() : "X";
        String grp  = vista.comboGrupo.getSelectedItem() != null
                ? vista.comboGrupo.getSelectedItem().toString() : "X";
        return asig + "_" + prof + "_" + grp;
    }

    private void limpiar() {
        vista.txtFecha.setText("");
        vista.txtCriterio1.setText("");
        vista.txtCriterio2.setText("");
        vista.txtCriterio3.setText("");
        vista.txtCriterio4.setText("");
        vista.txtCriterio5.setText("");
        vista.txtCriterio6.setText("");
        vista.txtObservaciones.setText("");
        inicializarRubrica();
        vista.panelListaCotejo.removeAll();
        vista.panelListaCotejo.revalidate();
        vista.panelListaCotejo.repaint();
        actualizarSemaforo();
    }
}
