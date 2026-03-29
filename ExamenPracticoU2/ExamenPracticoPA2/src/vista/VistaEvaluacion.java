package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaEvaluacion extends JInternalFrame {

    // ======================
    // DATOS GENERALES
    // ======================
    public JComboBox<String> comboAsignatura;
    public JComboBox<String> comboProfesor;
    public JComboBox<String> comboGrupo;

    public JButton btnCargar;
    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnLimpiar;
    public JButton btnGenerarExcel;

    // Semáforo de estatus
    public JLabel lblSemaforo;

    // ======================
    // PRODUCTO INTEGRADOR
    // ======================
    public JTextField txtFecha;
    public JTextField txtCriterio1;
    public JTextField txtCriterio2;
    public JTextField txtCriterio3;
    public JTextField txtCriterio4;
    public JTextField txtCriterio5;
    public JTextField txtCriterio6;
    public JTextArea txtObservaciones;

    // ======================
    // RUBRICA
    // ======================
    public JTable tablaRubrica;
    public DefaultTableModel modeloRubrica;

    // ======================
    // LISTA DE COTEJO
    // ======================
    public JPanel panelListaCotejo;
    public JButton btnMarcarTodos;

    public VistaEvaluacion() {
        super("Evaluacion de Atributos", true, true, true, true);
        setSize(1050, 650);
        setLayout(new BorderLayout());

        crearPanelSuperior();
        crearPanelCentral();
    }

    private void crearPanelSuperior() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panel.setBorder(BorderFactory.createTitledBorder("Datos Generales"));

        comboAsignatura = new JComboBox<>();
        comboProfesor   = new JComboBox<>();
        comboGrupo      = new JComboBox<>();

        btnCargar       = new JButton("Cargar");
        btnGuardar      = new JButton("Guardar");
        btnActualizar   = new JButton("Actualizar");
        btnEliminar     = new JButton("Eliminar");
        btnLimpiar      = new JButton("Limpiar");
        btnGenerarExcel = new JButton("Generar Excel");

        lblSemaforo = new JLabel("\u25CF");
        lblSemaforo.setFont(new Font("Dialog", Font.BOLD, 22));
        lblSemaforo.setForeground(Color.RED);
        lblSemaforo.setToolTipText("Rojo: sin datos | Amarillo: incompleto | Verde: listo");

        panel.add(new JLabel("Asignatura:"));  panel.add(comboAsignatura);
        panel.add(new JLabel("Profesor:"));    panel.add(comboProfesor);
        panel.add(new JLabel("Grupo:"));       panel.add(comboGrupo);
        panel.add(btnCargar);
        panel.add(btnGuardar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        panel.add(btnGenerarExcel);
        panel.add(lblSemaforo);

        add(panel, BorderLayout.NORTH);
    }

    private void crearPanelCentral() {

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Producto Integrador", crearProductoIntegrador());
        tabs.addTab("Rubrica",             crearRubrica());
        tabs.addTab("Lista de Cotejo",     crearListaCotejo());

        add(tabs, BorderLayout.CENTER);
    }

    private JPanel crearProductoIntegrador() {

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel criterios = new JPanel(new GridLayout(4, 4, 5, 5));
        criterios.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        txtFecha     = new JTextField(8);
        txtCriterio1 = new JTextField(3);
        txtCriterio2 = new JTextField(3);
        txtCriterio3 = new JTextField(3);
        txtCriterio4 = new JTextField(3);
        txtCriterio5 = new JTextField(3);
        txtCriterio6 = new JTextField(3);

        criterios.add(new JLabel("Fecha:"));      criterios.add(txtFecha);
        criterios.add(new JLabel("Criterio 1:")); criterios.add(txtCriterio1);
        criterios.add(new JLabel("Criterio 2:")); criterios.add(txtCriterio2);
        criterios.add(new JLabel("Criterio 3:")); criterios.add(txtCriterio3);
        criterios.add(new JLabel("Criterio 4:")); criterios.add(txtCriterio4);
        criterios.add(new JLabel("Criterio 5:")); criterios.add(txtCriterio5);
        criterios.add(new JLabel("Criterio 6:")); criterios.add(txtCriterio6);

        txtObservaciones = new JTextArea(4, 30);
        JScrollPane scrollObs = new JScrollPane(txtObservaciones);
        scrollObs.setBorder(BorderFactory.createTitledBorder("Observaciones"));

        panel.add(criterios, BorderLayout.NORTH);
        panel.add(scrollObs, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearRubrica() {

        JPanel panel = new JPanel(new BorderLayout());

        modeloRubrica = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 4; // Promedio no editable
            }
        };
        modeloRubrica.addColumn("Alumno");
        modeloRubrica.addColumn("Criterio 1");
        modeloRubrica.addColumn("Criterio 2");
        modeloRubrica.addColumn("Criterio 3");
        modeloRubrica.addColumn("Promedio");

        tablaRubrica = new JTable(modeloRubrica);
        tablaRubrica.setRowHeight(24);

        // Celdas rojas para alumnos reprobados (promedio < 6)
        tablaRubrica.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                try {
                    int promedio = Integer.parseInt(
                            table.getModel().getValueAt(row, 4).toString());
                    if (promedio > 0 && promedio < 6) {
                        c.setBackground(isSelected
                                ? new Color(200, 80, 80)
                                : new Color(255, 180, 180));
                    } else {
                        c.setBackground(isSelected
                                ? table.getSelectionBackground()
                                : Color.WHITE);
                    }
                } catch (Exception ignored) {
                    c.setBackground(isSelected
                            ? table.getSelectionBackground()
                            : Color.WHITE);
                }
                return c;
            }
        });

        panel.add(new JScrollPane(tablaRubrica), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearListaCotejo() {

        panelListaCotejo = new JPanel();
        panelListaCotejo.setLayout(new BoxLayout(panelListaCotejo, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panelListaCotejo);
        scroll.setBorder(BorderFactory.createTitledBorder("Indicadores"));

        btnMarcarTodos = new JButton("Marcar todos");

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(btnMarcarTodos, BorderLayout.NORTH);
        panel.add(scroll,         BorderLayout.CENTER);

        return panel;
    }
}
