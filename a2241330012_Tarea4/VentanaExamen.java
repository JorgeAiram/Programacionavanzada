package Tarea4;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class VentanaExamen extends JFrame {
    private Examen examenActual;
    private File archivoActual;
    private boolean preguntarAporB = true;

    private JMenuBar menuBar;
    private JMenu menuArchivo, menuOpciones;
    private JMenuItem itemAbrir, itemSalir, itemAporB, itemBporA;

    private JLabel lblEnunciado;
    private JRadioButton[] radioOpciones;
    private ButtonGroup grupoOpciones;
    private JButton btnStart, btnNext;
    private JTextArea txtComentarios;

    public VentanaExamen() {
        setTitle("Multiple Choice Exam");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        // --- MENÚS ---
        menuBar = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        itemAbrir = new JMenuItem("Abrir (.csv)");
        itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemSalir);

        menuOpciones = new JMenu("Opciones");
        itemAporB = new JRadioButtonMenuItem("Preguntar Término A dado B", true);
        itemBporA = new JRadioButtonMenuItem("Preguntar Término B dado A", false);
        ButtonGroup grupoOpcionesMenu = new ButtonGroup();
        grupoOpcionesMenu.add(itemAporB);
        grupoOpcionesMenu.add(itemBporA);
        menuOpciones.add(itemAporB);
        menuOpciones.add(itemBporA);

        menuBar.add(menuArchivo);
        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

        // --- PANEL CENTRAL (Preguntas y Opciones) ---
        JPanel panelCentral = new JPanel(new GridLayout(6, 1));
        lblEnunciado = new JLabel("Abre un archivo para comenzar", SwingConstants.CENTER);
        lblEnunciado.setFont(new Font("Arial", Font.BOLD, 16));
        panelCentral.add(lblEnunciado);

        radioOpciones = new JRadioButton[4];
        grupoOpciones = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            radioOpciones[i] = new JRadioButton("Opción " + (i + 1));
            radioOpciones[i].setEnabled(false);
            grupoOpciones.add(radioOpciones[i]);
            panelCentral.add(radioOpciones[i]);
        }
        
        txtComentarios = new JTextArea();
        txtComentarios.setEditable(false);
        txtComentarios.setBackground(new Color(255, 255, 204)); // Fondo amarillo claro
        panelCentral.add(new JScrollPane(txtComentarios));

        add(panelCentral, BorderLayout.CENTER);

        // --- PANEL INFERIOR (Botones) ---
        JPanel panelInferior = new JPanel();
        btnStart = new JButton("Start Exam");
        btnNext = new JButton("Next Question");
        btnStart.setEnabled(false);
        btnNext.setEnabled(false);
        
        panelInferior.add(btnStart);
        panelInferior.add(btnNext);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        itemAbrir.addActionListener(e -> abrirArchivo());
        itemSalir.addActionListener(e -> System.exit(0));
        
        itemAporB.addActionListener(e -> recargarExamen(true));
        itemBporA.addActionListener(e -> recargarExamen(false));

        btnStart.addActionListener(e -> iniciarExamen());
        btnNext.addActionListener(e -> evaluarYAvanzar());
    }

    private void abrirArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoActual = chooser.getSelectedFile();
            recargarExamen(preguntarAporB);
        }
    }

    private void recargarExamen(boolean aPorB) {
        if (archivoActual == null) return;
        this.preguntarAporB = aPorB;
        examenActual = GestorArchivos.cargarExamen(archivoActual, aPorB);
        
        if (examenActual != null) {
            lblEnunciado.setText("Archivo cargado: " + examenActual.getTotal() + " preguntas.");
            btnStart.setEnabled(true);
            txtComentarios.setText("Presiona 'Start Exam' para iniciar.");
        }
    }

    private void iniciarExamen() {
        if (examenActual == null) return;
        
        // Bloqueo de menús de configuración
        menuArchivo.setEnabled(false);
        menuOpciones.setEnabled(false);
        
        btnStart.setEnabled(false);
        btnNext.setEnabled(true);
        examenActual.barajar();
        mostrarPreguntaActual();
    }

    private void mostrarPreguntaActual() {
        Pregunta p = examenActual.getPreguntaActual();
        lblEnunciado.setText(p.getEnunciado());
        
        // Actualización Dinámica y Deselección
        grupoOpciones.clearSelection();
        String[] opciones = p.getOpciones();
        for (int i = 0; i < 4; i++) {
            radioOpciones[i].setText(opciones[i]);
            radioOpciones[i].setEnabled(true);
        }
        txtComentarios.setText("Pregunta " + (examenActual.getIndiceActual() + 1) + " de " + examenActual.getTotal());
    }

    private void evaluarYAvanzar() {
        // Validación de Selección
        String respuestaSeleccionada = null;
        for (JRadioButton rb : radioOpciones) {
            if (rb.isSelected()) {
                respuestaSeleccionada = rb.getText();
                break;
            }
        }

        if (respuestaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una respuesta antes de continuar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Evaluar
        Pregunta p = examenActual.getPreguntaActual();
        if (respuestaSeleccionada.equals(p.getRespuestaCorrecta())) {
            examenActual.registrarRespuesta(true);
        }

        // Avanzar o Terminar
        if (examenActual.hasNext()) {
            examenActual.avanzar();
            mostrarPreguntaActual();
        } else {
            finalizarExamen();
        }
    }

    private void finalizarExamen() {
        for (JRadioButton rb : radioOpciones) {
            rb.setEnabled(false);
        }
        btnNext.setEnabled(false);
        menuArchivo.setEnabled(true); // Desbloquear para poder abrir otro o salir
        menuOpciones.setEnabled(true);
        
        txtComentarios.setText("¡Examen Terminado!\nCalificación: " + 
            examenActual.getRespuestasCorrectas() + " de " + examenActual.getTotal());
        lblEnunciado.setText("Fin del examen");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaExamen().setVisible(true);
        });
    }
}