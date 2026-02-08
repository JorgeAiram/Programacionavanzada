package vista;

import javax.swing.*;

public class VPractica01_Tarea01 extends JFrame {

    private JTextField txtId, txtNombre;
    private JComboBox<String> cbCategoria;
    private JList<String> lista;
    private JButton btnAgregar, btnEliminar, btnSalir;

    public VPractica01_Tarea01() {
        setTitle("VPractica01_Tarea01 - CRUD Insumos");
        setSize(450, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20,20,80,25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120,20,100,25);
        add(txtId);

        JLabel lblNom = new JLabel("Insumo:");
        lblNom.setBounds(20,60,80,25);
        add(lblNom);

        txtNombre = new JTextField();
        txtNombre.setBounds(120,60,150,25);
        add(txtNombre);

        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setBounds(20,100,80,25);
        add(lblCat);

        cbCategoria = new JComboBox<>(new String[]{"Materiales","Mano de Obra","Herramienta","Servicios"});
        cbCategoria.setBounds(120,100,150,25);
        add(cbCategoria);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(300,20,120,30);
        add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(300,60,120,30);
        add(btnEliminar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(300,100,120,30);
        add(btnSalir);

        lista = new JList<>();
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBounds(20,150,400,140);
        add(scroll);
    }

    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JComboBox<String> getCbCategoria() { return cbCategoria; }
    public JList<String> getLista() { return lista; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnSalir() { return btnSalir; }
}