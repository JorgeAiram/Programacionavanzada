package Principal;

import Controlador.*;
import Modelo.GestionProductos;
import persistencia.GestorCSV;
import vista.*;

public class Main {
    public static void main(String[] args) {
        
        // 1. Iniciar Base de Datos
        GestionProductos gestionProd = new GestionProductos();
        GestorCSV csv = new GestorCSV();
        gestionProd.setLista(csv.importarCSV());

        // 2. Crear las 5 Pantallas
        VentanaPrincipal mdi = new VentanaPrincipal();
        VistaProducto vistaProd = new VistaProducto();
        VistaInventario vistaInv = new VistaInventario();
        VistaPuntoVenta vistaVenta = new VistaPuntoVenta();
        VistaProveedores vistaProv = new VistaProveedores();
        VistaReportes vistaRep = new VistaReportes();

        // 3. Crear los Controladores
        ControladorProducto ctrlProd = new ControladorProducto(mdi, vistaProd, gestionProd, csv);
        ControladorInventario ctrlInv = new ControladorInventario(mdi, vistaInv, gestionProd, csv);
        ControladorPuntoVenta ctrlVenta = new ControladorPuntoVenta(mdi, vistaVenta, gestionProd, csv);
        
        
        ControladorReportes ctrlRep = new ControladorReportes(vistaRep, gestionProd);
        
        ControladorPrincipal ctrlPrincipal = new ControladorPrincipal(mdi, vistaProd, vistaInv, vistaProv, vistaVenta, vistaRep);

        mdi.setVisible(true);
    }
}