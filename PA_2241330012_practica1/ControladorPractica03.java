package PA_2241330012_practica1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPractica03 implements ActionListener {
    private VistaPractica03 vista;
    private ListaInsumos modeloInsumos;
    private ListaCategorias modeloCategorias;
    private Archivotxt archivoInsumos;
    private Archivotxt archivoCategorias;
    private boolean modoAgregar = false;

    public ControladorPractica03(VistaPractica03 vista) {
        this.vista = vista;
        this.modeloInsumos = new ListaInsumos();
        this.modeloCategorias = new ListaCategorias();
        
        // Nombres de archivos según el manual
        this.archivoInsumos = new Archivotxt("insumos.txt");
        this.archivoCategorias = new Archivotxt("categorias.txt");

        // Listeners
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);

        // Carga inicial requerida por la Actividad 4
        cargarDatosIniciales();
        estadoInicial();
    }

    private void cargarDatosIniciales() {
        // 1. Intentar leer categorías desde archivo
        ArrayList<String[]> lineasCat = archivoCategorias.leerLineas();
        
        // Si el archivo está vacío, creamos datos por defecto (Actividad 4)
        if (lineasCat.isEmpty()) {
            System.out.println("Creando archivo de categorías por defecto...");
            String datosIniciales = "M1,Materiales\nM2,Mano de Obra\nM3,Maquinaria";
            archivoCategorias.guardarString(datosIniciales);
            // Volvemos a leer después de crear
            lineasCat = archivoCategorias.leerLineas();
        }

        vista.comboCategoria.removeAllItems();
        for (String[] datos : lineasCat) {
            if (datos.length >= 2) {
                Categoria cat = new Categoria(datos[0], datos[1]);
                modeloCategorias.agregarCategoria(cat);
                vista.comboCategoria.addItem(cat.toString());
            }
        }

        // 2. Cargar Insumos
        ArrayList<String[]> lineasIns = archivoInsumos.leerLineas();
        for (String[] datos : lineasIns) {
            if (datos.length >= 3) {
                modeloInsumos.agregarInsumo(new Insumo(datos[0], datos[1], datos[2]));
            }
        }
    }

    private void estadoInicial() {
        vista.txtId.setEditable(false);
        vista.txtInsumo.setEditable(false);
        
        // Requerimiento Actividad 4: Habilitar combo y seleccionar index 0
        vista.comboCategoria.setEnabled(true);
        if(vista.comboCategoria.getItemCount() > 0) {
            vista.comboCategoria.setSelectedIndex(0);
        }

        vista.btnAgregar.setText("Agregar");
        vista.btnSalir.setText("Salir");
        vista.btnEliminar.setEnabled(true);
        vista.txtId.setText("");
        vista.txtInsumo.setText("");
        modoAgregar = false;
        actualizarVista();
    }

    private void actualizarVista() {
        vista.areaProductos.setText(modeloInsumos.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            if (!modoAgregar) {
                habilitarFormulario(true);
            } else {
                guardarNuevoInsumo();
            }
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarInsumo();
        } else if (e.getSource() == vista.btnSalir) {
            if (modoAgregar) estadoInicial(); else vista.dispose();
        }
    }

    private void habilitarFormulario(boolean b) {
        vista.txtId.setEditable(b);
        vista.txtInsumo.setEditable(b);
        vista.btnAgregar.setText("Salvar");
        vista.btnSalir.setText("Cancelar");
        vista.btnEliminar.setEnabled(!b);
        modoAgregar = b;
    }

    private void guardarNuevoInsumo() {
        if(vista.txtId.getText().isEmpty() || vista.txtInsumo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Error: Datos incompletos");
            return;
        }

        // Obtener ID de categoría del ComboBox (ej. "M1")
        String seleccion = vista.comboCategoria.getSelectedItem().toString();
        String idCat = seleccion.split(" - ")[0];

        Insumo nuevo = new Insumo(vista.txtId.getText(), vista.txtInsumo.getText(), idCat);
        
        if (modeloInsumos.agregarInsumo(nuevo)) {
            persistirInsumos();
            estadoInicial();
        } else {
            JOptionPane.showMessageDialog(vista, "El ID ya existe");
        }
    }

    private void eliminarInsumo() {
        String id = JOptionPane.showInputDialog(vista, "ID a eliminar:");
        if (id != null && modeloInsumos.eliminarInsumoPorId(id)) {
            persistirInsumos();
            actualizarVista();
        }
    }

    private void persistirInsumos() {
        StringBuilder sb = new StringBuilder();
        for (Insumo i : modeloInsumos.getLista()) {
            sb.append(i.toCSV()).append("\n");
        }
        archivoInsumos.guardarString(sb.toString());
    }

    public static void main(String[] args) {
        VistaPractica03 v = new VistaPractica03();
        ControladorPractica03 c = new ControladorPractica03(v);
        v.setVisible(true);
    }
}