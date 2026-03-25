package Controlador;

import Modelo.GestionProductos;
import Modelo.Producto;
import vista.VistaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorReportes implements ActionListener {
    private VistaReportes vista;
    private GestionProductos gestion;

    public ControladorReportes(VistaReportes vista, GestionProductos gestion) {
        this.vista = vista;
        this.gestion = gestion;
        this.vista.btnGenerar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGenerar) {
            StringBuilder sb = new StringBuilder();
            sb.append("========== REPORTE DE INVENTARIO ==========\n\n");
            
            double valorTotalInvertido = 0;
            int totalArticulos = 0;
            
            for(Producto p : gestion.getLista()) {
                sb.append("SKU: ").append(p.getSku())
                  .append(" | ").append(p.getNombre())
                  .append(" | Cant: ").append(p.getCantidadAlmacen()).append("\n");
                
                valorTotalInvertido += (p.getPrecioCompra() * p.getCantidadAlmacen());
                totalArticulos += p.getCantidadAlmacen();
            }
            
            sb.append("\n===========================================\n");
            sb.append("Resumen del Negocio:\n");
            sb.append("Total de piezas en tienda : ").append(totalArticulos).append(" unidades\n");
            sb.append("Valor total invertido (Costo): $").append(String.format("%.2f", valorTotalInvertido)).append("\n");
            
            vista.txtReporte.setText(sb.toString());
        }
    }
}