package PA_2241330012_practica2;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MainPractica2 extends JFrame {
    private JDesktopPane escritorio;
    private ListaCategorias modeloCategorias;
    private ListaInsumos modeloInsumos;
    private Archivotxt archivoCat, archivoIns;
    
    // Variable para la Actividad 5: Determina si usamos txt o xml
    private String tipoBaseDatos = "TXT"; 

    public MainPractica2() {
        setTitle("Sistema Avanzado - Práctica 2");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        escritorio = new JDesktopPane();
        setContentPane(escritorio);

        // Inicializar Modelos y Archivos
        modeloCategorias = new ListaCategorias();
        modeloInsumos = new ListaInsumos();
        archivoCat = new Archivotxt("categorias.txt");
        archivoIns = new Archivotxt("insumos.txt");
        
        // Crear datos base si no existen
        if (!archivoCat.existe()) archivoCat.guardar("M1,Materiales\nM2,Mano de Obra\nM3,Maquinaria");
        modeloCategorias.cargarCategorias(archivoCat.cargar());
        if (archivoIns.existe()) modeloInsumos.cargar(archivoIns.cargar());

        // --- CREACIÓN DEL MENÚ EXACTO DEL PDF (Actividad 5) ---
        JMenuBar barra = new JMenuBar();

        // 1. Menú Módulos (Categorías, Insumos, Obras)
        JMenu menuModulos = new JMenu("Módulos");
        JMenuItem itemCategorias = new JMenuItem("1. Gestión de Categorías");
        JMenuItem itemInsumos = new JMenuItem("2. Gestión de Insumos");
        JMenuItem itemObras = new JMenuItem("3. Gestión de Obras");
        
        menuModulos.add(itemCategorias);
        menuModulos.add(itemInsumos);
        menuModulos.addSeparator();
        menuModulos.add(itemObras);

        // 2. Menú Configuración (Tipo de BD)
        JMenu menuConfiguracion = new JMenu("Configuración");
        JMenuItem itemBD = new JMenuItem("Tipo de Base de Datos (TXT / XML)");
        menuConfiguracion.add(itemBD);

        barra.add(menuModulos);
        barra.add(menuConfiguracion);
        setJMenuBar(barra);

        // --- EVENTOS DEL MENÚ ---
        
        itemInsumos.addActionListener(e -> abrirVentanaInsumos());
        
        itemCategorias.addActionListener(e -> {
            // Aquí iría la llamada al JInternalFrame de Categorías (Placeholder)
            JOptionPane.showMessageDialog(this, "Abriendo módulo de Categorías...\n(Base de datos actual: " + tipoBaseDatos + ")", "Módulo Categorías", JOptionPane.INFORMATION_MESSAGE);
        });

        itemObras.addActionListener(e -> {
            // Aquí iría la llamada al JInternalFrame de Obras (Placeholder)
            JOptionPane.showMessageDialog(this, "Abriendo módulo de Obras...\n(Base de datos actual: " + tipoBaseDatos + ")", "Módulo Obras", JOptionPane.INFORMATION_MESSAGE);
        });

        itemBD.addActionListener(e -> {
            // Opción de configuración pedida en Actividad 5
            String[] opciones = {"Usar archivos TXT", "Usar archivos XML"};
            int seleccion = JOptionPane.showOptionDialog(this, 
                    "Seleccione el tipo de almacenamiento a utilizar:", 
                    "Configuración de Base de Datos", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, opciones, opciones[0]);
            
            if (seleccion == 0) {
                tipoBaseDatos = "TXT";
                JOptionPane.showMessageDialog(this, "El sistema ahora guarda y carga en formato .TXT");
            } else if (seleccion == 1) {
                tipoBaseDatos = "XML";
                JOptionPane.showMessageDialog(this, "El sistema ahora guarda y carga en formato .XML");
            }
        });
    }

    // --- MÓDULO DE INSUMOS COMPLETADO ---
    private void abrirVentanaInsumos() {
        VistaInsumos vista = new VistaInsumos();
        
        // Cargar Datos en JList y JTable
        vista.listaCat.setModel(modeloCategorias.generarModelo());
        vista.tabla.setModel(modeloInsumos.generarTabla(modeloCategorias));

        // Evento: Mostrar Imagen al hacer clic en la tabla
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarImagenSeleccionada(vista);
            }
        });

        // Evento: Cargar Imagen desde la computadora
        vista.btnCargarImg.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un Insumo de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String id = vista.tabla.getValueAt(fila, 0).toString();
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Selecciona imagen para " + id);
            chooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivoOrigen = chooser.getSelectedFile();
                File directorio = new File("Imagenes");
                if (!directorio.exists()) directorio.mkdirs();

                File archivoDestino = new File("Imagenes/" + id + ".png");

                try {
                    Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    mostrarImagenSeleccionada(vista); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Evento: Agregar Insumo (Usa el JDialog)
        vista.btnAdd.addActionListener(e -> {
            DialogoInsumo dialog = new DialogoInsumo(this, modeloCategorias);
            dialog.setVisible(true);
            
            if (dialog.guardado) {
                if(modeloInsumos.agregar(dialog.nuevoInsumo)){
                    if(tipoBaseDatos.equals("TXT")) {
                        archivoIns.guardar(modeloInsumos.toTxt());
                    }
                    // Si fuera XML, se usaría una clase ArchivoXml.guardar()
                    vista.tabla.setModel(modeloInsumos.generarTabla(modeloCategorias));
                } else {
                    JOptionPane.showMessageDialog(this, "El ID ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Evento: Eliminar Insumo
        vista.btnDel.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Ingrese ID a eliminar:");
            if(id != null && !id.isEmpty()) {
                if(modeloInsumos.eliminar(id)){
                    if(tipoBaseDatos.equals("TXT")) {
                        archivoIns.guardar(modeloInsumos.toTxt());
                    }
                    vista.tabla.setModel(modeloInsumos.generarTabla(modeloCategorias));
                    File img = new File("Imagenes/" + id + ".png");
                    if(img.exists()) img.delete();
                    vista.lblImagen.setIcon(null);
                    vista.lblImagen.setText("Sin Imagen");
                } else {
                    JOptionPane.showMessageDialog(this, "ID no encontrado.");
                }
            }
        });

        escritorio.add(vista);
        vista.setVisible(true);
    }

    private void mostrarImagenSeleccionada(VistaInsumos vista) {
        int fila = vista.tabla.getSelectedRow();
        if (fila != -1) {
            String id = vista.tabla.getValueAt(fila, 0).toString();
            String ruta = "Imagenes/" + id + ".png"; 
            File img = new File(ruta);
            if(img.exists()) {
                ImageIcon icono = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH));
                vista.lblImagen.setIcon(icono);
                vista.lblImagen.setText("");
            } else {
                vista.lblImagen.setIcon(null);
                vista.lblImagen.setText("Sin Imagen");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPractica2().setVisible(true));
    }
}