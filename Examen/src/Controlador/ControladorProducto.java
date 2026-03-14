package Controlador;
import Modelo.GestionProductos;
import Modelo.Producto;
import persistencia.GestorCSV;
import vista.VentanaPrincipal;
import vista.VistaProducto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorProducto implements ActionListener {
    private VentanaPrincipal mdi;
    private VistaProducto vistaProd;
    private GestionProductos gestionProd;
    private GestorCSV csv;

    public ControladorProducto(VentanaPrincipal mdi, VistaProducto vistaProd, GestionProductos gestionProd, GestorCSV csv) {
        this.mdi = mdi;
        this.vistaProd = vistaProd;
        this.gestionProd = gestionProd;
        this.csv = csv;

        this.mdi.menuProductos.addActionListener(this);
        
        
        this.vistaProd.btnGuardar.addActionListener(this);
        this.vistaProd.btnLimpiar.addActionListener(this);
        this.vistaProd.btnBuscar.addActionListener(this);
        this.vistaProd.btnModificar.addActionListener(this);
        this.vistaProd.btnEliminar.addActionListener(this);
        this.vistaProd.btnExportarLista.addActionListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mdi.menuProductos) {
            if (!vistaProd.isVisible()) {
                mdi.escritorio.add(vistaProd);
                vistaProd.setVisible(true);
                actualizarTabla(); 
            }
        } 
        else if (e.getSource() == vistaProd.btnGuardar) {
            guardarProducto();
        } 
        else if (e.getSource() == vistaProd.btnLimpiar) {
            limpiarFormulario();
        } 
        else if (e.getSource() == vistaProd.btnBuscar) {
            buscarProducto();
        } 
        else if (e.getSource() == vistaProd.btnModificar) {
            modificarProducto();
        } 
        else if (e.getSource() == vistaProd.btnEliminar) {
            eliminarProducto();
        }
        else if (e.getSource() == vistaProd.btnExportarLista) {
            exportarDatos();
        }
    }


    private void guardarProducto() {
        if (vistaProd.txtId.getText().isEmpty() || vistaProd.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaProd, "El ID y Nombre no pueden estar vacíos.");
            return;
        }

        try {
            String id = vistaProd.txtId.getText();
            
            if (gestionProd.existe(id)) {
                JOptionPane.showMessageDialog(vistaProd, "Ese ID ya está registrado. Use otro.");
                return;
            }

            Producto p = capturarDatosPantalla();
            gestionProd.insertar(p);
            
            guardarYRefrescar("Producto guardado con éxito.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaProd, "Los precios y stock deben ser números.");
        }
    }

    private void buscarProducto() {
        String idBuscado = JOptionPane.showInputDialog(vistaProd, "Escriba el ID del producto a buscar:");
        
        if (idBuscado != null && !idBuscado.isEmpty()) {
            Producto p = gestionProd.buscar(idBuscado);
            
            if (p != null) {
                vistaProd.txtId.setText(p.getId());
                vistaProd.txtId.setEditable(false); 
                
                vistaProd.txtNombre.setText(p.getNombre());
                vistaProd.txtDescripcion.setText(p.getDescripcion());
                vistaProd.comboCategoria.setSelectedItem(p.getCategoria());
                vistaProd.txtPrecioCompra.setText(String.valueOf(p.getPrecioCompra()));
                vistaProd.txtPrecioVenta.setText(String.valueOf(p.getPrecioVenta()));
                vistaProd.txtStockInicial.setText(String.valueOf(p.getStockInicial()));
                vistaProd.txtStockMinimo.setText(String.valueOf(p.getStockMinimo()));
                
                if (p.getEstado().equals("Activo")) vistaProd.rbActivo.setSelected(true);
                else vistaProd.rbDesactivado.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(vistaProd, "No se encontró ningún producto con ese ID.");
            }
        }
    }

    private void modificarProducto() {
        if (vistaProd.txtId.isEditable()) {
            JOptionPane.showMessageDialog(vistaProd, "Primero debe buscar un producto con el botón 'Buscar'.");
            return;
        }

        try {
            Producto pActualizado = capturarDatosPantalla();
            gestionProd.actualizar(pActualizado);
            
            vistaProd.txtId.setEditable(true);
            guardarYRefrescar("Producto modificado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaProd, "Los precios y stock deben ser números.");
        }
    }

    private void eliminarProducto() {
        String idBorrar = JOptionPane.showInputDialog(vistaProd, "Escriba el ID del producto que desea eliminar:");
        
        if (idBorrar != null && !idBorrar.isEmpty()) {
            int respuesta = JOptionPane.showConfirmDialog(vistaProd, "¿Está seguro de eliminar el ID " + idBorrar + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                if (gestionProd.eliminar(idBorrar)) {
                    guardarYRefrescar("Producto eliminado.");
                } else {
                    JOptionPane.showMessageDialog(vistaProd, "Ese ID no existe.");
                }
            }
        }
    }

    private void exportarDatos() {
        
        csv.exportarCSV(gestionProd.getLista());
      
        JOptionPane.showMessageDialog(vistaProd, "¡Catálogo exportado exitosamente al archivo productos.csv!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private Producto capturarDatosPantalla() {
        String id = vistaProd.txtId.getText();
        String nombre = vistaProd.txtNombre.getText();
        String desc = vistaProd.txtDescripcion.getText();
        String cat = vistaProd.comboCategoria.getSelectedItem().toString();
        double pC = Double.parseDouble(vistaProd.txtPrecioCompra.getText());
        double pV = Double.parseDouble(vistaProd.txtPrecioVenta.getText());
        int sI = Integer.parseInt(vistaProd.txtStockInicial.getText());
        int sM = Integer.parseInt(vistaProd.txtStockMinimo.getText());
        String estado = vistaProd.rbActivo.isSelected() ? "Activo" : "Desactivado";
        
        return new Producto(id, nombre, desc, cat, pC, pV, sI, sM, estado);
    }

    private void limpiarFormulario() {
        vistaProd.txtId.setText("");
        vistaProd.txtId.setEditable(true);
        vistaProd.txtNombre.setText("");
        vistaProd.txtDescripcion.setText("");
        vistaProd.comboCategoria.setSelectedIndex(0);
        vistaProd.txtPrecioCompra.setText("");
        vistaProd.txtPrecioVenta.setText("");
        vistaProd.txtStockInicial.setText("");
        vistaProd.txtStockMinimo.setText("");
        vistaProd.rbActivo.setSelected(true);
    }

    private void guardarYRefrescar(String mensaje) {
        csv.exportarCSV(gestionProd.getLista()); 
        actualizarTabla(); 
        limpiarFormulario(); 
        JOptionPane.showMessageDialog(vistaProd, mensaje);
    }

    public void actualizarTabla() {
        vistaProd.modeloTabla.setRowCount(0); 
        for (Producto p : gestionProd.getLista()) {
            Object[] fila = {p.getId(), p.getNombre(), p.getCategoria(), p.getStockInicial(), p.getPrecioVenta(), p.getEstado()};
            vistaProd.modeloTabla.addRow(fila);
        }
    }
}