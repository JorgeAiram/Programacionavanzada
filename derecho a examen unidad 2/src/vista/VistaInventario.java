package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaInventario extends JInternalFrame {

    public JTextField txtIdFiltro, txtNombreFiltro;
    public JComboBox<String> comboTipoFiltro;
    public JRadioButton rbTodos, rbDisponible, rbAgotado;
    public ButtonGroup bgEstado;
    public JButton btnBuscar, btnLimpiarFiltros;

  
    public JTable tablaInventario;
    public DefaultTableModel modeloTabla;
    public JButton btnCrearNuevo, btnModificar, btnEliminar;

    public VistaInventario() {
        super("Inventario", true, true, true, true);
        setSize(900, 500);
        setLayout(new BorderLayout(10, 10));

        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder(null, "Filtros y Búsqueda", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; panelIzquierdo.add(new JLabel("ID del Producto:"), gbc);
        gbc.gridx = 0; gbc.gridy = 1; txtIdFiltro = new JTextField(15); panelIzquierdo.add(txtIdFiltro, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelIzquierdo.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 0; gbc.gridy = 3; txtNombreFiltro = new JTextField(15); panelIzquierdo.add(txtNombreFiltro, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panelIzquierdo.add(new JLabel("Categoría / Tipo:"), gbc);
        gbc.gridx = 0; gbc.gridy = 5; 
        comboTipoFiltro = new JComboBox<>(new String[]{"Abarrotes", "Perecederos", "Limpieza"});
        panelIzquierdo.add(comboTipoFiltro, gbc);

        gbc.gridx = 0; gbc.gridy = 6; 
        JPanel panelEstado = new JPanel(new GridLayout(3, 1));
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        rbTodos = new JRadioButton("Todos");
        rbDisponible = new JRadioButton("Disponible", true);
        rbAgotado = new JRadioButton("Agotado");
        bgEstado = new ButtonGroup();
        bgEstado.add(rbTodos); bgEstado.add(rbDisponible); bgEstado.add(rbAgotado);
        panelEstado.add(rbTodos); panelEstado.add(rbDisponible); panelEstado.add(rbAgotado);
        panelIzquierdo.add(panelEstado, gbc);

     
        gbc.gridx = 0; gbc.gridy = 7; 
        JPanel panelBotonesFiltro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnBuscar = new JButton("Buscar");
        btnLimpiarFiltros = new JButton("Limpiar Filtros");
        panelBotonesFiltro.add(btnBuscar);
        panelBotonesFiltro.add(btnLimpiarFiltros);
        panelIzquierdo.add(panelBotonesFiltro, gbc);

        JPanel panelDerecho = new JPanel(new BorderLayout(5, 5));
        panelDerecho.setBorder(BorderFactory.createTitledBorder(null, "Vista de Inventario", TitledBorder.CENTER, TitledBorder.TOP));

        String[] columnas = {"ID", "Nombre", "Tipo", "Cantidad", "Precio", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaInventario = new JTable(modeloTabla);
        panelDerecho.add(new JScrollPane(tablaInventario), BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelAcciones.setBorder(BorderFactory.createTitledBorder(null, "Acciones de Selección", TitledBorder.LEFT, TitledBorder.TOP));
       
        btnCrearNuevo = new JButton("Crear Nuevo");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        
        panelAcciones.add(btnCrearNuevo);
        panelAcciones.add(btnModificar);
        panelAcciones.add(btnEliminar);

        panelDerecho.add(panelAcciones, BorderLayout.SOUTH);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }
}