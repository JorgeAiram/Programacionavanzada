package vista;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public JDesktopPane escritorio;
   
    public JMenuItem menuProductos;
    public JMenuItem menuInventario;
    public JMenuItem menuPuntoVenta;
    public JMenuItem menuSalir;

    public VentanaPrincipal() {
        setTitle("Sistema de Abarrotes La Pequeña - MVC");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        escritorio = new JDesktopPane();
        setContentPane(escritorio);

        JMenuBar barraMenu = new JMenuBar();
        
        JMenu menuModulos = new JMenu("Módulos del Sistema");
        
        menuProductos = new JMenuItem("1. Gestión de Productos");
        menuInventario = new JMenuItem("2. Control de Inventario");
        menuPuntoVenta = new JMenuItem("3. Punto de Venta");
        menuSalir = new JMenuItem("4. Salir");

        menuModulos.add(menuProductos);
        menuModulos.add(menuInventario);
        menuModulos.add(menuPuntoVenta);
        menuModulos.addSeparator(); 
        menuModulos.add(menuSalir);
        
        barraMenu.add(menuModulos);
        setJMenuBar(barraMenu);
    }
}