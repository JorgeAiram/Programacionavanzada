package vista;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaProducto extends JInternalFrame {
    
    public JTextField txtId, txtNombre, txtPrecioCompra, txtPrecioVenta, txtStockInicial, txtStockMinimo;
    public JTextArea txtDescripcion;
    public JComboBox<String> comboCategoria;
    public JRadioButton rbActivo, rbDesactivado;
    public ButtonGroup bgEstado;
    
    public JButton btnGuardar, btnLimpiar;
    public JTable tablaProductos;
    public DefaultTableModel modeloTabla;
    
    public JButton btnBuscar, btnModificar, btnEliminar, btnExportarLista; 

    public VistaProducto() {
        super("Gestión de Productos", true, true, true, true);
        setSize(950, 550);
        setLayout(new BorderLayout(10, 10));

        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder(null, "Alta y Edición", TitledBorder.CENTER, TitledBorder.TOP));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelIzquierdo.add(new JLabel("ID del Producto:"), gbc);
        gbc.gridx = 1; txtId = new JTextField(15); panelIzquierdo.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelIzquierdo.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; txtNombre = new JTextField(15); panelIzquierdo.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelIzquierdo.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; 
        txtDescripcion = new JTextArea(3, 15);
        panelIzquierdo.add(new JScrollPane(txtDescripcion), gbc);

        gbc.gridx = 0; gbc.gridy = 3; panelIzquierdo.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; 
        comboCategoria = new JComboBox<>(new String[]{"Abarrotes", "Perecederos", "Bebidas", "Limpieza"});
        panelIzquierdo.add(comboCategoria, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panelIzquierdo.add(new JLabel("Precio Compra:"), gbc);
        gbc.gridx = 1; txtPrecioCompra = new JTextField(15); panelIzquierdo.add(txtPrecioCompra, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panelIzquierdo.add(new JLabel("Precio Venta:"), gbc);
        gbc.gridx = 1; txtPrecioVenta = new JTextField(15); panelIzquierdo.add(txtPrecioVenta, gbc);

        gbc.gridx = 0; gbc.gridy = 6; panelIzquierdo.add(new JLabel("Stock Inicial:"), gbc);
        gbc.gridx = 1; txtStockInicial = new JTextField(15); panelIzquierdo.add(txtStockInicial, gbc);

        gbc.gridx = 0; gbc.gridy = 7; panelIzquierdo.add(new JLabel("Stock Mínimo:"), gbc);
        gbc.gridx = 1; txtStockMinimo = new JTextField(15); panelIzquierdo.add(txtStockMinimo, gbc);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        JPanel panelEstado = new JPanel();
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        rbActivo = new JRadioButton("Activo", true);
        rbDesactivado = new JRadioButton("Desactivado");
        bgEstado = new ButtonGroup();
        bgEstado.add(rbActivo); bgEstado.add(rbDesactivado);
        panelEstado.add(rbActivo); panelEstado.add(rbDesactivado);
        panelIzquierdo.add(panelEstado, gbc);

        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        JPanel panelBotonesForm = new JPanel();
        btnGuardar = new JButton("Guardar Cambios");
        btnLimpiar = new JButton("Limpiar Formulario");
        panelBotonesForm.add(btnGuardar);
        panelBotonesForm.add(btnLimpiar);
        panelIzquierdo.add(panelBotonesForm, gbc);

        JPanel panelDerecho = new JPanel(new BorderLayout(5, 5));
        
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(null, "Catálogo de Productos", TitledBorder.CENTER, TitledBorder.TOP));
        String[] columnas = {"ID", "Nombre", "Categoría", "Stock", "P.Venta", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        panelTabla.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        panelDerecho.add(panelTabla, BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones (CRUD)"));
        btnBuscar = new JButton("Buscar Producto");
        btnModificar = new JButton("Modificar Producto");
        btnEliminar = new JButton("Eliminar Producto");
       
        btnExportarLista = new JButton("Exportar Lista"); 

        panelAcciones.add(btnBuscar);
        panelAcciones.add(btnModificar);
        panelAcciones.add(btnEliminar);
       
        panelAcciones.add(btnExportarLista); 
        
        panelDerecho.add(panelAcciones, BorderLayout.SOUTH);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }
}