package vista;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaPuntoVenta extends JInternalFrame {

    public JComboBox<String> comboProducto;
    public JTextField txtCantidad;
    public JButton btnAnadirCarrito, btnModificar, btnEliminar;

  
    public JTable tablaCarrito;
    public DefaultTableModel modeloCarrito;

    public JTextField txtSubtotal, txtIva, txtTotal;
    public JButton btnLimpiarCarrito, btnProcesarPago, btnExportarTicket;

    public VistaPuntoVenta() {
        super("Punto de Venta", true, true, true, true);
        setSize(800, 550);
        setLayout(new BorderLayout(10, 10));
        
      
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        JLabel lblCajero = new JLabel("ID Cliente / Cajero:");
        lblCajero.setFont(new Font("Arial", Font.BOLD, 14));
        panelSuperior.add(lblCajero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4;
        panelSuperior.add(new JLabel("--- SELECCIÓN DE PRODUCTO ---"), gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        panelSuperior.add(new JLabel("Producto:"), gbc);
        
        gbc.gridx = 1; 
        comboProducto = new JComboBox<>(new String[]{"Seleccione un producto"}); // Sin corchetes
        panelSuperior.add(comboProducto, gbc);

        gbc.gridx = 2; 
        panelSuperior.add(new JLabel("Cantidad:"), gbc);
        
        gbc.gridx = 3; 
        txtCantidad = new JTextField(10);
        panelSuperior.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; 
        panelSuperior.add(new JLabel("Acciones:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3;
        JPanel panelBotonesAdd = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
      
        btnAnadirCarrito = new JButton("Añadir a Carrito");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        
        panelBotonesAdd.add(btnAnadirCarrito);
        panelBotonesAdd.add(btnModificar);
        panelBotonesAdd.add(btnEliminar);
        panelSuperior.add(panelBotonesAdd, gbc);

        add(panelSuperior, BorderLayout.NORTH);

       
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder(null, "Detalles Transacción Actual", TitledBorder.LEFT, TitledBorder.TOP));
        
        String[] columnas = {"Cód", "Descripción", "Cant", "P.Unit", "Total"};
        modeloCarrito = new DefaultTableModel(columnas, 0);
        tablaCarrito = new JTable(modeloCarrito);
        panelCentral.add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
        
        add(panelCentral, BorderLayout.CENTER);

      
        JPanel panelInferior = new JPanel(new BorderLayout());
        
        JPanel panelTotales = new JPanel(new GridLayout(3, 2, 5, 5));
        
        panelTotales.add(new JLabel("Subtotal:"));
        txtSubtotal = new JTextField(10); txtSubtotal.setEditable(false);
        panelTotales.add(txtSubtotal);
        
        panelTotales.add(new JLabel("IVA (16%):"));
        txtIva = new JTextField(10); txtIva.setEditable(false);
        panelTotales.add(txtIva);
        
        JLabel lblTotalLabel = new JLabel("Total a Pagar:");
        lblTotalLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panelTotales.add(lblTotalLabel);
        
        txtTotal = new JTextField(10); txtTotal.setEditable(false);
        panelTotales.add(txtTotal);
        
        panelInferior.add(panelTotales, BorderLayout.WEST);

        JPanel panelBotonesPago = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPago = new GridBagConstraints();
        gbcPago.gridx = 0; gbcPago.gridy = 0; gbcPago.gridwidth = 3;
        gbcPago.anchor = GridBagConstraints.CENTER;
        
        panelBotonesPago.add(new JLabel("Opciones de Cobro:"), gbcPago);
        
        gbcPago.gridy = 1; gbcPago.gridwidth = 1; gbcPago.insets = new Insets(5, 5, 0, 0);
       
        btnLimpiarCarrito = new JButton("Limpiar Carrito");
        btnProcesarPago = new JButton("Procesar Pago");
        btnExportarTicket = new JButton("Exportar Ticket");
        
        panelBotonesPago.add(btnLimpiarCarrito, gbcPago);
        gbcPago.gridx = 1; panelBotonesPago.add(btnProcesarPago, gbcPago);
        gbcPago.gridx = 2; panelBotonesPago.add(btnExportarTicket, gbcPago);

        panelInferior.add(panelBotonesPago, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);
    }
}