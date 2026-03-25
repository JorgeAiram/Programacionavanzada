package Controlador;

import vista.*;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal implements ActionListener {
    
    private VentanaPrincipal mdi;
    private VistaProducto vistaProd;
    private VistaInventario vistaInv;
    private VistaProveedores vistaProv;
    private VistaPuntoVenta vistaVenta;
    private VistaReportes vistaRep;

    public ControladorPrincipal(VentanaPrincipal mdi, VistaProducto vistaProd, VistaInventario vistaInv, 
                                VistaProveedores vistaProv, VistaPuntoVenta vistaVenta, VistaReportes vistaRep) {
        this.mdi = mdi;
        this.vistaProd = vistaProd;
        this.vistaInv = vistaInv;
        this.vistaProv = vistaProv;
        this.vistaVenta = vistaVenta;
        this.vistaRep = vistaRep;

       
        this.mdi.menuProductos.addActionListener(this);
        this.mdi.menuInventario.addActionListener(this);
        this.mdi.menuProveedores.addActionListener(this);
        this.mdi.menuPuntoVenta.addActionListener(this);
        this.mdi.menuReportes.addActionListener(this);
        this.mdi.menuUnidadesMedida.addActionListener(this);
        this.mdi.menuSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mdi.menuProductos) abrirVentana(vistaProd);
        else if (e.getSource() == mdi.menuInventario) abrirVentana(vistaInv);
        else if (e.getSource() == mdi.menuProveedores) abrirVentana(vistaProv);
        else if (e.getSource() == mdi.menuPuntoVenta) abrirVentana(vistaVenta);
        else if (e.getSource() == mdi.menuReportes) abrirVentana(vistaRep);
        else if (e.getSource() == mdi.menuUnidadesMedida) {
            JOptionPane.showMessageDialog(mdi, "Unidades de Medida configuradas en el sistema: Pza, Kg, Lts, Caja.");
        }
        else if (e.getSource() == mdi.menuSalir) {
            System.exit(0); 
        }
    }

    private void abrirVentana(JInternalFrame ventana) {
        if (!ventana.isVisible()) {
            mdi.escritorio.add(ventana);
            ventana.setVisible(true);
            try { ventana.setSelected(true); } catch (Exception ex) { }
        } else {
            ventana.toFront();
        }
    }
}