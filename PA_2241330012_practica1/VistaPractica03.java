package PA_2241330012_practica1;

import javax.swing.*;
import java.awt.*;

public class VistaPractica03 extends JFrame {
    public JComboBox<String> comboCategoria;
    public JTextField txtId, txtInsumo;
    public JButton btnAgregar, btnEliminar, btnSalir;
    public JTextArea areaProductos;

    public VistaPractica03() {
        super("Administración de Productos");
        setBounds(100, 100, 400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 10, 70, 20);
        txtId = new JTextField();
        txtId.setBounds(90, 10, 150, 20);
        
        JLabel lblInsumo = new JLabel("Insumo:");
        lblInsumo.setBounds(10, 40, 70, 20);
        txtInsumo = new JTextField();
        txtInsumo.setBounds(90, 40, 150, 20);
        
        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setBounds(10, 70, 70, 20);
        comboCategoria = new JComboBox<>();
        comboCategoria.setBounds(90, 70, 150, 20);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 110, 100, 25);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(130, 110, 100, 25);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(240, 110, 100, 25);
        
        areaProductos = new JTextArea();
        areaProductos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaProductos);
        scrollPane.setBounds(20, 150, 340, 190);
        
        panelFormulario.add(lblId);
        panelFormulario.add(txtId);
        panelFormulario.add(lblInsumo);
        panelFormulario.add(txtInsumo);
        panelFormulario.add(lblCat);
        panelFormulario.add(comboCategoria);
        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnEliminar);
        panelFormulario.add(btnSalir);
        panelFormulario.add(scrollPane);
        
        getContentPane().add(panelFormulario, BorderLayout.CENTER);
    }
}