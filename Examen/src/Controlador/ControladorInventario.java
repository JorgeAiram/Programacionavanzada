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
            String idModificar = JOptionPane.showInputDialog(vistaInv, "Escriba el ID del producto para agregarle stock:");
            if (idModificar != null && !idModificar.isEmpty()) {
                Producto p = gestionProd.buscar(idModificar);
                if (p != null) {
                    
                	String nuevaCantStr = JOptionPane.showInputDialog(vistaInv, "Stock actual: " + p.getStockInicial() + "\n¿Cuánto stock extra le llegó?");
                    try {
                        int agregar = Integer.parseInt(nuevaCantStr);
                        if(agregar > 0) {
                           
                        	p.setStockInicial(p.getStockInicial() + agregar);
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
            JOptionPane.showMessageDialog(vistaInv, "Por favor, use el módulo 1 de 'Gestión de Productos' para crear o eliminar.");
        }
    }

    private void filtrarInventario() {
        String idFiltro = vistaInv.txtIdFiltro.getText().toLowerCase();
        String nomFiltro = vistaInv.txtNombreFiltro.getText().toLowerCase();
        
        ArrayList<Producto> filtrados = new ArrayList<>();
        
        for (Producto p : gestionProd.getLista()) {
            boolean pasaTexto = p.getId().toLowerCase().contains(idFiltro) && p.getNombre().toLowerCase().contains(nomFiltro);
            boolean pasaEstado = false;
      
            if (vistaInv.rbTodos.isSelected()) {
                pasaEstado = true;
            } else if (vistaInv.rbDisponible.isSelected() && p.getStockInicial() > 0) {
                pasaEstado = true;
            } else if (vistaInv.rbAgotado.isSelected() && p.getStockInicial() <= 0) {
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
            if (p.getStockInicial() <= 0) {
                estadoReal = "Agotado";
            }
            
            Object[] fila = {p.getId(), p.getNombre(), p.getCategoria(), p.getStockInicial(), p.getPrecioVenta(), estadoReal};
            vistaInv.modeloTabla.addRow(fila);
        }
    }
}