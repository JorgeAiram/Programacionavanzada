package Controlador;

import Modelo.GestionProductos;
import Modelo.Producto;
import persistencia.GestorCSV;
import vista.VentanaPrincipal;
import vista.VistaInventario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorInventario implements ActionListener {
    private VentanaPrincipal mdi;
    private VistaInventario vistaInv;
    private GestionProductos gestionProd;
    private GestorCSV csv;

    public ControladorInventario(VentanaPrincipal mdi, VistaInventario vistaInv, GestionProductos gestionProd, GestorCSV csv) {
        this.mdi = mdi;
        this.vistaInv = vistaInv;
        this.gestionProd = gestionProd;
        this.csv = csv;

        this.mdi.menuInventario.addActionListener(this);
        this.vistaInv.btnBuscar.addActionListener(this);
        this.vistaInv.btnLimpiarFiltros.addActionListener(this);
        this.vistaInv.btnModificar.addActionListener(this);
        this.vistaInv.btnCrearNuevo.addActionListener(this);
        this.vistaInv.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mdi.menuInventario) {
            if (!vistaInv.isVisible()) {
                mdi.escritorio.add(vistaInv);
                vistaInv.setVisible(true);
                actualizarTabla(gestionProd.getLista());
            }
        } 
        else if (e.getSource() == vistaInv.btnBuscar) {
            filtrarInventario();
        } 
        else if (e.getSource() == vistaInv.btnLimpiarFiltros) {
            vistaInv.txtIdFiltro.setText("");
            vistaInv.txtNombreFiltro.setText("");
            vistaInv.rbTodos.setSelected(true);
            actualizarTabla(gestionProd.getLista());
        } 
        else if (e.getSource() == vistaInv.btnModificar) {
            String skuModificar = JOptionPane.showInputDialog(vistaInv, "Escriba el SKU del producto para agregarle stock:");
            if (skuModificar != null && !skuModificar.isEmpty()) {
                Producto p = gestionProd.buscar(skuModificar);
                if (p != null) {
                    String nuevaCantStr = JOptionPane.showInputDialog(vistaInv, "Stock actual: " + p.getCantidadAlmacen() + "\n¿Cuánto stock extra le llegó?");
                    try {
                        int agregar = Integer.parseInt(nuevaCantStr);
                        if(agregar > 0) {
                            p.setCantidadAlmacen(p.getCantidadAlmacen() + agregar);
                            gestionProd.actualizar(p);
                            csv.exportarCSV(gestionProd.getLista());
                            actualizarTabla(gestionProd.getLista());
                            JOptionPane.showMessageDialog(vistaInv, "Stock sumado exitosamente.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(vistaInv, "Debe ingresar un número entero.");
                    }
                } else {
                    JOptionPane.showMessageDialog(vistaInv, "Producto no encontrado.");
                }
            }
        }
        else if (e.getSource() == vistaInv.btnCrearNuevo || e.getSource() == vistaInv.btnEliminar) {
            JOptionPane.showMessageDialog(vistaInv, "Por favor, use el módulo 1 de 'Catálogo de Productos' para crear o eliminar.");
        }
    }

    private void filtrarInventario() {
        String skuFiltro = vistaInv.txtIdFiltro.getText().toLowerCase();
        String nomFiltro = vistaInv.txtNombreFiltro.getText().toLowerCase();
        
        ArrayList<Producto> filtrados = new ArrayList<>();
        
        for (Producto p : gestionProd.getLista()) {
            boolean pasaTexto = p.getSku().toLowerCase().contains(skuFiltro) && p.getNombre().toLowerCase().contains(nomFiltro);
            boolean pasaEstado = false;
            
            if (vistaInv.rbTodos.isSelected()) {
                pasaEstado = true;
            } else if (vistaInv.rbDisponible.isSelected() && p.getCantidadAlmacen() > 0) {
                pasaEstado = true;
            } else if (vistaInv.rbAgotado.isSelected() && p.getCantidadAlmacen() <= 0) {
                pasaEstado = true;
            }
            
            if (pasaTexto && pasaEstado) {
                filtrados.add(p);
            }
        }
        actualizarTabla(filtrados);
    }

    private void actualizarTabla(ArrayList<Producto> lista) {
        vistaInv.modeloTabla.setRowCount(0); 
        for (Producto p : lista) {
            String estadoReal = "Disponible";
            if (p.getCantidadAlmacen() <= 0) {
                estadoReal = "Agotado";
            }
            // Formatear el precio de venta para que se vea con 2 decimales
            String precioFormat = String.format("%.2f", p.getPrecioVenta());
            
            Object[] fila = {p.getSku(), p.getNombre(), p.getCategoriaPrincipal(), p.getCantidadAlmacen(), precioFormat, estadoReal};
            vistaInv.modeloTabla.addRow(fila);
        }
    }
}