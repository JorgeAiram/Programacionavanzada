package Principal;

import Controlador.ControladorInventario;
import Controlador.ControladorProducto;
import Controlador.ControladorPuntoVenta;
import Modelo.GestionProductos;
import persistencia.GestorCSV;
import vista.VentanaPrincipal;
import vista.VistaInventario;
import vista.VistaProducto;
import vista.VistaPuntoVenta;

public class Main {
    public static void main(String[] args) {
        GestionProductos gestionProd = new GestionProductos();
        GestorCSV csv = new GestorCSV();

        VentanaPrincipal mdi = new VentanaPrincipal();
        VistaProducto vistaProd = new VistaProducto();
        VistaPuntoVenta vistaVenta = new VistaPuntoVenta();
        VistaInventario vistaInv = new VistaInventario();

        ControladorProducto ctrlProd = new ControladorProducto(mdi, vistaProd, gestionProd, csv);
        ControladorPuntoVenta ctrlVenta = new ControladorPuntoVenta(mdi, vistaVenta, gestionProd, csv);
        ControladorInventario ctrlInv = new ControladorInventario(mdi, vistaInv, gestionProd, csv);

        mdi.setVisible(true);
    }
}