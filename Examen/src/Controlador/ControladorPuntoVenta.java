package Controlador;
import Modelo.GestionProductos;
import Modelo.Producto;
import persistencia.GestorCSV;
import vista.VentanaPrincipal;
import vista.VistaPuntoVenta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ControladorPuntoVenta implements ActionListener {
    private VentanaPrincipal mdi;
    private VistaPuntoVenta vistaVenta;
    private GestionProductos gestionProd;
    private GestorCSV csv;
    private double totalVenta = 0.0;
    private double subtotalVenta = 0.0;
    private double ivaVenta = 0.0;

    public ControladorPuntoVenta(VentanaPrincipal mdi, VistaPuntoVenta vistaVenta, GestionProductos gestionProd, GestorCSV csv) {
        this.mdi = mdi;
        this.vistaVenta = vistaVenta;
        this.gestionProd = gestionProd;
        this.csv = csv;

        this.mdi.menuPuntoVenta.addActionListener(this);
        this.vistaVenta.btnAnadirCarrito.addActionListener(this);
        this.vistaVenta.btnModificar.addActionListener(this);
        this.vistaVenta.btnEliminar.addActionListener(this);
        this.vistaVenta.btnLimpiarCarrito.addActionListener(this);
        this.vistaVenta.btnProcesarPago.addActionListener(this);
        
        this.vistaVenta.btnExportarTicket.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mdi.menuPuntoVenta) {
            if (!vistaVenta.isVisible()) {
                mdi.escritorio.add(vistaVenta);
                vistaVenta.setVisible(true);
                cargarProductosEnCombo(); 
            }
        } 
        else if (e.getSource() == vistaVenta.btnAnadirCarrito) agregarAlTicket();
        else if (e.getSource() == vistaVenta.btnModificar) modificarDelCarrito(); 
        else if (e.getSource() == vistaVenta.btnEliminar) eliminarDelCarrito();   
        else if (e.getSource() == vistaVenta.btnLimpiarCarrito) limpiarTicket();
        else if (e.getSource() == vistaVenta.btnProcesarPago) pagarTicket();
        
        
        else if (e.getSource() == vistaVenta.btnExportarTicket) {
            exportarTicketTXT();
        }
    }

    private void cargarProductosEnCombo() {
        vistaVenta.comboProducto.removeAllItems();
        vistaVenta.comboProducto.addItem("Seleccione un producto");
        for (Producto p : gestionProd.getLista()) {
            if (p.getStockInicial() > 0 && p.getEstado().equals("Activo")) {
                vistaVenta.comboProducto.addItem(p.getId() + " - " + p.getNombre());
            }
        }
    }

    private void agregarAlTicket() {
        if (vistaVenta.comboProducto.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(vistaVenta, "Seleccione un producto del combo.");
            return;
        }

        try {
            String seleccion = vistaVenta.comboProducto.getSelectedItem().toString();
            String idBuscado = seleccion.split(" - ")[0]; 
            
            Producto p = gestionProd.buscar(idBuscado);
            int cantidadPedida = Integer.parseInt(vistaVenta.txtCantidad.getText());
            
            if (p.getStockInicial() < cantidadPedida) {
                JOptionPane.showMessageDialog(vistaVenta, "No hay suficiente stock. Solo quedan: " + p.getStockInicial());
                return;
            }

            double totalArticulo = p.getPrecioVenta() * cantidadPedida;
            Object[] fila = {p.getId(), p.getNombre(), cantidadPedida, p.getPrecioVenta(), totalArticulo};
            vistaVenta.modeloCarrito.addRow(fila);
            
            recalcularTotales();
            
            vistaVenta.comboProducto.setSelectedIndex(0);
            vistaVenta.txtCantidad.setText("1");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaVenta, "La cantidad debe ser un número entero.");
        }
    }

    private void modificarDelCarrito() {
        int filaSelec = vistaVenta.tablaCarrito.getSelectedRow();
        if (filaSelec == -1) {
            JOptionPane.showMessageDialog(vistaVenta, "Primero seleccione un producto de la tabla.");
            return;
        }

        String idProd = vistaVenta.tablaCarrito.getValueAt(filaSelec, 0).toString();
        Producto p = gestionProd.buscar(idProd);
        
        String nuevaCantStr = JOptionPane.showInputDialog(vistaVenta, "¿Qué nueva cantidad desea llevar?");
        
        if (nuevaCantStr != null && !nuevaCantStr.isEmpty()) {
            try {
                int nuevaCant = Integer.parseInt(nuevaCantStr);
                
                if (nuevaCant <= 0) {
                    JOptionPane.showMessageDialog(vistaVenta, "La cantidad debe ser mayor a 0.");
                    return;
                }
                if (p.getStockInicial() < nuevaCant) {
                    JOptionPane.showMessageDialog(vistaVenta, "Stock insuficiente. Solo quedan: " + p.getStockInicial());
                    return;
                }
                
                double nuevoTotalArticulo = p.getPrecioVenta() * nuevaCant;
                vistaVenta.modeloCarrito.setValueAt(nuevaCant, filaSelec, 2); 
                vistaVenta.modeloCarrito.setValueAt(nuevoTotalArticulo, filaSelec, 4); 
                
                recalcularTotales(); 
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaVenta, "Debe ingresar un número válido.");
            }
        }
    }

    private void eliminarDelCarrito() {
        int filaSelec = vistaVenta.tablaCarrito.getSelectedRow();
        if (filaSelec == -1) {
            JOptionPane.showMessageDialog(vistaVenta, "Primero seleccione un producto de la tabla.");
            return;
        }
        vistaVenta.modeloCarrito.removeRow(filaSelec);
        recalcularTotales(); 
    }

    private void recalcularTotales() {
        subtotalVenta = 0.0;
        for (int i = 0; i < vistaVenta.modeloCarrito.getRowCount(); i++) {
            subtotalVenta += Double.parseDouble(vistaVenta.modeloCarrito.getValueAt(i, 4).toString());
        }
        ivaVenta = subtotalVenta * 0.16;
        totalVenta = subtotalVenta + ivaVenta;
        
        vistaVenta.txtSubtotal.setText(String.format("%.2f", subtotalVenta));
        vistaVenta.txtIva.setText(String.format("%.2f", ivaVenta));
        vistaVenta.txtTotal.setText(String.format("%.2f", totalVenta));
    }

    private void pagarTicket() {
        if (vistaVenta.modeloCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vistaVenta, "El carrito está vacío.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(vistaVenta, "¿Desea procesar el pago por $" + String.format("%.2f", totalVenta) + "?", "Confirmar Pago", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            for (int i = 0; i < vistaVenta.modeloCarrito.getRowCount(); i++) {
                String idProd = vistaVenta.modeloCarrito.getValueAt(i, 0).toString();
                int cantidadVendida = Integer.parseInt(vistaVenta.modeloCarrito.getValueAt(i, 2).toString());
                
                Producto p = gestionProd.buscar(idProd);
                if (p != null) {
                    p.setStockInicial(p.getStockInicial() - cantidadVendida);
                    gestionProd.actualizar(p);
                }
            }
            csv.exportarCSV(gestionProd.getLista());
            
            JOptionPane.showMessageDialog(vistaVenta, "¡Pago procesado con éxito!");
            limpiarTicket();
            cargarProductosEnCombo();
        }
    }

    private void limpiarTicket() {
        vistaVenta.modeloCarrito.setRowCount(0);
        subtotalVenta = 0.0;
        ivaVenta = 0.0;
        totalVenta = 0.0;
        vistaVenta.txtSubtotal.setText("");
        vistaVenta.txtIva.setText("");
        vistaVenta.txtTotal.setText("");
        vistaVenta.comboProducto.setSelectedIndex(0);
        vistaVenta.txtCantidad.setText("1");
    }

    private void exportarTicketTXT() {
        if (vistaVenta.modeloCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vistaVenta, "El carrito está vacío. Agregue productos antes de exportar un ticket.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ticket_venta.txt"))) {
            bw.write("=========== TICKET DE COMPRA ===========");
            bw.newLine();
            bw.write("CANT | PRODUCTO | P.UNIT | TOTAL");
            bw.newLine();
            bw.write("----------------------------------------");
            bw.newLine();

            for (int i = 0; i < vistaVenta.modeloCarrito.getRowCount(); i++) {
                String desc = vistaVenta.modeloCarrito.getValueAt(i, 1).toString();
                String cant = vistaVenta.modeloCarrito.getValueAt(i, 2).toString();
                String pUnit = vistaVenta.modeloCarrito.getValueAt(i, 3).toString();
                String totalProd = vistaVenta.modeloCarrito.getValueAt(i, 4).toString();
                
                bw.write(cant + "x | " + desc + " | $" + pUnit + " | $" + totalProd);
                bw.newLine();
            }

            bw.write("----------------------------------------");
            bw.newLine();
            bw.write("Subtotal: $" + vistaVenta.txtSubtotal.getText());
            bw.newLine();
            bw.write("IVA (16%): $" + vistaVenta.txtIva.getText());
            bw.newLine();
            bw.write("Total a Pagar: $" + vistaVenta.txtTotal.getText());
            bw.newLine();
            bw.write("========================================");
            bw.newLine();
            bw.write("¡Gracias por su compra!");
            
            JOptionPane.showMessageDialog(vistaVenta, "¡El recibo se ha guardado exitosamente como 'ticket_venta.txt'!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(vistaVenta, "Hubo un error al crear el archivo TXT.");
        }
    }
}